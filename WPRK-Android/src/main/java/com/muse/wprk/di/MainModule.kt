package com.muse.wprk.di

import android.app.Application
import android.content.Context
import android.media.AudioManager
import android.media.audiofx.LoudnessEnhancer
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.ASCENDING
import com.muse.wprk.core.utilities.Constants
import com.muse.wprk.core.utilities.Constants.COLLECTION_NAME
import com.muse.wprk.core.utilities.Constants.ORDER_BY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.math.log10
import kotlin.math.roundToInt

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Singleton
    @Provides
    fun provideExoplayer(@ApplicationContext context: Context): ExoPlayer {

        return ExoPlayer.Builder(context)
            .build()
            .apply {
                val dataSourceFactory = DefaultHttpDataSource.Factory()
                val media = MediaItem.Builder()
                    .setUri(Constants.DEFAULT_STREAM)
                    .setLiveConfiguration(
                        MediaItem.LiveConfiguration.Builder()
                            .build().apply {
                                setAudioAttributes(
                                    AudioAttributes.Builder()
                                        .setUsage(C.USAGE_MEDIA)
                                        .setContentType(C.STREAM_TYPE_MUSIC)
                                        .build(),
                                    true
                                )
                                setForegroundMode(true)
                            })
                    .build()
                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(media)

                setMediaSource(source)
                prepare()
            }
    }

    @Singleton
    @Provides
    fun provideLoudnessEnhancer(player: ExoPlayer) = LoudnessEnhancer(player.audioSessionId).apply {
        val audioPct = 1.2
        val gainMB = (log10(audioPct) * 10000).roundToInt()
        setTargetGain(gainMB)
        enabled = true
    }

    @Singleton
    @Provides
    fun provideAudioManager(@ApplicationContext context: Context): AudioManager {
        return context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }
}