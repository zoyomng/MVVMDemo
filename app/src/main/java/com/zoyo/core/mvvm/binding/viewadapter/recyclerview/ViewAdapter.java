package com.zoyo.core.mvvm.binding.viewadapter.recyclerview;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAdapter {


    /**
     * 自己写好RecyclerView.Adapter
     * 在xml布局中使用:
     * <android.support.v7.widget.RecyclerView
     * app:adapter="@{viewModel.adapter}"
     * />
     *
     * @param recyclerView
     * @param adapter
     * @param <T>
     */
    @BindingAdapter(value = {"adapter", "layoutManager"})
    public static <T> void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
        if (adapter == null) {
            return;
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    /**
     * TODO 有bug
     * 直接在xml布局中设置item布局,变量ViewModel的id(创建BaseAdapter中使用),data即可自动生成RecyclerView.Adapter
     * <android.support.v7.widget.RecyclerView
     * app:data="@{viewModel.itemBeans}"
     * app:itemLayoutRes="@{@layout/item_main}"
     * app:variableId="@{viewModel.getVariableId()}" />
     *
     * @param recyclerView
     * @param itemLayoutRes
     * @param variableId
     * @param data
     * @param <T>
     */
//    @BindingAdapter(value = {"itemLayoutRes", "variableId", "data", "layoutManager"})
//    public static <T> void setAdapter(RecyclerView recyclerView, @LayoutRes int itemLayoutRes, int variableId, List<T> data, RecyclerView.LayoutManager layoutManager) {
//        if (recyclerView.getAdapter() == null) {
//            BaseAdapter<T> tBaseAdapter = new BaseAdapter<>(itemLayoutRes, variableId, data);
//            recyclerView.setLayoutManager(layoutManager);
//            recyclerView.setAdapter(tBaseAdapter);
//        }
//    }
}
