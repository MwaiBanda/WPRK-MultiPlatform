package com.muse.wprk.core.utilities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.muse.wprk.R

class NotificationWorker(val context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {
    override fun doWork(): Result {
        val alarmManager = context.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager
        val showTitle = inputData.getString("title")
        val showID= inputData.getInt("showId", 1)
        val builder = NotificationCompat.Builder(context, NotificationReceiver.channelId)
            .setSmallIcon(R.drawable.wprklogo)
            .setContentTitle(showTitle)
            .setContentText("Hello from one-time worker!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(showID, builder.build())
        }

        return Result.success()
    }
}