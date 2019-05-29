package com.example.mvp.network

import com.example.mvp.data.Meal
import com.google.gson.annotations.SerializedName

class LatestMealResponse (@SerializedName("meals") var meal: List<Meal> = ArrayList())