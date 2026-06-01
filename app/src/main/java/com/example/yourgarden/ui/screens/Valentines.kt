package com.example.yourgarden.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewModelScope
import com.example.yourgarden.R
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.Executor
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlin.math.absoluteValue
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

// ViewModel
class ValentineViewModel : ViewModel() {

    var permissionGranted = mutableStateOf(false)

    var isLoading = mutableStateOf(true)
    var currentLoadingText = mutableStateOf("Ładowanie przytulasów...")
    var currentStep = mutableStateOf(0)

    var hasShaken = mutableStateOf(false)

    var heartbeatTouched = mutableStateOf(false)

    var brightnessIncreased = mutableStateOf(false)

    var scratchProgress = mutableStateOf(0f)
    var scratchRevealed = mutableStateOf(false)

    var darknessAcknowledged = mutableStateOf(false)

    var flashlightActivated = mutableStateOf(false)

    var cloudsCleared = mutableStateOf(false)

    var cameraViewed = mutableStateOf(false)

    var compassAligned = mutableStateOf(false)

    var galleryViewed = mutableStateOf(false)

    var letterRead = mutableStateOf(false)

    var willBeValentine = mutableStateOf("")
    var excitementLevel = mutableStateOf(5f)
    var hugCount = mutableStateOf(0)
    var expectations = mutableStateOf(setOf<String>())
    var loveLevel = mutableStateOf(5f)

    private val loadingTexts = listOf(
        "Ładowanie przytulasów... 💕",
        "Kompilowanie miłości... ❤️",
        "Inicjalizacja romantyzmu... 🌹",
        "Sprawdzanie poziomu słodyczy... 🍫",
        "Przygotowywanie buziaków... 💋",
        "Finalizowanie walentynek... 💝"
    )

    init {
        viewModelScope.launch {
            loadingTexts.forEach { text ->
                currentLoadingText.value = text
                delay(1000)
            }
            isLoading.value = false
        }
    }

    fun goToNextStep() {
        currentStep.value++
    }

    fun goToPreviousStep() {
        if (currentStep.value > 0) {
            currentStep.value--
        }
    }

    fun incrementHugs() {
        hugCount.value++
    }

    fun toggleExpectation(expectation: String) {
        val current = expectations.value.toMutableSet()
        if (current.contains(expectation)) {
            current.remove(expectation)
        } else {
            current.add(expectation)
        }
        expectations.value = current
    }

    fun updateScratchProgress(delta: Float) {
        scratchProgress.value = (scratchProgress.value + delta).coerceIn(0f, 100f)
        if (scratchProgress.value >= 80f) {
            scratchRevealed.value = true
        }
    }

    fun sendResults() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val props = Properties().apply {
                        put("mail.smtp.host", "smtp.gmail.com")
                        put("mail.smtp.port", "587")
                        put("mail.smtp.auth", "true")
                        put("mail.smtp.starttls.enable", "true")
                    }

                    val session = Session.getDefaultInstance(
                        props,
                        object : javax.mail.Authenticator() {
                            override fun getPasswordAuthentication(): javax.mail.PasswordAuthentication {
                                return javax.mail.PasswordAuthentication(
                                    "yourgardenapp@gmail.com",
                                    "lerktpsnljzurqcu"
                                )
                            }
                        }
                    )

                    val expectationsList =
                        expectations.value.joinToString(", ")

                    val loveLevelText =
                        if (loveLevel.value >= 10f)
                            "∞ (Nieskończoność!)"
                        else
                            loveLevel.value.toInt().toString()

                    val message = MimeMessage(session).apply {
                        setFrom(InternetAddress("yourgardenapp@gmail.com"))
                        addRecipient(
                            Message.RecipientType.TO,
                            InternetAddress("michalmaleczek@gmail.com")
                        )
                        subject = "💝 Odpowiedź na zaproszenie walentynkowe!"
                        setText(
                            """
                                Twoja dziewczyna odpowiedziała na zaproszenie walentynkowe! 💕
                                
                                Zostanie Twoją Walentynką: ${willBeValentine.value}
                                Poziom podekscytowania (1-10): ${excitementLevel.value.toInt()}
                                Liczba przytulasów 14 lutego: ${hugCount.value}
                                Oczekiwania: $expectationsList
                                Jak bardzo Cię kocha: $loveLevelText
                                
                                Data: ${Date()}
                                
                                💖 WIDZIMY SIĘ W WALENTYNKI! 💖
                            """.trimIndent()
                        )
                    }

                    Transport.send(message)
                }
                Log.d("ValentineViewModel", "E-mail wysłany pomyślnie")
            } catch (e: Exception) {
                Log.e("ValentineViewModel", "Błąd wysyłania e-maila", e)
            }
        }
    }
}


