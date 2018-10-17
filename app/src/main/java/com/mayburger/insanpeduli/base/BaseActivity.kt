package com.mayburger.insanpeduli.base

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.google.firebase.auth.FirebaseAuth
import com.mayburger.insanpeduli.utils.OnBackPressedFun

/**
 * Created by Rosinante24 on 10/01/18.
 */

open class BaseActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
        ButterKnife.bind(this)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun setContentView(view: View) {
        super.setContentView(view)
        ButterKnife.bind(this)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        super.setContentView(view, params)
        ButterKnife.bind(this)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishAfterTransition()
        }
        return super.onOptionsItemSelected(item)
    }

    /** This method is called on the onBackPressed method inside an activity where the onBackPressed
     *  method is required inside a fragment
     *  See [com.mayburger.football.detail.DetailFragment.onBackPressed]
     **/
    fun initBackPressed(activity: AppCompatActivity) {
        OnBackPressedFun().logRegFragmentBack(activity)
        OnBackPressedFun().loginFragmentBack(activity)
        OnBackPressedFun().registerFragmentBack(activity)
        OnBackPressedFun().verifyEmailFragmentBack(activity)
        OnBackPressedFun().homeFragmentBack(activity)
        OnBackPressedFun().campaignFragmentBack(activity)
        OnBackPressedFun().profileFragmentBack(activity)
        OnBackPressedFun().donateFragmentBack(activity)
        OnBackPressedFun().donateFormFragmentBack(activity)
        OnBackPressedFun().userDonationFragmentBack(activity)
        OnBackPressedFun().userConfirmDonationFragmentBack(activity)
    }

    companion object {

        fun addFragmentOnTop(activity:AppCompatActivity,fragment: Fragment, layout: Int) {
            activity.supportFragmentManager
                    .beginTransaction()
                    .replace(layout, fragment)
                    .addToBackStack(null)
                    .commit()
        }
        fun showFragment(activity:AppCompatActivity,fragment: Fragment) {
            activity.supportFragmentManager
                    .beginTransaction()
                    .show(fragment)
                    .commit()
        }
        fun hideFragment(activity:AppCompatActivity,fragment: Fragment) {
            activity.supportFragmentManager
                    .beginTransaction()
                    .hide(fragment)
                    .commit()
        }
    }
}
