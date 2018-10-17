package com.mayburger.insanpeduli.mvp.main.fragments.home.adapter

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.base.BaseActivity
import com.mayburger.insanpeduli.model.Category
import com.mayburger.insanpeduli.mvp.main.fragments.campaign.CampaignFragment
import com.mayburger.insanpeduli.utils.regular
import kotlinx.android.synthetic.main.row_categories.view.*
import org.jetbrains.anko.backgroundDrawable


class CategoriesAdapter(val items: List<Category>, val context: AppCompatActivity) : RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(LayoutInflater.from(context).inflate(R.layout.row_categories, parent, false))
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        with(holder.root){ backgroundDrawable = getGradientDrawable(items[position]) }
        Glide.with(context).load(items[position].image).apply(RequestOptions().centerCrop()).transition(DrawableTransitionOptions.withCrossFade()).into(holder.image)
        with(holder.category) {
            text = items[position].category
            typeface = regular
        }
        holder.card.setOnClickListener {
            BaseActivity.addFragmentOnTop(context, CampaignFragment(items[position].category.toLowerCase()), R.id.frame_main)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getGradientDrawable(item: Category): Drawable {
        val colors = intArrayOf(Color.parseColor(item.color1), Color.parseColor(item.color2))
        val gd = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, colors)
        gd.cornerRadius = 0f
        return gd
    }
}

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image = view.image
    val root = view.root
    val category = view.category
    val card = view.card
}