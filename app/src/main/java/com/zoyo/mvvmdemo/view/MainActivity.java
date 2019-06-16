package com.zoyo.mvvmdemo.view;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.zoyo.core.adapter.recyclerview.BaseAdapter;
import com.zoyo.core.adapter.recyclerview.ItemViewHolder;
import com.zoyo.core.base.BaseActivity;
import com.zoyo.mvvmdemo.BR;
import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.databinding.ActivityMainBinding;
import com.zoyo.mvvmdemo.databinding.ItemMainBinding;
import com.zoyo.mvvmdemo.model.ItemBean;
import com.zoyo.mvvmdemo.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainViewModel> {

    @ColorInt
    private static final int[] BG_COLORS = {
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079
    };


    @Override
    protected int initViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }


    @Override
    public void initData() {
        RecyclerView rvMain = ((ActivityMainBinding) dataBinding).rvMain;
        rvMain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rvMain.setAdapter(new MainAdapter(initItemDatas()));
        BaseAdapter<ItemBean> adapter = new BaseAdapter<ItemBean>(R.layout.item_main, BR.model, initItemDatas()) {
            @Override
            public void addItemClickListener(ItemViewHolder viewHolder, ItemBean itemBean, int position) {
                super.addItemClickListener(viewHolder, itemBean, position);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                    }
                });
                //第一种方式
                ((ItemMainBinding) (viewHolder.getDataBinding())).ivAvatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "childClick", Toast.LENGTH_SHORT).show();

                    }
                });
                //TODO 布局中设置点击事件,BaseAdapter抽取
                

            }
        };
        rvMain.setAdapter(adapter);
    }

    private List<ItemBean> initItemDatas() {
        ArrayList<ItemBean> itemBeans = new ArrayList<>();
        itemBeans.add(new ItemBean("DataBinding的使用", BG_COLORS[0]));
        itemBeans.add(new ItemBean("DataBinding的使用", BG_COLORS[0]));
        itemBeans.add(new ItemBean("DataBinding的使用", BG_COLORS[0]));
        return itemBeans;
    }
}
