package com.mayburger.insanpeduli.mvp.main.fragments.home

/*
 * Created by Rosinante24 on 24/01/18.
 */

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mayburger.insanpeduli.base.BasePresenter
import com.mayburger.insanpeduli.model.Campaign
import com.mayburger.insanpeduli.model.Category
import com.mayburger.insanpeduli.utils.Constants


class HomeFragmentPresenter(view: HomeFragmentView, ctx: AppCompatActivity) : BasePresenter<HomeFragmentView>() {
    init {
        attachView(view)
        initialize()
        attachContext(ctx)
    }

    fun getCampaign() {
        val ref = FirebaseFirestore.getInstance()
        val query = ref.collection(Constants.CAMPAIGN).whereEqualTo(Constants.UUID,Constants.OFFICIAL_UUID)
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
                    mvpView?.onSuccessGetHeadlineCampaign(list)
                }
                Log.d(ContentValues.TAG, list.toString())
            }
        }
    }

    fun getCategories() {
        val ref = FirebaseFirestore.getInstance()
        val query = ref.collection(Constants.CATEGORIES)
        val list: ArrayList<Category> = ArrayList()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    list.add(Category(
                            document.get(Constants.CATEGORY).toString(),
                            document.get(Constants.DESCRIPTION).toString(),
                            document.get(Constants.IMAGE).toString(),
                            document.get(Constants.COLOR1).toString(),
                            document.get(Constants.COLOR2).toString()
                    ))
                    mvpView?.onSuccessGetCategories(list)
                }
                Log.d(ContentValues.TAG, list.toString())
            }
        }
    }
}
