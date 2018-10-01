package com.example.svaury.myfridge.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.svaury.myfridge.data.dao.ProductDao;
import com.example.svaury.myfridge.data.entities.ProductEntity;

/**
 * Created by cbm0447 on 28/12/2017.
 */

@Database(entities = {ProductEntity.class},version = 2)
public abstract class ProductDBHelper extends RoomDatabase {
    public abstract ProductDao productDao();
}
