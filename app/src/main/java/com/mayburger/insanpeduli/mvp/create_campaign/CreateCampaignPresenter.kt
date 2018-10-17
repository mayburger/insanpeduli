package com.mayburger.insanpeduli.mvp.create_campaign

/*
 * Created by Rosinante24 on 24/01/18.
 */

import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mayburger.insanpeduli.base.BasePresenter
import com.mayburger.insanpeduli.model.Donation
import com.mayburger.insanpeduli.model.Users
import com.mayburger.insanpeduli.utils.Constants
import com.mayburger.insanpeduli.utils.Constants.EMAIL
import com.mayburger.insanpeduli.utils.Constants.IMAGE
import com.mayburger.insanpeduli.utils.Constants.NAME
import com.mayburger.insanpeduli.utils.Constants.PASSWORD
import com.mayburger.insanpeduli.utils.Constants.PHONE
import com.mayburger.insanpeduli.utils.Constants.USER_DONATIONS
import com.mayburger.insanpeduli.utils.Constants.VERIFIED
import java.util.*


class CreateCampaignPresenter(view: CreateCampaignView, ctx: AppCompatActivity) : BasePresenter<CreateCampaignView>() {
    init {
        attachView(view)
        initialize()
        attachContext(ctx)
    }
}
