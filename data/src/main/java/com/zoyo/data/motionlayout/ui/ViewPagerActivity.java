package com.zoyo.data.motionlayout.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zoyo.data.R;
import com.zoyo.data.motionlayout.adapter.ViewPagerAdapter;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/8/19 15:51
 */
public class ViewPagerActivity extends AppCompatActivity {

    private View motionLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int layout = intent.getIntExtra("layout", R.layout.activity_motion_16);
        setContentView(layout);
        motionLayout = findViewById(R.id.motionLayout);

        if (motionLayout instanceof MotionLayout) {
            ((MotionLayout) motionLayout).setDebugMode(MotionLayout.DEBUG_SHOW_PATH);
        }
        TabLayout tableLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addPage("page 1", R.layout.motion_16_viewpager_page1);
        adapter.addPage("page 2", R.layout.motion_16_viewpager_page2);
        adapter.addPage("page 3", R.layout.motion_16_viewpager_page3);
        viewPager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewPager);

        if (motionLayout instanceof ViewPager.OnPageChangeListener) {
            viewPager.addOnPageChangeListener((ViewPager.OnPageChangeListener) motionLayout);
        }
    }

}
