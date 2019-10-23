package com.zoyo.data.dialog.impl;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.zoyo.data.R;

/**
 * @Description: java类作用描述
 * 两种自定义方式:
 * 1.重写onCreateView()
 * 2.重写onCreateDialog()
 * @CreateDate: 2019/10/16 14:14
 */
public class CommonDialogFragment extends DialogFragment {

    private View mRootView;

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (mRootView == null) {
//            mRootView = inflater.inflate(R.layout.dialog_confirm, container, false);
//        }
//        return mRootView;
//    }


    private String[] items = new String[]{"北京", "天津", "河北", "河南", "山东", "上海", "山西",
            "陕西", "贵州", "云南", "江苏", "浙江", "广东", "深圳", "湖南"};
    private boolean[] checked = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

    /**
     * setMessage()与setItems()冲突
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle("标题")
//                .setMessage("message")
                .setCancelable(false)
                .setIcon(R.drawable.car)
//                .setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getContext(), items[which], Toast.LENGTH_SHORT).show();
//                    }
//                })
                .setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(getContext(), items[which] + (isChecked ? "被选中" : "被取消"), Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
    }
}
