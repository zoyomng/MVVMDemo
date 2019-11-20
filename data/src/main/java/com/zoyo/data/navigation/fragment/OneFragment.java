package com.zoyo.data.navigation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.zoyo.data.R;

/**
 * @Description: java类作用描述
 * @CreateDate: 2019/10/25 9:35
 */
public class OneFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmnet_navigation_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //接收参数
        String tag = getArguments().getString("tag");
        TextView textView = (TextView) view.findViewById(R.id.textView17);
        textView.setText(tag);

        view.findViewById(R.id.button24).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传递参数
                Bundle bundle = new Bundle();
                bundle.putString("tag", "one");
                Navigation.findNavController(v).navigate(R.id.action_oneFragment_to_twoFragment, bundle);
            }
        });
    }
}
