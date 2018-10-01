package com.example.svaury.myfridge.presentatio.view

import com.example.svaury.myfridge.presentatio.model.Product

/**
 * Created by cbm0447 on 03/11/2017.
 */

interface ProductView{

    fun addProductList(product: Product)

    fun removeProductList(product: Product)

}