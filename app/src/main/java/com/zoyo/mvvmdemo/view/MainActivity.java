package com.zoyo.mvvmdemo.view;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Transition;
import android.transition.TransitionInflater;

import com.zoyo.core.base.BaseActivity;
import com.zoyo.data.ConstraintActivity;
import com.zoyo.data.MultiLayoutActivity;
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


                    default:
                        break;
                }
            }
        });
    }

}
