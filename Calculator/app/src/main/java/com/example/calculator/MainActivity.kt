package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private var lastNumeric = false
    private var lastDecimal = false

    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        tvInput.text = ""
         lastNumeric = false
          lastDecimal = false
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDecimal){
            tvInput.append(".")
            lastDecimal = true
            lastNumeric = false
        }
    }

    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDecimal = false
        }
    }

    private fun isOperatorAdded(str: String): Boolean{

        if(str.startsWith("-")){
            return false
        }
        else {
            if (str.contains("+") || str.contains("-") || str.contains("/") || str.contains("x")) {
                return true
            }
        }
        return false
    }

    fun onSubstraction(view: View){
        if(lastNumeric) {
            var prefix = ""
            var tvValue = tvInput.text

            try {
                if (tvValue.startsWith("-")) {

                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {

                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("+")) {

                        val splitValue = tvValue.split("+")
                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (!prefix.isEmpty()) {
                            one = prefix + one
                        }

                        tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    } else if (tvValue.contains("x")) {

                            val splitValue = tvValue.split("x")
                            var one = splitValue[0]
                            var two = splitValue[1]

                            if (!prefix.isEmpty()) {
                                one = prefix + one
                            }

                            tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                        } else if (tvValue.contains("/")) {

                                val splitValue = tvValue.split("/")
                                var one = splitValue[0]
                                var two = splitValue[1]

                                if (!prefix.isEmpty()) {
                                    one = prefix + one
                                }

                                tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                            }
            } catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(str: String): String{
        var value = str
        if(value.contains(".0")){
            value = str.substring(0, value.length-2)
        }
        return value
    }
}