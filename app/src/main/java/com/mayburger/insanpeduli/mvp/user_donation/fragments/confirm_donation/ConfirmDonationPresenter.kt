package com.mayburger.insanpeduli.mvp.user_donation.fragments.confirm_donation

/*
 * Created by Rosinante24 on 24/01/18.
 */

import android.support.v7.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.mayburger.insanpeduli.base.BasePresenter
import com.mayburger.insanpeduli.model.Donation
import com.mayburger.insanpeduli.utils.Constants
import com.mayburger.insanpeduli.utils.Constants.USER_DONATIONS
import java.util.*


class ConfirmDonationPresenter(view: ConfirmDonationView, ctx: AppCompatActivity) : BasePresenter<ConfirmDonationView>() {
    init {
        attachView(view)
        initialize()
        attachContext(ctx)
    }
}
