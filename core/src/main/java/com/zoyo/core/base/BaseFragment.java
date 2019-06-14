package com.zoyo.core.base;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.zoyo.core.utils.TypeUtil;


public abstract class BaseFragment<VM extends BaseViewModel> extends RxFragment implements IBaseView {

    private ViewDataBinding dataBinding;
    private VM viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(savedInstanceState), container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewDataBinding();
        initData();
        initViewObservable();
    }


    protected void initViewDataBinding() {
        int viewModelId = initVariableId();
        viewModel = initViewModel();
        dataBinding.setVariable(viewModelId, viewModel);
        getLifecycle().addObserver(viewModel);
        viewModel.injectLifecycleProvider(this);
    }

    private VM initViewModel() {
        return (VM) ViewModelProviders.of(this).get(TypeUtil.getClassType(this, 0, BaseViewModel.class));
    }

    protected abstract int initVariableId();

    protected abstract int getLayoutId(Bundle savedInstanceState);


    @Override
    public void initData() {

    }

    protected abstract void initViewObservable();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getLifecycle().removeObserver(viewModel);
        if (dataBinding != null) {
            dataBinding.unbind();
        }
    }
}
