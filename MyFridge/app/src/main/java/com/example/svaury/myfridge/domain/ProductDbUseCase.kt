package com.example.svaury.myfridge.domain

import android.util.Log
import com.example.svaury.myfridge.App
import com.example.svaury.myfridge.data.ProductDBHelper
import com.example.svaury.myfridge.data.entities.ProductEntity
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by cbm0447 on 12/01/2018.
 */
class ProductDbUseCase: UseCase{

    @Inject lateinit var productDbHelper : ProductDBHelper

    init {
        App.netComponent.inject(this);
    }

    fun insertProduct(productEntity: ProductEntity):Observable<Long> =
            Maybe.fromCallable { productDbHelper.productDao().insert(productEntity) }
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    fun getProducts(): Observable<List<ProductEntity>> =
            Maybe.fromCallable {productDbHelper.productDao().all}
                    .toObservable()
                    .subscribeOn(Schedulers.io())


    fun deleteProduct(productEntity: ProductEntity){
        Maybe.fromCallable {productDbHelper.productDao().deleteEntity(productEntity.firebaseKey)}
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()


    }
    fun findProductByFireBaseKey(key:String):Observable<ProductEntity> =
            Maybe.fromCallable {productDbHelper.productDao().findByFireBaseId(key)}
                    .toObservable()
                    .subscribeOn(Schedulers.io())

    fun findProductById(id:Long):Observable<ProductEntity> =
            Maybe.fromCallable {productDbHelper.productDao().findById(id)}
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


}