package com.zoyo.mvvmdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.zoyo.core.base.BaseActivity;
import com.zoyo.data.MultiLayoutActivity;
import com.zoyo.data.constraint_layout.ConstraintActivity;
import com.zoyo.data.constraint_layout.MotionLayoutActivity;
import com.zoyo.mvvmdemo.BR;
import com.zoyo.mvvmdemo.R;
import com.zoyo.mvvmdemo.viewModel.MainViewModel;

public class MainActivity extends BaseActivity<MainViewModel> {

    private int layoutResId;

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
//        RecyclerView rvMain = ((ActivityMainBinding) dataBinding).rvMain;
//        rvMain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rvMain.setAdapter(new MainAdapter(initItemDatas()));
//        BaseAdapter<ItemBean> adapter = new BaseAdapter<ItemBean>(R.layout.item_main, BR.model, initItemDatas()) {
//            @Override
//            public void addItemClickListener(ItemViewHolder viewHolder, ItemBean itemBean, int position) {
//                super.addItemClickListener(viewHolder, itemBean, position);
//                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                //第一种方式
//                ((ItemMainBinding) (viewHolder.getDataBinding())).ivAvatar.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, "childClick", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//                //TODO 布局中设置点击事件,BaseAdapter抽取
//
//
//            }
//        };
//        rvMain.setAdapter(adapter);

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
                        startActivity(R.layout.activity_motion_01);
                        break;
                    case 6:
                        startActivity(R.layout.activity_motion_02);
                        break;
                    case 7:
                        startActivity(R.layout.activity_motion_03);
                        break;
                    case 8:
                        startActivity(R.layout.activity_motion_04);
                        break;
                    case 9:
                        startActivity(R.layout.activity_motion_05);
                        break;
                    case 10:
                        startActivity(R.layout.activity_motion_06);
                        break;
                    case 11:
                        startActivity(R.layout.activity_motion_07);
                        break;
                    case 12:
                        startActivity(R.layout.activity_motion_08);
                        break;
                    case 13:
                        startActivity(R.layout.activity_motion_09);
                        break;
                    case 14:
                        startActivity(R.layout.activity_motion_10);
                        break;
                    case 15:
                        startActivity(R.layout.activity_motion_08);
                        break;
                    case 16:
                        startActivity(R.layout.activity_motion_08);
                        break;
                    case 17:
                        startActivity(R.layout.activity_motion_08);
                        break;

                    default:
                        break;
                }
            }
        });
    }

    private void startActivity(int layoutResId) {
        Intent intent = new Intent(MainActivity.this, MotionLayoutActivity.class);
        intent.putExtra("layoutResId", layoutResId);
        startActivity(intent);
    }

}
