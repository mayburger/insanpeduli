package com.mayburger.insanpeduli.mvp.create_campaign

import android.os.Bundle
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.base.BaseActivity

class CreateCampaignActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_campaign)
        addFragmentOnTop(this, CreateCampaignFragment(), R.id.frame_create_campaign)
    }

    override fun onBackPressed() {
        initBackPressed(this@CreateCampaignActivity)
    }
}
