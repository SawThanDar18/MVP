package com.example.mvp.mvp.presenters

import com.example.mvp.events.RestApiEvents
import com.example.mvp.mvp.models.MealsModel
import com.example.mvp.mvp.views.LatestMealsView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class LatestMealsPresenter constructor(val mView : LatestMealsView){
    fun onStart(){
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }
    }

    fun startLoadingLatestMeals(){
        mView.showLoading()
        MealsModel.getInstance().getLatestMeals()
    }

    fun startLoadingSearchMeals(searchValue: String) {
        mView.showLoading()
        MealsModel.getInstance().getSearchMeals(searchValue)
    }

    fun startLoadingDetailMeals(value1 : String) {
        //mView.showLoading()
        MealsModel.getInstance().getDetailMeals(value1)
    }

    fun onStop(){
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onLatestMealsLoaded(event : RestApiEvents.LatestMealsDataLoadedEvent){
        mView.dismissLoading()
        mView.displayMeals(event.meal)
    }

    @Subscribe
    fun onError(event: RestApiEvents.ErrorInvokingAPIEvent){
        mView.dismissLoading()
        mView.showPrompt(event.message)
    }
}