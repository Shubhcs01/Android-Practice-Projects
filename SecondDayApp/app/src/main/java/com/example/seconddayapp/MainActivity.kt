package com.example.seconddayapp

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOrder.setOnClickListener {
            val radioButtonCheckedId = rg.checkedRadioButtonId
            val meat = findViewById<RadioButton>(radioButtonCheckedId)
            val cheese = cbCheese.isChecked
            val onion = cbOnion.isChecked
            val salad = cbSalad.isChecked

            val orderString = "You Ordered a burger with:\n"+"${meat.text}" +
                    (if(cheese)"\nCheese" else "") +
                    (if(onion)"\nOnion" else "") +
                    (if(salad)"\nSalad" else "")
            tvOrder.text = orderString
        }
    }
}