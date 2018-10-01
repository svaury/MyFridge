package com.example.svaury.myfridge;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.svaury.myfridge.data.ProductDBHelper;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cbm0447 on 19/10/2017.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    BarcodeDetector provideBarCodeDetector(Context context){
        return new BarcodeDetector.Builder(context).setBarcodeFormats(Barcode.ALL_FORMATS).build();
    }


    @Provides
    @Singleton
    CameraSource provideCameraSource(Context context,BarcodeDetector barcodeDetector){
        return new CameraSource
                .Builder(context, barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1600, 1024)
                .setRequestedFps(15.0f)
                .setAutoFocusEnabled(true)
                .build();
    }

    @Provides
    @Singleton
    FirebaseDatabase provideFireBaseDb(){
         return FirebaseDatabase.getInstance();
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mApplication;
    }

    @Provides
    @Singleton
    ProductDBHelper provideProductDb(Context context){
        return Room.databaseBuilder(context,ProductDBHelper.class,"MyProductsDb")
                .fallbackToDestructiveMigration()
                .build();
    }


}
