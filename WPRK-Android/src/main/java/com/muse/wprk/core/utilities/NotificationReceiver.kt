package com.muse.wprk.core.utilities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.muse.wprk.R



class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            val title = intent.getStringExtra(titleKey) ?: ""
            val text = intent.getStringExtra(messageKey) ?: ""
            val id: Int = intent.getIntExtra(notificationId, 1)

            showNotification(context, id, title, text)
        } catch (e: Exception) {
            Log.d("Receive Ex", "onReceive: ${e.printStackTrace()}")
        }
    }


    private fun showNotification(context: Context, id: Int, title: String, text: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.wprk_black)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        manager.notify(id, builder.build())
    }
    companion object {
        const val name = "Schedule Shows"
        const val description = "Used to Schedule Reminders for Shows"
        const val notificationId = "notificationId"
        const val channelId = "channel1"
        const val titleKey = "titleKey"
        const val messageKey = "messageKey"
    }
}
