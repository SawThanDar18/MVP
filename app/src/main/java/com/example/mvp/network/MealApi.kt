package com.example.mvp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("api/json/v1/1/latest.php")
    fun getLatestMeals(): Call<LatestMealResponse>

    @GET("api/json/v1/1/search.php/")
    fun getSearchMeals(@Query("s") keyword : String) : Call<SearchMealsResponse>

    @GET("api/json/v1/1/lookup.php/")
    fun getDetailMeals(@Query("i") keyword: String) : Call<DetailMealsResponse>
}