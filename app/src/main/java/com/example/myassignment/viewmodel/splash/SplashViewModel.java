package com.example.myassignment.viewmodel.splash;

import android.os.Handler;

import com.example.myassignment.base.activity.ActivityViewModel;
import com.example.myassignment.view.splash.SplashActivity;

public class SplashViewModel extends ActivityViewModel<SplashActivity> {

    private static int SPLASH_TIME_OUT = 3000;
    private SplashNavigator splashNavigator;
    private SplashActivity activity;

    public SplashViewModel(SplashActivity activity,SplashNavigator splashNavigator) {
        super(activity);
        this.activity = activity;
        this.splashNavigator=splashNavigator;

        decideNextActivity();
    }


    private void decideNextActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                splashNavigator.openHomeActivity();

            }
        }, SPLASH_TIME_OUT);
    }
}
