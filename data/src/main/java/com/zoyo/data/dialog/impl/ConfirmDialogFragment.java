package com.zoyo.data.dialog.impl;

import android.widget.TextView;

import com.zoyo.data.R;
import com.zoyo.data.dialog.base.BaseDialogFragment;

/**
 * @Description: 确认弹出框
 * @CreateDate: 2019/10/16 10:09
 */
public class ConfirmDialogFragment extends BaseDialogFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_confirm;
    }

    @Override
    protected void initialize() {
        super.initialize();
        TextView tvCancle = (TextView) findViewById(R.id.cancle);
        TextView tvSure = (TextView) findViewById(R.id.sure);

    }
}
