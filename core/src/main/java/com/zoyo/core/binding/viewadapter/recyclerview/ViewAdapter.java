package com.zoyo.core.binding.viewadapter.recyclerview;

import android.databinding.BindingAdapter;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import com.zoyo.core.binding.adapter.recyclerview.BaseAdapter;

import java.util.List;

public class ViewAdapter {
    @BindingAdapter(value = {"itemLayoutRes", "variableId", "data"})
    public static <T> void setAdapter(RecyclerView recyclerView, @LayoutRes int itemLayoutRes, int variableId, List<T> data) {
        if (recyclerView.getAdapter() == null) {
            BaseAdapter<T> tBaseAdapter = new BaseAdapter<>(itemLayoutRes, variableId, data);
            recyclerView.setAdapter(tBaseAdapter);
        }
    }
}
