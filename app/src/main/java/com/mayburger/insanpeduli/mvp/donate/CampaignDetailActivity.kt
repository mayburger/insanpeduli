package com.mayburger.insanpeduli.mvp.donate

import android.os.Bundle
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.base.BaseActivity


class CampaignDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)
        addFragmentOnTop(this, CampaignDetailFragment(), R.id.frame_donate)
    }

    override fun onBackPressed() {
        initBackPressed(this@CampaignDetailActivity)
    }
}
