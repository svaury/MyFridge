package com.example.svaury.myfridge.data.net

import com.example.svaury.myfridge.presentatio.model.Product
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by cbm0447 on 20/10/2017.
 */
interface ProductService {

    @GET("api/v0/product/{productId}")
    fun productByZipCode(@Path("productId") productId:String): Observable<ProductDo>
}