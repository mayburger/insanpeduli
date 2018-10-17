package com.mayburger.insanpeduli.base

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.mayburger.insanpeduli.R
import org.jetbrains.anko.textColor


/**
 * Created by Mayburger on 5/19/18.
 */

open class BaseLinearLayout : LinearLayout {

    fun initEditText(edit: EditText, div: View, title: TextView, card:CardView) {
        edit.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                title.textColor = resources.getColor(R.color.colorPrimaryDark)
                div.visibility = View.VISIBLE
                card.setCardBackgroundColor(resources.getColor(R.color.colorWhite))
            } else {
                title.textColor = resources.getColor(R.color.colorGreyIndicatorDark)
                div.visibility = View.GONE
                card.setCardBackgroundColor(resources.getColor(R.color.colorInputBackground))
            }
        }
    }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}
}
