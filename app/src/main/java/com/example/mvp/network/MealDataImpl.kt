package com.example.mvp.network

import android.widget.TextView
import com.example.mvp.events.RestApiEvents
import com.example.mvp.ui.activities.Search_Activity
import com.example.mvp.ui.utils.AppConstant
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class MealDataImpl private constructor() : MealData{

    private var mealApi : MealApi
    lateinit var search_Activity: Search_Activity

    companion object{
        private var INSTANCE : MealDataImpl? = null
        fun getInstance() : MealDataImpl{
            if(INSTANCE == null){
                INSTANCE = MealDataImpl()
            }
            val i = INSTANCE
            return i!!
        }
    }

    init {
        val okHttpClient = OkHttpClient
            .Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .build()

        mealApi = retrofit.create(MealApi::class.java)
    }

    override fun getLatestMeals() {

        mealApi.getLatestMeals().enqueue(object : Callback<LatestMealResponse>{
            override fun onFailure(call: Call<LatestMealResponse>, t: Throwable) {
                EventBus.getDefault()
                    .post(RestApiEvents.ErrorInvokingAPIEvent(
                        t.localizedMessage
                    ))
            }

            override fun onResponse(call: Call<LatestMealResponse>, response: Response<LatestMealResponse>) {
                val latestMealResponse = response.body()
                if(latestMealResponse != null && latestMealResponse.meal.isNotEmpty()){
                    EventBus.getDefault()
                        .post(RestApiEvents.LatestMealsDataLoadedEvent(
                            latestMealResponse.meal
                        ))
                }
                else{
                    EventBus.getDefault()
                        .post(RestApiEvents.ErrorInvokingAPIEvent(
                            "No data found"
                        ))
                }
            }

        })
    }

    override fun getSearchMeals(searchValue: String) {

        mealApi.getSearchMeals(searchValue).enqueue(object : Callback<SearchMealsResponse> {
            override fun onFailure(call: Call<SearchMealsResponse>, t: Throwable) {
                EventBus.getDefault()
                    .post(
                        RestApiEvents.ErrorInvokingAPIEvent(
                            t.localizedMessage
                        )
                    )
            }

            override fun onResponse(call: Call<SearchMealsResponse>, response: Response<SearchMealsResponse>) {
                val searchMealsResponse = response.body()
                if (searchMealsResponse != null && searchMealsResponse.meal.isNotEmpty()) {
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.SearchMealsDataLoadedEvent(
                                searchMealsResponse.meal
                            )
                        )
                } else {
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.ErrorInvokingAPIEvent(
                                "No data found"
                            )
                        )
                }
            }

        })
    }

     override fun getDetailMeals(value : String) {
        mealApi.getDetailMeals(value).enqueue(object : Callback<DetailMealsResponse>{
            override fun onFailure(call: Call<DetailMealsResponse>, t: Throwable) {
                EventBus.getDefault()
                    .post(
                        RestApiEvents.ErrorInvokingAPIEvent(
                            t.localizedMessage
                        )
                    )
            }

            override fun onResponse(call: Call<DetailMealsResponse>, response: Response<DetailMealsResponse>) {
                val detailMealsResponse = response.body()
                if (detailMealsResponse != null && detailMealsResponse.meal.isNotEmpty()) {
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.DetailMealsDataLoadedEvent(
                                detailMealsResponse.meal
                            )
                        )
                } else {
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.ErrorInvokingAPIEvent(
                                "No data found"
                            )
                        )
                }
            }

        })
    }
}