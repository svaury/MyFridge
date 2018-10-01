package com.example.svaury.myfridge.presentatio.presenter

import com.example.svaury.myfridge.presentatio.model.Product
import com.example.svaury.myfridge.presentatio.view.ProductView

/**
 * Created by cbm0447 on 20/10/2017.
 */
interface FoodPresenter{

    fun init(productView: ProductView)

    fun getAllProducts()

    fun removeItem(product: Product)


}
