package com.example.svaury.myfridge.presentatio.presenter;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;

import com.example.svaury.myfridge.R;

/**
 * Created by cbm0447 on 24/01/2018.
 */

public class AnimUtils {

    public static void bounceAnimation(View v, Animation.AnimationListener animationListener){

        BounceInterpolator bounceInterpolator = new BounceInterpolator();

        Animation myAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.interpolator);

        myAnim.setFillAfter(true);
        myAnim.setAnimationListener(animationListener);
        myAnim.setInterpolator(bounceInterpolator);
        v.startAnimation(myAnim);

    }

    public static void alphaAnimation(View v, Animation.AnimationListener animationListener){

        AlphaAnimation animation = new AlphaAnimation(0f, 1.0f);
        animation.setDuration(2000);
        animation.setStartOffset(2000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        v.startAnimation(animation);
    }
}
