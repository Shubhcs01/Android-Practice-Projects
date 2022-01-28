   package com.example.kotlincoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay

   class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        GlobalScope.launch {
//            val networkCallAnswer = doNetworkCall()
//            val networkCallAnswer2 = doNetworkCall2()
//
//            delay(3000)  //--> suspend function (only called within a coroutine or another suspend function.)
//            Log.d("MainActivity","Coroutine says hello from thread ${Thread.currentThread().name}")
//        }

        //--> coroutines context

        //--> You can change UI in Main Dispatcher only.
        //--> IO Dispatcher is for only Networking calls.

//        GlobalScope.launch(Dispatchers.Main) {
////            Log.d("MainActivity","Starting coroutine in thread ${Thread.currentThread().name}")
////            val answer = doNetworkCall()
////            withContext(Dispatchers.Main){
////                Log.d("MainActivity","Setting text in thread ${Thread.currentThread().name}")
////                tvDummy.text = answer
////            }    //-->Change context to main to change in UI
//
//            Log.d("MainActivity","Before runBlocking")
//            runBlocking{
//                Log.d("MainActivity", "Start of runBlocking")
//                delay(5000L)
//                Log.d("MainActivity","End of RunBlocking")
//            }                  //--> runBlocking is use to use suspend function without coroutine.
//            Log.d("MainActivity","After RunBlocking")
//        }

             //--> Jobs, waiting, cancellation
            //--> Async and awaits
           /**--> Coroutines with Firebase Firestore */







    }

       suspend fun doNetworkCall():String {
           delay(3000L)
           return "This is a answer"
       }


//       suspend fun doNetworkCall2():String {
//           delay(3000L)
//           return "This is a answer"
//       }
}