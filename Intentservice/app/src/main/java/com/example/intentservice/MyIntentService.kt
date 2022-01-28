package com.example.intentservice

import android.app.IntentService
import android.content.Intent
import android.util.Log

  //--> IntentService is a subclass of Service class.

class MyIntentService: IntentService("MyIntentService") {

    init {
        instance = this
    }

    companion object{
        private lateinit var instance: MyIntentService
        var isRunning = false

        fun stopService(){
            Log.d("MyIntentService","Service is stopping.....")
            isRunning = false
            instance.stopSelf()
        }
    }

    override fun onHandleIntent(p0: Intent?) {
       try {
           isRunning = true
           while (isRunning){
               Log.d("MyIntentService","Service is running.....")
               Thread.sleep(1000)
           }
       } catch (e: InterruptedException){
           Thread.currentThread().interrupt()
       }
    }
}