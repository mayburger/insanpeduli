package com.mayburger.insanpeduli.mvp.main.fragments.campaign.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.model.Campaign
import com.mayburger.insanpeduli.mvp.donate.CampaignDetailActivity
import com.mayburger.insanpeduli.utils.Constants
import com.mayburger.insanpeduli.utils.bold
import com.mayburger.insanpeduli.utils.extrabold
import com.mayburger.insanpeduli.utils.light
import kotlinx.android.synthetic.main.row_campaign.view.*

/**
 * Created by Mayburger on 10/14/18.
 */
class CampaignAdapter(val items: List<Campaign>, val context: Context) : RecyclerView.Adapter<HeadlineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        return HeadlineViewHolder(LayoutInflater.from(context).inflate(R.layout.row_campaign, parent, false))
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        Glide.with(context).load(items[position].picture).apply(RequestOptions().centerCrop()).transition(DrawableTransitionOptions.withCrossFade()).into(holder.image)
        with(holder.title) {
            text = items[position].title
            typeface = bold
        }
        with(holder.description) {
            text = items[position].description
            typeface = light
        }
        with(holder.donate) {
            typeface = extrabold
            setOnClickListener {
                val intent = Intent(context, CampaignDetailActivity::class.java)
                intent.putExtra(Constants.CAMPAIGN, items[position])
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
    val title = view.title
    val description = view.description
    val donate = view.donate
//    val name = view.name
//    val email = view.email
//    val cardRoot = view.cardRoot
//    val verified = view.verified
}