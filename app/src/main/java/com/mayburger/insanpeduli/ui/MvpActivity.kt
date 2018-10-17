package com.mayburger.insanpeduli.ui

import android.os.Bundle
import com.mayburger.insanpeduli.base.BaseActivity
import com.mayburger.insanpeduli.base.BasePresenter


/**
 * Created by Mayburger on 10/01/18.
 */

// FOR FUTURE USE

abstract class MvpActivity<P : BasePresenter<*>> : BaseActivity() {

    protected var mvpPresenter: P? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        mvpPresenter = createPresenter()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun createPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        if (mvpPresenter != null) {
            mvpPresenter!!.detachView()
        }
    }
}
