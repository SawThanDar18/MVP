package com.example.mvp.data

import com.google.gson.annotations.SerializedName

class Meal {

    @SerializedName("idMeal")
    var idMeal : String? = null

    @SerializedName("strMeal")
    var strMeal : String? = null

    @SerializedName("strMealThumb")
    var strMealThumb : String? = null

    @SerializedName("strInstructions")
    val strInstructions : String? = null
}