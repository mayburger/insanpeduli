package com.mayburger.insanpeduli.mvp.logreg.fragments.register

/**
* Created by Mayburger on 24/01/18.
*/


import com.mayburger.insanpeduli.base.BaseView

interface RegisterFragmentView : BaseView {
    fun onSuccessAuth()
    fun onSuccessFirestore()
}
