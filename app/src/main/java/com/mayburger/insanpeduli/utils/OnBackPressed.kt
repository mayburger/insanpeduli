package com.mayburger.insanpeduli.utils

import android.support.v7.app.AppCompatActivity
import com.mayburger.insanpeduli.mvp.donate.DonateFormFragment
import com.mayburger.insanpeduli.mvp.donate.CampaignDetailFragment
import com.mayburger.insanpeduli.mvp.logreg.fragments.register.RegisterFragment
import com.mayburger.insanpeduli.mvp.logreg.fragments.login.LoginFragment
import com.mayburger.insanpeduli.mvp.logreg.fragments.LogRegFragment
import com.mayburger.insanpeduli.mvp.logreg.fragments.VerifyEmailFragment
import com.mayburger.insanpeduli.mvp.main.fragments.campaign.CampaignFragment
import com.mayburger.insanpeduli.mvp.main.fragments.home.HomeFragment
import com.mayburger.insanpeduli.mvp.profile.ProfileFragment
import com.mayburger.insanpeduli.mvp.user_donation.fragments.confirm_donation.ConfirmDonationFragment
import com.mayburger.insanpeduli.mvp.user_donation.fragments.user_donation.UserDonationFragment

interface OnBackPressed {
    fun onBackPressed()
}

class OnBackPressedFun {
    fun logRegFragmentBack(appCompatActivity: AppCompatActivity) {
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is LogRegFragment)
                f.onBackPressed()
        }
    }
    fun loginFragmentBack(appCompatActivity: AppCompatActivity) {
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is LoginFragment)
                f.onBackPressed()
        }
    }
    fun registerFragmentBack(appCompatActivity: AppCompatActivity) {
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is RegisterFragment)
                f.onBackPressed()
        }
    }
    fun verifyEmailFragmentBack(appCompatActivity: AppCompatActivity) {
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is VerifyEmailFragment)
                f.onBackPressed()
        }
    }
    fun homeFragmentBack(appCompatActivity: AppCompatActivity) {
    }
    fun campaignFragmentBack(appCompatActivity: AppCompatActivity) {
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is CampaignFragment)
                f.onBackPressed()
        }
    }
    fun profileFragmentBack(appCompatActivity: AppCompatActivity) {
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is ProfileFragment)
                f.onBackPressed()
        }
    }
    fun donateFragmentBack(appCompatActivity: AppCompatActivity) {
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is CampaignDetailFragment)
                f.onBackPressed()
        }
    }
    fun donateFormFragmentBack(appCompatActivity: AppCompatActivity) {
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is DonateFormFragment)
                f.onBackPressed()
        }
    }
    fun userDonationFragmentBack(appCompatActivity: AppCompatActivity) {
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is UserDonationFragment)
                f.onBackPressed()
        }
    }
    fun userConfirmDonationFragmentBack(appCompatActivity: AppCompatActivity) {
        val fragments = appCompatActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is ConfirmDonationFragment)
                f.onBackPressed()
        }
    }
}