package com.zoyo.core.adapter.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * ---日期----------维护人-----------变更内容----------
 * 2019/6/16       zozo          zozo
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding dataBinding;

    public ItemViewHolder(@NonNull ViewDataBinding dataBinding) {
        super(dataBinding.getRoot());
        this.dataBinding = dataBinding;
    }

    public ViewDataBinding getDataBinding() {
        return dataBinding;
    }

    public void setDataBinding(ViewDataBinding dataBinding) {
        this.dataBinding = dataBinding;
    }
}
