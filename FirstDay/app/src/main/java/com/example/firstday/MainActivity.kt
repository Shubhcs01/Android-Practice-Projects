@file:Suppress("DEPRECATION")

package com.example.firstday

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnApply.setOnClickListener {
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val birthDate = etBirthDay.text.toString()
            val country = etCountry.text.toString()

            Log.d("MainActivity", "$firstName $lastName born on $birthDate in $country . code just worked")

            ivShubh.setImageResource(R.drawable.studentphoto)

//            Toast.makeText(this,"Hi, I'm a Toast", Toast.LENGTH_SHORT).show() //--> Activity context
//            Toast.makeText(applicationContext,"Hi, I'm a Toast", Toast.LENGTH_SHORT).show() //--> Application Context

            // Own's custom toast by creating toast instance
            Toast(this).apply {
                duration = Toast.LENGTH_LONG
                view = layoutInflater.inflate(R.layout.custom_toast, clToast)
                show()
            }

        }

        var count = 0
        btnApply2.setOnClickListener {
            count += 1
            tvCount.text = "Let's have the count: $count"
            Log.d("MainActivity","$count")
            ivShubh.setImageResource(R.drawable.studentphoto)
        }

        btnAdd.setOnClickListener {
            val firstNumber = etNum1.text.toString().toInt()
            val secondName = etNum2.text.toString().toInt()

            tvResult.text = "${firstNumber + secondName}"
            Log.d("MainActivity","${firstNumber + secondName}")
            ivShubh.setImageResource(R.drawable.studentphoto)
        }


    }

}