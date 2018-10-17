package com.mayburger.insanpeduli.mvp.user_donation.fragments.user_donation

/**
 * Created by Mayburger on 24/01/18.
 */


import com.mayburger.insanpeduli.base.BaseView
import com.mayburger.insanpeduli.model.Campaign
import com.mayburger.insanpeduli.model.Donation
import com.mayburger.insanpeduli.model.Users

interface UserDonationView : BaseView {
    fun onSuccessGetUserDonations(donations: List<Donation>)
}
