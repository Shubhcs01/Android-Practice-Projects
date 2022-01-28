package com.example.multiscreenapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_third.*

@Suppress("DEPRECATION")
class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        btnTakePhoto.setOnClickListener {
            //--> Implicit Intent
           Intent(Intent.ACTION_GET_CONTENT).also {
               it.type = "image/*"
               startActivityForResult(it, 0)
           }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 0){
            val uri = data?.data     //--> gives Uri of selected image.
            ivPhoto.setImageURI(uri)
        }
    }
}