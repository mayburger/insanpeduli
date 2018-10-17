package com.mayburger.insanpeduli.base

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import butterknife.ButterKnife
import com.google.firebase.auth.FirebaseAuth
import com.mayburger.insanpeduli.R
import java.util.regex.Pattern

/**
 * Created by Mayburger on 10/01/18.
 */

abstract class BaseFragment : Fragment() {

    lateinit var handler: Handler
    lateinit var auth: FirebaseAuth
    lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getFragmentLayout(), container, false)
        ButterKnife.bind(this, rootView)
        handler = Handler()
        auth = FirebaseAuth.getInstance()
        return rootView
    }


    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    open fun circularReveal(currentLayout: View, layoutToBeTriggered: View, isOpen: Boolean, viewTrigger: View,root:View) {
        if (!isOpen) {
            val x = (currentLayout.x + currentLayout.width / 2).toInt()
            val y = currentLayout.bottom
            val startRadius = 0
            val endRadius = Math.hypot(root.width.toDouble(), root.height.toDouble()).toInt()
            val anim = android.view.ViewAnimationUtils.createCircularReveal(layoutToBeTriggered, x, y, startRadius.toFloat(), endRadius.toFloat())
            layoutToBeTriggered.visibility = View.VISIBLE
            viewTrigger.visibility = View.GONE
            anim.start()
        } else {
            val x = (layoutToBeTriggered.x + layoutToBeTriggered.width / 2).toInt()
            val y = layoutToBeTriggered.bottom
            val startRadius = Math.max(currentLayout.width, currentLayout.height)
            val endRadius = 0
            val anim = android.view.ViewAnimationUtils.createCircularReveal(layoutToBeTriggered, x, y, startRadius.toFloat(), endRadius.toFloat())
            anim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {
                }

                override fun onAnimationEnd(animator: Animator) {
                    layoutToBeTriggered.visibility = View.GONE
                    viewTrigger.visibility = View.VISIBLE
                }

                override fun onAnimationCancel(animator: Animator) {
                }

                override fun onAnimationRepeat(animator: Animator) {
                }
            })
            anim.start()
        }
    }


    companion object {


        fun addFragmentOnTop(activity: AppCompatActivity, fragment: Fragment, layout: Int) {
            activity.supportFragmentManager
                    .beginTransaction()
                    .add(layout, fragment)
                    .addToBackStack(null)
                    .commit()
        }

        fun replaceFragment(activity: AppCompatActivity, fragment: Fragment, layout: Int) {
            activity.supportFragmentManager
                    .beginTransaction()
                    .replace(layout, fragment)
                    .addToBackStack(null)
                    .commit()
        }

        fun addFragmentOnTopSlideUp(activity: AppCompatActivity, fragment: Fragment, layout: Int) {
            activity.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_up, R.anim.stay, R.anim.stay, R.anim.slide_out_down)
                    .add(layout, fragment, fragment.toString())
                    .addToBackStack(null)
                    .commit()
        }
    }

    abstract fun getFragmentLayout(): Int
}
