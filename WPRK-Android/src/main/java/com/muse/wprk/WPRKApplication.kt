package com.muse.wprk

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.jakewharton.threetenabp.AndroidThreeTen
import com.muse.wprk.core.utilities.NotificationWorker
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class WPRKApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(NotificationWorker.channelId, NotificationWorker.name, importance)
        channel.description = NotificationWorker.description

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}


