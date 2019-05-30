package com.example.mvp.network

interface MealData {

    fun getLatestMeals()
    fun getSearchMeals(searchValue: String)
    fun getDetailMeals(value : String)
}