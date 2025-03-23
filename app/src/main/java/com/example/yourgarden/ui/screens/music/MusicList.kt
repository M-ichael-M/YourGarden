package com.example.yourgarden.ui.screens.music

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.yourgarden.R
import com.example.yourgarden.data.song.SongEntity
import kotlinx.coroutines.delay
import java.io.File

@Composable
fun MusicList(viewModel: MusicViewModel, modifier: Modifier = Modifier) {
    val songs by viewModel.songs.collectAsState()
    val downloadedSongs = songs.filter { it.filePath != null && File(it.filePath).exists() }
    val availableSongs = songs.filter { it.filePath == null }

    // Stan wyszukiwania
    var searchQuery by remember { mutableStateOf("") }
    // Filtrowanie utworów do pobrania bez uwzględniania znaków specjalnych i wielkości liter
    val filteredAvailableSongs = availableSongs.filter { song ->
        val cleanTitle = song.title.lowercase().replace("[^a-zA-Z0-9]".toRegex(), "")
        val cleanArtist = song.artist.lowercase().replace("[^a-zA-Z0-9]".toRegex(), "")
        val cleanQuery = searchQuery.lowercase().replace("[^a-zA-Z0-9]".toRegex(), "")
        cleanTitle.contains(cleanQuery) || cleanArtist.contains(cleanQuery)
    }

    // Stan odtwarzacza
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }
    var currentSong by remember { mutableStateOf<SongEntity?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var playbackPosition by remember { mutableLongStateOf(0L) }
    var totalDuration by remember { mutableLongStateOf(0L) }

    val latestDownloadedSongs by rememberUpdatedState(downloadedSongs)

    DisposableEffect(player) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    // Używamy najnowszej listy utworów
                    val currentIndex = latestDownloadedSongs.indexOf(currentSong)
                    if (currentIndex != -1 && latestDownloadedSongs.isNotEmpty()) {
                        val nextIndex = if (currentIndex == latestDownloadedSongs.lastIndex) 0 else currentIndex + 1
                        playbackPosition = 0L  // resetujemy pozycję
                        currentSong = latestDownloadedSongs[nextIndex]
                        isPlaying = true
                    }
                }
            }
        }
        player.addListener(listener)
        onDispose { player.removeListener(listener) }
    }

    // Ustawienie bieżącego utworu
    LaunchedEffect(currentSong) {
        currentSong?.filePath?.let { path ->
            player.clearMediaItems()
            val mediaItem = MediaItem.fromUri(Uri.fromFile(File(path)))
            player.setMediaItem(mediaItem)
            player.prepare()
            player.seekTo(playbackPosition)
            if (isPlaying) player.play()
            totalDuration = player.duration
        }
    }

    // Aktualizacja pozycji odtwarzania
    LaunchedEffect(player) {
        while (true) {
            playbackPosition = player.currentPosition
            totalDuration = player.duration
            delay(1000L)
        }
    }

    DisposableEffect(Unit) {
        onDispose { player.release() }
    }

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            // Sekcja pobranych utworów
            if (downloadedSongs.isNotEmpty()) {
                item {
                    Text(
                        text = "Downloaded Songs",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                items(downloadedSongs) { song ->
                    SongItem(
                        song = song,
                        isPlaying = isPlaying && song == currentSong,
                        onPlayPauseClick = {
                            if (currentSong == song) {
                                isPlaying = !isPlaying
                                if (isPlaying) player.play() else player.pause()
                            } else {
                                playbackPosition = 0L  // resetujemy pozycję
                                player.stop()          // zatrzymujemy aktualny utwór, żeby uniknąć konfliktu
                                currentSong = song
                                isPlaying = true
                            }
                        },
                        onDeleteClick = { viewModel.deleteSong(song) }
                    )

                }
            }

            // Sekcja utworów do pobrania z wyszukiwarką
            if (availableSongs.isNotEmpty()) {
                item {
                    Text(
                        text = "Available to Download",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    // Pasek wyszukiwania
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Szukaj utworu...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                }
                items(filteredAvailableSongs) { song ->
                    SongItem(
                        song = song,
                        isPlaying = false,
                        onPlayPauseClick = { viewModel.downloadSong(song) },
                        onDeleteClick = {}
                    )
                }
            }
        }

        PlaybackControls(
            isPlaying = isPlaying,
            playbackPosition = playbackPosition,
            totalDuration = totalDuration,
            onPlayPauseClick = {
                isPlaying = !isPlaying
                if (isPlaying) player.play() else player.pause()
            },
            onSeek = { newPosition ->
                playbackPosition = newPosition.toLong()
                player.seekTo(playbackPosition)
            }
        )
    }
}

@Composable
fun SongItem(
    song: SongEntity,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id = when {
                        isPlaying -> R.drawable.baseline_pause_24
                        song.filePath != null -> R.drawable.baseline_play_arrow_24
                        else -> R.drawable.baseline_download_24
                    }
                ),
                contentDescription = if (song.filePath != null) "Play/Pause" else "Download",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onPlayPauseClick)
            )
            Column(modifier = Modifier.weight(1f).padding(start = 16.dp)) {
                Text(text = song.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Artist: ${song.artist}", style = MaterialTheme.typography.bodyMedium)
                song.downloadStatus?.let {
                    Text(
                        text = it,
                        color = if (it.startsWith("Error")) Color.Red else Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            if (song.filePath != null) {
                IconButton(onClick = onDeleteClick) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_delete_24),
                        contentDescription = "Delete",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PlaybackControls(
    isPlaying: Boolean,
    playbackPosition: Long,
    totalDuration: Long,
    onPlayPauseClick: () -> Unit,
    onSeek: (Float) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onPlayPauseClick) {
                    Image(
                        painter = painterResource(id = if (isPlaying) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24),
                        contentDescription = "Play/Pause",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            Slider(
                value = playbackPosition.toFloat(),
                onValueChange = onSeek,
                valueRange = 0f..totalDuration.coerceAtLeast(1L).toFloat(),
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(formatTime(playbackPosition))
                Text(formatTime(totalDuration))
            }
        }
    }
}

fun formatTime(millis: Long): String {
    val minutes = (millis / 1000) / 60
    val seconds = (millis / 1000) % 60
    return String.format("%d:%02d", minutes, seconds)
}