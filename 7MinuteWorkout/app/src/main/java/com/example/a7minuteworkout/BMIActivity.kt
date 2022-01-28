package com.example.a7minuteworkout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bmiactivity.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
    val US_UNITS_VIEW = "US_UNITS_VIEW"

    var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)

        setSupportActionBar(toolbar_bmi_activity)

        val actionbar = supportActionBar //actionbar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true) //set back button
            actionbar.title = "CALCULATE BMI" // Setting an title in the action bar.
        }

        toolbar_bmi_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        rgUnits.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUsUnitsView()
            }
        }

        btnCalculateBmi.setOnClickListener {
            if(rgUnits.checkedRadioButtonId == R.id.rbMetricUnits) {
                if (validateMetricUnits()) {
                    val heightValue: Float = etHeight_cm.text.toString().toFloat() / 100
                    val weightValue: Float = etWeight_kg.text.toString().toFloat()
                    val bmi = weightValue / (heightValue * heightValue)
                    displayBMIResult(bmi)
                } else {
                    Toast.makeText(
                        this@BMIActivity,
                        "Please enter valid values.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else  if(rgUnits.checkedRadioButtonId == R.id.rbUsUnits) {
                if (validateUsUnits()) {
                    val heightValueFeet: Float = etUsHeightFeet.text.toString().toFloat()
                    val heightValueInch: Float = etHeightInch.text.toString().toFloat() + (heightValueFeet * 12)
                    val weightValue: Float = etWeight_lbs.text.toString().toFloat()
                    val bmi = weightValue / (heightValueInch * heightValueInch)  * 703
                    displayBMIResult(bmi)
                } else {
                    Toast.makeText(
                        this@BMIActivity,
                        "Please enter valid values.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    /**
     * Function is used to validate the input values for METRIC UNITS.
     */
    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (etWeight_kg.text.toString().isEmpty()) {
            isValid = false
        } else if (etHeight_cm.text.toString().isEmpty()) {
            isValid = false
        }

        return isValid
    }

    private fun validateUsUnits(): Boolean {
        var isValid = true

        when {
            etWeight_lbs.text.toString().isEmpty() -> isValid = false
            etHeightInch.text.toString().isEmpty() -> isValid = false
            etUsHeightFeet.text.toString().isEmpty() -> isValid = false
        }

        return isValid
    }

    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW
        tilMetricHeight_cm.visibility = View.VISIBLE
        tilMetricWeight_kg.visibility = View.VISIBLE

        etWeight_kg.text!!.clear()
        etHeight_cm.text!!.clear()

        tilUsWeight_kg.visibility = View.GONE
        llUnitsHeight.visibility = View.GONE

        llDisplayBmiResult.visibility = View.GONE
    }

    private fun makeVisibleUsUnitsView(){
        currentVisibleView = US_UNITS_VIEW
        tilMetricHeight_cm.visibility = View.GONE
        tilMetricWeight_kg.visibility = View.GONE

        etWeight_lbs.text!!.clear()
        etHeightInch.text!!.clear()
        etUsHeightFeet .text!!.clear()

        tilUsWeight_kg.visibility = View.VISIBLE
        llUnitsHeight.visibility = View.VISIBLE

        llDisplayBmiResult.visibility = View.GONE
    }

    /**
     * Function is used to display the result of METRIC UNITS.
     */
    private fun displayBMIResult(bmi: Float) {

        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        llDisplayBmiResult.visibility = View.VISIBLE

//        tvYourBMI.visibility = View.VISIBLE
//        tvBmiValue.visibility = View.VISIBLE
//        tvBMIType.visibility = View.VISIBLE
//        tvBMIDescription.visibility = View.VISIBLE

        // This is used to round the result value to 2 decimal values after "."
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        tvBmiValue.text = bmiValue
        tvBMIType.text = bmiLabel
        tvBMIDescription.text = bmiDescription
    }

}