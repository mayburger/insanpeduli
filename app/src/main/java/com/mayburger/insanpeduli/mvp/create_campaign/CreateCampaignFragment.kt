package com.mayburger.insanpeduli.mvp.create_campaign

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.mayburger.football.ui.MvpFragment
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.model.Campaign
import com.mayburger.insanpeduli.model.Donation
import com.mayburger.insanpeduli.model.Users
import com.mayburger.insanpeduli.utils.*
import com.mayburger.insanpeduli.utils.Constants.CAMPAIGN
import kotlinx.android.synthetic.main.fragment_campaign_detail.*
import org.jetbrains.anko.support.v4.ctx


/**
 * A simple [Fragment] subclass.
 */
class CreateCampaignFragment : MvpFragment<CreateCampaignPresenter>(), OnBackPressed, CreateCampaignView {

    override fun onBackPressed() {
        activity?.finish()
    }

    override fun createPresenter(): CreateCampaignPresenter {
        return CreateCampaignPresenter(this@CreateCampaignFragment, activity as AppCompatActivity)
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_create_campaign
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}// Required empty public constructor
