package com.zoyo.core.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zoyo.core.utils.TypeUtil;

public abstract class BaseActivity<T extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity {

    private T dataBinding;
    private VM viewModel;

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
        //RxAppCompatActivity->SupportActivity->FragmentActivity->SupportActivity->implements LifecycleOwner.设置LifecycleOwner,LifecycleOwner可以观察LiveData数据的变化以更新UI
        dataBinding.setLifecycleOwner(this);

        //获取ViewModel
        Object classType = TypeUtil.getClassType(this, 0);
//        (VM) ViewModelProviders.of(this).get(classType);

    }

    /**
     * 页面布局
     *
     * @return
     */
    protected abstract int getLayoutId();

}
