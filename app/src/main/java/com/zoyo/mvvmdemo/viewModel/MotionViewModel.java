package com.zoyo.mvvmdemo.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zoyo.core.mvvm.base.BaseViewModel;
import com.zoyo.core.mvvm.binding.adapter.recyclerview.BaseAdapter;
import com.zoyo.core.mvvm.binding.adapter.recyclerview.ItemViewHolder;
import com.zoyo.mvvmdemo.BR;
import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.app.Constants;
import com.zoyo.mvvmdemo.model.bean.ItemBean;
import com.zoyo.mvvmdemo.model.repository.MotionRespository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @CreateDate: 2019/9/23 13:54
 */
public class MotionViewModel extends BaseViewModel<MotionRespository> {

    //绑定数据
    public ArrayList<ItemBean> itemBeans = new ArrayList<>();
    public MutableLiveData<Integer> itemIndexClicked = new MutableLiveData<>();

    public MotionViewModel(@NonNull Application application) {
        super(application);
    }

    public BaseAdapter adapter = new BaseAdapter<ItemBean>(R.layout.item_main, BR.model, initItemDatas()) {
        @Override
        public void addItemClickListener(ItemViewHolder viewHolder, ItemBean itemBean, int position) {
            super.addItemClickListener(viewHolder, itemBean, position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemIndexClicked.setValue(position);
                }
            });
        }
    };

    private List<ItemBean> initItemDatas() {

        itemBeans.add(new ItemBean("MotionLayout-Basic(1/3)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-Basic(2/3)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-Basic(3/3)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-自定义属性", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-图片过渡(1/2)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-图片过渡(2/2)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-属性:路径点", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-属性:旋转/缩放/透明度/平移", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-属性:周期(sin/cos/三角形/矩形/落球...)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-协调者布局(1/3)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-协调者布局(2/3)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-协调者布局(3/3)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-拖拽布局(1/2)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-拖拽布局(2/2)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-视差效果", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-ViewPager(1/2)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-ViewPager(2/2)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout的协调行为(1/3)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout的协调行为(2/3)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout的协调行为(3/3)", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-Fragment Transition", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-YouTube", Constants.BG_COLORS[5]));
        itemBeans.add(new ItemBean("MotionLayout-多状态", Constants.BG_COLORS[5]));

        return itemBeans;
    }
}
