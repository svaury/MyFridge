package com.example.svaury.myfridge.domain

import com.example.svaury.myfridge.App
import com.example.svaury.myfridge.data.net.ProductDo
import com.example.svaury.myfridge.data.net.ProductService
import com.example.svaury.myfridge.presentatio.model.Product
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by cbm0447 on 12/01/2018.
 */
class GetProductsUseCase: UseCase{

    @Inject lateinit var retrofit: Retrofit

    init {
        App.netComponent.inject(this)
    }

    fun getProductByBarcode(barcode : String):Observable<ProductDo>{

        return  retrofit.create(ProductService::class.java).productByZipCode(barcode)
    }

}