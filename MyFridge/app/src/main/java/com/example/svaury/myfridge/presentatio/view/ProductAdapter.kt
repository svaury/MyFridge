package com.example.svaury.myfridge.presentatio.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.svaury.myfridge.App
import com.example.svaury.myfridge.R
import com.example.svaury.myfridge.presentatio.model.Product
import com.example.svaury.myfridge.presentatio.ProductsHolder
import com.example.svaury.myfridge.presentatio.presenter.FoodPresenterImpl
import java.util.*
import javax.inject.Inject

/**
 * Created by cbm0447 on 03/11/2017.
 */
class ProductAdapter(products:ArrayList<Product>): RecyclerView.Adapter<ProductsHolder>() {


    var products : ArrayList<Product> =  products
    @Inject lateinit var foodPresenter:FoodPresenterImpl

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProductsHolder {

        App.netComponent.inject(this)
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.product_description_layout,parent,false)
        return ProductsHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {

        holder.bind(products[position])
    }

    fun addProduct(product : Product){

        products.add(product)
        notifyDataSetChanged()
    }

    fun resetList(){
        products.clear()
    }
    fun switchProductsPosition(currentPos: kotlin.Int, targetPos : kotlin.Int){
        Collections.swap(products, currentPos, targetPos)
        notifyItemMoved(currentPos, targetPos)
    }

    fun removeProduct(position : kotlin.Int){
        foodPresenter.removeItem(products.get(position))
        products.removeAt(position)
        notifyItemRemoved(position)

    }

    fun removeProduct(product: Product){
        products.remove(product)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return products.size
    }


}