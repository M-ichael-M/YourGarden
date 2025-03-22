package com.example.yourgarden.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    private val repository: CouponsRepository,
    private val context: Context
) : ViewModel() {

    var couponCode by mutableStateOf("")
        private set

    val couponStatus = mutableStateOf<String?>(null)
    val usedCoupons = mutableStateOf<List<CouponsEntity>>(emptyList())

    fun updateCouponCode(newCode: String) {
        couponCode = newCode
    }

    fun submitCoupon() {
        viewModelScope.launch {
            val coupon = repository.getCouponByCode(couponCode)
            if (coupon != null && !coupon.used) {
                val updatedCoupon = coupon.copy(
                    used = true,
                    date = Date()
                )
                repository.updateCoupon(updatedCoupon)
                couponStatus.value = "Kupon aktywowany!"
                sendEmail(coupon.code, coupon.title) // Wysyłamy e-mail
                couponCode = ""
                refreshUsedCoupons()
            } else {
                couponStatus.value = "Nieprawidłowy lub użyty kupon."
            }
        }
    }

    private fun refreshUsedCoupons() {
        viewModelScope.launch {
            usedCoupons.value = repository.getAllCoupons().filter { it.used }
        }
    }

    // Funkcja wysyłająca e-mail
    private fun sendEmail(code: String, title: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    // Ustawienia SMTP dla Gmaila
                    val props = Properties().apply {
                        put("mail.smtp.host", "smtp.gmail.com")
                        put("mail.smtp.port", "587")
                        put("mail.smtp.auth", "true")
                        put("mail.smtp.starttls.enable", "true")
                    }

                    // Sesja z uwierzytelnieniem
                    val session = Session.getDefaultInstance(props, object : javax.mail.Authenticator() {
                        override fun getPasswordAuthentication(): javax.mail.PasswordAuthentication {
                            return javax.mail.PasswordAuthentication(
                                "yourgardenapp@gmail.com", // Adres e-mail nadawcy
                                "lerktpsnljzurqcu"         // Hasło aplikacji (bez spacji)
                            )
                        }
                    })

                    // Tworzenie wiadomości
                    val message = MimeMessage(session).apply {
                        setFrom(InternetAddress("yourgardenapp@gmail.com"))
                        addRecipient(Message.RecipientType.TO, InternetAddress("michalmaleczek@gmail.com"))
                        subject = "Aktywacja kuponu"
                        setText("Twoja dziewczyna aktywowała kupon: $title (kod: $code) w dniu ${Date()}")
                    }

                    // Wysłanie wiadomości
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
        }
    }
}

class CouponsViewModelFactory(
    private val repository: CouponsRepository,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CouponsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CouponsViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}