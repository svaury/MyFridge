package com.example.svaury.myfridge;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;


import com.example.svaury.myfridge.presentatio.view.FridgeListFragment;


import butterknife.ButterKnife;

/**
 * Created by cbm0447 on 20/10/2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        Fragment fragment = new FridgeListFragment();
        switchFragment(fragment,"listFragment");

    }

    public void switchFragment(Fragment fragment , String tag){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment f =  fm.findFragmentByTag(tag);

         if(f == null &&  !fragment.isAdded()){
            transaction.add(R.id.frameLayout ,fragment ,tag);
        }
        transaction.commit();
    }

}
