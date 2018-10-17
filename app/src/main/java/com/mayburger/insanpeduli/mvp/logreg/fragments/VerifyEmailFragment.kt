package com.mayburger.insanpeduli.mvp.logreg.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.base.BaseFragment
import com.mayburger.insanpeduli.utils.OnBackPressed
import com.mayburger.insanpeduli.utils.regular
import com.mayburger.insanpeduli.utils.bold
import kotlinx.android.synthetic.main.fragment_login_register.*
import kotlinx.android.synthetic.main.fragment_verify_email.*


/**
 * A simple [Fragment] subclass.
 */
class VerifyEmailFragment : BaseFragment(), OnBackPressed {


    @SuppressLint("InflateParams")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(title) {
            typeface = bold
        }
        with(subtitle) {
            typeface = regular
        }
        with(done_card) {
            setOnClickListener {
                val fragments = fragmentManager!!.fragments
                for (f in fragments) {
                    if (f != null && f is LogRegFragment) {
                        f.isFragmentVisible = true
                        f.login_root.visibility = View.VISIBLE
                    }
                }
                fragmentManager!!.beginTransaction().remove(this@VerifyEmailFragment).commit()
            }
        }
        with(done_text) {
            typeface = regular
        }
    }

    override fun onBackPressed() {
        val fragments = fragmentManager!!.fragments
        for (f in fragments) {
            if (f != null && f is LogRegFragment) {
                f.isFragmentVisible = true
                f.login_root.visibility = View.VISIBLE
            }
        }
        fragmentManager!!.beginTransaction().remove(this).commit()
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_verify_email
    }

}// Required empty public constructor
