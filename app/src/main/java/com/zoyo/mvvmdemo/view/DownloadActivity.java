package com.zoyo.mvvmdemo.view;

import android.os.Bundle;
import android.view.View;

import com.zoyo.core.mvvm.base.BaseActivity;
import com.zoyo.mvvmdemo.BR;
import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.viewModel.DownloadViewModel;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/7/12 15:29
 */
public class DownloadActivity extends BaseActivity<DownloadViewModel> implements View.OnClickListener {


    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_download;
    }

    @Override
    protected int initViewModelId() {
        return BR.viewModel;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getContentLayoutId() {
        return R.id.contentLayout;
    }
}
