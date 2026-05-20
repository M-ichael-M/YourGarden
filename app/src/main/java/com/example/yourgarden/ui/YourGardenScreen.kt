package com.example.yourgarden.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.yourgarden.R
import com.example.yourgarden.ui.screens.CouponsScreen
import com.example.yourgarden.ui.screens.CouponsViewModel
import com.example.yourgarden.ui.screens.HeartScreenTransition
import com.example.yourgarden.ui.screens.HomeScreen
import com.example.yourgarden.ui.screens.Valentine
import com.example.yourgarden.ui.screens.music.MusicList
import com.example.yourgarden.ui.screens.music.MusicViewModel

enum class GardenScreen(
    @StringRes val title: Int,
    val emoji: String
) {
    Start(title = R.string.yourgarden, emoji = "🌱"),
    MusicList(title = R.string.music, emoji = "🎵"),
    Coupons(title = R.string.coupons, emoji = "🎟️"),
    Valentines(title = R.string.valentines_day, emoji = "❤️")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GardenAppBar(
    currentScreen: GardenScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    onSettingsClick: () -> Unit, // Dodajemy callback
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = Color.Unspecified,
            actionIconContentColor = Color.Unspecified
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onSettingsClick) { // Używamy przekazanej funkcji
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Zmień URL serwera"
                )
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GardenApp(
    navController: NavController = rememberNavController(),
    musicViewModel: MusicViewModel,
    couponsViewModel: CouponsViewModel
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GardenScreen.valueOf(
        backStackEntry?.destination?.route ?: GardenScreen.Start.name
    )

    var showDialog by remember { mutableStateOf(false) }
    var urlText by remember { mutableStateOf(musicViewModel.getServerUrl()) }
    var showScreenTransition by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GardenAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = {
                showScreenTransition = true
                navController.popBackStack()
            },
            onSettingsClick = {
                urlText = musicViewModel.getServerUrl()
                showDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController as NavHostController,
                startDestination = GardenScreen.Start.name
            ) {
                composable(GardenScreen.Start.name) {
                    HomeScreen(
                        screens = listOf(
                            GardenScreen.MusicList,
                            GardenScreen.Coupons,
                            GardenScreen.Valentines
                        ),
                        onNextButtonClicked = { screen ->
                            showScreenTransition = true
                            when (screen) {
                                GardenScreen.MusicList -> navController.navigate(GardenScreen.MusicList.name)
                                GardenScreen.Coupons -> navController.navigate(GardenScreen.Coupons.name)
                                GardenScreen.Valentines -> navController.navigate(GardenScreen.Valentines.name)
                                else -> {}
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }

                composable(GardenScreen.MusicList.name) {
                    MusicList(musicViewModel)
                }
                composable(GardenScreen.Coupons.name) {
                    CouponsScreen(viewModel = couponsViewModel)
                }
                composable(GardenScreen.Valentines.name) {
                    Valentine()
                }
            }

            // Animacja przejścia ekranów
            if (showScreenTransition) {
                HeartScreenTransition(
                    onAnimationEnd = { showScreenTransition = false }
                )
            }
        }

        // Dialog do zmiany URL
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Zmień URL serwera") },
                text = {
                    Column {
                        Text("Podaj bazowy adres serwera")
                        TextField(
                            value = urlText,
                            onValueChange = { urlText = it },
                            label = { Text("URL serwera") }
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        musicViewModel.setServerUrl(urlText)
                        showDialog = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Anuluj")
                    }
                }
            )
        }
    }
}



@Composable
@Preview
fun Preview() {
    //PsycheaApp()
}