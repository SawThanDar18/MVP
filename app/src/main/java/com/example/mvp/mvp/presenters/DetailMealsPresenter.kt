package com.example.mvp.mvp.presenters

import com.example.mvp.events.RestApiEvents
import com.example.mvp.mvp.models.MealsModel
import com.example.mvp.mvp.views.DetailMealsView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class DetailMealsPresenter constructor(val mView: DetailMealsView){

    fun onStart(){
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }
    }

    fun startLoadingDetailMeals(value : String) {
        mView.showLoading()
        MealsModel.getInstance().getDetailMeals(value)
    }

    fun onStop(){
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }
    @Subscribe
    fun onDetailMealsLoaded(event : RestApiEvents.DetailMealsDataLoadedEvent){
        mView.dismissLoading()
        mView.displayMeals(event.meal)
    }

    @Subscribe
    fun onError(event: RestApiEvents.ErrorInvokingAPIEvent){
        mView.dismissLoading()
        mView.showPrompt(event.message)
    }
}