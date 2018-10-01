package com.example.svaury.myfridge.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by cbm0447 on 28/12/2017.
 */
@Entity
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name ="FirebaseKey")
    private String firebaseKey;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "peremption_date")
    private Long peremptionDate;

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getPeremptionDate() {
        return peremptionDate;
    }

    public String getFirebaseKey(){
        return firebaseKey;
    }

    public void setFirebaseKey(String key){
        firebaseKey = key;
    }

    public void setPeremptionDate(Long peremptionDate) {
        this.peremptionDate = peremptionDate;
    }
}
