package com.mayburger.insanpeduli

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.mayburger.insanpeduli.base.BaseLinearLayout
import com.mayburger.insanpeduli.utils.bold
import com.mayburger.insanpeduli.utils.regular

import kotlinx.android.synthetic.main.login_input_layout.view.*

class LoginInputLayout : BaseLinearLayout {

    internal var text: String? = null

    // donute-input-layout-drawable
    internal var ldrawable: Drawable? = null
    // donute-input-layout-title
    internal var ltitle: String? = null
    // donute-input-layout-input-type
    internal var dilInputType: Int? = null
    // donute-input-layout-hint
    internal var dilHint: String? = null
    // donute-input-layout-hint-color
    internal var dilHintColor: Int? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    fun getText(): String {
        return edit!!.text.toString()
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.login_input_layout, this)

        with(edit){
            typeface = bold
        }
        with(title){
            typeface = regular
        }

        edit!!.setHintTextColor(resources.getColor(R.color.colorTextHint))

        val a = getContext().obtainStyledAttributes(attrs, R.styleable.LoginInputLayout)

        val n = a.indexCount
        for (i in 0 until n) {
            val attr = a.getIndex(i)
            when (attr) {
                R.styleable.LoginInputLayout_dil_drawable -> {
                    ldrawable = a.getDrawable(attr)
                    if (ldrawable != null) {
                        icon!!.setImageDrawable(ldrawable)
                        icon!!.visibility = View.VISIBLE
                    } else{
                        icon!!.visibility = View.GONE
                    }
                }
                R.styleable.LoginInputLayout_dil_title -> {
                    ltitle = a.getString(attr)
                    if (ltitle != null) {
                        title!!.text = ltitle
                        title!!.visibility = View.VISIBLE
                    }
                }
                R.styleable.LoginInputLayout_android_inputType -> {
                    dilInputType = a.getInteger(attr, 0)
                    edit!!.inputType = dilInputType!!
                }
                R.styleable.LoginInputLayout_android_hint -> {
                    dilHint = a.getString(attr)
                    edit!!.hint = dilHint
                }
                R.styleable.LoginInputLayout_android_text -> edit!!.setText(a.getString(attr))
                else -> Log.d("TAG", "Unknown attribute for " + javaClass.toString() + ": " + attr)
            }
        }
        a.recycle()
        initEditText(edit!!, div!!, title!!,card_background!!)
    }
}