@Composable
fun Valentine(viewModel: ValentineViewModel = viewModel()) {

    val backgroundColor = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFE5EC),
            Color(0xFFFFC0D3),
            Color(0xFFFFE5EC)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        HeartsBackground()

        when {
            viewModel.isLoading.value -> LoadingScreen(viewModel.currentLoadingText.value)
            else -> {
                when (viewModel.currentStep.value) {
                    0 -> GreetingScreen(viewModel)  // HEJ ŚLICZNOTKO
                    1 -> ShakeScreen(viewModel)  // Potrząśnij telefonem
                    2 -> HeartbeatScreen(viewModel)  // Pulsujące serce
                    3 -> BrightnessScreen(viewModel)  // Oślepiający blask
                    4 -> ScratchScreen(viewModel)  // Zdrapka
                    5 -> DarknessScreen(viewModel)  // Zgaś światło
                    6 -> FlashlightScreen(viewModel)  // Jesteś moim światłem + latarka
                    7 -> BlowScreen(viewModel)  // Dmuchnij w mikrofon
                    8 -> CameraScreen(viewModel)  // Najpięniejsza dziewczyna
                    9 -> GalleryScreen(viewModel)  // Walentynki rok temu
                    10 -> LetterScreen(viewModel)  // List
                    11 -> QuestionScreen(viewModel)  // Pytanie o walentynkę
                    12 -> ExcitementScreen(viewModel)
                    13 -> HugsScreen(viewModel)
                    14 -> ExpectationsScreen(viewModel)
                    15 -> LoveLevelScreen(viewModel)
                    16 -> FinalScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun LoadingScreen(text: String) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .scale(scale),
                tint = Color(0xFFE91E63)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD81B60),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(
                color = Color(0xFFE91E63)
            )
        }
    }
}

// KROK 0: HEJ ŚLICZNOTKO
@Composable
fun GreetingScreen(viewModel: ValentineViewModel) {
    val infiniteTransition = rememberInfiniteTransition(label = "greeting")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "HEJ",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE91E63).copy(alpha = alpha),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "ŚLICZNOTKO",
                fontSize = 56.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFC2185B),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "💕",
                fontSize = 72.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            ValentineButton(
                text = "Dalej →",
                onClick = { viewModel.goToNextStep() }
            )
        }
    }
}

// KROK 1: POTRZĄŚNIJ TELEFONEM
@Composable
fun ShakeScreen(viewModel: ValentineViewModel) {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val accelerometer = remember { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }

    val shakeAnimation = remember { Animatable(0f) }
    val showMessage = remember { mutableStateOf(false) }

    // Animacja trzęsienia
    LaunchedEffect(Unit) {
        while (true) {
            shakeAnimation.animateTo(
                10f,
                animationSpec = tween(100, easing = LinearEasing)
            )
            shakeAnimation.animateTo(
                -10f,
                animationSpec = tween(100, easing = LinearEasing)
            )
        }
    }

    // Sensor listener
    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            private var lastUpdate: Long = 0
            private var lastX = 0f
            private var lastY = 0f
            private var lastZ = 0f

            override fun onSensorChanged(event: SensorEvent) {
                val currentTime = System.currentTimeMillis()

                if (currentTime - lastUpdate > 100) {
                    val diffTime = currentTime - lastUpdate
                    lastUpdate = currentTime

                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]

                    val speed = sqrt(
                        ((x - lastX) * (x - lastX) +
                                (y - lastY) * (y - lastY) +
                                (z - lastZ) * (z - lastZ)).toDouble()
                    ) / diffTime * 10000

                    if (speed > 600) {
                        viewModel.hasShaken.value = true
                        showMessage.value = true
                    }

                    lastX = x
                    lastY = y
                    lastZ = z
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(32.dp)
                .offset(x = shakeAnimation.value.dp)
        ) {
            if (!showMessage.value) {
                Text(
                    text = "Potrząśnij",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE91E63),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "telefonem...",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFFD81B60),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "📱",
                    fontSize = 100.sp,
                    modifier = Modifier.offset(x = shakeAnimation.value.dp)
                )
            } else {
                AnimatedVisibility(
                    visible = showMessage.value,
                    enter = fadeIn() + scaleIn()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "TAK WŁAŚNIE",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE91E63),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "WSTRZĄSASZ",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFC2185B),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "MOIM ŚWIATEM",
                            fontSize = 44.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFE91E63),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = "💘",
                            fontSize = 80.sp
                        )

                        Spacer(modifier = Modifier.height(48.dp))

                        ValentineButton(
                            text = "Dalej →",
                            onClick = { viewModel.goToNextStep() }
                        )
                    }
                }
            }
        }
    }
}

// KROK 2: PULSUJĄCE SERCE Z WIBRACJAMI
@Composable
fun HeartbeatScreen(viewModel: ValentineViewModel) {
    val context = LocalContext.current
    val vibrator = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "heartbeat")
    val heartScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(600),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heartScale"
    )

    val scope = rememberCoroutineScope()
    var vibrationJob by remember { mutableStateOf<Job?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            if (!viewModel.heartbeatTouched.value) {
                Text(
                    text = "Poczuj jak bije",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFFD81B60),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "moje serce przy Tobie",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC2185B),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "(dotknij serca)",
                    fontSize = 16.sp,
                    color = Color(0xFFD81B60),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .scale(heartScale)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                viewModel.heartbeatTouched.value = true
                                vibrationJob = scope.launch {
                                    while (true) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            vibrator.vibrate(
                                                VibrationEffect.createOneShot(
                                                    100,
                                                    VibrationEffect.DEFAULT_AMPLITUDE
                                                )
                                            )
                                        } else {
                                            @Suppress("DEPRECATION")
                                            vibrator.vibrate(100)
                                        }
                                        delay(600)
                                    }
                                }
                                tryAwaitRelease()
                                vibrationJob?.cancel()
                                vibrationJob = null
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    tint = Color(0xFFE91E63)
                )
            }

            if (viewModel.heartbeatTouched.value) {
                Spacer(modifier = Modifier.height(48.dp))

                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "❤️ Czujesz to? ❤️",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE91E63),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "(połóż palec na serce i przytrzymaj)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFE91E63),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        ValentineButton(
                            text = "Dalej →",
                            onClick = { viewModel.goToNextStep() }
                        )
                    }
                }
            }
        }
    }
}

