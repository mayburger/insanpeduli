package com.mayburger.insanpeduli.mvp.donate

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


class CampaignDetailPresenter(view: CampaignDetailView, ctx: AppCompatActivity) : BasePresenter<CampaignDetailView>() {
    init {
        attachView(view)
        initialize()
        attachContext(ctx)
    }

    fun getUser(uid: String) {
        val db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val user = db.collection(Constants.USERS).document(uid)
        user.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val doc = task.result
                mvpView?.onSuccessGetUser(Users(doc.get(EMAIL).toString(), doc.get(PASSWORD).toString(), doc.getBoolean(VERIFIED), doc.get(NAME).toString(), doc.get(IMAGE).toString(), doc.get(PHONE).toString()))
            }
        }.addOnFailureListener({ })
    }

    fun getUserDonation(id: String) {
        val rootRef = FirebaseFirestore.getInstance()
        val iquery = rootRef.collection(USER_DONATIONS).whereEqualTo(Constants.CAMPAIGN_ID, id)
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
