package com.muse.wprk.presentation.components


import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class Person(val first: String, val last: String)
@Composable
fun PlayerView(player: SimpleExoPlayer,context: ProvidableCompositionLocal<Context>){
    val context = context.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

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

            factory = { _ ->
                playerView
            }
        )




}

@Composable
fun PLayerControlsWrapper(player: Player) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    )  {
        PlayerControls(player = player)
    }
}

@Composable
fun PlayerControls(player: Player) {
    Row(
        Modifier
            .fillMaxWidth(0.9f),

        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = {}){
            Icon(imageVector = Icons.Filled.Pause, contentDescription = "")
        }
        IconButton(onClick = { player.pause() }){
            Icon(imageVector = Icons.Filled.Pause, contentDescription = "", modifier = Modifier.background(
                Color.White))
        }
        IconButton(onClick = {}){
            Icon(imageVector = Icons.Filled.Pause, contentDescription = "")
        }
    }
}

@Composable
@Preview
fun PlayerScreenPreview(){
    val context = LocalContext
    val current = context.current
    val player = remember {
        SimpleExoPlayer.Builder(current).build().apply {
            val dataSourceFactory = DefaultDataSourceFactory(
                current,
                Util.getUserAgent(current, current.packageName)
            )
            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse("http://wprk.broadcasttool.stream:80/stream")))

            setMediaSource(source)
            prepare()
            play()
        }
    }

    PlayerView( player = player, context = context)
}
