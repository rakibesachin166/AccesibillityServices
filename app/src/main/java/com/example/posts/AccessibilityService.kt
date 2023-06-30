package com.example.posts

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Handler
import android.util.Log
import android.view.accessibility.AccessibilityEvent


class AccessibilityService : AccessibilityService() {
    private lateinit var handler: Handler
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString()
            if (!packageName.isNullOrEmpty()) {
                val pm: PackageManager = packageManager
                try {
                    val appInfo: ApplicationInfo = pm.getApplicationInfo(packageName, 0)
                    val appName: CharSequence = pm.getApplicationLabel(appInfo)
                    Log.d("AppName", "$appName - $packageName")
                } catch (e: PackageManager.NameNotFoundException) {
                    if (e.message.equals("com.whatsapp")){
                        Log.d("App_Launch","Whatsapp Launched")
                    }

                }
            }
        }
    }


    override fun onInterrupt() {

    }
    override fun onServiceConnected() {
        handler = Handler(mainLooper)

        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or
                AccessibilityEvent.TYPE_VIEW_FOCUSED or AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK
        info.notificationTimeout = 100
        this.serviceInfo = info
        Log.d("on service Connected","service connected")
    }
}