// KROK 3: ZWIĘKSZANIE JASNOŚCI
@Composable
fun BrightnessScreen(viewModel: ValentineViewModel) {
    val context = LocalContext.current
    val view = LocalView.current
    val window = (view.context as? android.app.Activity)?.window

    var currentBrightness by remember { mutableStateOf(0.5f) }
    val targetBrightness = 1.0f

    LaunchedEffect(Unit) {
        // Animacja zwiększania jasności
        val startBrightness = try {
            Settings.System.getInt(
                context.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS
            ) / 255f
        } catch (e: Exception) {
            0.5f
        }

        for (i in 0..100) {
            currentBrightness = startBrightness + (targetBrightness - startBrightness) * (i / 100f)
            window?.attributes = window?.attributes?.apply {
                screenBrightness = currentBrightness
            }
            delay(20)
        }
        viewModel.brightnessIncreased.value = true
    }

    DisposableEffect(Unit) {
        onDispose {
            // Przywróć normalną jasność
            window?.attributes = window.attributes?.apply {
                screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = currentBrightness)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "✨",
                fontSize = 100.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "TWÓJ BLASK",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFE91E63),
                textAlign = TextAlign.Center
            )

            Text(
                text = "MNIE OŚLEPIA",
                fontSize = 44.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFC2185B),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "☀️",
                fontSize = 80.sp
            )

            if (viewModel.brightnessIncreased.value) {
                Spacer(modifier = Modifier.height(48.dp))

                ValentineButton(
                    text = "Dalej →",
                    onClick = { viewModel.goToNextStep() }
                )
            }
        }
    }
}

// KROK 4: ZDRAPKA - NAPRAWIONA
@Composable
fun ScratchScreen(viewModel: ValentineViewModel) {
    val scratchedAreas = remember { mutableStateListOf<Offset>() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "Jesteś...",
                fontSize = 40.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFFD81B60),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "(zdrap aby odkryć)",
                fontSize = 16.sp,
                color = Color(0xFFD81B60),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                // Tekst pod zdrapką - zawsze widoczny
                Text(
                    text = "NAJNIEZWYKLEJSZA\nNA ŚWIECIE",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFE91E63),
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp
                )

                // Warstwa do zdrapywania - overlay na tekście
                if (!viewModel.scratchRevealed.value) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectDragGestures { change, _ ->
                                    scratchedAreas.add(change.position)
                                    viewModel.updateScratchProgress(0.5f)
                                    change.consume()
                                }
                            }
                    ) {
                        // Szara warstwa do zdrapywania
                        drawRect(
                            color = Color(0xFFC0C0C0),
                            size = size
                        )

                        // Wzór zdrapki (na całej powierzchni)
                        for (i in 0 until size.width.toInt() step 20) {
                            for (j in 0 until size.height.toInt() step 20) {
                                drawCircle(
                                    color = Color(0xFFB0B0B0),
                                    radius = 2f,
                                    center = Offset(i.toFloat(), j.toFloat())
                                )
                            }
                        }

                        // "Zdrapane" obszary - rysujemy na końcu aby usunąć warstwę
                        scratchedAreas.forEach { point ->
                            drawCircle(
                                color = Color.Transparent,
                                radius = 40f,
                                center = point,
                                blendMode = androidx.compose.ui.graphics.BlendMode.Clear
                            )
                        }
                    }
                }
            }

            if (viewModel.scratchRevealed.value) {
                Spacer(modifier = Modifier.height(48.dp))

                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + scaleIn()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "💖",
                            fontSize = 60.sp
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        ValentineButton(
                            text = "Dalej →",
                            onClick = { viewModel.goToNextStep() }
                        )
                    }
                }
            }
        }
    }
}

// KROK 5: ZGAŚ ŚWIATŁO
@Composable
fun DarknessScreen(viewModel: ValentineViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "🌙",
                fontSize = 80.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Teraz zgaś światło,",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE91E63),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "niech dookoła",
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFFFFC0D3),
                textAlign = TextAlign.Center
            )

            Text(
                text = "będzie ciemno",
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFFFFC0D3),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            ValentineButton(
                text = "Zgasiłam →",
                onClick = {
                    viewModel.darknessAcknowledged.value = true
                    viewModel.goToNextStep()
                }
            )
        }
    }
}

