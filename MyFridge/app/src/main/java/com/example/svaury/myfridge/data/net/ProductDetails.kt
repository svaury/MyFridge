package com.example.svaury.myfridge.data.net

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by cbm0447 on 20/10/2017.
 */
class ProductDetails {

    @SerializedName("product_name_fr")
    var productName: String? = null
    @SerializedName("image_thumb_url")
    var imageFrontSmallUrl: String? = null

    override fun toString(): String {
        return "ProductDetails(productName=$productName, imageFrontSmallUrl=$imageFrontSmallUrl)"
    }


}