package com.example.svaury.myfridge.data.dao

import android.arch.persistence.room.*
import com.example.svaury.myfridge.data.entities.ProductEntity

/**
 * Created by cbm0447 on 28/12/2017.
 */
@Dao
interface ProductDao {

    @get:Query("SELECT * FROM productEntity")
    val all: List<ProductEntity>


    @Query("SELECT * FROM productEntity WHERE uid LIKE :uid")
    fun findById(uid:Long): ProductEntity

    @Query("SELECT * FROM ProductEntity WHERE FireBaseKey LIKE :key")
    fun findByFireBaseId(key:String): ProductEntity

    @Insert
    fun insert(products:ProductEntity):Long

    @Update
    fun update(product: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)
}