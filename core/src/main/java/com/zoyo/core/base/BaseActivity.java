package com.zoyo.core.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import com.zoyo.core.utils.TypeUtil;

public abstract class BaseActivity<VM extends BaseViewModel> extends RxAppCompatActivity implements IBaseView {

    public VM viewModel;
    public ViewDataBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        //初始化DataBinding
        initViewDataBinding(savedInstanceState);
        //初始化数据
        initData();
    }

    /**
     * 注入绑定
     *
     * @param savedInstanceState
     */
    private void initViewDataBinding(Bundle savedInstanceState) {
        //DataBindingUtil类需要在project-build中配置DataBinding{enabled true},同步后会自动关联android.databinding包
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId(savedInstanceState));
        //RxAppCompatActivity->SupportActivity->FragmentActivity->SupportActivity->implements LifecycleOwner.设置LifecycleOwner,LifecycleOwner可以观察LiveData数据的变化以更新UI
        dataBinding.setLifecycleOwner(this);

        //获取ViewModel
        viewModel = initViewModel();

        //关联ViewModel
        int viewModelId = initViewModelId();
        dataBinding.setVariable(viewModelId, viewModel);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this);
    }

    private VM initViewModel() {
        return (VM) ViewModelProviders.of(this).get(TypeUtil.getClassType(this, 0, BaseViewModel.class));
    }


    /**
     * 页面布局
     *
     * @param savedInstanceState 保存的状态数据
     * @return
     */
    protected abstract int getLayoutId(Bundle savedInstanceState);

    /**
     * 布局文件中设置ViewModel变量
     * <data>
     * <variable
     * name="viewModel"
     * type="ViewModel" />
     * </data>
     * <p>
     * 使用方式:
     *
     * @return
     * @Override public int initVariableId() {
     * return BR.viewModel;
     * }
     * <p>
     * 以上抽取相当于在继承BaseActivity的类中:DataBinding.setViewModel(viewModel)
     */
    protected abstract int initViewModelId();


    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除ViewModel生命周期感应
        getLifecycle().removeObserver(viewModel);
        if (dataBinding != null) {
            dataBinding.unbind();
        }
        AppManager.getInstance().removeActivity(this);
    }
}
