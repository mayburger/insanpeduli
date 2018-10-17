package com.mayburger.insanpeduli.mvp.main

/**
* Created by Mayburger on 24/01/18.
*/


import com.mayburger.insanpeduli.base.BaseView
import com.mayburger.insanpeduli.model.Users

interface MainView : BaseView {
    fun onSuccessGetUser(users:Users)
}
