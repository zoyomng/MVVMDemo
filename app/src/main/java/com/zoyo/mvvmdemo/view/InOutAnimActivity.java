package com.zoyo.mvvmdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.TextView;

import com.zoyo.mvvmdemo.R;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/6/28 13:53
 */
public class InOutAnimActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out_anim);
        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.tran_fade);
        getWindow().setEnterTransition(explode);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InOutAnimActivity.this, MainActivity.class));
            }
        });
    }
}
