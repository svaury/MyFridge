package com.example.svaury.myfridge.presentatio.presenter

import android.util.Log
import com.example.svaury.myfridge.App
import com.example.svaury.myfridge.data.entities.ProductEntity
import com.example.svaury.myfridge.data.net.ProductDo
import com.example.svaury.myfridge.domain.GetProductsUseCase
import com.example.svaury.myfridge.domain.Mappers
import com.example.svaury.myfridge.domain.ProductDbUseCase
import com.example.svaury.myfridge.presentatio.model.Product
import com.example.svaury.myfridge.presentatio.view.ProductDetailView
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by cbm0447 on 15/01/2018.
 */
class ProductDialogPresenter{


    @Inject lateinit var getProductUseCase: GetProductsUseCase

    @Inject lateinit var productDbUseCase : ProductDbUseCase

    @Inject lateinit var database : FirebaseDatabase

    var product : Product? = null



    var productDetailView : ProductDetailView ?= null

    fun init(productDetailView: ProductDetailView){

        App.netComponent.inject(this)

        this.productDetailView = productDetailView

    }

    fun addProductToFirebase(product: ProductEntity) {
        val ref = database.reference
        val ref2 = ref.child("products").push()
        product.firebaseKey = ref2.key
        ref2.setValue(product)
    }



    fun getProductByBarCode(barCode:String){

        val post = getProductUseCase.getProductByBarcode(barCode)

        post.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ product -> onSuccess(product)},
                        {throwable-> throwable.printStackTrace()})

    }

    fun onSuccess(productDo: ProductDo){

        this.product = Mappers.jsonToProduct(productDo)
        this.productDetailView?.showDescription(product?.productName ?:"")
        this.productDetailView?.showProductImage(product?.imageFrontSmallUrl)
        this.productDetailView?.showLayout()
    }


     fun insertProductDb(timeInMillis:Long):Observable<Long>{

        val productEntity = Mappers.productToEntity(product)


         productEntity.peremptionDate = timeInMillis

         addProductToFirebase(productEntity)
         return productDbUseCase.insertProduct(productEntity)
    }


}