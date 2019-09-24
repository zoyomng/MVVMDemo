package com.zoyo.core.mvvm.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.zoyo.core.common.constants.Constants;
import com.zoyo.core.common.multistatusmanager.MultiStatusManager;
import com.zoyo.core.common.multistatusmanager.OnStatusChildClickListener;
import com.zoyo.core.mvvm.utils.TypeUtil;

public abstract class BaseActivity<VM extends BaseViewModel> extends RxAppCompatActivity implements IBaseView {

    public VM viewModel;
    public ViewDataBinding dataBinding;
    private MultiStatusManager multiStatusManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        //初始化DataBinding
        initViewDataBinding(savedInstanceState);
        //初始化多状态管理器
        setupMultiStatusManager();
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
     * 初始化多状态管理器
     */
    protected void setupMultiStatusManager() {
        View contentLayout = findViewById(getContentLayoutId());
        multiStatusManager = new MultiStatusManager.Builder(contentLayout == null ? findViewById(android.R.id.content) : contentLayout)
                .setDefaultErrorClickViewVisible(true)
                .setDefaultErrorClickViewText("reload")
                .setDefaultLayoutsBackgroundColor(0xffff0000)
                .setOnStatusChildClickListener(new OnStatusChildClickListener() {
                    @Override
                    public void onEmptyChildClick(View view) {
                        onStatusRefresh();
                    }

                    @Override
                    public void onErrorChildClick(View view) {
                        onStatusRefresh();
                    }

                    @Override
                    public void onCustomerChildClick(View view) {

                    }
                })
                .build();

        viewModel.statusValue.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                switch (integer) {
                    case Constants.STAUTS_LOADING:
                        multiStatusManager.showLoadingLayout();
                        break;
                    case Constants.STATUS_NETWORK_ERROR:
                        multiStatusManager.showErrorLayout();
                        break;
                    case Constants.STATUS_SERVER_ERROR:
                        multiStatusManager.showErrorLayout();
                        break;
                    case Constants.STATUS_SUCCESS:
                        multiStatusManager.showSuccessLayout();
                        break;

                    default:
                        multiStatusManager.showSuccessLayout();
                        break;
                }
            }
        });
    }

    /**
     * 重刷数据
     */
    protected void onStatusRefresh() {
    }

    /**
     * 多状态加载根布局
     *
     * @return
     */
    protected int getContentLayoutId() {
        return 0;
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
