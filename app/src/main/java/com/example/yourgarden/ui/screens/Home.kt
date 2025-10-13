package com.example.yourgarden.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import com.example.yourgarden.ui.GardenScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random
import com.example.yourgarden.R
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    screens: List<GardenScreen>,
    onNextButtonClicked: (GardenScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
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
            Spacer(modifier = Modifier.height(8.dp))
            DaysSinceBox()
            Spacer(modifier = Modifier.height(16.dp))
            BearGreeting()
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DaysSinceBox(modifier: Modifier = Modifier) {
    val startDateTime = LocalDateTime.of(2024, 6, 22, 20, 25)
    val currentTime = remember { mutableStateOf(LocalDateTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime.value = LocalDateTime.now()
        }
    }

    val daysPassed = ChronoUnit.DAYS.between(startDateTime, currentTime.value)
    val secondsPassed = ChronoUnit.SECONDS.between(startDateTime, currentTime.value)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .size(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$daysPassed dni z ∞",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "razem",
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "czyli $secondsPassed sekund",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "i będzie ich tylko więcej...",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BearGreeting(modifier: Modifier = Modifier) {
    val currentTime = remember { mutableStateOf(LocalDateTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(60000) // Aktualizuj co minutę
            currentTime.value = LocalDateTime.now()
        }
    }

    val hour = currentTime.value.hour
    val isSleepTime = hour >= 22 || hour < 7
    val bearImage = if (isSleepTime) R.drawable.sbear else R.drawable.wbear
    val greeting = if (isSleepTime) "Dobranoooc" else "Miłego dnia Martynka"

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = bearImage),
                    contentDescription = "Bear",
                    modifier = Modifier
                        .size(120.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = greeting,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
fun HeartScreenTransition(
    onAnimationEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val hearts = remember {
        List(25) {
            HeartParticle(
                id = it,
                startX = Random.nextFloat() * 1000 - 500,
                startY = 900f,
                delay = (it * 25L)
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .pointerInput(Unit) { detectTapGestures { } }
    ) {
        hearts.forEach { heart ->
            TransitionHeart(heart)
        }
    }

    LaunchedEffect(Unit) {
        delay(2500)
        onAnimationEnd()
    }
}

data class HeartParticle(
    val id: Int,
    val startX: Float,
    val startY: Float,
    val delay: Long = 0L
)

@Composable
fun TransitionHeart(heart: HeartParticle) {
    val offsetY = remember { Animatable(heart.startY) }
    val offsetX = remember { Animatable(heart.startX) }
    val alpha = remember { Animatable(1f) }
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(heart.id) {
        delay(heart.delay)

        launch {
            offsetY.animateTo(
                targetValue = heart.startY - 1200f,
                animationSpec = tween(durationMillis = 2200, easing = EaseOut)
            )
        }
        launch {
            offsetX.animateTo(
                targetValue = heart.startX * 0.8f,
                animationSpec = tween(durationMillis = 2200, easing = EaseOut)
            )
        }
        launch {
            rotation.animateTo(
                targetValue = 360f,
                animationSpec = tween(durationMillis = 2200, easing = EaseOut)
            )
        }
        launch {
            delay(1000)
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 1000)
            )
        }
    }

    Box(
        modifier = Modifier
            .offset(x = offsetX.value.dp, y = offsetY.value.dp)
            .alpha(alpha.value)
            .rotate(rotation.value)
            .shadow(6.dp, shape = CircleShape)
    ) {
        Text(
            text = "❤️",
            fontSize = 56.sp
        )
    }
}