package com.zoyo.mvvmdemo.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zoyo.core.widget.toast.Toasty;
import com.zoyo.mvvmdemo.R;

import static android.graphics.Typeface.BOLD_ITALIC;

/**
 * @Description: java类作用描述
 * @CreateDate: 2019/9/25 11:25
 */
public class ToastTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_test);
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_error_toast:
                Toasty.error(ToastTestActivity.this, getText(R.string.error_message), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_info_toast:
                Toasty.info(ToastTestActivity.this, getText(R.string.info_message), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_info_toast_with_formatting:
                Toasty.info(ToastTestActivity.this, getFormattedMessage(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_normal_toast_w_icon:
                Toasty.normal(ToastTestActivity.this, getText(R.string.normal_message_with_icon), R.drawable.ic_pets_white_48dp, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_normal_toast_wo_icon:
                Toasty.normal(ToastTestActivity.this, getText(R.string.normal_message_without_icon), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_success_toast:
                Toasty.success(ToastTestActivity.this, getText(R.string.success_message), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_warning_toast:
                Toasty.warning(ToastTestActivity.this, getText(R.string.warning_message), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_custom_config:
                Toasty.Config.getInstance()
                        .setTypeface(Typeface.createFromAsset(getAssets(), "PCap Terminal.otf"))
                        .apply();
                Toasty.build(ToastTestActivity.this, getText(R.string.custom_message), android.R.color.holo_green_light, getResources().getDrawable(R.mipmap.laptop512),
                        android.R.color.black, Toast.LENGTH_SHORT).show();
                Toasty.Config.reset();
                break;

            default:
                break;
        }
    }

    private CharSequence getFormattedMessage() {
        String prefix = "Formatted ";
        String highlight = "bold italic";
        String suffix = " text";
        SpannableStringBuilder ssb = new SpannableStringBuilder(prefix).append(highlight).append(suffix);
        int prefixLen = prefix.length();
        ssb.setSpan(new StyleSpan(BOLD_ITALIC), prefixLen, prefixLen + highlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }
}
