package com.mayburger.insanpeduli.mvp.user_donation.fragments.user_donation

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


class UserDonationPresenter(view: UserDonationView, ctx: AppCompatActivity) : BasePresenter<UserDonationView>() {
    init {
        attachView(view)
        initialize()
        attachContext(ctx)
    }

    fun getUserDonation(id: String) {
        val rootRef = FirebaseFirestore.getInstance()
        val iquery = rootRef.collection(USER_DONATIONS).whereEqualTo(Constants.UUID, id)
        val ilist: ArrayList<Donation> = ArrayList()
        iquery.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    ilist.add(Donation(
                            document.get(Constants.AMOUNT).toString(),
                            document.get(Constants.ID).toString(),
                            document.get(Constants.CAMPAIGN_ID).toString(),
                            document.get(Constants.UUID).toString(),
                            document.getBoolean(Constants.IS_PAID),
                            document.get(Constants.TRANSFER_PROOF).toString(),
                            document.get(Constants.TITLE).toString()
                    ))
                    mvpView?.onSuccessGetUserDonations(ilist)
                }
            }
        }
    }
}
