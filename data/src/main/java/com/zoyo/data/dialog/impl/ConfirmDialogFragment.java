package com.zoyo.data.dialog.impl;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zoyo.data.R;
import com.zoyo.data.dialog.base.BaseDialogFragment;
import com.zoyo.data.dialog.base.DialogListener;

/**
 * @Description: 确认弹出框
 * @CreateDate: 2019/10/16 10:09
 */
public class ConfirmDialogFragment extends BaseDialogFragment {
    private DialogListener.OnClickListener mPositiveButtonListener;
    private CharSequence mPositiveButtonText;
    private DialogListener.OnClickListener mNegativeButtonListener;
    private CharSequence mNegativeButtonText;
    private CharSequence mMessage;
    private boolean cancelable;

    private ConfirmDialogFragment(Builder builder) {
        this.mMessage = builder.message;
        this.mNegativeButtonListener = builder.negativeButtonListener;
        this.mNegativeButtonText = builder.negativeText;
        this.mPositiveButtonListener = builder.positiveButtonListener;
        this.mPositiveButtonText = builder.positiveText;
        this.cancelable = builder.cancelable;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_confirm;
    }

    @Override
    protected void initialize() {
        super.initialize();

        TextView tvNegative = (TextView) findViewById(R.id.tv_negative);
        TextView tvPositive = (TextView) findViewById(R.id.tv_positive);
        TextView tvMessage = (TextView) findViewById(R.id.tv_message);
        tvNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNegativeButtonListener != null) {
                    mNegativeButtonListener.onClick(ConfirmDialogFragment.this);
                }
            }
        });
        tvPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPositiveButtonListener != null) {
                    mPositiveButtonListener.onClick(ConfirmDialogFragment.this);
                }
            }
        });
        tvNegative.setText(mNegativeButtonText);
        tvPositive.setText(mPositiveButtonText);
        tvMessage.setText(mMessage);

        setCancelable(cancelable);

    }

    public static class Builder {

        private CharSequence positiveText;
        private DialogListener.OnClickListener positiveButtonListener;
        private CharSequence negativeText;
        private DialogListener.OnClickListener negativeButtonListener;
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

        public Builder setNegativeButtonListener(CharSequence text, DialogListener.OnClickListener negativeButtonListener) {
            this.negativeText = text;
            this.negativeButtonListener = negativeButtonListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public BaseDialogFragment create() {
            return new ConfirmDialogFragment(this);
        }

    }
}
