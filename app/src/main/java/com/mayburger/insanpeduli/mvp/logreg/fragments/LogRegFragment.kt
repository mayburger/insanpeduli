package com.mayburger.insanpeduli.mvp.logreg.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationUtils
import com.mayburger.insanpeduli.utils.OnBackPressed
import com.mayburger.insanpeduli.utils.regular
import com.mayburger.insanpeduli.utils.bold
import com.mayburger.insanpeduli.base.BaseFragment
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.mvp.logreg.fragments.login.LoginFragment
import com.mayburger.insanpeduli.mvp.logreg.fragments.register.RegisterFragment
import kotlinx.android.synthetic.main.fragment_login_register.*
import kotlinx.android.synthetic.main.login_register_a.*
import kotlinx.android.synthetic.main.login_register_b.*


/**
 * A simple [Fragment] subclass.
 */
class LogRegFragment : BaseFragment(), OnBackPressed {

    var isFragmentVisible: Boolean = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isFragmentVisible = true
        initLayoutA()
        initLayoutB()
    }

    fun initLayoutA() {
        val slideUp = AnimationUtils.loadAnimation(activity, R.anim.slide_up)
        // Title text hello
        with(title_a) {
            typeface = bold
            startAnimation(slideUp)
        }
        // Subtitle text attendance
        with(subtitle_a) {
            typeface = regular
            startAnimation(slideUp)
        }
        // Playstation buttons decoration
        with(ornaments) {
            startAnimation(slideUp)
        }
        // Start button text
        with(start_text) {
            typeface = regular
        }
        // Start button CardView
        with(start_card) {
            startAnimation(slideUp)
            setOnClickListener {
                circularReveal(layoutContent, layoutButtons, false, start_card, login_root)
            }
        }
    }

    fun initLayoutB() {
        with(title_b) {
            typeface = bold
        }
        with(subtitle_b) {
            typeface = regular
        }
        with(find_school_text) {
            typeface = bold
        }
        with(create_school_text) {
            typeface = bold
        }
        find_school_card.setOnClickListener {
            addFragmentOnTop(activity as AppCompatActivity, LoginFragment(), R.id.frame_login)
            isFragmentVisible = false
            login_root.visibility = View.GONE
        }
        create_school_card.setOnClickListener {
            addFragmentOnTop(activity as AppCompatActivity, RegisterFragment(), R.id.frame_login)
            isFragmentVisible = false
            login_root.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (isFragmentVisible) {
            circularReveal(layoutContent, layoutButtons, true, start_card, login_root)
        }
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_login_register
    }


}// Required empty public constructor
