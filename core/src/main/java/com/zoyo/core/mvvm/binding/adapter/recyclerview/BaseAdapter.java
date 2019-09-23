package com.zoyo.core.mvvm.binding.adapter.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * ---日期----------维护人-----------变更内容----------
 * 2019/6/16       zozo          zozo
 */
public class BaseAdapter<E> extends RecyclerView.Adapter<ItemViewHolder> {

    public List<E> data;
    public int defaultLayoutRes;
    public int variableId;
    public OnItemClickListener onItemClickListener;


    /**
     * item布局不同,item中变量不同,数据不同
     *
     * @param layoutRes  条目布局
     * @param variableId 变量
     * @param data       数据
     */
    public BaseAdapter(@LayoutRes int layoutRes, int variableId, List<E> data) {
        this.defaultLayoutRes = layoutRes;
        this.variableId = variableId;
        this.data = data;
    }

    /**
     * @param viewGroup
     * @param layoutRes (viewType)  原来应该返回的是viewType (多种布局中,决定item使用哪一种布局的参数),现在改成条目布局的资源(R.layout.item_***)
     * @return
     */
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int layoutRes) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), layoutRes, viewGroup, false);
        return new ItemViewHolder(binding);
    }

    /**
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {

        viewHolder.getDataBinding().setVariable(variableId, data.get(position));

        //条目点击事件
        addItemClickListener(viewHolder, data.get(position), position);

        viewHolder.getDataBinding().executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    /**
     * 多种类型布局
     *
     * @param position
     * @return 直接返回条目布局
     */
    @Override
    public int getItemViewType(int position) {
        return getItemLayoutRes(data.get(position));
    }

    @LayoutRes
    private int getItemLayoutRes(E model) {
        return defaultLayoutRes;
    }


    /**
     * 其实就是将在onBindViewHolder()中初始化子控件的点击事件的操作交给外部处理
     *
     * @param viewHolder
     * @param e
     * @param position
     */
    public void addItemClickListener(ItemViewHolder viewHolder, E e, int position) {

    }

    /**
     * @param newData
     */
    public void onItemDataChangeed(List<E> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    public void onItemRangeChanged(List<E> newData, int positionStart, int itemCount) {
        this.data = newData;
        notifyItemRangeChanged(positionStart, itemCount);
    }

    public void onItemRangeInserted(List<E> newData, int positionStart, int itemCount) {
        this.data = newData;
        notifyItemRangeInserted(positionStart, itemCount);
    }

    public void onItemRangeRemoved(List<E> newData, int positionStart, int itemCount) {
        this.data = newData;
        notifyItemRangeRemoved(positionStart, itemCount);
    }

}
