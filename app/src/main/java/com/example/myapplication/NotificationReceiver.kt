package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "CLOSE_NOTIFICATION") {
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.cancel(1)
        }
    }
}