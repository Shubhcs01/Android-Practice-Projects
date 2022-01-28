 package com.example.a7minuteworkout

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_finished_page.*
import java.text.SimpleDateFormat
import java.util.*

class FinishedPage : AppCompatActivity() {

    private var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finished_page)

        setSupportActionBar(toolbar_finished_activity)
        val actionbar = supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_finished_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnFinished.setOnClickListener {
            finish()
        }

        try {
            player = MediaPlayer.create(this, R.raw.applause)
            player!!.isLooping = false
            player!!.start()
        } catch (e: Exception){
            e.printStackTrace()
        }

        addDateToDatabase()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(player != null){
            player!!.stop()
        }
    }

    private fun addDateToDatabase(){
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        Log.i("DATE", "" + dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        val dbHandler = SqliteOpenHelper(this, null)
        dbHandler.addDate(date)
        Log.i("DATE: ", "Added")
    }

}