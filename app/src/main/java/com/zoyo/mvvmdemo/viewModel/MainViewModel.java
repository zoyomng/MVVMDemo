package com.zoyo.mvvmdemo.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zoyo.common.Utils.ToastUtil;
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
                    ToastUtil.shortShow("click" + i++);

                }
            });
        }
    };

    public MainViewModel(@NonNull Application application) {
        super(application);

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

    public ArrayList<ItemBean> initItemDatas() {
        itemBeans.add(new ItemBean("DataBinding的使用", BG_COLORS[0]));
        itemBeans.add(new ItemBean("跳转Activity的动画", BG_COLORS[1]));
        itemBeans.add(new ItemBean("文件下载", BG_COLORS[2]));
        itemBeans.add(new ItemBean("ConstraintLayout + ConstraintSet 实现动画", BG_COLORS[3]));
        itemBeans.add(new ItemBean("一个Activity加载多个布局", BG_COLORS[4]));
        itemBeans.add(new ItemBean("MotionLayout-Basic(1/3)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-Basic(2/3)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-Basic(3/3)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-自定义属性", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-图片过渡(1/2)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-图片过渡(2/2)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-属性:路径点", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-属性:旋转/缩放/透明度/平移", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-属性:周期(sin/cos/三角形/矩形/落球...)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-协调者布局(1/3)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-协调者布局(2/3)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-协调者布局(3/3)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-拖拽布局(1/2)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-拖拽布局(2/2)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-视差效果", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-ViewPager(1/2)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-ViewPager(2/2)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout的协调行为(1/3)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout的协调行为(2/3)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout的协调行为(3/3)", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-Fragment Transition", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-YouTube", BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-多状态", BG_COLORS[5]));

        return itemBeans;
    }


}
