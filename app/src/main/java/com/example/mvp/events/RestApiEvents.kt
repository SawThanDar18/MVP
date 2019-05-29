package com.example.mvp.events

import com.example.mvp.data.Meal

object RestApiEvents {
    class ErrorInvokingAPIEvent(val message : String)
    class LatestMealsDataLoadedEvent(val meal: List<Meal>)
}