package com.mayburger.insanpeduli.mvp.main.fragments.campaign

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mayburger.football.ui.MvpFragment
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.model.Campaign
import com.mayburger.insanpeduli.mvp.main.MainActivity
import com.mayburger.insanpeduli.mvp.main.fragments.campaign.adapter.CampaignAdapter
import com.mayburger.insanpeduli.utils.*
import kotlinx.android.synthetic.main.fragment_campaign.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx


@SuppressLint("ValidFragment")
/**
 * A simple [Fragment] subclass.
 */
class CampaignFragment @SuppressLint("ValidFragment") constructor
(var category: String) : MvpFragment<CampaignPresenter>(), OnBackPressed, CampaignView {

    override fun onSuccessGetCampaign(campaigns: List<Campaign>) {
        progress.visibility = View.GONE
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerCampaign.layoutManager = layoutManager
        recyclerCampaign.adapter = CampaignAdapter(campaigns, act as AppCompatActivity)
        recyclerCampaign.isNestedScrollingEnabled = false
    }

    override fun createPresenter(): CampaignPresenter {
        return CampaignPresenter(this@CampaignFragment, activity as AppCompatActivity)
    }


    @SuppressLint("InflateParams")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(title_text) {
            typeface = bold
        }

        if (category == "") {
            mvpPresenter?.getCampaign()
            toolbar.visibility = View.GONE
        } else {
            mvpPresenter?.getCampaignWithCategory(category)
        }
    }

    override fun onBackPressed() {
        if (category != "") {
            fragmentManager!!.beginTransaction().remove(this).commit()
        }
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_campaign
    }

}// Required empty public constructor
