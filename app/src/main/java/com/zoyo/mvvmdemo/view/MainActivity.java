package com.zoyo.mvvmdemo.view;

import android.os.Bundle;

import com.zoyo.core.base.BaseActivity;
import com.zoyo.mvvmdemo.BR;
import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.viewModel.MainViewModel;

public class MainActivity extends BaseActivity<MainViewModel> {

    @Override
    protected int initViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }


    @Override
    public void initData() {
    }
}
