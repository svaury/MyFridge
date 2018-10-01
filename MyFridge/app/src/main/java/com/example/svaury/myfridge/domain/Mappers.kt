package com.example.svaury.myfridge.domain

import android.util.Log
import com.example.svaury.myfridge.data.entities.ProductEntity
import com.example.svaury.myfridge.presentatio.model.Product
import com.example.svaury.myfridge.data.net.ProductDo


/**
 * Created by{ cbm0447 on 24/01/2018.
 */
class Mappers{

    companion object {
        fun entityToProduct(productEntity: ProductEntity): Product {


            var product = Product()
            product.id = productEntity.uid
            product.millisPeremptionDate = productEntity.peremptionDate
            product.productName = productEntity.name
            product.fireBaseKey = productEntity.firebaseKey
            product.imageFrontSmallUrl = productEntity.imageUrl

            return product
        }
        fun productToEntity(product: Product?): ProductEntity {
            var productEntity = ProductEntity()
            productEntity.peremptionDate = product?.millisPeremptionDate ?:0
            productEntity.firebaseKey = product?.fireBaseKey
            productEntity.imageUrl = product?.imageFrontSmallUrl ?:""
            productEntity.name = product?.productName ?: ""
            if(product?.id != -1) {
                productEntity?.uid = product?.id ?: 0
            }

            return productEntity
        }
        fun jsonToProduct(productDo: ProductDo): Product {

            var product = Product()
            product.imageFrontSmallUrl= productDo.productDetails?.imageFrontSmallUrl
            product.productName = productDo.productDetails?.productName
            return product
        }
    }






}