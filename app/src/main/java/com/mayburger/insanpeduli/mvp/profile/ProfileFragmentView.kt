package com.mayburger.insanpeduli.mvp.profile

/**
* Created by Mayburger on 24/01/18.
*/


import com.mayburger.insanpeduli.base.BaseView
import com.mayburger.insanpeduli.model.Users

interface ProfileFragmentView : BaseView {
    fun onSuccessGetUser(users:Users)
}
