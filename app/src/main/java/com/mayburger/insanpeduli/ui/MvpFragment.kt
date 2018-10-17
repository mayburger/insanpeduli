package com.mayburger.football.ui

import android.os.Bundle
import com.mayburger.insanpeduli.base.BaseFragment
import com.mayburger.insanpeduli.base.BasePresenter


/**
 * Created by Mayburger on 10/01/18.
 */

abstract class MvpFragment<P : BasePresenter<*>> : BaseFragment() {

    var mvpPresenter: P? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mvpPresenter = createPresenter()
    }

    abstract fun createPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        if (mvpPresenter != null) {
            mvpPresenter!!.detachView()
        }
    }
}


