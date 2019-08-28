package com.zoyo.data.motionlayout.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zoyo.data.R;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/8/23 10:34
 */
public class MainFragment extends Fragment {

    private static MainFragment mainFragment;

    public static MainFragment getInstance() {
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.motion_21_main_fragment, container, false);
    }
}
