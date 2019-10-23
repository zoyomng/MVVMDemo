package com.zoyo.core.common.widget.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;


/**
 * @Description: 输入法的显示与隐藏
 * @CreateDate: 2019/10/8 11:29
 */
public class SoftInputUtil {
    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showSoftInput(@NonNull View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * 显示软键盘
     *
     * @param context
     */
    public static void showSoftInput(@NonNull Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 延迟显示软键盘
     *
     * @param view
     * @param delayMillis
     */
    public static void showSoftInput(@NonNull View view, long delayMillis) {
        view.postDelayed(new Runnable() {

            @Override
            public void run() {
                SoftInputUtil.showSoftInput(view);
            }
        }, delayMillis);
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideSoftInput(@NonNull Activity activity) {
        View view = activity.getWindow().getDecorView().getRootView();
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideSoftInput(@NonNull View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static ViewTreeObserver.OnGlobalLayoutListener observerKeybordWithView(@NonNull Activity activity, @NonNull OnKeyboardChangeListener onKeyboardChangeListener) {
        View decorView = activity.getWindow().getDecorView();

        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            Rect rect = new Rect();
            Rect keyboardRect = new Rect();

            @Override
            public void onGlobalLayout() {
                decorView.getWindowVisibleDisplayFrame(rect);
                int screenHeight = decorView.getRootView().getHeight();
                keyboardRect.set(rect.left, rect.bottom, rect.right, screenHeight);
                boolean isVisible = keyboardRect.height() > 0;
                onKeyboardChangeListener.onKeyboardChange(keyboardRect, isVisible);
            }
        };
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        return onGlobalLayoutListener;
    }

    interface OnKeyboardChangeListener {
        void onKeyboardChange(Rect keyboardBounds, boolean isVisible);
    }
}