// KROK 6: LATARKA - JESTEŚ MOIM ŚWIATŁEM
@Composable
fun FlashlightScreen(viewModel: ValentineViewModel) {
    val context = LocalContext.current
    val cameraManager = remember { context.getSystemService(Context.CAMERA_SERVICE) as CameraManager }
    val cameraId = remember {
        try {
            cameraManager.cameraIdList[0]
        } catch (e: Exception) {
            null
        }
    }

    // Automatyczne włączenie latarki po wejściu na ekran
    LaunchedEffect(Unit) {
        delay(500)
        try {
            cameraId?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cameraManager.setTorchMode(it, true)
                    viewModel.flashlightActivated.value = true
                }
            }
        } catch (e: Exception) {
            Log.e("FlashlightScreen", "Błąd włączania latarki", e)
        }
    }

    // Wyłączenie latarki przy opuszczaniu ekranu
    DisposableEffect(Unit) {
        onDispose {
            try {
                cameraId?.let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        cameraManager.setTorchMode(it, false)
                    }
                }
            } catch (e: Exception) {
                Log.e("FlashlightScreen", "Błąd wyłączania latarki", e)
            }
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "light")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(3f / 4f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF2A2A2A))
                    .border(
                        width = 3.dp,
                        color = Color(0xFFE91E63).copy(alpha = alpha),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gal3),
                    contentDescription = "Nasze zdjęcie",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "💡",
                fontSize = 60.sp,
                modifier = Modifier.scale(if (viewModel.flashlightActivated.value) alpha else 1f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Kiedy jest ciemno",
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFFFFC0D3),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "ty jesteś",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE91E63).copy(alpha = alpha),
                textAlign = TextAlign.Center
            )

            Text(
                text = "MOIM ŚWIATŁEM",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFE91E63).copy(alpha = alpha),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            ValentineButton(
                text = "Dalej →",
                onClick = { viewModel.goToNextStep() }
            )
        }
    }
}

// KROK 7: DMUCHNIJ W MIKROFON
@Composable
fun BlowScreen(viewModel: ValentineViewModel) {
    val context = LocalContext.current
    var isListening by remember { mutableStateOf(false) }
    var audioRecorder by remember { mutableStateOf<AudioRecord?>(null) }
    val cloudOffset = remember { Animatable(0f) }

    // launcher do żądania uprawnienia
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            viewModel.permissionGranted.value = true
        } else {
            viewModel.permissionGranted.value = false
        }
    }

    // sprawdzenie uprawnień na starcie
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
            == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.permissionGranted.value = true
        } else {
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    // Tworzenie i obsługa AudioRecord dopiero gdy uprawnienie jest przyznane
    if (viewModel.permissionGranted.value) {
        DisposableEffect(Unit) {
            val bufferSize = AudioRecord.getMinBufferSize(
                44100,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT
            )

            try {
                audioRecorder = AudioRecord(
                    MediaRecorder.AudioSource.MIC,
                    44100,
                    AudioFormat.CHANNEL_IN_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    bufferSize
                )

                audioRecorder?.startRecording()
                isListening = true

                val audioData = ShortArray(bufferSize)

                Thread {
                    while (isListening && !viewModel.cloudsCleared.value) {
                        val readSize = audioRecorder?.read(audioData, 0, bufferSize) ?: 0

                        if (readSize > 0) {
                            var sum = 0L
                            for (i in 0 until readSize) {
                                sum += (audioData[i] * audioData[i]).toLong()
                            }
                            val amplitude = sqrt((sum / readSize).toDouble())

                            if (amplitude > 2000) {
                                viewModel.cloudsCleared.value = true
                            }
                        }

                        Thread.sleep(100)
                    }
                }.start()

            } catch (e: SecurityException) {
                Log.e("BlowScreen", "Brak uprawnień do mikrofonu", e)
            } catch (e: Exception) {
                Log.e("BlowScreen", "Błąd AudioRecord", e)
            }

            onDispose {
                isListening = false
                audioRecorder?.stop()
                audioRecorder?.release()
            }
        }
    }

    // Animacja chmur
    LaunchedEffect(viewModel.cloudsCleared.value) {
        if (viewModel.cloudsCleared.value) {
            cloudOffset.animateTo(
                -1000f,
                animationSpec = tween(1000, easing = FastOutSlowInEasing)
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            if (!viewModel.permissionGranted.value) {
                Text(
                    text = "Aplikacja potrzebuje dostępu do mikrofonu!",
                    fontSize = 24.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
                return@Column
            }

            if (!viewModel.cloudsCleared.value) {
                Text(
                    text = "Dmuchnij w mikrofon...",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD81B60),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(48.dp))

                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .offset(x = cloudOffset.value.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "☁️", fontSize = 80.sp)
                    Text(
                        text = "☁️", fontSize = 100.sp,
                        modifier = Modifier.offset(x = (-20).dp, y = 20.dp)
                    )
                    Text(
                        text = "☁️", fontSize = 90.sp,
                        modifier = Modifier.offset(x = 30.dp, y = (-10).dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "💨", fontSize = 60.sp)

            } else {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + scaleIn()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "☀️", fontSize = 120.sp)
                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = "ZAWSZE UMIESZ",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE91E63),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "MNIE",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFC2185B),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "ROZCHMURZYĆ",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFC2185B),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(48.dp))

                        ValentineButton(
                            text = "Dalej →",
                            onClick = { viewModel.goToNextStep() }
                        )
                    }
                }
            }
        }
    }
}


