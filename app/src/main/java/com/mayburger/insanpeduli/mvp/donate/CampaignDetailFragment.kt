package com.mayburger.insanpeduli.mvp.donate

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
class CampaignDetailFragment : MvpFragment<CampaignDetailPresenter>(), OnBackPressed, CampaignDetailView {
    override fun onSuccessGetUserDonations(donations: List<Donation>) {
        var amount = 0
        var size: ArrayList<Int> = ArrayList()
        for (i in 0 until donations.size) {
            if (donations[i].is_paid) {
                amount += donations[i].amount.toInt()
                size.add(donations[i].amount.toInt())
            }
        }

        donor_content.text = size.size.toString()
        donated_content.text = (amount / 1000000).toString()

    }

    var isFragmentVisible: Boolean = true

    override fun onSuccessGetUser(users: Users) {
        Glide.with(this)
                .load(users.image)
                .apply(RequestOptions.circleCropTransform()).into(profile_image)
        with(email) {
            typeface = regular
            text = users.email
        }
        with(name) {
            typeface = bold
            text = users.name
        }
        if (users.verified!!) {
            verified.visibility = View.VISIBLE
        } else {
            verified.visibility = View.GONE
        }
    }


    override fun onBackPressed() {
        if (isFragmentVisible) {
            activity?.finish()
        }
    }

    override fun createPresenter(): CampaignDetailPresenter {
        return CampaignDetailPresenter(this@CampaignDetailFragment, activity as AppCompatActivity)
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_campaign_detail
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val campaign = activity?.intent?.getSerializableExtra(CAMPAIGN) as Campaign
        with(title) {
            text = campaign.title
            typeface = bold
        }
        with(description) {
            text = campaign.description
            typeface = regular
        }

        with(target_title) { typeface = semibold }
        with(target_content) {
            typeface = black
            if (campaign.target.length > 9){
            text = (campaign.target.toLong() / 1000000000).toInt().toString()
                target_subtitle.text = "Billion Rupiah"
            } else{
                text = (campaign.target.toLong() / 1000000).toInt().toString()
            }
        }
        with(target_subtitle) { typeface = regular }
        with(donated_title) { typeface = semibold }
        with(donated_content) { typeface = black }
        with(donated_subtitle) { typeface = regular }
        with(donor_title) { typeface = semibold }
        with(donor_content) { typeface = black }
        with(donor_subtitle) { typeface = regular }


        with(donate_text) { typeface = bold }
        with(donate_card) {
            setOnClickListener {
                addFragmentOnTop(activity as AppCompatActivity, DonateFormFragment(campaign), R.id.frame_donate)
                isFragmentVisible = false
            }
        }

        if (campaign.target == "0" && campaign.uuid == Constants.OFFICIAL_UUID) {
            target_root.visibility = View.GONE
        }

        Glide.with(ctx).load(campaign.picture).apply(RequestOptions().centerCrop()).transition(DrawableTransitionOptions.withCrossFade()).into(image)
        mvpPresenter?.getUser(campaign.uuid)
        mvpPresenter?.getUserDonation(campaign.id)
    }

}// Required empty public constructor
