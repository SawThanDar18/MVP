package com.example.mvp.ui.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mvp.R
import com.example.mvp.data.Meal

class DetailViewHolder(itemView : View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val meal_image: ImageView
    private val meal_title: TextView
    private val meal_instruction : TextView

    init {
        meal_image = itemView.findViewById<View>(R.id.detail_image) as ImageView
        meal_title = itemView.findViewById<View>(R.id.detail_name) as TextView
        meal_instruction = itemView.findViewById<View>(R.id.detail_text) as TextView
    }

    fun index(meal: Meal) {
        Glide.with(mContext).load(meal.strMealThumb).into(meal_image)
        meal_title.text = meal.strMeal
        meal_instruction.text = meal.strInstructions
    }
}