// KROK 8: KAMERA - NAJPIĘNIEJSZA DZIEWCZYNA
@Composable
fun CameraScreen(viewModel: ValentineViewModel) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }

    // Automatyczne żądanie uprawnień przy pierwszym wejściu
    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            delay(300) // Krótkie opóźnienie dla płynności
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "camera_text")
    val textScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "textScale"
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (hasCameraPermission) {
            // Podgląd z kamery
            AndroidView(
                factory = { ctx ->
                    PreviewView(ctx).apply {
                        scaleType = PreviewView.ScaleType.FILL_CENTER
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE

                        val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                        cameraProviderFuture.addListener({
                            try {
                                val cameraProvider = cameraProviderFuture.get()

                                val preview = Preview.Builder()
                                    .build()
                                    .also {
                                        it.setSurfaceProvider(surfaceProvider)
                                    }

                                val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

                                cameraProvider.unbindAll()
                                cameraProvider.bindToLifecycle(
                                    lifecycleOwner,
                                    cameraSelector,
                                    preview
                                )
                            } catch (e: Exception) {
                                Log.e("CameraScreen", "Błąd uruchamiania kamery", e)
                            }
                        }, ContextCompat.getMainExecutor(ctx))
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            // Delikatny gradient dla czytelności tekstu
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.4f),
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.5f)
                            )
                        )
                    )
            )
        } else {
            // Ekran gdy brak uprawnień
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFE5EC),
                                Color(0xFFFFC0D3)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(32.dp)
                ) {
                    Text(
                        text = "📸",
                        fontSize = 80.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Potrzebuję dostępu",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE91E63),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "do kamery",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE91E63),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    ValentineButton(
                        text = "Przyznaj uprawnienia",
                        onClick = {
                            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    )
                }
            }
        }

        // Tekst na górze
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 60.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "OTO",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium.copy(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.Black.copy(alpha = 0.8f),
                        offset = Offset(4f, 4f),
                        blurRadius = 8f
                    )
                ),
                modifier = Modifier.scale(textScale)
            )

            Text(
                text = "NAJPIĘKNIEJSZA",
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFFFE5EC),
                textAlign = TextAlign.Center,
                lineHeight = 48.sp,
                style = MaterialTheme.typography.displayMedium.copy(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.Black.copy(alpha = 0.8f),
                        offset = Offset(4f, 4f),
                        blurRadius = 8f
                    )
                ),
                modifier = Modifier.scale(textScale)
            )

            Text(
                text = "DZIEWCZYNA",
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFFFE5EC),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium.copy(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.Black.copy(alpha = 0.8f),
                        offset = Offset(4f, 4f),
                        blurRadius = 8f
                    )
                ),
                modifier = Modifier.scale(textScale)
            )

            Text(
                text = "NA CAŁYM ŚWIECIE",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFE91E63),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium.copy(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.Black.copy(alpha = 0.9f),
                        offset = Offset(4f, 4f),
                        blurRadius = 10f
                    )
                ),
                modifier = Modifier.scale(textScale)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "💖",
                fontSize = 60.sp,
                style = MaterialTheme.typography.displayMedium.copy(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.Black.copy(alpha = 0.5f),
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                )
            )
        }

        // Przycisk na dole
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp, start = 32.dp, end = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ValentineButton(
                text = "Dalej →",
                onClick = {
                    viewModel.cameraViewed.value = true
                    viewModel.goToNextStep()
                }
            )
        }
    }
}

