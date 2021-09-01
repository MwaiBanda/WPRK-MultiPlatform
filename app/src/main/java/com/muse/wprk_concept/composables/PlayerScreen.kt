package com.muse.wprk_concept.composables


import android.net.Uri
import android.support.v4.media.MediaBrowserCompat
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavDeepLinkRequest.Builder.Companion.fromUri
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.C.USAGE_MEDIA
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.offline.DownloadHelper.createMediaSource
import com.google.android.exoplayer2.source.MediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.MediaItem

@Composable
fun PlayerScreen(navController: NavHostController?){

    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var autoPlay by remember { mutableStateOf(true) }
    var window by remember { mutableStateOf(0)}
    var position by remember{ mutableStateOf(0L) }




    val player = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            val mediaItem: MediaItem =
                MediaItem.fromUri("https://firebasestorage.googleapis.com/v0/b/mwai-banda-1557193356993.appspot.com/o/bensound-beyondtheline.mp3?alt=media&token=a10f9e8c-6c7b-4106-b946-9f10964ddbe3")
            setMediaItem(mediaItem)
            prepare()
            seekTo(position)
            play()
        }
    }



    val playerView = remember {
        val playerView = PlayerView(context)
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStart() {
                playerView.onResume()
                player.playWhenReady = true
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStop() {
                playerView.onPause()
                player.playWhenReady = false
            }
        })
        playerView.player = player
        playerView
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        factory = { _ ->
            playerView
        }
    )
    DisposableEffect(key1 = player) {
        onDispose {
            player.release()
        }
    }
}



@Composable
@Preview
fun PlayerScreenPreview(){
    PlayerScreen(navController = null)
}
