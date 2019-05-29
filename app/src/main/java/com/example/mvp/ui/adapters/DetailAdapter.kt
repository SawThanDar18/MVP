package com.example.mvp.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvp.R
import com.example.mvp.data.Meal
import com.example.mvp.ui.viewholders.DetailViewHolder

class DetailAdapter (val context: Context) : RecyclerView.Adapter<DetailViewHolder>(){

    private var mMeal : List<Meal> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): DetailViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.detail_list, viewGroup, false)
        return DetailViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return mMeal.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder?.index(mMeal[position])
    }

    fun setNewData(meals : List<Meal>){
        mMeal = meals
        notifyDataSetChanged()
    }
}