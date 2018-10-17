package com.mayburger.insanpeduli.mvp.user_donation.fragments.user_donation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.mayburger.football.ui.MvpFragment
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.model.Donation
import com.mayburger.insanpeduli.mvp.user_donation.UserDonationAdapter
import com.mayburger.insanpeduli.utils.OnBackPressed
import com.mayburger.insanpeduli.utils.black
import kotlinx.android.synthetic.main.fragment_user_donation.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast


/**
 * A simple [Fragment] subclass.
 */
class UserDonationFragment : MvpFragment<UserDonationPresenter>(), OnBackPressed, UserDonationView {

    override fun onSuccessGetUserDonations(donations: List<Donation>) {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerUserDonation.layoutManager = layoutManager
        recyclerUserDonation.adapter = UserDonationAdapter(donations, activity as AppCompatActivity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(title_text){
            typeface = black
            text = getString(R.string.my_donations)
        }
        mvpPresenter?.getUserDonation(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    override fun onBackPressed() {
        activity?.finish()
    }

    override fun createPresenter(): UserDonationPresenter {
        return UserDonationPresenter(this@UserDonationFragment, activity as AppCompatActivity)
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_user_donation
    }
}// Required empty public constructor
