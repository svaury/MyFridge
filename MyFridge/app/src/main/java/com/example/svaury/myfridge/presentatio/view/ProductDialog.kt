package com.example.svaury.myfridge.presentatio.view


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.svaury.myfridge.App
import com.example.svaury.myfridge.R
import com.example.svaury.myfridge.presentatio.presenter.ProductDialogPresenter
import com.squareup.picasso.Picasso
import javax.inject.Inject

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.os.Build
import com.example.svaury.myfridge.domain.Mappers


import java.util.*


/**
 * Created by cbm0447 on 12/01/2018.
 */
class ProductDialog : DialogFragment(),ProductDetailView {


    var productImageView:ImageView?=null
    var productTextView: TextView?=null
    var validButton: Button?=null

    var productDatePicker : DatePicker ?=null

    var productTimePicker : TimePicker ?=null

    var dialogLayout : LinearLayout ?=null

    var progressBar : ProgressBar ?=null


    private var codeBar:String= ""

    @Inject lateinit var presenter: ProductDialogPresenter

    @Inject lateinit var cont: Context

    companion object {
        fun newInstance(productBarCode: String): ProductDialog {
            val f = ProductDialog()

            val args = Bundle()
            args.putString("barCode", productBarCode)
            f.arguments = args

            return f
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.netComponent.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View? = inflater?.inflate(R.layout.product_detail_dialog, container, false)

        productImageView = v?.findViewById(R.id.productImageView)
        productTextView  = v?.findViewById(R.id.productTextView)
        validButton = v?.findViewById(R.id.validButton)
        dialogLayout = v?.findViewById(R.id.dialogLayout)
        progressBar = v?.findViewById(R.id.progressBar)

        productDatePicker = v?.findViewById(R.id.productDatePicker)
        productTimePicker = v?.findViewById(R.id.productTimePicker)

        validButton?.setOnClickListener({confirmProductAdding()})


        codeBar = arguments?.getString("barCode")?:""
        presenter.init(this)
        presenter.getProductByBarCode(codeBar)

        return v
    }

    override fun showProductImage(url: String?) {

        Picasso.with(activity).load(url).into(productImageView)

    }

    override fun showDescription(description: String) {
        productTextView?.text = description
    }

    override fun showLayout() {
        dialogLayout?.visibility = View.VISIBLE
        progressBar?.visibility = View.GONE

    }
    override fun confirmProductAdding(){

        presenter.insertProductDb(getTimeInMillis())
                .subscribe { uid ->
                    registerPeremptionAlarm(uid)
                    targetFragment.onActivityResult(
                            targetRequestCode,
                            Activity.RESULT_OK,
                            Intent().putExtra("ConfirmButton", "OK"))
                    dismiss()
                }


    }

    fun getTimeInMillis():Long{
        val calendar:Calendar = Calendar.getInstance()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            calendar.set(productDatePicker?.year ?: 0 ,productDatePicker?.month ?: 0, productDatePicker?.dayOfMonth ?:0 ,productTimePicker?.hour ?: 0 , productTimePicker?.minute ?:0,0)
        }else{
            calendar.set(productDatePicker?.year ?: 0 ,productDatePicker?.month ?: 0, productDatePicker?.dayOfMonth ?:0 ,productTimePicker?.currentHour ?: 0 ,productTimePicker?.currentMinute ?:0,0 )
        }
        return calendar.timeInMillis
    }

    fun registerPeremptionAlarm(id:Long){

        val mgrAlarm = context.getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, OnAlarmReceiver::class.java)
        intent.putExtra("requestCode",id)

        val pendingIntent = PendingIntent.getBroadcast(context, id.toInt(), intent,0)
        mgrAlarm.setExact(AlarmManager.RTC_WAKEUP, getTimeInMillis(), pendingIntent)

    }

}