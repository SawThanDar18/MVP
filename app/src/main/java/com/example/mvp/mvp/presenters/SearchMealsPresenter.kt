package com.example.mvp.mvp.presenters

import com.example.mvp.events.RestApiEvents
import com.example.mvp.mvp.models.MealsModel
import com.example.mvp.mvp.views.SearchMealsView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class SearchMealsPresenter constructor(val mView : SearchMealsView) {

    fun onStart(){
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }
    }

    fun startLoadingSearchMeals(searchValue: String) {
        mView.showLoading()
        MealsModel.getInstance().getSearchMeals(searchValue)
    }

    fun onStop(){
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onSearchMealsLoaded(event : RestApiEvents.SearchMealsDataLoadedEvent){
        mView.dismissLoading()
        mView.displayMeals(event.meal)
    }

    @Subscribe
    fun onError(event: RestApiEvents.ErrorInvokingAPIEvent){
        mView.dismissLoading()
        mView.showPrompt(event.message)
    }
}