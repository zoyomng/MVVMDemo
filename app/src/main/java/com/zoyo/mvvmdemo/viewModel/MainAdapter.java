package com.zoyo.mvvmdemo.viewModel;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.databinding.ItemMainBinding;
import com.zoyo.mvvmdemo.model.ItemBean;

import java.util.List;

/**
 * ---日期----------维护人-----------变更内容----------
 * 2019/6/16       zozo          zozo
 * adapter的原始写法
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemViewHolder> {
    private List<ItemBean> list;

    public MainAdapter(List<ItemBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMainBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_main, viewGroup, false);
        ItemViewHolder holder = new ItemViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int i) {
        viewHolder.getBinding().setModel(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {


        private ItemMainBinding binding;

        public ItemViewHolder(@NonNull ItemMainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ItemMainBinding getBinding() {
            return this.binding;
        }

        public void setBinding(ItemMainBinding binding) {
            this.binding = binding;
        }

    }

}