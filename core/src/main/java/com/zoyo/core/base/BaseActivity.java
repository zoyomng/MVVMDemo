package com.zoyo.core.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity {

    private V dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewDataBinding();
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding() {
        //DataBindingUtil类需要在project-build中配置DataBinding{enabled true},同步后会自动关联android.databinding包
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());

    }

    /**
     * 页面布局
     *
     * @return
     */
    protected abstract int getLayoutId();

}
