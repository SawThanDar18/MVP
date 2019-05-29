package com.example.mvp.mvp.models

import com.example.mvp.network.MealDataImpl

class MealsModel{
    
    companion object{
        private var INSTANCE : MealsModel? = null
        fun getInstance() : MealsModel {
            if(INSTANCE == null){
                INSTANCE = MealsModel()
            }
            val i = INSTANCE
            return i!!
        }
    }
    
    fun getLatestMeals(){
        MealDataImpl.getInstance().getLatestMeals()
    }

   fun getSearchMeals(){
        MealDataImpl.getInstance().getSearchMeals()
    }

    fun getDetailMeals(){
        MealDataImpl.getInstance().getDetailMeals()
    }
}