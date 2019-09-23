package com.zoyo.mvvmdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.zoyo.core.mvvm.base.BaseActivity;
import com.zoyo.data.MultiLayoutActivity;
import com.zoyo.data.motionlayout.ui.ConstraintActivity;
import com.zoyo.mvvmdemo.BR;
import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.viewModel.MainViewModel;

public class MainActivity extends BaseActivity<MainViewModel> {

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

        //第三种方式
        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.tran_explode);
        getWindow().setEnterTransition(explode);

        viewModel.itemIndexClicked.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                switch (integer) {
                    case 1:
                        startActivity(new Intent(MainActivity.this, InOutAnimActivity.class));
                        //1.Activity切换的进出过渡动画
//                        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
                        break;

                    case 2:
                        startActivity(new Intent(MainActivity.this, DownloadActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ConstraintActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, MultiLayoutActivity.class));
                        break;
                    case 5:
                        Intent intent = new Intent(MainActivity.this, MotionActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        break;
                }
            }
        });
    }

}
