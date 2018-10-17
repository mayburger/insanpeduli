package com.mayburger.insanpeduli.mvp.profile

import android.os.Bundle
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.base.BaseActivity


class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        addFragmentOnTop(this, ProfileFragment(), R.id.frame_profile)
    }

    override fun onBackPressed() {
        initBackPressed(this@ProfileActivity)
    }
}
