package com.example.svaury.myfridge.presentatio.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by cbm0447 on 24/01/2018.
 */
class Product {

    var id:Int = -1
    var fireBaseKey : String ?= null
    var millisPeremptionDate : Long= 0
    var productName: String? = null
    var imageFrontSmallUrl: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Product

        if (!fireBaseKey.equals(other.fireBaseKey)) return false

        return true
    }
}