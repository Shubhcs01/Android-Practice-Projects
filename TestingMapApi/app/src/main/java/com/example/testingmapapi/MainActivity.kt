package com.example.testingmapapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val placesApi = Places.Builder().apiKey("YOUR_API_KEY").build(this@MainActivity)
        autoCompleteEditText.setAdapter(PlacesAutoCompleteAdapter(this, placesApi))


    }
}

