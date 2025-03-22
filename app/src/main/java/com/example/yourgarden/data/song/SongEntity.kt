package com.example.yourgarden.data.song

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val artist: String,
    val duration: Int,
    val youtubeUrl: String,
    val filePath: String?,
    val downloadStatus: String? = null
)