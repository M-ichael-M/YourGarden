package com.example.yourgarden.data.song

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow



@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: SongEntity)

    @Query("SELECT * FROM songs")
    fun getAllSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE filePath IS NOT NULL")
    fun getDownloadedSongs(): Flow<List<SongEntity>>

    @Query("UPDATE songs SET filePath = :filePath WHERE youtubeUrl = :youtubeUrl")
    suspend fun updateFilePath(youtubeUrl: String, filePath: String?)

    @Query("UPDATE songs SET downloadStatus = :status WHERE youtubeUrl = :youtubeUrl")
    suspend fun updateDownloadStatus(youtubeUrl: String, status: String?)

    @Query("DELETE FROM songs WHERE id = :id")
    suspend fun deleteSong(id: Int)

    @Query("DELETE FROM songs WHERE filePath IS NOT NULL")
    suspend fun deleteAllDownloadedSongs()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(songs: List<SongEntity>)
}