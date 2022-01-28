package com.example.intentservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartService.setOnClickListener {
            Intent(this,MyService::class.java).also {
                startService(it)
                tvServiceInfo.text = "Service running"
            }
        }

        btnStopService.setOnClickListener {
            Intent(this,MyService::class.java).also {
                stopService(it)
                tvServiceInfo.text = "Service stopped."
            }
        }

        btnSave.setOnClickListener {
            Intent(this,MyService::class.java).also {
                val dataString = etServiceData.text.toString()
                it.putExtra("EXTRA_DATA",dataString)
                startService(it)
            }
        }

        //--> ***DO NOT FORGET TO ADD A SERVICE IN MANIFEST FILE ALSO.***

    }
}