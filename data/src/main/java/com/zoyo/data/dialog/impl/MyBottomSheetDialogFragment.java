package com.zoyo.data.dialog.impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.zoyo.data.R;
import com.zoyo.data.dialog.adapter.BottomSheetAdapter;

import java.util.Arrays;

/**
 * @Description: java类作用描述
 * @CreateDate: 2019/11/18 15:33
 */
public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    public static MyBottomSheetDialogFragment newInstance() {
        Bundle args = new Bundle();
        MyBottomSheetDialogFragment fragment = new MyBottomSheetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bottom_sheet, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        Bundle args = getArguments();
        String[] items = args.getStringArray("items");

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        BottomSheetAdapter bottomSheetAdapter = new BottomSheetAdapter(Arrays.asList(items));
        bottomSheetAdapter.setOnItemClickListener(new BottomSheetAdapter.OnItemClickListener() {

            @Override
            public void onItemClick() {
                Toast.makeText(MyBottomSheetDialogFragment.this.getContext(), "点击了", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        recyclerView.setAdapter(bottomSheetAdapter);
    }
}
