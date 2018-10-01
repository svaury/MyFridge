package com.example.svaury.myfridge.presentatio.presenter

import android.content.Context
import android.util.Log
import com.example.svaury.myfridge.App
import com.example.svaury.myfridge.data.entities.ProductEntity
import com.example.svaury.myfridge.domain.ProductDbUseCase
import com.example.svaury.myfridge.presentatio.model.Product
import com.example.svaury.myfridge.domain.Mappers
import com.example.svaury.myfridge.presentatio.view.ProductView
import com.google.firebase.database.FirebaseDatabase


import javax.inject.Inject
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ChildEventListener



/**
 * Created by cbm0447 on 20/10/2017.
 */
class FoodPresenterImpl : FoodPresenter {

    @Inject lateinit var context : Context

    @Inject lateinit var productDbUseCase : ProductDbUseCase

    @Inject lateinit var database: FirebaseDatabase

    lateinit var productView: ProductView



    override fun init(productView: ProductView){

        App.netComponent.inject(this)

        this.productView = productView

        database?.reference?.child("products")?.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot?, prevChildKey: String?) {

                var productEntity  = dataSnapshot?.getValue(ProductEntity::class.java)?: ProductEntity()
                Log.i("ADD","ADD " + productEntity.firebaseKey)
                addProductIntoList(productEntity)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot?, prevChildKey: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                var productEntity  = dataSnapshot?.getValue(ProductEntity::class.java)?: ProductEntity()
                removeProduct(Mappers.entityToProduct(productEntity))
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot?, prevChildKey: String?) {}

            override fun onCancelled(databaseError: DatabaseError?) {}
        })

    }


    fun productEntityFromProductMapper(productEntity: ProductEntity) : Product {
        return Mappers.entityToProduct(productEntity)
    }


    override fun getAllProducts(){

        productDbUseCase.getProducts()
                .flatMapIterable { products -> products }
                .map { productEntity -> productEntityFromProductMapper(productEntity) }
                .subscribe { product -> productView.addProductList(product) }

    }

    fun deleteProductToFireBase(product: Product?){
        val ref = database.reference
        ref.child("products")?.child(product?.fireBaseKey ?:"")?.removeValue()

    }

    fun addProductIntoList(productEntity: ProductEntity){
        var isEmpty = productDbUseCase.findProductByFireBaseKey(productEntity.firebaseKey).isEmpty
        isEmpty.subscribe { it->
            if(it){
                productDbUseCase.insertProduct(productEntity).subscribe()
                productView.addProductList(Mappers.entityToProduct(productEntity))
            }
        }

    }
    fun removeProduct(product: Product) {
        productDbUseCase.deleteProduct(Mappers.productToEntity(product))
        productView.removeProductList(product)
    }

    override fun removeItem(product: Product) {
        productDbUseCase.deleteProduct(Mappers.productToEntity(product))
        deleteProductToFireBase(product)
    }
}