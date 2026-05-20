package com.example.yourgarden.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yourgarden.ui.GardenScreen
import com.example.yourgarden.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    screens: List<GardenScreen>,
    onNextButtonClicked: (GardenScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    // Dzielimy listę na wiersze po 3 kafelki
    val rows = screens.chunked(3)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        rows.forEach { rowItems ->
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    rowItems.forEach { item ->
                        IconTile(
                            emoji = item.emoji,
                            labelResourceId = item.title,
                            onClick = { onNextButtonClicked(item) },
                            modifier = Modifier.weight(1f) // równomierna szerokość kafelków
                        )
                    }

                    // Jeśli wiersz ma mniej niż 3 elementy, dodajemy pustą przestrzeń
                    repeat(3 - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { AnimatedDaysSinceBox() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { AnimatedBearGreeting() }
    }
}

@Composable
fun IconTile(
    @StringRes labelResourceId: Int,
    emoji: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale = remember { Animatable(1f) }
    val pressedScale = 0.95f // zmniejszenie przy dotknięciu

    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(Unit) {
        // Pulsowanie kafelka
        while (true) {
            scale.animateTo(1.05f, tween(800, easing = EaseOut))
            scale.animateTo(1f, tween(800, easing = EaseOut))
        }
    }

    Card(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value
            )
            .shadow(6.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        interactionSource = interactionSource,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        // Efekt dotknięcia – zmiana skali
        val isPressed by interactionSource.collectIsPressedAsState()
        val currentScale = if (isPressed) pressedScale else 1f

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(scaleX = currentScale, scaleY = currentScale)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f),
                            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = emoji,
                    fontSize = 36.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(labelResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1
                )
            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnimatedDaysSinceBox(modifier: Modifier = Modifier) {
    val startDateTime = LocalDateTime.of(2024, 6, 22, 20, 25)
    var currentTime by remember { mutableStateOf(LocalDateTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime = LocalDateTime.now()
        }
    }

    val daysPassed = ChronoUnit.DAYS.between(startDateTime, currentTime)
    val secondsPassed = ChronoUnit.SECONDS.between(startDateTime, currentTime)

    val offsetY = remember { Animatable(-30f) }
    LaunchedEffect(Unit) {
        offsetY.animateTo(0f, tween(1200, easing = EaseOut))
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(200.dp)
            .graphicsLayer(translationY = offsetY.value),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$daysPassed dni z ∞",
                fontSize = 34.sp,
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
fun AnimatedBearGreeting(modifier: Modifier = Modifier) {
    var currentTime by remember { mutableStateOf(LocalDateTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(60000)
            currentTime = LocalDateTime.now()
        }
    }

    val hour = currentTime.hour
    val isSleepTime = hour >= 22 || hour < 7
    val bearImage = if (isSleepTime) R.drawable.sbear else R.drawable.wbear
    val greeting = if (isSleepTime) "Dobranoooc" else "Miłego dnia Martynka"

    val scale = remember { Animatable(0.8f) }
    LaunchedEffect(Unit) {
        scale.animateTo(1f, tween(1200, easing = EaseOut))
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .graphicsLayer(scaleX = scale.value, scaleY = scale.value),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
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
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(120.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = greeting,
                style = MaterialTheme.typography.titleMedium,
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
        launch { offsetY.animateTo(heart.startY - 1200f, tween(2200, easing = EaseOut)) }
        launch { offsetX.animateTo(heart.startX * 0.8f, tween(2200, easing = EaseOut)) }
        launch { rotation.animateTo(360f, tween(2200, easing = EaseOut)) }
        launch {
            delay(1000)
            alpha.animateTo(0f, tween(1000))
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
