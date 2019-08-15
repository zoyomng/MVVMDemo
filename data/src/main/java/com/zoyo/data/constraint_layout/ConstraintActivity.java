package com.zoyo.data.constraint_layout;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.zoyo.data.R;

/**
 * @Description: Android ConstraintLayout + ConstraintSet实现动画效果
 * @Author: zoyomng
 * @CreateDate: 2019/7/16 9:39
 * http://www.uwanttolearn.com/android/constraint-layout-animations-dynamic-constraints-ui-java-hell/
 * 实质是: 将第二套布局的属性通过ConstraintSet设置到第一套布局上
 * 只支持大小,位置不支持色值的改变
 */
public class ConstraintActivity extends AppCompatActivity {

    private ConstraintSet applyConstraintSet = new ConstraintSet();
    private ConstraintSet resetConstraintSet = new ConstraintSet();
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        //两个布局中需要动画过渡的控件id必须一致,才能实现动画;如果id不一致,原布局控件不会过渡到新布局控件
        // R.id.constraintLayout:根布局
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        //原布局
        resetConstraintSet.clone(constraintLayout);
        //新布局
        applyConstraintSet.clone(this, R.layout.activity_constraint_apply);
//        applyConstraintSet.clone(constraintLayout);
    }

    public void apply(View view) {

        //设置Margin
//        applyConstraintSet.setMargin(R.id.textView4, ConstraintSet.TOP, 200);
        //设置宽高
//        applyConstraintSet.constrainWidth(R.id.textView4, 600);
        //清除所有约束
//        applyConstraintSet.clear(R.id.textView3);

        TransitionManager.beginDelayedTransition(constraintLayout);
        applyConstraintSet.applyTo(constraintLayout);
    }

    public void reset(View view) {
        TransitionManager.beginDelayedTransition(constraintLayout);

        resetConstraintSet.applyTo(constraintLayout);
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        //新布局
        applyConstraintSet.clone(this, R.layout.activity_constraint_apply);

        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(5000);

        TransitionManager.beginDelayedTransition(constraintLayout, autoTransition);
        applyConstraintSet.applyTo(constraintLayout);
    }
}
