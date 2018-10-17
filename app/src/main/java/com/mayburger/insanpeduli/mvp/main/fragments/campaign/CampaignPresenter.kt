package com.mayburger.insanpeduli.mvp.main.fragments.campaign

/*
 * Created by Rosinante24 on 24/01/18.
 */

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mayburger.insanpeduli.base.BasePresenter
import com.mayburger.insanpeduli.model.Campaign
import com.mayburger.insanpeduli.utils.Constants


class CampaignPresenter(view: CampaignView, ctx: AppCompatActivity) : BasePresenter<CampaignView>() {

    init {
        attachView(view)
        initialize()
        attachContext(ctx)
    }

    fun getCampaign() {
        val rootRef = FirebaseFirestore.getInstance()
        val query = rootRef.collection(Constants.CAMPAIGN)
        val list: ArrayList<Campaign> = ArrayList()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    list.add(Campaign(
                            document.get(Constants.CATEGORY).toString(),
                            document.get(Constants.DESCRIPTION).toString(),
                            document.get(Constants.PHONE).toString(),
                            document.get(Constants.PICTURE).toString(),
                            document.get(Constants.TARGET).toString(),
                            document.get(Constants.TITLE).toString(),
                            document.get(Constants.ID).toString(),
                            document.get(Constants.UUID).toString()
                    ))
                    mvpView?.onSuccessGetCampaign(list)
                }
                Log.d(ContentValues.TAG, list.toString())
            }
        }
    }

    fun getCampaignWithCategory(category: String) {
        val rootRef = FirebaseFirestore.getInstance()
        val query = rootRef.collection(Constants.CAMPAIGN).whereEqualTo(Constants.CATEGORY, category)
        val list: ArrayList<Campaign> = ArrayList()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    list.add(Campaign(
                            document.get(Constants.CATEGORY).toString(),
                            document.get(Constants.DESCRIPTION).toString(),
                            document.get(Constants.PHONE).toString(),
                            document.get(Constants.PICTURE).toString(),
                            document.get(Constants.TARGET).toString(),
                            document.get(Constants.TITLE).toString(),
                            document.get(Constants.ID).toString(),
                            document.get(Constants.UUID).toString()
                    ))
                    mvpView?.onSuccessGetCampaign(list)
                }
                Log.d(ContentValues.TAG, list.toString())
            }
        }
    }

}
