package com.zoyo.mvvmdemo.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.zoyo.core.mvvm.base.BaseActivity;
import com.zoyo.data.motionlayout.ui.FragmentExampleActivity;
import com.zoyo.data.motionlayout.ui.MotionLayoutActivity;
import com.zoyo.data.motionlayout.ui.ViewPagerActivity;
import com.zoyo.data.motionlayout.ui.YouTubeActivity;
import com.zoyo.mvvmdemo.BR;
import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.viewModel.MotionViewModel;

/**
 * @Description: MotionLayout的使用介绍
 * @CreateDate: 2019/9/23 13:53
 */
public class MotionActivity extends BaseActivity<MotionViewModel> {


    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_motion;
    }

    @Override
    protected int initViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getContentLayoutId() {
        return R.id.contentLayout;
    }
    @Override
    public void initData() {
        super.initData();
        viewModel.itemIndexClicked.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case 0:
                        startActivity(R.layout.activity_motion_01);
                        break;
                    case 1:
                        startActivity(R.layout.activity_motion_02);
                        break;
                    case 2:
                        startActivity(R.layout.activity_motion_03);
                        break;
                    case 3:
                        startActivity(R.layout.activity_motion_04);
                        break;
                    case 4:
                        startActivity(R.layout.activity_motion_05);
                        break;
                    case 5:
                        startActivity(R.layout.activity_motion_06);
                        break;
                    case 6:
                        startActivity(R.layout.activity_motion_07);
                        break;
                    case 7:
                        startActivity(R.layout.activity_motion_08);
                        break;
                    case 8:
                        startActivity(R.layout.activity_motion_09);
                        break;
                    case 9:
                        startActivity(R.layout.activity_motion_10);
                        break;
                    case 10:
                        startActivity(R.layout.activity_motion_11);
                        break;
                    case 11:
                        startActivity(R.layout.activity_motion_12);
                        break;
                    case 12:
                        startActivity(R.layout.activity_motion_13);
                        break;
                    case 13:
                        startActivity(R.layout.activity_motion_14);
                        break;
                    case 14:
                        startActivity(R.layout.activity_motion_15);
                        break;
                    case 15:
                        startActivity(new Intent(MotionActivity.this, ViewPagerActivity.class).putExtra("layout", R.layout.activity_motion_16));
                        break;
                    case 16:
                        startActivity(new Intent(MotionActivity.this, ViewPagerActivity.class).putExtra("layout", R.layout.activity_motion_17));
                        break;
                    case 17:
                        //与activity_motion_10对比学习,使用MotionLayout的协调行为取代Coordinatorlayout的作用,从而实现AppBar+toolBar+ScrollView的协调
                        //FloatingActionButton协调行为
                        startActivity(R.layout.activity_motion_18);
                        break;
                    case 18:
                        //background:width和height设置成match与200dp的区别(对比motion_scene_18_header.xml和motion_scene_19_header.xml)
                        startActivity(R.layout.activity_motion_19);
                        break;
                    case 19:
                        //FlyingBounceHelper--进入页面时会出现动画(未实现,原因待查)
                        startActivity(R.layout.activity_motion_20);
                        break;
                    case 20:
                        //Fragment切换
                        startActivity(new Intent(MotionActivity.this, FragmentExampleActivity.class));
                        break;
                    case 21:
                        //Fragment切换
                        startActivity(new Intent(MotionActivity.this, YouTubeActivity.class));
                        break;
                    case 22:
                        startActivity(R.layout.activity_motion_23);
                        break;

                    default:
                        break;
                }
            }
        });

    }


    private void startActivity(int layoutResId) {
        Intent intent = new Intent(MotionActivity.this, MotionLayoutActivity.class);
        intent.putExtra("layoutResId", layoutResId);
        startActivity(intent);
    }
}
