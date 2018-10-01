package com.example.svaury.myfridge.presentatio

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.svaury.myfridge.App
import com.example.svaury.myfridge.R
import com.example.svaury.myfridge.presentatio.model.Product
import com.squareup.picasso.Picasso
import java.util.*
import javax.inject.Inject


/**
 * Created by cbm0447 on 03/11/2017.
 */
class ProductsHolder(view: View) : RecyclerView.ViewHolder(view) {

    @Inject lateinit var context: Context

    init {
        App.netComponent.inject(this)
    }


    var tvProductName:TextView = view.findViewById(R.id.tvProductName)
    var ivProduct : ImageView = view.findViewById(R.id.productImageView)
    var ivPeremption :ImageView = view.findViewById(R.id.peremptionImageView)
    var tvPeremption :TextView = view.findViewById(R.id.tvPeremption)


    fun bind(product: Product){
        tvProductName.text = product?.productName;

        if(!product?.imageFrontSmallUrl.isNullOrEmpty()) {
            Picasso.with(context).load(product?.imageFrontSmallUrl).into(ivProduct)
        }
        val currentDate = Calendar.getInstance()
        currentDate.timeInMillis = System.currentTimeMillis()

        val peremptionDate = Calendar.getInstance()
        peremptionDate.timeInMillis = product.millisPeremptionDate


        var peremptionString = "Périme le "+ peremptionDate.get(Calendar.DAY_OF_MONTH) + "/"+ (peremptionDate.get(Calendar.MONTH) +1) + "/"+ peremptionDate.get(Calendar.YEAR)
        if(checkDateEquals(peremptionDate,currentDate)){
            ivPeremption.setBackgroundResource(R.drawable.yellow_shape)
            ivPeremption.setImageResource(R.drawable.attention_ic)
            peremptionString = "Périme aujourd'hui "
        }else if(peremptionDate.timeInMillis < currentDate.timeInMillis){
            ivPeremption.setBackgroundResource(R.drawable.red_shape)
            ivPeremption.setImageResource(R.drawable.attention_ic)

            peremptionString = "Périmé depuis le " +peremptionDate.get(Calendar.DAY_OF_MONTH) + "/"+ (peremptionDate.get(Calendar.MONTH) +1) + "/"+ peremptionDate.get(Calendar.YEAR);
        }
        tvPeremption.text = peremptionString
    }

    fun checkDateEquals(currentDate: Calendar , peremptionDate : Calendar):Boolean{

        if( currentDate.get(Calendar.DAY_OF_MONTH) == peremptionDate.get(Calendar.DAY_OF_MONTH)
                && currentDate.get(Calendar.MONTH) == peremptionDate.get(Calendar.MONTH)
                && currentDate.get(Calendar.YEAR) == peremptionDate.get(Calendar.YEAR)){
            return true
        }
        return false
    }
}