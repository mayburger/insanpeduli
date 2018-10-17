package com.mayburger.insanpeduli.mvp.user_donation

import android.os.Bundle
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.base.BaseActivity
import com.mayburger.insanpeduli.mvp.user_donation.fragments.user_donation.UserDonationFragment


class UserDonationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_donation)
        addFragmentOnTop(this, UserDonationFragment(), R.id.frame_user_donation)
    }

    override fun onBackPressed() {
        initBackPressed(this@UserDonationActivity)
    }
}
