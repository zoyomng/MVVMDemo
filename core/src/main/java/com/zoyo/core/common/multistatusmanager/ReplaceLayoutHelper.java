package com.zoyo.core.common.multistatusmanager;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

/**
 * @Description: 替换布局的帮助类
 * @CreateDate: 2019/9/18 15:25
 */
class ReplaceLayoutHelper {
    /**
     * 需要替换的View
     */
    private View contentLayout;
    /**
     * contentLayout的布局参数
     */
    private ViewGroup.LayoutParams params;
    /**
     * contentLayout的父ViewGroup
     */
    private ViewGroup parentLayout;
    /**
     * contentLayout在parentLayout中的位置
     */
    private int viewIndex;
    /**
     * 当前显示的View
     */
    private View currentLayout;

    public ReplaceLayoutHelper(@NonNull View contentLayout) {
        this.contentLayout = contentLayout;
        getContentLayoutParams();
    }

    private void getContentLayoutParams() {
        this.params = contentLayout.getLayoutParams();
        if (contentLayout.getParent() != null) {
            //有父控件
            this.parentLayout = (ViewGroup) contentLayout.getParent();
        } else {
            //认为contentLayout是activity的根布局,所以它的父控件是android.R.id.content
            this.parentLayout = contentLayout.getRootView().findViewById(android.R.id.content);
        }
        if (parentLayout == null) {
            //以上两种都没获取到父控件,contentLayout非activity的根布局,父控件就是自己
            if (contentLayout instanceof ViewGroup) {
                parentLayout = (ViewGroup) contentLayout;
                this.viewIndex = 0;
            } else {
                //否则contentLayout是一个非ViewGroup的根布局,该情况没办法替换布局,暂不支持
                throw new IllegalArgumentException("Parameter error：StatusLayoutManager#Build#with()，The argument cannot be a root layout of a non-ViewGroup.");
            }
        } else {
            int count = parentLayout.getChildCount();
            for (int index = 0; index < count; index++) {
                if (contentLayout == parentLayout.getChildAt(index)) {
                    //获取contentLayout在ParentLayout中的位置
                    this.viewIndex = index;
                    break;
                }
            }
        }
        this.currentLayout = this.contentLayout;
    }

    public void showStatusLayout(View view) {
        if (view == null) {
            return;
        }
        if (currentLayout != view) {
            currentLayout = view;
            ViewGroup parent = (ViewGroup) view.getParent();
            //去除view的父view,才能添加到别的ViewGroup中
            if (parent != null) {
                parent.removeView(view);
            }
            //替换 = 移除 + 添加
            parentLayout.removeViewAt(viewIndex);
            parentLayout.addView(view, viewIndex, params);
        }
    }

    public void restoreLayout() {
        showStatusLayout(contentLayout);
    }
}
