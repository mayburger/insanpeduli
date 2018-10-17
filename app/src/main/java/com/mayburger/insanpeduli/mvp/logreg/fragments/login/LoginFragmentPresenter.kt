package com.mayburger.insanpeduli.mvp.logreg.fragments.login

/*
 * Created by Rosinante24 on 24/01/18.
 */

import android.app.Activity
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.base.BasePresenter
import com.google.firebase.auth.FirebaseAuth
import com.mayburger.insanpeduli.utils.regular
import com.pnikosis.materialishprogress.ProgressWheel
import org.jetbrains.anko.toast


class LoginFragmentPresenter(view: LoginFragmentView, ctx: AppCompatActivity) : BasePresenter<LoginFragmentView>() {
    init {
        attachView(view)
        initialize()
        attachContext(ctx)
    }

    fun onStartLogin(email: String, password: String, progress: ProgressWheel){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ctx as Activity) { task ->
                    if (task.isSuccessful) {
                        isUserVerified(progress)
                    } else {
                        progress.visibility = View.GONE
                        ctx?.toast("Login Failed: Please check your username or password")
                    }
                }
    }


    private fun isUserVerified(progress:ProgressWheel) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user!!.isEmailVerified) {
            mvpView?.onSuccessLogin()
        } else {
            progress.visibility = View.GONE
            ctx?.toast("Email is not verified yet")
            FirebaseAuth.getInstance().signOut()
        }
    }

    fun onInputError(string: Int, input: View) {
        val errorPop: PopupWindow?
        val inflater = ctx?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.error_window, null)
        errorPop = PopupWindow(
                v,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        errorPop.setBackgroundDrawable(BitmapDrawable())
        errorPop.isOutsideTouchable = true
        errorPop.showAsDropDown(input)
        val error = v.findViewById<TextView>(R.id.error)
        with(error) {
            text = ctx?.getString(string)
            typeface = regular
        }
        handler?.postDelayed({ errorPop.dismiss() }, 1500)
    }

}
