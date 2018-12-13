package com.example.svaury.myfridge.presentatio.view

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.util.Log
import com.example.svaury.myfridge.App
import com.example.svaury.myfridge.MainActivity
import com.example.svaury.myfridge.R
import com.example.svaury.myfridge.domain.ProductDbUseCase
import javax.inject.Inject
import android.app.NotificationChannel
import android.os.Build



/**
 * Created by cbm0447 on 19/01/2018.
 */
class OnAlarmReceiver : BroadcastReceiver() {


    @Inject lateinit var productDbUseCase: ProductDbUseCase


    override fun onReceive(context: Context?, intent: Intent?) {


        App.netComponent.inject(this)


        var id = intent?.getLongExtra("requestCode",0) ?:0

        val intent = Intent(context, MainActivity::class.java)

        productDbUseCase.findProductById(id)
                .subscribe { productEntity->

                    val pIntent = TaskStackBuilder.create(context)
                            .addNextIntent(intent)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

                    // Create Notification using NotificationCompat.Builder
                    val builder = NotificationCompat.Builder(context!!,"Attention")
                            .setContentTitle("Attention, ce produit périme aujourd'hui !!")
                            .setSmallIcon(R.drawable.attention_ic)
                            .setContentText(productEntity.name)
                            .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
                            .setContentIntent(pIntent)
                            .setAutoCancel(true)
                    builder.setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))

                    val notificationmanager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channel = NotificationChannel("Attention",
                                "Produit périmé",
                                NotificationManager.IMPORTANCE_DEFAULT)
                        notificationmanager.createNotificationChannel(channel)
                    }

                    // Build Notification with Notification Manager
                    notificationmanager.notify(1, builder.build())

                }
    }


}