package com.mayburger.insanpeduli.mvp.main.fragments.campaign

/**
* Created by Mayburger on 24/01/18.
*/


import com.mayburger.insanpeduli.base.BaseView
import com.mayburger.insanpeduli.model.Campaign

interface CampaignView : BaseView {
    fun onSuccessGetCampaign(campaigns:List<Campaign>)
}
