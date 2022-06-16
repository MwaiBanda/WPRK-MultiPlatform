package com.muse.wprk.core.utilities

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.muse.wprk.MainActivity
import com.muse.wprk.R

class NotificationWorker(val context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {
    override fun doWork(): Result {
        val showTitle = inputData.getString("title")
        val showID= inputData.getInt("showId", 1)
        val builder = NotificationCompat.Builder(context, NotificationReceiver.channelId)
            .setSmallIcon(R.drawable.wprk_black)
            .setContentTitle("Reminder ⏰")
            .setContentText("$showTitle Is Now Live On WPRK 91.5FM")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND))

        with(NotificationManagerCompat.from(context)) {
            notify(showID, builder.build())
        }

        return Result.success()
    }
}