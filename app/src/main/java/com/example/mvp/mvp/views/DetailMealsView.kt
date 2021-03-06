package com.example.mvp.mvp.views

import com.example.mvp.data.Meal

interface DetailMealsView {
    fun displayMeals(meal: List<Meal>)
    fun showPrompt(message : String)
    fun showLoading()
    fun dismissLoading()
}