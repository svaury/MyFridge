package com.example.svaury.myfridge;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;


import com.example.svaury.myfridge.presentatio.view.FridgeListFragment;
import com.example.svaury.myfridge.presentatio.view.OnAlarmReceiver;


import java.util.Calendar;

import butterknife.BindView;
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




        /*AlarmManager mgrAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(this, OnAlarmReceiver.class);
        intent.putExtra("requestCode",2L);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 2, intent,0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mgrAlarm.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, getTimeInMillis(), pendingIntent);
        }*/


    }

    /*Long getTimeInMillis(){
        Calendar calendar = Calendar.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            calendar.set(productDatePicker.getYear() ,productDatePicker.getMonth(), productDatePicker.getDayOfMonth() ,productTimePicker.getHour() , productTimePicker.getMinute(),0);
        }
        return calendar.getTimeInMillis();
    }*/

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
