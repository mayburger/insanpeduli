package com.mayburger.insanpeduli.mvp.logreg

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.base.BaseActivity
import com.mayburger.insanpeduli.mvp.logreg.fragments.LogRegFragment
import com.mayburger.insanpeduli.mvp.main.MainActivity

class LogRegActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser != null) {
            startActivity(Intent(this@LogRegActivity, MainActivity::class.java))
            finish()
        }

        addFragmentOnTop(this@LogRegActivity as AppCompatActivity, LogRegFragment(), R.id.frame_login)

    }

    override fun onBackPressed() {
        initBackPressed(this@LogRegActivity)
    }
}
