package com.example.yourgarden.ui.screens

import android.net.wifi.hotspot2.pps.HomeSp
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.yourgarden.R
import com.example.yourgarden.ui.GardenScreen

@Composable
fun HomeScreen(screens: List<GardenScreen>,
               onNextButtonClicked: (GardenScreen) -> Unit,
               viewModel: HomeViewModel,
               modifier: Modifier = Modifier
)
{
    LazyColumn(
        modifier = modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        item {
            Row {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                    screens.forEach { item ->
                        SelectQuantityButton(
                            labelResourceId = item.title,
                            onClick = { onNextButtonClicked(item) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SelectQuantityButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
        Text(
            stringResource(labelResourceId),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
