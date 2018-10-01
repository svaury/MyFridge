package com.example.svaury.myfridge.presentatio.view

/**
 * Created by cbm0447 on 15/01/2018.
 */
interface ProductDetailView{

    fun showProductImage(url: String?)

    fun showDescription(description:String)

    fun confirmProductAdding()

    fun showLayout()
}