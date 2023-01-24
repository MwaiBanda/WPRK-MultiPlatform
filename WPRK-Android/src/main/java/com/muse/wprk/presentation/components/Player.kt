package com.muse.wprk.presentation.components

import android.R
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaMetadata
import com.muse.wprk.core.exts.parse
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class, ExperimentalCoilApi::class)
@Composable
fun WPRKPlayer(player: ExoPlayer, isPlaying: Boolean, onPlayerSwitch: (Boolean) -> Unit) {
    var currentTitle by rememberSaveable { mutableStateOf("") }
    var count by rememberSaveable { mutableStateOf(0) }

    val onMediaMetadataChanged: (MediaMetadata) -> Unit = { mediaMetadata ->
        if (mediaMetadata.title != null) {
            val remoteTitle = player.mediaMetadata.title.toString()
            if (remoteTitle != currentTitle) currentTitle = remoteTitle
        }
    }
    LaunchedEffect(key1 = count) {
        if (isPlaying) {
            onMediaMetadataChanged(player.mediaMetadata)
            delay(6100L)
            count++
        }
    }
    LaunchedEffect(key1 = isPlaying, block = {
        if (isPlaying) {
            count--
            val remoteTitle = player.mediaMetadata.title.toString()
            if (remoteTitle != currentTitle && player.mediaMetadata.title.isNullOrEmpty().not()) currentTitle = remoteTitle
        } else {
            count++

        }


    })
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .height(10.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp) // ADD Clickable Navigate to Detail
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.parse("#ffafcc"))
                .border(1.dp, color = Color.White, RoundedCornerShape(10.dp)),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(Modifier.padding(start = 35.dp), horizontalAlignment = Alignment.Start) {

                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                                append("WPRK 91.5")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 13.sp
                                )
                            ) {
                                append("FM")
                            }
                        },
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 15.dp)
                    )




                    AnimatedContent(
                        targetState = count,
                        transitionSpec = {
                            if (targetState > initialState) {
                                slideInHorizontally(
                                    animationSpec = tween(
                                        durationMillis = 5000
                                    )
                                ) { width -> (width + (width * 0.5)).toInt() } + fadeIn(
                                    animationSpec = tween(
                                        durationMillis = 6000,
                                    )
                                ) with slideOutHorizontally(
                                    animationSpec = tween(
                                        durationMillis = 6000,
                                    )
                                ) { width -> -(width + (width.toDouble() * 0.5).toInt()) } + fadeOut(
                                    animationSpec = tween(
                                        durationMillis = 5000
                                    )
                                )
                            } else {
                                slideInHorizontally(
                                    animationSpec = tween(
                                        durationMillis = 4000,
                                    )
                                ) { width -> -(width + (width.toDouble() * 0.3).toInt()) } + fadeIn(
                                    animationSpec = tween(
                                        durationMillis = 5000,
                                    )
                                ) with slideOutHorizontally(
                                    animationSpec = tween(
                                        durationMillis = 4000,
                                    )
                                ) { width -> (width + (width.toDouble() * 0.3).toInt()) } + fadeOut()
                            }
                        },
                        modifier = Modifier
                            .widthIn(300.dp)
                            .layout { measurable, constraints ->
                                val r =
                                    measurable.measure(constraints = constraints.copy(maxWidth = Constraints.Infinity))
                                layout(r.measuredWidth, r.measuredHeight, placementBlock = {
                                    r.placeWithLayer(45, 0, 0f) {
                                    }
                                })
                            }
                    ) {
                        Text(
                            if (currentTitle == "") "Tune In..." else "$currentTitle",
                            color = Color.White,
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )
                    }

                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .padding(end = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(45.dp)
                                .height(55.dp)
                                .background(color = Color(0xFFffafcc).copy(0.5f))
                                .padding(start = 7.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(data = "https://firebasestorage.googleapis.com/v0/b/wprk-c6825.appspot.com/o/IMG_4018-removebg-preview%403x.png?alt=media&token=2c203484-1186-4b8e-81c7-5a3186d7640d")
                                        .apply(block = fun ImageRequest.Builder.() {
                                            crossfade(true)
                                        }).build()
                                ),
                                modifier = Modifier.size(45.dp),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .background(color = Color(0xFFffafcc).copy(0.5f))
                            .padding(start = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(color = Color(0xFFffafcc))
                                .padding(start = 7.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            IconButton(
                                onClick = {
                                    when (player.isPlaying) {
                                        true -> player.pause()
                                            .also {
                                                onPlayerSwitch(false)
                                            }

                                        false -> player.play()
                                            .also {
                                                onPlayerSwitch(true)
                                            }
                                            .also {
                                                val remoteTitle =
                                                    player.mediaMetadata.title.toString()
                                                if (remoteTitle != currentTitle) currentTitle =
                                                    remoteTitle
                                            }
                                            .also { Log.d("MAIN", currentTitle) }


                                    }
                                },
                                modifier = Modifier
                                    .offset(x = (-10).dp)

                            ) {
                                Icon(
                                    when (isPlaying) {
                                        true -> Icons.Default.PauseCircle
                                        false -> Icons.Default.PlayCircle
                                    },
                                    "",
                                    tint = Color.White,
                                    modifier = Modifier.size(44.dp, 44.dp)
                                )
                            }
                        }
                    }

                }

            }


        }
    }

}

