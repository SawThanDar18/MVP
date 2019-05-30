package com.example.mvp.network

import com.example.mvp.data.Meal
import com.google.gson.annotations.SerializedName

class DetailMealsResponse (@SerializedName("meals") var meal: List<Meal> = ArrayList())