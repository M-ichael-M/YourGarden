package com.example.yourgarden.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yourgarden.data.coupons.CouponsEntity
import com.example.yourgarden.ui.CouponsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.Properties
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class CouponsViewModel(
    private val repository: CouponsRepository
) : ViewModel() {

    val couponStatus = mutableStateOf<String?>(null)
    val usedCoupons = mutableStateOf<List<CouponsEntity>>(emptyList())
    val unusedCoupons = mutableStateOf<List<CouponsEntity>>(emptyList())
    val couponToActivate = mutableStateOf<CouponsEntity?>(null) // Nowy stan dla dialogu

    private fun refreshUsedCoupons() {
        viewModelScope.launch {
            usedCoupons.value = repository.getAllCoupons().filter { it.used }
        }
    }

    private fun refreshUnusedCoupons() {
        viewModelScope.launch {
            unusedCoupons.value = repository.getAllCoupons().filter { !it.used }
        }
    }

    // Przygotowanie kuponu do aktywacji (zamiast bezpośredniej aktywacji)
    fun prepareActivateCoupon(coupon: CouponsEntity) {
        couponToActivate.value = coupon
    }

    fun activateCoupon(coupon: CouponsEntity) {
        viewModelScope.launch {
            if (!coupon.used) {
                val updatedCoupon = coupon.copy(
                    used = true,
                    date = Date()
                )
                repository.updateCoupon(updatedCoupon)
                couponStatus.value = "Kupon ${coupon.title} aktywowany!"
                sendEmail(coupon.code, coupon.title)
                refreshUsedCoupons()
                refreshUnusedCoupons()
            }
        }
    }

    // Potwierdzenie aktywacji
    fun confirmActivateCoupon() {
        val coupon = couponToActivate.value ?: return
        viewModelScope.launch {
            if (!coupon.used) {
                val updatedCoupon = coupon.copy(
                    used = true,
                    date = Date()
                )
                repository.updateCoupon(updatedCoupon)
                couponStatus.value = "Kupon ${coupon.title} aktywowany!"
                sendEmail(coupon.code, coupon.title)
                refreshUsedCoupons()
                refreshUnusedCoupons()
                couponToActivate.value = null // Reset stanu
            }
        }
    }

    fun cancelActivateCoupon() {
        couponToActivate.value = null // Reset stanu
    }

    private fun sendEmail(code: String, title: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val props = Properties().apply {
                        put("mail.smtp.host", "smtp.gmail.com")
                        put("mail.smtp.port", "587")
                        put("mail.smtp.auth", "true")
                        put("mail.smtp.starttls.enable", "true")
                    }

                    val session = Session.getDefaultInstance(props, object : javax.mail.Authenticator() {
                        override fun getPasswordAuthentication(): javax.mail.PasswordAuthentication {
                            return javax.mail.PasswordAuthentication(
                                "yourgardenapp@gmail.com",
                                "lerktpsnljzurqcu"
                            )
                        }
                    })

                    val message = MimeMessage(session).apply {
                        setFrom(InternetAddress("yourgardenapp@gmail.com"))
                        addRecipient(Message.RecipientType.TO, InternetAddress("michalmaleczek@gmail.com"))
                        subject = "Aktywacja kuponu"
                        setText("Twoja dziewczyna aktywowała kupon: $title (kod: $code) w dniu ${Date()}")
                    }

                    Transport.send(message)
                }
                Log.d("CouponsViewModel", "E-mail wysłany pomyślnie")
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Nieznany błąd"
                Log.e("CouponsViewModel", "Błąd: ${e::class.simpleName} - $errorMessage", e)
                couponStatus.value = "Błąd wysyłania e-maila: $errorMessage"
            }
        }
    }

    init {
        viewModelScope.launch {
            repository.insertInitialCoupons()
            refreshUsedCoupons()
            refreshUnusedCoupons()
        }
    }
}

class CouponsViewModelFactory(
    private val repository: CouponsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CouponsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CouponsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}