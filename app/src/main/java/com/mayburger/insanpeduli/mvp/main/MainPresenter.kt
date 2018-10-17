package com.mayburger.insanpeduli.mvp.main

/*
 * Created by Rosinante24 on 24/01/18.
 */

import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mayburger.insanpeduli.base.BasePresenter
import com.mayburger.insanpeduli.model.Users
import com.mayburger.insanpeduli.utils.Constants
import com.mayburger.insanpeduli.utils.Constants.EMAIL
import com.mayburger.insanpeduli.utils.Constants.IMAGE
import com.mayburger.insanpeduli.utils.Constants.NAME
import com.mayburger.insanpeduli.utils.Constants.PASSWORD
import com.mayburger.insanpeduli.utils.Constants.PHONE
import com.mayburger.insanpeduli.utils.Constants.VERIFIED


class MainPresenter(view: MainView, ctx: AppCompatActivity) : BasePresenter<MainView>() {
    init {
        attachView(view)
        initialize()
        attachContext(ctx)
    }

    fun getUser(){
        val db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val user = db.collection(Constants.USERS).document(auth.currentUser?.uid.toString())
        user.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val doc = task.result

                mvpView?.onSuccessGetUser(Users(doc.get(EMAIL).toString(),doc.get(PASSWORD).toString(),doc.getBoolean(VERIFIED),doc.get(NAME).toString(),doc.get(IMAGE).toString(),doc.get(PHONE).toString()))
            }
        }.addOnFailureListener({ })
    }

}
