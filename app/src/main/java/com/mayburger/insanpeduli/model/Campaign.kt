package com.mayburger.insanpeduli.model

import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter
import java.io.Serializable

/**
 * Created by Mayburger on 10/12/18.
 */

class Campaign(
        var category: String,
        var description: String,
        var phone: String,
        var picture: String,
        var target: String,
        var title: String,
        var id: String,
        var uuid: String
):Serializable
