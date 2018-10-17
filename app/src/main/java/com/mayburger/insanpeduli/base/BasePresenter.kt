package com.mayburger.insanpeduli.base

import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mayburger.insanpeduli.model.Users
import com.mayburger.insanpeduli.utils.Constants

/**
 * Created by Alvin on 02/02/2018.
 */

open class BasePresenter<V> {

    protected var mvpView: V? = null
    var databaseReference: DatabaseReference? = null
    lateinit var auth: FirebaseAuth
    var ctx: AppCompatActivity? = null
    var handler: Handler? = null

    protected fun attachView(mvpView: V) {
        this.mvpView = mvpView
    }

    protected fun attachContext(ctx: AppCompatActivity) {
        this.ctx = ctx
    }

    protected fun initialize() {
        auth = FirebaseAuth.getInstance()
        handler = Handler()
    }

    fun detachView() {
        this.mvpView = null
        //        onUnsubscribe();
    }

    //    private void onUnsubscribe() {
    //        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
    //            compositeSubscription.unsubscribe();
    //        }
    //    }
    //
    //
    //    public void addSubscription(Observable observable, Subscriber subscriber) {
    //        if (compositeSubscription == null) {
    //            compositeSubscription = new CompositeSubscription();
    //        }
    //        compositeSubscription.add(observable
    //                .subscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread())
    //                .subscribe(subscriber));
    //    }
}