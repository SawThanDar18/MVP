package com.example.mvp.mvp.views

import android.content.Context
import com.example.mvp.data.Meal

interface LatestMealsView {
    fun displayMeals(meal: List<Meal>)
    fun showPrompt(message : String)
    fun showLoading()
    fun dismissLoading()
}