package com.example.svaury.myfridge.presentatio.presenter

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.svaury.myfridge.App
import com.example.svaury.myfridge.data.entities.ProductEntity
import com.example.svaury.myfridge.data.net.ProductDo
import com.example.svaury.myfridge.domain.ProductDbUseCase
import com.example.svaury.myfridge.presentatio.model.Product
import com.example.svaury.myfridge.domain.Mappers
import com.example.svaury.myfridge.presentatio.view.OnAlarmReceiver
import com.example.svaury.myfridge.presentatio.view.ProductView
import com.google.firebase.database.*
import io.reactivex.android.schedulers.AndroidSchedulers


import javax.inject.Inject


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

        database.getReference("products").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("Change ", "Change $dataSnapshot")

                var products =  dataSnapshot?.value as HashMap<String,ProductDo>
                removeProducts(products)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

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
                 .observeOn(AndroidSchedulers.mainThread())
                .subscribe { product -> productView.addProductList(product) }

    }

    fun deleteProductToFireBase(product: Product?){
        val ref = database.reference
        ref.child("products")?.child(product?.fireBaseKey ?:"")?.removeValue()

    }

    fun addProductIntoList(productEntity: ProductEntity){
        var isEmpty = productDbUseCase.findProductByFireBaseKey(productEntity.firebaseKey).isEmpty

        isEmpty.observeOn(AndroidSchedulers.mainThread())
                .subscribe { it->
            if(it){
                productDbUseCase.insertProduct(productEntity)
                        .subscribe({uid ->
                    registerPeremptionAlarm(uid,productEntity)
                    },{t->t.printStackTrace()})
                productView.addProductList(Mappers.entityToProduct(productEntity))
            }
        }

    }

    fun registerPeremptionAlarm(id:Long, productEntity: ProductEntity){

        val mgrAlarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, OnAlarmReceiver::class.java)
        intent.putExtra("requestCode",id)

        val pendingIntent = PendingIntent.getBroadcast(context, id.toInt(), intent,0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mgrAlarm.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, productEntity.peremptionDate, pendingIntent)
        }else{
            mgrAlarm.setExact(AlarmManager.RTC_WAKEUP, productEntity.peremptionDate, pendingIntent)

        }
    }

    fun removeProducts(products : Map<String,ProductDo>){
        productDbUseCase.getProducts()
                .flatMapIterable { products -> products }
                .filter{product -> !products.containsKey(product.firebaseKey)}
                .subscribe({
                    product -> removeProduct(Mappers.entityToProduct(product))
                },{
                    it-> it.printStackTrace()
                })

    }
    fun removeProduct(product: Product) {
        Log.i("Remove Product","Remove product" )

        productDbUseCase.deleteProduct(Mappers.productToEntity(product))
        productView.removeProductList(product)
    }

    override fun removeItem(product: Product) {

        Log.i("Remove Item","Remove Item ")
        productDbUseCase.deleteProduct(Mappers.productToEntity(product))
        deleteProductToFireBase(product)
    }
}