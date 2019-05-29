package com.example.mvp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import com.example.mvp.R
import com.example.mvp.data.Meal
import com.example.mvp.mvp.presenters.LatestMealsPresenter
import com.example.mvp.mvp.views.LatestMealsView
import com.example.mvp.ui.adapters.DetailAdapter
import kotlinx.android.synthetic.main.content_main.*

class DetailActivity : AppCompatActivity(), LatestMealsView {

    private lateinit var presenter: LatestMealsPresenter
    private lateinit var adapter: DetailAdapter
    private lateinit var text_detail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        presenter = LatestMealsPresenter(this)
        presenter.startLoadingLatestMeals()

        adapter = DetailAdapter(this)
        rvMeals.adapter = adapter
        rvMeals.setHasFixedSize(true)
        rvMeals.layoutManager = GridLayoutManager(this, 1, GridLayout.VERTICAL, false)

        swipeRefresh.setOnRefreshListener {
            presenter.startLoadingLatestMeals()
        }

        val bundle : Bundle? = intent.extras
        val value1 = bundle!!.getString("id")
        val id : String = intent.getStringExtra("id")

        presenter.startLoadingDetailMeals(value1)
    }

    override fun displayMeals(meal: List<Meal>) {
       adapter.setNewData(meal)
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