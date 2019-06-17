package com.zoyo.core.binding.viewadapter.recyclerview;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ViewAdapter1 {
    @BindingAdapter("adapter")
    public static <T> void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        if (adapter == null) {
            return;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }
}
