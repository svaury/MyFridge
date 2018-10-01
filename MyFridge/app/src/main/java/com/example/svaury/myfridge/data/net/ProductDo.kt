package com.example.svaury.myfridge.data.net

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by cbm0447 on 20/10/2017.
 */
class ProductDo:Serializable {


    @SerializedName("product")
    var productDetails: ProductDetails? = null
        internal set

    override fun toString(): String {
        return "Product(productDetails=$productDetails)"
    }


}