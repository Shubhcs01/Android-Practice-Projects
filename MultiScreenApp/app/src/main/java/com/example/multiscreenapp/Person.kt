package com.example.multiscreenapp

import java.io.Serializable

data class Person( val name: String,                    //--> data class is use to store data.
                   val age: Int,
                   val country: String
                   ): Serializable                      //--> Serializable is use for passing data in intent.