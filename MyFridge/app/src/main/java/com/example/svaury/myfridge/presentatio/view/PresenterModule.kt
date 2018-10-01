package com.example.svaury.myfridge.presentatio.view

import com.example.svaury.myfridge.presentatio.presenter.FoodPresenterImpl
import com.example.svaury.myfridge.presentatio.presenter.ProductDialogPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by cbm0447 on 20/10/2017.
 */

@Module
class PresenterModule {


    @Singleton
    @Provides
    fun provideFoodPresenter(): FoodPresenterImpl {

        return FoodPresenterImpl();
    }

    @Singleton
    @Provides
    fun provideProductDialogPresenter(): ProductDialogPresenter {

        return ProductDialogPresenter()
    }

}