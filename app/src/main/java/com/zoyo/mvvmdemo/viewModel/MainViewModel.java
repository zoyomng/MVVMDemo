package com.zoyo.mvvmdemo.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zoyo.core.common.net.Response;
import com.zoyo.core.mvvm.base.BaseViewModel;
import com.zoyo.core.mvvm.base.OnDataCallback;
import com.zoyo.core.mvvm.binding.adapter.recyclerview.BaseAdapter;
import com.zoyo.core.mvvm.binding.adapter.recyclerview.ItemViewHolder;
import com.zoyo.mvvmdemo.BR;
import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.app.Constants;
import com.zoyo.mvvmdemo.databinding.ItemMainBinding;
import com.zoyo.mvvmdemo.model.bean.ItemBean;
import com.zoyo.mvvmdemo.model.bean.MainResponse;
import com.zoyo.mvvmdemo.model.repository.MainRepository;

import java.util.ArrayList;

public class MainViewModel extends BaseViewModel<MainRepository> {

    int i = 0;

    //绑定数据
    public ArrayList<ItemBean> itemBeans = new ArrayList<>();
    public MutableLiveData<Integer> itemIndexClicked = new MutableLiveData<>();

    //第一种代码中创建adapter,在布局中设置adapter
    public BaseAdapter<ItemBean> adapter = new BaseAdapter<ItemBean>(R.layout.item_main, BR.model, initItemDatas()) {
        @Override
        public void addItemClickListener(ItemViewHolder viewHolder, ItemBean itemBean, final int position) {
            super.addItemClickListener(viewHolder, itemBean, position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemIndexClicked.setValue(position);
                }
            });
            ((ItemMainBinding) (viewHolder.getDataBinding())).ivAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    };

    public MainViewModel(@NonNull Application application) {
        super(application);

        request();
    }

    public void request() {
        repository.request(statusValue, new OnDataCallback<Response<MainResponse>>() {
            @Override
            public void onData(Response<MainResponse> mainResponseResponse) {
                System.out.println("===============" + mainResponseResponse.toString());
            }
        });
    }

    public ArrayList<ItemBean> initItemDatas() {
        itemBeans.add(new ItemBean("DataBinding的使用", Constants.BG_COLORS[0]));
        itemBeans.add(new ItemBean("跳转Activity的动画", Constants.BG_COLORS[1]));
        itemBeans.add(new ItemBean("文件下载", Constants.BG_COLORS[2]));
        itemBeans.add(new ItemBean("ConstraintLayout + ConstraintSet 实现动画", Constants.BG_COLORS[3]));
        itemBeans.add(new ItemBean("一个Activity加载多个布局", Constants.BG_COLORS[4]));
        itemBeans.add(new ItemBean("MotionLayout使用介绍", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("Toast", Constants.BG_COLORS[6]));
        itemBeans.add(new ItemBean("DialogFragment的使用", Constants.BG_COLORS[7]));
        itemBeans.add(new ItemBean("Android原生组件Navigation的使用", Constants.BG_COLORS[8]));
        itemBeans.add(new ItemBean("数据格式化", Constants.BG_COLORS[9]));

        return itemBeans;
    }
}
