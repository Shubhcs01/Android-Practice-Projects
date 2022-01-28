package com.example.a7minuteworkout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(toolbar_history_activity)

        val actionbar = supportActionBar //actionbar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true) //set back button
            actionbar.title = "History" // Setting an title in the action bar.
        }

        toolbar_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        getAllCompletedDates()
    }

    private fun getAllCompletedDates(){

        val dbHandler = SqliteOpenHelper(this, null)
        val allCompletedDatesList = dbHandler.getAllCompletedDatesList()

       if(allCompletedDatesList.size > 0){

           tvHistory.visibility = View.VISIBLE
           rvHistory.visibility = View.VISIBLE
           tvNoDataAvailable.visibility = View.GONE

           rvHistory.layoutManager = LinearLayoutManager(this)
           val adapter = HistoryAdapter(this, allCompletedDatesList)
           rvHistory.adapter = adapter

       } else {

           tvHistory.visibility = View.GONE
           rvHistory.visibility = View.GONE
           tvNoDataAvailable.visibility = View.VISIBLE
       }
    }
}