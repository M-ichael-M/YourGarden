package com.example.yourgarden.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CouponsScreen(
    modifier: Modifier = Modifier,
    viewModel: CouponsViewModel
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Sekcja niewykorzystanych kuponów
        item {
            Text(
                text = "Dostępne kupony:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        if (viewModel.unusedCoupons.value.isEmpty()) {
            item {
                Text(text = "Brak dostępnych kuponów.", fontStyle = FontStyle.Italic)
            }
        } else {
            items(viewModel.unusedCoupons.value) { coupon ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = coupon.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(text = "Kod: ${coupon.code}", fontSize = 16.sp)
                        Text(text = coupon.description, fontSize = 14.sp, color = Color.DarkGray)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(onClick = { viewModel.prepareActivateCoupon(coupon) }) {
                                Text("Aktywuj")
                            }
                        }
                    }
                }
            }
        }

        // Sekcja wykorzystanych kuponów
        item {
            Text(
                text = "Zużyte kupony:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
        }
        if (viewModel.usedCoupons.value.isEmpty()) {
            item {
                Text(text = "Brak zużytych kuponów.", fontStyle = FontStyle.Italic)
            }
        } else {
            items(viewModel.usedCoupons.value) { coupon ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = coupon.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textDecoration = TextDecoration.LineThrough,
                            color = Color.Gray
                        )
                        Text(
                            text = "Kod: ${coupon.code}",
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.LineThrough,
                            color = Color.Gray
                        )
                        Text(
                            text = "Zużyto: ${coupon.date}",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        // Wyświetlanie statusu
        viewModel.couponStatus.value?.let {
            item {
                Text(
                    text = it,
                    fontWeight = FontWeight.Medium,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }

    // Dialog potwierdzający (poza LazyColumn, bo musi być na wierzchu)
    viewModel.couponToActivate.value?.let { coupon ->
        AlertDialog(
            onDismissRequest = { viewModel.cancelActivateCoupon() },
            title = { Text("Potwierdzenie") },
            text = { Text("Czy na pewno chcesz aktywować kupon ${coupon.title}?") },
            confirmButton = {
                Button(onClick = { viewModel.confirmActivateCoupon() }) {
                    Text("Tak")
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.cancelActivateCoupon() }) {
                    Text("Nie")
                }
            }
        )
    }
}