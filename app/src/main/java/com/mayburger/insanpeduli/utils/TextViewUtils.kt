package com.mayburger.insanpeduli.utils

import android.graphics.Typeface
import android.widget.TextView

/**
* Created by Mayburger on 9/1/18.
*/
val TextView.black: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/Raleway-Black.ttf")
val TextView.bold: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/Raleway-Bold.ttf")
val TextView.extrabold: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/Raleway-ExtraBold.ttf")
val TextView.extralight: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/Raleway-ExtraLight.ttf")
val TextView.light: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/Raleway-Light.ttf")
val TextView.medium: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/Raleway-Medium.ttf")
val TextView.regular: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/Raleway-Regular.ttf")
val TextView.semibold: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/Raleway-SemiBold.ttf")
val TextView.thin: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/Raleway-Thin.ttf")



