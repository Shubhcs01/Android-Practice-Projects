package com.example.happyplaces.models

import java.io.Serializable

data class HappyPlaceModel(
    val id: Int,
    val title: String,
    var image: String,
    val date: String,
    val description: String,
    val location: String,
    val latitude: Double,
    val longitude: Double
) : Serializable
