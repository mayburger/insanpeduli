package com.mayburger.insanpeduli.mvp.logreg.fragments.register

/*
 * Created by Rosinante24 on 24/01/18.
 */

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.base.BasePresenter
import com.mayburger.insanpeduli.utils.Constants.EMAIL
import com.mayburger.insanpeduli.utils.Constants.IMAGE
import com.mayburger.insanpeduli.utils.Constants.KTP
import com.mayburger.insanpeduli.utils.Constants.NAME
import com.mayburger.insanpeduli.utils.Constants.PASSWORD
import com.mayburger.insanpeduli.utils.Constants.PHONE
import com.mayburger.insanpeduli.utils.Constants.USERS
import com.mayburger.insanpeduli.utils.Constants.UUID
import com.mayburger.insanpeduli.utils.Constants.VERIFIED
import com.mayburger.insanpeduli.utils.regular
import com.pnikosis.materialishprogress.ProgressWheel
import org.jetbrains.anko.toast
import java.util.*


class RegisterFragmentPresenter(view: RegisterFragmentView, ctx: AppCompatActivity) : BasePresenter<RegisterFragmentView>() {
    init {
        attachView(view)
        initialize()
        attachContext(ctx)
    }

    fun onCreateAccountAuth(email: String, password: String, progress: ProgressWheel) {
        auth.fetchProvidersForEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (task.result.providers!!.size > 0) {
                    ctx?.toast(ctx?.getString(R.string.email_taken).toString())
                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    mvpView?.onSuccessAuth()
                                } else {
                                    ctx?.toast("Failed creating account, please check your internet!")
                                }
                            }

                }
            } else {
                ctx?.toast("Failed creating account, please check your internet!")
                progress.visibility = View.GONE
            }
        }
    }

    fun onCreateAccountFirestore(email: String, password: String) {
        val db = FirebaseFirestore.getInstance()
        val user = HashMap<String, Any>()
        user[EMAIL] = email
        user[PASSWORD] = password
        user[VERIFIED] = false
        user[NAME] = email.substring(0, email.indexOf("@"))
        user[UUID] = (auth.currentUser?.uid).toString()
        user[IMAGE] = ""
        user[PHONE] = ""
        user[KTP] = ""
        db.collection(USERS).document(auth.currentUser?.uid.toString())
                .set(user)
                .addOnSuccessListener({ mvpView?.onSuccessFirestore() })
                .addOnFailureListener({ ctx?.toast("failure") })
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
