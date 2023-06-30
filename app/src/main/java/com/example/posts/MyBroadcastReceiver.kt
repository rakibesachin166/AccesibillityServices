package com.example.posts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "com.example.posts.WHATSAPP_LAUNCHED" -> {
                Toast.makeText(context, "WhatsApp Launched", Toast.LENGTH_SHORT).show()
                Log.d("AppNameexbr", "whats app launch")
            }
            "com.example.posts.WHATSAPP_NOT_FOUND" -> {
                Toast.makeText(context, "WhatsApp Not Found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
