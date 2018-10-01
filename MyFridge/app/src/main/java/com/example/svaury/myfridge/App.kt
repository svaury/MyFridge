package com.example.svaury.myfridge

import android.app.Application
import com.example.svaury.myfridge.data.net.DaggerNetComponent
import com.example.svaury.myfridge.data.net.NetComponent
import com.facebook.stetho.Stetho

/**
 * Created by cbm0447 on 20/10/2017.
 */
class App : Application() {


    companion object {
         lateinit var netComponent: NetComponent
    }

    override fun onCreate() {
        super.onCreate()

        netComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .build()
        Stetho.initializeWithDefaults(this)
    }

    fun getComponent(): NetComponent? {
        return netComponent
    }

}