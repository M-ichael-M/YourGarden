package com.example.yourgarden

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.yourgarden.data.GardenDatabase
import com.example.yourgarden.ui.CouponsRepository
import com.example.yourgarden.ui.GardenApp
import com.example.yourgarden.ui.MusicListRepository
import com.example.yourgarden.ui.MusicViewModelFactory
import com.example.yourgarden.ui.screens.CouponsViewModel
import com.example.yourgarden.ui.screens.CouponsViewModelFactory
import com.example.yourgarden.ui.screens.HomeViewModel
import com.example.yourgarden.ui.screens.music.MusicViewModel
import com.example.yourgarden.ui.theme.YourGardenTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val homeVm by viewModels<HomeViewModel>()
    private val musicVm: MusicViewModel by viewModels {
        MusicViewModelFactory(application)
    }
    private val couponsVm: CouponsViewModel by viewModels {
        CouponsViewModelFactory(
            repository = CouponsRepository(GardenDatabase.getInstance(this).couponsDao())
            // Przekazujemy MainActivity jako Context
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean("is_first_launch", true)

        if (isFirstLaunch) {
            val repository = MusicListRepository(this)
            lifecycleScope.launch {
                repository.insertSampleSongs()
                sharedPreferences.edit().putBoolean("is_first_launch", false).apply()
            }
        }

        setContent {
            YourGardenTheme {
                GardenApp(homeViewModel = homeVm, musicViewModel = musicVm, couponsViewModel = couponsVm)
            }
        }
    }
}