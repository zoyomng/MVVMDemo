package com.zoyo.mvvmdemo.viewModel;

import android.app.Application;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.zoyo.core.base.BaseViewModel;
import com.zoyo.core.base.OnDataCallback;
import com.zoyo.core.binding.adapter.recyclerview.BaseAdapter;
import com.zoyo.core.binding.adapter.recyclerview.ItemViewHolder;
import com.zoyo.mvvmdemo.BR;
import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.databinding.ItemMainBinding;
import com.zoyo.mvvmdemo.model.ItemBean;
import com.zoyo.mvvmdemo.model.bean.MainResponse;
import com.zoyo.mvvmdemo.model.repository.MainRepository;
import com.zoyo.net.Response;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends BaseViewModel<MainRepository> {

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
    public ArrayList<ItemBean> itemBeans = new ArrayList<>();

    public BaseAdapter<ItemBean> adapter = new BaseAdapter<ItemBean>(R.layout.item_main, BR.model, initItemDatas()) {
        @Override
        public void addItemClickListener(ItemViewHolder viewHolder, ItemBean itemBean, int position) {
            super.addItemClickListener(viewHolder, itemBean, position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplication(), "click", Toast.LENGTH_SHORT).show();
                }
            });
            ((ItemMainBinding) (viewHolder.getDataBinding())).ivAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplication(), "childClick", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    public MainViewModel(@NonNull Application application) {
        super(application);

//        initItemDatas();
        model.request(new OnDataCallback<Response<MainResponse>>() {
            @Override
            public void onData(Response<MainResponse> mainResponseResponse) {
                System.out.println("===============" + mainResponseResponse.toString());
            }
        });


    }

    public int getVariableId() {
        return BR.viewModel;
    }

    private List<ItemBean> initItemDatas() {
        itemBeans.add(new ItemBean("DataBinding的使用", BG_COLORS[0]));
        itemBeans.add(new ItemBean("DataBinding的使用", BG_COLORS[0]));
        itemBeans.add(new ItemBean("DataBinding的使用", BG_COLORS[0]));
        return itemBeans;
    }


}
