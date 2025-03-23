package com.example.yourgarden.ui

import androidx.annotation.StringRes
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
import com.example.yourgarden.ui.screens.HomeScreen
import com.example.yourgarden.ui.screens.HomeViewModel
import com.example.yourgarden.ui.screens.music.MusicList
import com.example.yourgarden.ui.screens.music.MusicViewModel

enum class GardenScreen(@StringRes val title: Int)
{
    Start(title = R.string.yourgarden),
    MusicList(title = R.string.music),
    Coupons(title = R.string.coupons)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GardenAppBar(
    currentScreen: GardenScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    viewModel: MusicViewModel,
    onSettingsClick: () -> Unit, // Dodajemy callback
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
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

@Composable
fun GardenApp(
    navController: NavController = rememberNavController(),
    homeViewModel: HomeViewModel,
    musicViewModel: MusicViewModel,
    couponsViewModel: CouponsViewModel
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GardenScreen.valueOf(
        backStackEntry?.destination?.route ?: GardenScreen.Start.name
    )

    // Stan dla dialogu zmiany URL
    var showDialog by remember { mutableStateOf(false) }
    var urlText by remember { mutableStateOf(musicViewModel.getServerUrl()) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GardenAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.popBackStack() },
            viewModel = musicViewModel, // Przekazujemy viewModel
            onSettingsClick = {
                urlText = musicViewModel.getServerUrl() // Ustawiamy aktualny URL przed otwarciem dialogu
                showDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        NavHost(navController = navController as NavHostController, startDestination = GardenScreen.Start.name) {
            composable(GardenScreen.Start.name) {
                HomeScreen(
                    screens = listOf(
                        GardenScreen.MusicList,
                        GardenScreen.Coupons
                    ),
                    onNextButtonClicked = { screen ->
                        when (screen) {
                            GardenScreen.MusicList -> navController.navigate(GardenScreen.MusicList.name)
                            GardenScreen.Coupons -> navController.navigate(GardenScreen.Coupons.name)
                            else -> {}
                        }
                    },
                    homeViewModel,
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
                        musicViewModel.setServerUrl(urlText) // Zapisujemy nowy URL
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