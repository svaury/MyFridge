package com.example.svaury.myfridge.domain

import com.example.svaury.myfridge.App
import com.example.svaury.myfridge.data.ProductDBHelper
import com.example.svaury.myfridge.data.entities.ProductEntity
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
            Observable.fromCallable({ productDbHelper.productDao().insert(productEntity) })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())

    fun getProducts(): Observable<List<ProductEntity>> =
            Observable.fromCallable({productDbHelper.productDao().all})
                     .subscribeOn(Schedulers.newThread())
                     .observeOn(AndroidSchedulers.mainThread())

    fun deleteProduct(productEntity: ProductEntity){
        Observable.fromCallable({productDbHelper.productDao().delete(productEntity)})
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
    fun findProductByFireBaseKey(key:String):Observable<ProductEntity> =
            Observable.fromCallable({productDbHelper.productDao().findByFireBaseId(key)})
                    .subscribeOn(Schedulers.newThread())

    fun findProductById(id:Long):Observable<ProductEntity> =
            Observable.fromCallable({productDbHelper.productDao().findById(id)})
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())


}