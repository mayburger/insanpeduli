package com.mayburger.insanpeduli.mvp.main.fragments.home.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.mayburger.insanpeduli.model.Campaign
import com.mayburger.insanpeduli.utils.bold
import kotlinx.android.synthetic.main.row_donation_headline.view.*
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.mvp.donate.CampaignDetailActivity
import com.mayburger.insanpeduli.utils.Constants.CAMPAIGN


class HeadlineAdapter(val items: List<Campaign>, val context: Context) : RecyclerView.Adapter<HeadlineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        return HeadlineViewHolder(LayoutInflater.from(context).inflate(R.layout.row_donation_headline, parent, false))
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        Glide.with(context).load(items[position].picture).apply(RequestOptions().centerCrop()).transition(DrawableTransitionOptions.withCrossFade()).into(holder.image)

        with(holder.title) {
            text = items[position].title
            typeface = bold
        }
        with(holder.donate) {
            typeface = bold
        }
        with(holder.cardDonateNow){
            setOnClickListener {
                val intent = Intent(context, CampaignDetailActivity::class.java)
                intent.putExtra(CAMPAIGN, items[position])
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class HeadlineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image = view.image
    var cardDonateNow = view.cardDonateNow
    val title = view.title
    val donate = view.donate
}