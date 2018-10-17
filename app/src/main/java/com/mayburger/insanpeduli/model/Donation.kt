package com.mayburger.insanpeduli.model

import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter
import java.io.Serializable

/**
 * Created by Mayburger on 10/12/18.
 */

class Donation(
        var amount: String,
        var id: String,
        var campaign_id: String,
        var uuid: String,
        var is_paid: Boolean,
        var transfer_proof: String,
        var title: String
) : Serializable