// KROK 9: KOMPAS - ZNAJDŹ MNIE - NAPRAWIONY
@Composable
fun CompassScreen(viewModel: ValentineViewModel) {
    val context = LocalContext.current

    val targetLatitude = 52.223222
    val targetLongitude = 20.944083

    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasLocationPermission = isGranted
    }

    var currentLocation by remember { mutableStateOf<Location?>(null) }
    var azimuth by remember { mutableStateOf(0f) }
    var bearingToTarget by remember { mutableStateOf(0f) }
    var distance by remember { mutableStateOf(0f) }

    // NOWE: Filtrowanie odczytów kompasu dla stabilności
    val azimuthHistory = remember { mutableListOf<Float>() }
    val smoothedAzimuth = remember { mutableStateOf(0f) }

    // Obliczanie kąta do celu
    fun calculateBearing(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        val dLon = Math.toRadians(lon2 - lon1)
        val lat1Rad = Math.toRadians(lat1)
        val lat2Rad = Math.toRadians(lat2)

        val y = sin(dLon) * cos(lat2Rad)
        val x = cos(lat1Rad) * sin(lat2Rad) - sin(lat1Rad) * cos(lat2Rad) * cos(dLon)

        var bearing = Math.toDegrees(atan2(y, x))
        bearing = (bearing + 360) % 360

        return bearing.toFloat()
    }

    // Obliczanie odległości
    fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        val results = FloatArray(1)
        Location.distanceBetween(lat1, lon1, lat2, lon2, results)
        return results[0]
    }

    // Sprawdzanie czy telefon jest skierowany w dobrą stronę
    LaunchedEffect(smoothedAzimuth.value, bearingToTarget) {
        if (currentLocation != null) {
            val difference = Math.abs((smoothedAzimuth.value - bearingToTarget + 360) % 360)
            val normalizedDiff = if (difference > 180) 360 - difference else difference

            if (normalizedDiff < 20) { // Tolerancja 20 stopni
                viewModel.compassAligned.value = true
            }
        }
    }

    // Lokalizacja GPS
    DisposableEffect(Unit) {
        if (hasLocationPermission) {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            val locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    currentLocation = location
                    bearingToTarget = calculateBearing(
                        location.latitude,
                        location.longitude,
                        targetLatitude,
                        targetLongitude
                    )
                    distance = calculateDistance(
                        location.latitude,
                        location.longitude,
                        targetLatitude,
                        targetLongitude
                    )
                }

                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            }

            try {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    1f,
                    locationListener
                )

                // Pobierz ostatnią znaną lokalizację
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let {
                    currentLocation = it
                    bearingToTarget = calculateBearing(
                        it.latitude,
                        it.longitude,
                        targetLatitude,
                        targetLongitude
                    )
                    distance = calculateDistance(
                        it.latitude,
                        it.longitude,
                        targetLatitude,
                        targetLongitude
                    )
                }
            } catch (e: SecurityException) {
                Log.e("CompassScreen", "Brak uprawnień lokalizacji", e)
            }

            onDispose {
                locationManager.removeUpdates(locationListener)
            }
        } else {
            onDispose { }
        }
    }

    // Sensor magnetyczny (kompas) - Z FILTROWANIEM
    DisposableEffect(Unit) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        val accelerometerReading = FloatArray(3)
        val magnetometerReading = FloatArray(3)
        val rotationMatrix = FloatArray(9)
        val orientationAngles = FloatArray(3)

        val sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                when (event.sensor.type) {
                    Sensor.TYPE_ACCELEROMETER -> {
                        System.arraycopy(event.values, 0, accelerometerReading, 0, accelerometerReading.size)
                    }
                    Sensor.TYPE_MAGNETIC_FIELD -> {
                        System.arraycopy(event.values, 0, magnetometerReading, 0, magnetometerReading.size)
                    }
                }

                SensorManager.getRotationMatrix(
                    rotationMatrix,
                    null,
                    accelerometerReading,
                    magnetometerReading
                )
                SensorManager.getOrientation(rotationMatrix, orientationAngles)

                azimuth = Math.toDegrees(orientationAngles[0].toDouble()).toFloat()
                azimuth = (azimuth + 360) % 360

                // Filtrowanie - uśrednianie ostatnich 5 odczytów
                azimuthHistory.add(azimuth)
                if (azimuthHistory.size > 5) {
                    azimuthHistory.removeAt(0)
                }
                smoothedAzimuth.value = azimuthHistory.average().toFloat()
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(sensorListener, magnetometer, SensorManager.SENSOR_DELAY_UI)

        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }

    // Żądanie uprawnień
    LaunchedEffect(Unit) {
        if (!hasLocationPermission) {
            delay(300)
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // Animacja pulsowania
    val infiniteTransition = rememberInfiniteTransition(label = "compass")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFE5EC),
                        Color(0xFFFFC0D3),
                        Color(0xFFFFE5EC)
                    )
                )
            )
    ) {
        if (!hasLocationPermission) {
            // Ekran bez uprawnień
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "🧭",
                    fontSize = 80.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Potrzebuję dostępu",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE91E63),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "do lokalizacji",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE91E63),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                ValentineButton(
                    text = "Przyznaj uprawnienia",
                    onClick = {
                        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "ZNAJDŹ MNIE",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFE91E63),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Skieruj telefon w moją stronę",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFFD81B60),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Kompas
                Box(
                    modifier = Modifier
                        .size(280.dp)
                        .scale(if (viewModel.compassAligned.value) pulse else 1f),
                    contentAlignment = Alignment.Center
                ) {
                    // Okrąg kompasu
                    Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val radius = size.minDimension / 2
                        val center = Offset(size.width / 2, size.height / 2)

                        // Tło kompasu
                        drawCircle(
                            color = Color.White.copy(alpha = 0.9f),
                            radius = radius,
                            center = center
                        )

                        // Obramowanie
                        drawCircle(
                            color = Color(0xFFE91E63),
                            radius = radius,
                            center = center,
                            style = Stroke(width = 8f)
                        )

                        // Kierunki świata
                        val directions = listOf("N", "E", "S", "W")
                        directions.forEachIndexed { index, direction ->
                            val angle = index * 90f
                            val textSize = 40f

                            drawContext.canvas.nativeCanvas.apply {
                                val paint = android.graphics.Paint().apply {
                                    color = android.graphics.Color.rgb(198, 24, 91)
                                    this.textSize = textSize
                                    textAlign = android.graphics.Paint.Align.CENTER
                                    isFakeBoldText = true
                                }

                                val x = center.x + (radius - 50f) * sin(Math.toRadians(angle.toDouble())).toFloat()
                                val y = center.y - (radius - 50f) * cos(Math.toRadians(angle.toDouble())).toFloat() + textSize / 3

                                drawText(direction, x, y, paint)
                            }
                        }

                        if (currentLocation != null) {
                            val targetAngle = bearingToTarget
                            val dotX = center.x + (radius - 80f) * sin(Math.toRadians(targetAngle.toDouble())).toFloat()
                            val dotY = center.y - (radius - 80f) * cos(Math.toRadians(targetAngle.toDouble())).toFloat()

                            drawCircle(
                                color = Color(0xFF4CAF50),
                                radius = 12f,
                                center = Offset(dotX, dotY)
                            )
                        }
                    }

                    if (currentLocation != null) {
                        val arrowRotation = bearingToTarget - smoothedAzimuth.value

                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Kierunek do mnie",
                            modifier = Modifier
                                .size(100.dp)
                                .rotate(arrowRotation),
                            tint = Color(0xFFE91E63)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Odległość
                if (currentLocation != null) {
                    Text(
                        text = if (distance >= 1000) {
                            "Odległość: ${String.format("%.2f", distance / 1000)} km"
                        } else {
                            "Odległość: ${distance.toInt()} m"
                        },
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD81B60),
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        text = "Czekam na lokalizację GPS...",
                        fontSize = 18.sp,
                        color = Color(0xFFD81B60),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Status wyrównania
                AnimatedVisibility(
                    visible = viewModel.compassAligned.value,
                    enter = fadeIn() + scaleIn()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "💕 IDEALNIE! 💕",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFE91E63),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Kierujesz się w moją stronę!",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD81B60),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        ValentineButton(
                            text = "Dalej →",
                            onClick = { viewModel.goToNextStep() }
                        )
                    }
                }

                if (!viewModel.compassAligned.value && currentLocation != null) {
                    Text(
                        text = "🧭",
                        fontSize = 60.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Dopasuj różowe serce do zielonej kropki",
                        fontSize = 16.sp,
                        color = Color(0xFFD81B60),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

// KROK 10: GALERIA - WALENTYNKI ROK TEMU
@Composable
fun GalleryScreen(viewModel: ValentineViewModel) {
    val pagerState = rememberPagerState(pageCount = { 3 })

    val infiniteTransition = rememberInfiniteTransition(label = "gallery")
    val heartBeat by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heartBeat"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFE5EC),
                        Color(0xFFFFC0D3),
                        Color(0xFFFFE5EC)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "TO NASZE",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE91E63),
                textAlign = TextAlign.Center
            )

            Text(
                text = "WALENTYNKI",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFC2185B),
                textAlign = TextAlign.Center
            )

            Text(
                text = "ROK TEMU",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFE91E63),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "💕",
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                pageSpacing = 16.dp
            ) { page ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .graphicsLayer {
                            val pageOffset = (
                                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                                    ).absoluteValue

                            alpha = 1f - pageOffset.coerceIn(0f, 1f) * 0.5f
                            scaleX = 1f - pageOffset.coerceIn(0f, 1f) * 0.15f
                            scaleY = 1f - pageOffset.coerceIn(0f, 1f) * 0.15f
                        },
                    elevation = CardDefaults.cardElevation(12.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val imageRes = when (page) {
                            0 -> R.drawable.gal3
                            1 -> R.drawable.gal3
                            2 -> R.drawable.gal3
                            else -> R.drawable.gal3
                        }

                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = "Walentynki 2024 - zdjęcie ${page + 1}",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Fit
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                                .background(
                                    Color(0xFFE91E63).copy(alpha = 0.9f),
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "${page + 1} / 3",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                repeat(3) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (isSelected) 12.dp else 8.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) Color(0xFFE91E63)
                                else Color(0xFFE91E63).copy(alpha = 0.3f)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Pytanie
            Text(
                text = "CZY W TYM ROKU",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD81B60),
                textAlign = TextAlign.Center,
                modifier = Modifier.scale(heartBeat)
            )

            Text(
                text = "BĘDZIE JESZCZE LEPIEJ?",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFE91E63),
                textAlign = TextAlign.Center,
                modifier = Modifier.scale(heartBeat)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "💗",
                fontSize = 44.sp,
                modifier = Modifier.scale(heartBeat)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier.padding(horizontal = 32.dp)) {
                ValentineButton(
                    text = "Oczywiście! →",
                    onClick = {
                        viewModel.galleryViewed.value = true
                        viewModel.goToNextStep()
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// KROK 10: LIST
@Composable
fun LetterScreen(viewModel: ValentineViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "💌",
                fontSize = 80.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Myszka,",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE91E63)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Mamy szansę spędzić to święto już drugi raz razem i szczerze nie mogę się doczekać.",
                        fontSize = 18.sp,
                        color = Color(0xFF424242),
                        lineHeight = 28.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Jesteś najlepszą dziewczyną jaką mógłbym sobie wymarzyć.",
                        fontSize = 18.sp,
                        color = Color(0xFF424242),
                        lineHeight = 28.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Cieszę się że cię mam i kocham cię najmocniej na świecie myszka.",
                        fontSize = 18.sp,
                        color = Color(0xFF424242),
                        lineHeight = 28.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Dlatego mam teraz pytanie...",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE91E63),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "💕",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            ValentineButton(
                text = "Jakie pytanie? →",
                onClick = { viewModel.goToNextStep() }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// KROK 9: PYTANIE O WALENTYNKĘ
@Composable
fun QuestionScreen(viewModel: ValentineViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Pulsujące serce
        val infiniteTransition = rememberInfiniteTransition(label = "heart")
        val heartScale by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.15f,
            animationSpec = infiniteRepeatable(
                animation = tween(800),
                repeatMode = RepeatMode.Reverse
            ),
            label = "heartScale"
        )

        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .scale(heartScale),
            tint = Color(0xFFE91E63)
        )

        Spacer(modifier = Modifier.height(32.dp))


        Text(
            text = "Czy zostaniesz",
            fontSize = 32.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFD81B60),
            textAlign = TextAlign.Center
        )

        Text(
            text = "moją Walentynką?",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC2185B),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Przycisk TAK
        ValentineButton(
            text = "TAK",
            onClick = {
                viewModel.willBeValentine.value = "TAK"
                viewModel.goToNextStep()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Przycisk TAK!!!!
        ValentineButton(
            text = "TAK!!!!",
            primary = false,
            onClick = {
                viewModel.willBeValentine.value = "TAK!!!!"
                viewModel.goToNextStep()
            }
        )
    }
}

@Composable
fun ExcitementScreen(viewModel: ValentineViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Jak bardzo",
            fontSize = 28.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFD81B60),
            textAlign = TextAlign.Center
        )

        Text(
            text = "cieszysz się na Walentynki?",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC2185B),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Emoji feedback
        val emoji = when {
            viewModel.excitementLevel.value < 3 -> "😊"
            viewModel.excitementLevel.value < 6 -> "😍"
            viewModel.excitementLevel.value < 9 -> "🥰"
            else -> "😻"
        }

        Text(
            text = emoji,
            fontSize = 80.sp,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "${viewModel.excitementLevel.value.toInt()}/10",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE91E63)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Slider(
            value = viewModel.excitementLevel.value,
            onValueChange = { viewModel.excitementLevel.value = it },
            valueRange = 1f..10f,
            steps = 8,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFFE91E63),
                activeTrackColor = Color(0xFFE91E63),
                inactiveTrackColor = Color(0xFFFFCDD2)
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        ValentineButton(
            text = "Dalej →",
            onClick = { viewModel.goToNextStep() }
        )
    }
}

