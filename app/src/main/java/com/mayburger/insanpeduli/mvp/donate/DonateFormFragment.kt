package com.mayburger.insanpeduli.mvp.donate

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.base.BaseFragment
import com.mayburger.insanpeduli.model.Campaign
import com.mayburger.insanpeduli.utils.*
import com.mayburger.insanpeduli.utils.Constants.USER_DONATIONS
import kotlinx.android.synthetic.main.fragment_donate_form.*
import kotlinx.android.synthetic.main.fragment_donate_form_success.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.toast
import java.util.*


@SuppressLint("ValidFragment")
/**
 * A simple [Fragment] subclass.
 */
class DonateFormFragment @SuppressLint("ValidFragment") constructor
(var campaign: Campaign) : BaseFragment(), OnBackPressed {


    override fun onBackPressed() {
        val appCompatActivity = activity as AppCompatActivity
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is CampaignDetailFragment)
                f.isFragmentVisible = true
        }
        fragmentManager!!.beginTransaction().remove(this).commit()
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_donate_form
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(title) {
            typeface = bold
        }
        with(success_title) {
            typeface = bold
        }
        with(success_subtitle) {
            typeface = regular
        }

        done_card.setOnClickListener {

            if (amount_input.getText() == "") {
                onInputError(R.string.not_empty, amount_input)
            } else {
                initDonate()
            }

        }
    }

    fun onInputError(string: Int, input: View) {
        val errorPop: PopupWindow?
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.error_window, null)
        errorPop = PopupWindow(
                v,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        errorPop.setBackgroundDrawable(BitmapDrawable())
        errorPop.isOutsideTouchable = true
        errorPop.showAsDropDown(input)
        val error = v.findViewById<TextView>(R.id.error)
        with(error) {
            text = ctx.getString(string)
            typeface = regular
        }
        handler.postDelayed({ errorPop.dismiss() }, 1500)
    }

    fun initDonate() {
        done_card.isClickable = false
        progress.visibility = View.VISIBLE
        val db = FirebaseFirestore.getInstance()
        val user = HashMap<String, Any>()
        auth = FirebaseAuth.getInstance()
        val uid: String = UUID.randomUUID().toString()
        user[Constants.AMOUNT] = amount_input.getText()
        user[Constants.CAMPAIGN_ID] = campaign.id
        user[Constants.ID] = uid
        user[Constants.UUID] = FirebaseAuth.getInstance().currentUser?.uid.toString()
        user[Constants.IS_PAID] = false
        user[Constants.TRANSFER_PROOF] = ""
        user[Constants.TITLE] = campaign.title
        db.collection(USER_DONATIONS).document(uid)
                .set(user)
                .addOnSuccessListener({
                    progress.visibility = View.GONE
                    success.visibility = View.VISIBLE
                    with(value) {
                        typeface = bold
                        text = "Rp. " + (amount_input.getText().toDouble().toString().replace("Rp", ""))
                    }
                    with(campaignTitle) {
                        text = campaign.title
                    }
                    with(finish_card) {
                        setOnClickListener {
                            val appCompatActivity = activity as AppCompatActivity
                            val fragments = appCompatActivity.supportFragmentManager.fragments
                            for (f in fragments) {
                                if (f != null && f is CampaignDetailFragment)
                                    f.isFragmentVisible = true
                            }
                            fragmentManager!!.beginTransaction().remove(this@DonateFormFragment).commit()
                        }
                    }

                })
                .addOnFailureListener({ ctx?.toast("failure") })

    }


}// Required empty public constructor
