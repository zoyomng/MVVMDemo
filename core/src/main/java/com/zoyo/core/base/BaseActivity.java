package com.zoyo.core.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zoyo.core.utils.TypeUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity {

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
        ViewDataBinding dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        //RxAppCompatActivity->SupportActivity->FragmentActivity->SupportActivity->implements LifecycleOwner.设置LifecycleOwner,LifecycleOwner可以观察LiveData数据的变化以更新UI
        dataBinding.setLifecycleOwner(this);

        //获取ViewModel
        viewModel = (VM) ViewModelProviders.of(this).get(getTypeClass());

    }

    /**
     * 获取ViewModel的Class
     *
     * @return
     */
    private Class getTypeClass() {
        Class modelClass;
        //获取集成的(上层)类
        Type superclass = getClass().getGenericSuperclass();
        //ParameterizedType参数化类型,暂时理解为类上是否有泛型
        if (superclass instanceof ParameterizedType) {
            //获取的即是泛型<Class>中的Class类型,index指定泛型<String,String..>第几个Type
            modelClass = (Class) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        } else {
            modelClass = BaseViewModel.class;
        }
        return modelClass;
    }

    /**
     * 页面布局
     *
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("++++++++++onStop+++++++++++");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("++++++++++onPause+++++++++++");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("++++++++++onDestroy+++++++++++");

    }
}
