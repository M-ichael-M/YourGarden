package com.example.yourgarden

import android.content.Context
import android.util.Log
import com.example.yourgarden.data.song.SongDao
import com.example.yourgarden.data.song.SongEntity
import java.io.File

object FileUtils {
    fun saveFileToStorage(context: Context, fileName: String, byteArray: ByteArray, extension: String = "webm"): String {
        val sanitizedFileName = fileName.replace("[^a-zA-Z0-9]".toRegex(), "_")
        val file = File(context.filesDir, "music/$sanitizedFileName.$extension")
        file.parentFile?.mkdirs()
        file.writeBytes(byteArray)
        return file.absolutePath
    }

    suspend fun deleteDownloadedSong(song: SongEntity, songDao: SongDao) {
        song.filePath?.let {
            val file = File(it)
            if (file.exists()) {
                val deleted = file.delete()
                Log.d("FileUtils", "Plik usunięty: $deleted dla ścieżki: $it")
            } else {
                Log.w("FileUtils", "Plik nie istnieje: $it")
            }
        }
        // Aktualizacja obu pól w bazie danych
        songDao.updateFilePath(song.youtubeUrl, null)
        songDao.updateDownloadStatus(song.youtubeUrl, null)
        Log.d("FileUtils", "Zaktualizowano dane utworu: ${song.title}")
    }
}