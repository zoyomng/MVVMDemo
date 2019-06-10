package com.zoyo.mvvmdemo.view;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.zoyo.core.base.BaseActivity;
import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.viewModel.MainViewModel;

public class MainActivity extends BaseActivity<MainViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


}
