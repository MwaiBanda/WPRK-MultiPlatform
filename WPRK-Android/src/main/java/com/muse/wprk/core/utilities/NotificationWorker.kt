package com.muse.wprk.core.utilities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {
    override fun doWork(): Result {
        val alarmManager = applicationContext.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager
        val showTitle = inputData.getString("title")
        val showID= inputData.getInt("showId", 1)
        val intent = Intent(applicationContext, NotificationReceiver::class.java).apply {
            putExtra(NotificationReceiver.titleKey, "Reminder ⏰")
            putExtra(NotificationReceiver.messageKey, "${showTitle} Is Now Live On WPRK 91.5FM")
            putExtra(NotificationReceiver.notificationId, showID)
        }
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, showID, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.set(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(), pendingIntent)
        return Result.success()
    }
}