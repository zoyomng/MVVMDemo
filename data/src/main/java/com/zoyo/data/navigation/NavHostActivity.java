package com.zoyo.data.navigation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zoyo.data.R;

import static androidx.navigation.Navigation.findNavController;

/**
 * @Description: java类作用描述
 * @CreateDate: 2019/10/28 10:15
 */
public class NavHostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    /**将back键点击事件委托出去,如果当前Fragment不是在最顶端,则返回上一个Fragment
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return findNavController(this, R.id.fragment_nav_host).navigateUp();
    }
}
