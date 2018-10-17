package com.mayburger.insanpeduli.mvp.main.fragments.home

/**
* Created by Mayburger on 24/01/18.
*/


import com.mayburger.insanpeduli.base.BaseView
import com.mayburger.insanpeduli.model.Campaign
import com.mayburger.insanpeduli.model.Category

interface HomeFragmentView : BaseView {
    fun onSuccessGetHeadlineCampaign(campaigns:List<Campaign>)
    fun onSuccessGetCategories(categories:List<Category>)
}
