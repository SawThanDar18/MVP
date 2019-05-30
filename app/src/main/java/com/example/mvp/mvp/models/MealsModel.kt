package com.example.mvp.mvp.models

import android.widget.TextView
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

   fun getSearchMeals(searchValue: String) {
        MealDataImpl.getInstance().getSearchMeals(searchValue)
    }

    fun getDetailMeals(value : String) {
        MealDataImpl.getInstance().getDetailMeals(value)
    }
}