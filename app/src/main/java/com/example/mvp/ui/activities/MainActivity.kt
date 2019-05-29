package com.example.mvp.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.GridLayout.VERTICAL
import android.widget.Toast
import com.example.mvp.ItemClickListener
import com.example.mvp.R
import com.example.mvp.data.Meal
import com.example.mvp.mvp.presenters.LatestMealsPresenter
import com.example.mvp.mvp.views.LatestMealsView
import com.example.mvp.network.MealDataImpl
import com.example.mvp.ui.adapters.MealsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), LatestMealsView, ItemClickListener {

    private lateinit var presenter: LatestMealsPresenter
    private lateinit var adapter: MealsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent(this, Search_Activity::class.java)
            startActivity(intent)
        }

        presenter = LatestMealsPresenter(this)
        presenter.startLoadingLatestMeals()

        adapter = MealsAdapter(this, this)
        rvMeals.adapter = adapter
        rvMeals.setHasFixedSize(true)
        rvMeals.layoutManager = GridLayoutManager(this, 2, VERTICAL, false)

        swipeRefresh.setOnRefreshListener {
            presenter.startLoadingLatestMeals()
        }

        MealDataImpl.getInstance().getDetailMeals()
    }

    override fun displayMeals(meal: List<Meal>) {
       adapter.setNewData(meal)
    }

    override fun onClicked(id: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", id)
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
