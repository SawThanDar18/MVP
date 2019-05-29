package com.example.mvp.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvp.ItemClickListener
import com.example.mvp.R
import com.example.mvp.data.Meal
import com.example.mvp.ui.viewholders.MealsViewHolder

class MealsAdapter(val context: Context, private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<MealsViewHolder>(){

    private var mMeal : List<Meal> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MealsViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_item_meal, viewGroup, false)
        return MealsViewHolder(view, context, itemClickListener)
    }

    override fun getItemCount(): Int {
        return mMeal.size
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder?.index(mMeal[position])
    }

    fun setNewData(meals : List<Meal>){
        mMeal = meals
        notifyDataSetChanged()
    }
}

