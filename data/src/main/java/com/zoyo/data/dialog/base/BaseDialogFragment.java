package com.zoyo.data.dialog.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @Description: DialogFragment基类
 * @CreateDate: 2019/10/17 10:28
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
        initialize();
        return mRootView;
    }


    /**
     * @return 布局资源id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件/数据
     */
    protected void initialize() {
    }

    /**
     * 通用findViewById
     *
     * @param id  控件Id
     * @param <T> 控件类型
     * @return 控件对象
     */
    @NonNull
    public <T extends View> T findViewById(@IdRes int id) {
        return mRootView.findViewById(id);
    }

}
