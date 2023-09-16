package com.example.serviceexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BackgroundService: Service() {

    private val TAG = "TAG"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate: Service")

    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"onStartCommand: Service")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy: Service")

    }
}