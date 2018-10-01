package com.example.svaury.myfridge.data.net

import com.example.svaury.myfridge.AppModule
import com.example.svaury.myfridge.MainActivity
import com.example.svaury.myfridge.domain.GetProductsUseCase
import com.example.svaury.myfridge.domain.ProductDbUseCase
import com.example.svaury.myfridge.presentatio.ProductsHolder
import com.example.svaury.myfridge.presentatio.presenter.FoodPresenterImpl
import com.example.svaury.myfridge.presentatio.presenter.ProductDialogPresenter
import com.example.svaury.myfridge.presentatio.view.*
import dagger.Component
import javax.inject.Singleton

/**
 * Created by cbm0447 on 19/10/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class , PresenterModule::class))
interface NetComponent {

    fun inject(activity: MainActivity)
    fun inject(foodPresenter : FoodPresenterImpl)
    fun inject(fridgeListFragment: FridgeListFragment)
    fun inject(scanBarFragment: ScanBarFragment)
    fun inject(getProductsUseCase: GetProductsUseCase)
    fun inject(productDbUseCase: ProductDbUseCase)
    fun inject(productsHolder: ProductsHolder)
    fun inject(productDialogPresenter: ProductDialogPresenter)
    fun inject(productDialog: ProductDialog)
    fun inject(productAdapter: ProductAdapter)
    fun inject(onAlarmReceiver: OnAlarmReceiver)

}