@Composable
fun HugsScreen(viewModel: ValentineViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Kliknij misia tyle razy",
            fontSize = 24.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFD81B60),
            textAlign = TextAlign.Center
        )

        Text(
            text = "ile przytulisz mnie 14 lutego! 🤗",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC2185B),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Animowany counter
        val scale = remember { Animatable(1f) }

        LaunchedEffect(viewModel.hugCount.value) {
            scale.animateTo(1.3f, animationSpec = tween(100))
            scale.animateTo(1f, animationSpec = tween(100))
        }

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFE5EC))
                .border(4.dp, Color(0xFFE91E63), CircleShape)
                .clickable { viewModel.incrementHugs() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Miś",
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale.value),
                tint = Color(0xFFE91E63)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "${viewModel.hugCount.value}",
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE91E63),
            modifier = Modifier.scale(scale.value)
        )

        Text(
            text = "przytulasów! 💕",
            fontSize = 24.sp,
            color = Color(0xFFD81B60)
        )

        Spacer(modifier = Modifier.height(48.dp))

        ValentineButton(
            text = "Dalej →",
            onClick = { viewModel.goToNextStep() }
        )
    }
}

@Composable
fun ExpectationsScreen(viewModel: ValentineViewModel) {
    val expectations = listOf(
        "Dobre jedzonko 🍝",
        "Prezent 🎁",
        "Kawaaa ☕",
        "Ty już wiesz co 😏",
        "Masaż 💆",
        "Jakiś super film 🎬",
        "Ciebie 💕"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Co oczekujesz",
            fontSize = 28.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFD81B60),
            textAlign = TextAlign.Center
        )

        Text(
            text = "w Walentynki?",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC2185B),
            textAlign = TextAlign.Center
        )

        Text(
            text = "(możesz wybrać kilka!)",
            fontSize = 16.sp,
            color = Color(0xFFD81B60),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        expectations.forEach { expectation ->
            ExpectationCard(
                text = expectation,
                isSelected = viewModel.expectations.value.contains(expectation),
                onToggle = { viewModel.toggleExpectation(expectation) }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        ValentineButton(
            text = "Dalej →",
            onClick = { viewModel.goToNextStep() }
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ExpectationCard(text: String, isSelected: Boolean, onToggle: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFE91E63) else Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) Color.White else Color(0xFF424242)
            )

            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun LoveLevelScreen(viewModel: ValentineViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Jak bardzo",
            fontSize = 28.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFD81B60),
            textAlign = TextAlign.Center
        )

        Text(
            text = "mnie kochasz? 💖",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC2185B),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        val loveText = when {
            viewModel.loveLevel.value < 10f -> "${viewModel.loveLevel.value.toInt()}/10"
            else -> "∞"
        }

        Text(
            text = loveText,
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE91E63)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Slider(
            value = viewModel.loveLevel.value,
            onValueChange = { viewModel.loveLevel.value = it },
            valueRange = 1f..11f,
            steps = 9,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFFE91E63),
                activeTrackColor = Color(0xFFE91E63),
                inactiveTrackColor = Color(0xFFFFCDD2)
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = if (viewModel.loveLevel.value >= 10f) "Nieskończoność! 💞" else "",
            fontSize = 20.sp,
            color = Color(0xFFD81B60),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        ValentineButton(
            text = "Dalej →",
            onClick = {
                viewModel.sendResults()
                viewModel.goToNextStep()
            }
        )
    }
}

@Composable
fun FinalScreen(viewModel: ValentineViewModel) {
    val infiniteTransition = rememberInfiniteTransition(label = "final")
    val heartScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heartScale"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .scale(heartScale),
                tint = Color(0xFFE91E63)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "WIDZIMY SIĘ",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC2185B),
                textAlign = TextAlign.Center
            )

            Text(
                text = "W WALENTYNKI !!!",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFE91E63),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "KOCHAM CIĘ",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD81B60),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "~ Michał",
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFFC2185B),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "💕 💖 💗 💝 💞 💓",
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ValentineButton(
    text: String,
    primary: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (primary) Color(0xFFE91E63) else Color(0xFFF48FB1)
        ),
        shape = RoundedCornerShape(30.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp
        )
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun HeartsBackground() {
    // Tło z animowanymi serduszkami
    val infiniteTransition = rememberInfiniteTransition(label = "bg")
}