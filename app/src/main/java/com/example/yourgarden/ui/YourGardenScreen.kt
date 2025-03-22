package com.example.yourgarden.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.yourgarden.ui.screens.HomeScreen
import com.example.yourgarden.ui.screens.HomeViewModel
import com.example.yourgarden.ui.screens.music.MusicList
import com.example.yourgarden.ui.screens.music.MusicViewModel

enum class GardenScreen(@StringRes val title: Int)
{
    Start(title = R.string.yourgarden),
    MusicList(title = R.string.music)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GardenAppBar(
    currentScreen: GardenScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
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
        }

    )
}

@Composable
fun GardenApp(navController: NavController = rememberNavController(), homeViewModel: HomeViewModel, musicViewModel: MusicViewModel)
{
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GardenScreen.valueOf(
        backStackEntry?.destination?.route ?: GardenScreen.Start.name
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GardenAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        NavHost(navController = navController as NavHostController, startDestination = GardenScreen.Start.name) {
            composable(GardenScreen.Start.name) {
                HomeScreen( screens = listOf(
                    GardenScreen.MusicList
                ),
                    onNextButtonClicked = { screen ->
                        when (screen) {
                            GardenScreen.MusicList -> navController.navigate(GardenScreen.MusicList.name)

                            else -> {}
                        }
                    },
                    homeViewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            composable(GardenScreen.MusicList.name)
            {
                MusicList(musicViewModel)
            }

        }
    }
}

@Composable
@Preview
fun Preview() {
    //PsycheaApp()
}