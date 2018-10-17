package com.mayburger.insanpeduli.mvp.user_donation

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.model.Donation
import com.mayburger.insanpeduli.mvp.user_donation.fragments.confirm_donation.ConfirmDonationFragment
import com.mayburger.insanpeduli.utils.bold
import com.mayburger.insanpeduli.utils.regular
import kotlinx.android.synthetic.main.row_user_donation.view.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.util.*

class UserDonationAdapter(val items: List<Donation>, val context: AppCompatActivity) : RecyclerView.Adapter<UserDonationViewHolder>() {
    override fun onBindViewHolder(holder: UserDonationViewHolder, position: Int) {
        with(holder.title) {
            text = items[position].title
            typeface = bold
        }
        with(holder.donated) {
            typeface = regular
        }

        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        with(holder.value) {
            typeface = bold
            text = "Rp. " + (formatRupiah.format(items.get(position).amount.toDouble())).replace("Rp", "")
        }

        if (items.get(position).is_paid) {
            holder.frame.backgroundResource = R.drawable.frame_card_blue
            holder.donated.textColor = R.color.colorPrimaryDark
            holder.cardRoot.setOnClickListener {
                context.toast("This donation has been verified")
            }
        } else {
            holder.frame.backgroundResource = R.drawable.frame_card_red
            holder.donated.textColor = R.color.colorPrimaryDarkRed
            holder.cardRoot.setOnClickListener {
                context.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_user_donation, ConfirmDonationFragment(items.get(position)))
                        .addToBackStack(null)
                        .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDonationViewHolder {
        return UserDonationViewHolder(LayoutInflater.from(context).inflate(R.layout.row_user_donation, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class UserDonationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title = view.title
    val donated = view.donated
    val frame = view.frame
    val value = view.value
    val cardRoot = view.cardRoot
}