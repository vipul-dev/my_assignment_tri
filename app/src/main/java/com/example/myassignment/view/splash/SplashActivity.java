package com.example.myassignment.view.splash;

import com.example.myassignment.BR;
import com.example.myassignment.R;
import com.example.myassignment.base.activity.BindingActivity;
import com.example.myassignment.databinding.ActivitySplashBinding;
import com.example.myassignment.util.ActivityController;
import com.example.myassignment.view.grid_layout.GridLayoutActivity;
import com.example.myassignment.viewmodel.splash.SplashNavigator;
import com.example.myassignment.viewmodel.splash.SplashViewModel;

public class SplashActivity extends BindingActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {
    @Override
    public SplashViewModel onCreate() {
        return new SplashViewModel(this,this);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void openHomeActivity() {
        ActivityController.startActivity(SplashActivity.this, GridLayoutActivity.class,true,true);
    }
}
