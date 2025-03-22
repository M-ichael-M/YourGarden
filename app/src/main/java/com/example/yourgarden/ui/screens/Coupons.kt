package com.example.yourgarden.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yourgarden.data.GardenDatabase
import com.example.yourgarden.ui.CouponsRepository
import com.example.yourgarden.ui.GardenScreen

@Composable
fun CouponsScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: CouponsViewModel
) {
    val database = GardenDatabase.getInstance(context)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = viewModel.couponCode,
            onValueChange = { viewModel.updateCouponCode(it) },
            label = { Text("Wpisz kod kuponu") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { viewModel.submitCoupon() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Wyślij kupon")
        }

        viewModel.couponStatus.value?.let {
            Text(text = it)
        }

        // Sekcja wyświetlająca zużyte kupony
        Text(
            text = "Zużyte kupony:",
            modifier = Modifier.padding(top = 16.dp)
        )
        if (viewModel.usedCoupons.value.isEmpty()) {
            Text(text = "Brak zużytych kuponów.")
        } else {
            LazyColumn {
                items(viewModel.usedCoupons.value) { coupon ->
                    Text(
                        text = "Kod: ${coupon.code}, Tytuł: ${coupon.title}, Data: ${coupon.date}",
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}
