package com.example.yourgarden.ui.screens.music

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourgarden.FileUtils
import com.example.yourgarden.data.song.SongDao
import com.example.yourgarden.data.song.SongEntity
import com.example.yourgarden.ui.MusicListRepository
import com.yausername.youtubedl_android.YoutubeDL
import com.yausername.youtubedl_android.YoutubeDLRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MusicViewModel(private val application: Application) : ViewModel() {
    private val repository = MusicListRepository(application)
    val songs: StateFlow<List<SongEntity>> = repository.getAllSongs().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun downloadSong(song: SongEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateDownloadStatus(song.youtubeUrl, "Downloading")
                val client = OkHttpClient()
                val json = JSONObject().put("youtube_url", song.youtubeUrl).toString()
                val body = json.toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url("http://192.168.1.8:5000/download")
                    .post(body)
                    .build()

                Log.d("MusicViewModel", "Sending request to: ${request.url}")
                client.newCall(request).execute().use { response ->
                    Log.d("MusicViewModel", "Response code: ${response.code}")
                    if (response.isSuccessful) {
                        val byteArray = response.body?.bytes()
                        byteArray?.let {
                            val filePath = FileUtils.saveFileToStorage(application, song.title, it, "webm")
                            repository.updateFilePath(song.youtubeUrl, filePath)
                            repository.updateDownloadStatus(song.youtubeUrl, "Downloaded")
                            Log.d("MusicViewModel", "File saved: $filePath")
                        }
                    } else {
                        val errorMessage = "Error: ${response.message}"
                        repository.updateDownloadStatus(song.youtubeUrl, errorMessage)
                        Log.e("MusicViewModel", errorMessage)
                    }
                }
            } catch (e: Exception) {
                val errorMessage = "Error: ${e.message}"
                repository.updateDownloadStatus(song.youtubeUrl, errorMessage)
                Log.e("MusicViewModel", "Exception: ${e.message}")
            }
        }
    }

    fun deleteSong(song: SongEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                FileUtils.deleteDownloadedSong(application, song, repository)
                Log.d("MusicViewModel", "Utwór usunięty: ${song.title}")
            } catch (e: Exception) {
                Log.e("MusicViewModel", "Błąd przy usuwaniu utworu: ${e.message}")
            }
        }
    }
}