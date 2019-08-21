package com.example.myassignment.view.grid_layout;

import com.example.myassignment.BR;
import com.example.myassignment.R;
import com.example.myassignment.base.activity.BindingActivity;
import com.example.myassignment.databinding.ActivityGridLayoutBinding;
import com.example.myassignment.viewmodel.grid_layout.GridLayoutViewModel;

public class GridLayoutActivity extends BindingActivity<ActivityGridLayoutBinding, GridLayoutViewModel> {
    @Override
    public GridLayoutViewModel onCreate() {
        return new GridLayoutViewModel(this);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_grid_layout;
    }
}
