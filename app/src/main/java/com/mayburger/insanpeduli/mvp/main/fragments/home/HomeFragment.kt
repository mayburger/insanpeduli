package com.mayburger.insanpeduli.mvp.main.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mayburger.football.ui.MvpFragment
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.model.Campaign
import com.mayburger.insanpeduli.model.Category
import com.mayburger.insanpeduli.mvp.main.fragments.home.adapter.HeadlineAdapter
import com.mayburger.insanpeduli.mvp.main.fragments.home.adapter.CategoriesAdapter
import com.mayburger.insanpeduli.utils.bold
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : MvpFragment<HomeFragmentPresenter>(), HomeFragmentView {

    @SuppressLint("InflateParams")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initPresenter()
        initFonts()
    }

    fun initFonts() {
        with(tv_featured) { typeface = bold }
        with(tv_donations) { typeface = bold }
        with(create_campaign_text) { typeface = bold }
    }

    fun initPresenter() {
        mvpPresenter?.getCampaign()
        mvpPresenter?.getCategories()
    }

    override fun onSuccessGetHeadlineCampaign(campaigns: List<Campaign>) {
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        with(recyclerHome) {
            layoutManager = linearLayoutManager
            adapter = HeadlineAdapter(campaigns, ctx)
        }
        progress_featured.visibility = View.GONE
    }

    override fun onSuccessGetCategories(categories: List<Category>) {
        val linearLayoutManager = GridLayoutManager(activity, 2)
        with(recyclerCategory) {
            layoutManager = linearLayoutManager
            adapter = CategoriesAdapter(categories, act as AppCompatActivity)
            isNestedScrollingEnabled = false
        }
        progress_category.visibility = View.GONE
    }

    override fun createPresenter(): HomeFragmentPresenter {
        return HomeFragmentPresenter(this@HomeFragment, activity as AppCompatActivity)
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_home
    }

}// Required empty public constructor
