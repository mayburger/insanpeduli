package com.mayburger.insanpeduli.mvp.logreg.fragments.login

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mayburger.football.ui.MvpFragment
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.mvp.logreg.fragments.LogRegFragment
import com.mayburger.insanpeduli.mvp.main.MainActivity
import com.mayburger.insanpeduli.utils.OnBackPressed
import com.mayburger.insanpeduli.utils.regular
import com.mayburger.insanpeduli.utils.bold
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login_register.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : MvpFragment<LoginFragmentPresenter>(), OnBackPressed, LoginFragmentView {

    override fun createPresenter(): LoginFragmentPresenter {
        return LoginFragmentPresenter(this@LoginFragment, activity as AppCompatActivity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(back_text) {
            typeface = regular
        }
        with(title) {
            typeface = bold
        }
        with(subtitle) {
            typeface = regular
        }
        with(login_text) {
            typeface = regular
        }

        back.setOnClickListener {
            onBack()
        }

        login_card.setOnClickListener {
            if (email_input.getText() == "") {
                mvpPresenter!!.onInputError(R.string.not_empty, email_input)
            } else if (password_input.getText() == "") {
                mvpPresenter!!.onInputError(R.string.not_empty, password_input)
            } else {
                if (isEmailValid(email_input.getText())) {
                    mvpPresenter!!.onStartLogin(email_input.getText(), password_input.getText(), progress)
                    progress.visibility = View.VISIBLE
                } else {
                    mvpPresenter!!.onInputError(R.string.not_valid_email, email_input)
                }
            }
        }
    }

    override fun onBackPressed() {
        onBack()
    }

    private fun onBack() {
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
        return R.layout.fragment_login
    }

    override fun onSuccessLogin() {
        progress.visibility = View.GONE
        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finish()
    }


}// Required empty public constructor
