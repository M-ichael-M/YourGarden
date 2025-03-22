package com.example.yourgarden.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.yourgarden.ui.screens.music.MusicViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

    }
}

class MusicViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MusicViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
