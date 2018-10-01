package com.example.svaury.myfridge.presentatio.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.svaury.myfridge.MainActivity;
import com.example.svaury.myfridge.R;
import com.example.svaury.myfridge.presentatio.presenter.AnimUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cbm0447 on 24/01/2018.
 */

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {

    @BindView(R.id.splashTextView)
    TextView splashTextView;

    @BindView(R.id.snowImageView)
    ImageView snowImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        Log.i("SplashActivity ","SplashActivity");

        AnimUtils.bounceAnimation(splashTextView, null);
        AnimUtils.alphaAnimation(snowImageView, this);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
