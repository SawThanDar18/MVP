package com.example.mvp.ui.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mvp.ItemClickListener
import com.example.mvp.R
import com.example.mvp.data.Meal

class MealsViewHolder(itemView : View, private val mContext: Context, private val itemClickListener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {

    private val meal_image: ImageView
    private val meal_title: TextView

    init {
        meal_image = itemView.findViewById<View>(R.id.meal_image) as ImageView
        meal_title = itemView.findViewById<View>(R.id.tvName) as TextView
    }

    fun index(meal: Meal) {
        Glide.with(mContext).load(meal.strMealThumb).into(meal_image)
        meal_title.text = meal.strMeal

        itemView.setOnClickListener {
            itemClickListener.onClicked(meal.idMeal.toString())
        }

    }
}