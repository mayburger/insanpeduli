package com.mayburger.insanpeduli.mvp.donate

/**
* Created by Mayburger on 24/01/18.
*/


import com.mayburger.insanpeduli.base.BaseView
import com.mayburger.insanpeduli.model.Donation
import com.mayburger.insanpeduli.model.Users

interface CampaignDetailView : BaseView {
    fun onSuccessGetUser(users:Users)
    fun onSuccessGetUserDonations(donations:List<Donation>)
}
