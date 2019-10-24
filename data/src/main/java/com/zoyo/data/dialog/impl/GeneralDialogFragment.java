package com.zoyo.data.dialog.impl;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zoyo.data.R;
import com.zoyo.data.dialog.base.BaseDialogFragment;
import com.zoyo.data.dialog.base.DialogListener;

/**
 * @Description: 一般的提示框
 * @CreateDate: 2019/10/16 14:14
 */
public class GeneralDialogFragment extends BaseDialogFragment {

    private DialogListener.OnClickListener mPositiveButtonListener;
    private CharSequence mPositiveButtonText;
    private CharSequence mMessage;
    private boolean cancelable;

    private GeneralDialogFragment(Builder builder) {
        this.mMessage = builder.message;
        this.mPositiveButtonListener = builder.positiveButtonListener;
        this.mPositiveButtonText = builder.positiveText;
        this.cancelable = builder.cancelable;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_general;
    }

    @Override
    protected void initialize() {
        super.initialize();
        TextView tvPositive = (TextView) findViewById(R.id.tv_positive);
        TextView tvMessage = (TextView) findViewById(R.id.tv_message);

        if (mPositiveButtonListener != null) {
            tvPositive.setVisibility(View.VISIBLE);
            tvPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPositiveButtonListener != null) {
                        mPositiveButtonListener.onClick(GeneralDialogFragment.this);
                    }
                }
            });
            tvPositive.setText(mPositiveButtonText);
        } else {
            tvPositive.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, 3000);
            tvPositive.setVisibility(View.GONE);
        }

        tvMessage.setText(mMessage);
        setCancelable(cancelable);
    }

    public static class Builder {

        private CharSequence positiveText;
        private DialogListener.OnClickListener positiveButtonListener;
        private CharSequence message;
        private boolean cancelable;

        public Builder setMessage(@Nullable CharSequence message) {
            this.message = message;
            return this;
        }

        public Builder setPositiveButtonListener(CharSequence text, DialogListener.OnClickListener positiveButtonListener) {
            this.positiveText = text;
            this.positiveButtonListener = positiveButtonListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public BaseDialogFragment create() {
            return new GeneralDialogFragment(this);
        }

    }
}
