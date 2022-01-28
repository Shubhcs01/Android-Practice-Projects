package com.example.intentservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * IntentService runs on separate Thread but won't support MultiThreading,
 * while Service allow MultiThreading but not runs on separate Thread.
 * So, we have to create Thread manually.
 * */

class MyService: Service() {

    init {
        Log.d("MyService","Service is running...")
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("EXTRA_DATA")
        dataString?.let {
            Log.d("MyService",dataString)
        }

        Thread{
            while (true){ }
        }.start()

        return START_STICKY
    }
}