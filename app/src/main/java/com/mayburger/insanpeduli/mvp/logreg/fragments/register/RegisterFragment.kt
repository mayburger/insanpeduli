package com.mayburger.insanpeduli.mvp.logreg.fragments.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.mayburger.football.ui.MvpFragment
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.mvp.logreg.fragments.LogRegFragment
import com.mayburger.insanpeduli.mvp.logreg.fragments.VerifyEmailFragment
import com.mayburger.insanpeduli.utils.OnBackPressed
import com.mayburger.insanpeduli.utils.regular
import com.mayburger.insanpeduli.utils.bold
import kotlinx.android.synthetic.main.fragment_login_register.*
import kotlinx.android.synthetic.main.fragment_register.*


/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : MvpFragment<RegisterFragmentPresenter>(), OnBackPressed, RegisterFragmentView {


    override fun createPresenter(): RegisterFragmentPresenter {
        return RegisterFragmentPresenter(this@RegisterFragment, activity as AppCompatActivity)
    }

    @SuppressLint("InflateParams")
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
        with(register_text){
            typeface = regular
        }

        register_card.setOnClickListener {
            if (email_input.getText() == "") {
                mvpPresenter!!.onInputError(R.string.not_empty, email_input)
            } else if (password_input.getText() == "") {
                mvpPresenter!!.onInputError(R.string.not_empty, password_input)
            } else if (confirm_password_input.getText() == "") {
                mvpPresenter!!.onInputError(R.string.not_empty, confirm_password_input)
            } else if (password_input.getText() != confirm_password_input.getText()) {
                mvpPresenter!!.onInputError(R.string.password_not_match, password_input)
            } else if (password_input.getText().length < 6) {
                mvpPresenter!!.onInputError(R.string.password_weak, password_input)
            } else {
                if (isEmailValid(email_input.getText())) {
                    progress.visibility = View.VISIBLE
                    mvpPresenter?.onCreateAccountAuth(email_input.getText(), confirm_password_input.getText(), progress)
                } else {
                    mvpPresenter!!.onInputError(R.string.not_valid_email, email_input)
                }
            }
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
        return R.layout.fragment_register
    }

    override fun onSuccessAuth() {
        mvpPresenter?.onCreateAccountFirestore(email_input.getText(), confirm_password_input.getText())
    }

    override fun onSuccessFirestore() {
        progress.visibility = View.GONE
        sendVerificationEmail()
        fragmentManager!!.beginTransaction().remove(this).commit()
    }

    private fun sendVerificationEmail() {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        user?.sendEmailVerification()
                ?.addOnCompleteListener({ task ->
                    if (task.isSuccessful) {
                        addFragmentOnTop(activity as AppCompatActivity, VerifyEmailFragment(), R.id.frame_login)
                        auth.signOut()
                    }
                })
    }

}// Required empty public constructor
