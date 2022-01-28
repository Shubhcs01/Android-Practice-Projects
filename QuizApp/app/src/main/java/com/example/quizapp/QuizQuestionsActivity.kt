package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var currentPosition:Int = 1
    private var questionsList: ArrayList<Questions>? = null
    private var selectedOptPos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        questionsList = Constants.getQuestions()

        setQuestion()

       tvOption1.setOnClickListener(this)
       tvOption2.setOnClickListener(this)
       tvOption3.setOnClickListener(this)
       tvOption4.setOnClickListener(this)
       btnSubmit.setOnClickListener(this)
    }

    private fun setQuestion(){

        val question = questionsList!![currentPosition-1]

        defaultOptionsLayout()

        if(currentPosition == questionsList!!.size){
            btnSubmit.text = "FINISH"
        } else{
            btnSubmit.text = "SUBMIT"
        }

        tvQuestion.text = question!!.question
        ivImg.setImageResource(question.img)
        tvOption1.text = question.optionOne
        tvOption2.text = question.optionTwo
        tvOption3.text = question.optionThree
        tvOption4.text = question.optionFour

        progressBar.progress = currentPosition
        tvProgress.text = "$currentPosition"+"/"+progressBar.max
    }

    private fun defaultOptionsLayout(){
        val options = ArrayList<TextView>()
        options.add(0,tvOption1)
        options.add(1,tvOption2)
        options.add(2,tvOption3)
        options.add(3,tvOption4)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvOption1 -> {
                selectedOptionLayout(tvOption1, 1)
            }
            R.id.tvOption2 -> {
                selectedOptionLayout(tvOption2, 2)
            }
            R.id.tvOption3 -> {
                selectedOptionLayout(tvOption3, 3)
            }
            R.id.tvOption4 -> {
                selectedOptionLayout(tvOption4, 4)
            }
            R.id.btnSubmit -> {

                if(selectedOptPos == 0){
                    currentPosition++

                    when{
                        currentPosition <= questionsList!!.size -> {
                             setQuestion()
                        } else -> {
                                Toast.makeText(this,
                                "Success",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    val question = questionsList!![currentPosition - 1]
                    if (question!!.ans != selectedOptPos) {
                        answerLayout(selectedOptPos, R.drawable.wrong_option_border_bg)
                    }

                    answerLayout(question.ans, R.drawable.correct_option_border_bg)

                    if (currentPosition == questionsList!!.size) {
                        btnSubmit.text = "FINISH"
                    } else {
                        btnSubmit.text = "GO TO NEXT QUESTION"
                    }

                    selectedOptPos = 0
                }
            }
        }
    }

    private fun answerLayout(answer:Int, drawableView:Int){
        when(answer){
            1 -> {
                tvOption1.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tvOption2.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tvOption3.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                tvOption4.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }

    private fun selectedOptionLayout(tv:TextView, selectedOptNum:Int){

        defaultOptionsLayout() // Reset all buttons

        selectedOptPos = selectedOptNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

}