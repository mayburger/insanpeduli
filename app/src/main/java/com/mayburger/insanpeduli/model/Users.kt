package com.mayburger.insanpeduli.model

import java.io.Serializable

/**
 * Created by Mayburger on 10/12/18.
 */

class Users(var email: String, var password: String, var verified: Boolean?, var name: String,var image:String,var phone:String):Serializable
