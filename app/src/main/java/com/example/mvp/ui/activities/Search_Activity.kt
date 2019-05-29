package com.example.mvp.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.GridLayout.VERTICAL
import android.widget.Toast
import com.example.mvp.ItemClickListener
import com.example.mvp.R
import com.example.mvp.data.Meal
import com.example.mvp.mvp.presenters.LatestMealsPresenter
import com.example.mvp.mvp.views.LatestMealsView
import com.example.mvp.network.MealDataImpl
import com.example.mvp.ui.adapters.MealsAdapter
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.search_activity.*

class Search_Activity : AppCompatActivity(), LatestMealsView, ItemClickListener {

    private lateinit var presenter: LatestMealsPresenter
    private lateinit var adapter: MealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        presenter = LatestMealsPresenter(this)
        presenter.startLoadingLatestMeals()

        adapter = MealsAdapter(this, this)
        rvMeals.adapter = adapter
        rvMeals.setHasFixedSize(true)
        rvMeals.layoutManager = GridLayoutManager(this, 2, VERTICAL, false) as RecyclerView.LayoutManager?

        swipeRefresh.setOnRefreshListener {
            presenter.startLoadingLatestMeals()
        }

        search.setOnClickListener {
            presenter.startLoadingSearchMeals()
        }

        MealDataImpl.getInstance().getDetailMeals()
    }

    override fun displayMeals(meal: List<Meal>) {
        adapter.setNewData(meal)
    }

    override fun onClicked(id: String) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
        Toast.makeText(applicationContext, id, Toast.LENGTH_SHORT).show()
    }

    override fun showPrompt(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        if(!swipeRefresh.isRefreshing){
            swipeRefresh.isRefreshing = true
        }
    }

    override fun dismissLoading() {
        if(swipeRefresh.isRefreshing){
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onStart(){
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}