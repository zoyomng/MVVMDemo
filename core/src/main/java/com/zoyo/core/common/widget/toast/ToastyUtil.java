package com.zoyo.core.common.widget.toast;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import com.zoyo.core.R;

/**
 * @Description: Toasty工具类
 * 9.png-左上定义伸缩区域,右下定义文本区域
 * Drawable.setColorFilter()渲染图片颜色;可用于一张图片,选中与非选中两种状态的情形下轻松解决,减少图片的导入
 * PorterDuff.Mode多种渲染模式(简单说:取并集,合集...)
 * @CreateDate: 2019/9/25 14:55
 */
class ToastyUtil {
    public static Drawable tint9PatchDrawableFrame(@NonNull Context context, @ColorInt int tintColor) {
        NinePatchDrawable toastDrawable = (NinePatchDrawable) getDrawable(context, R.drawable.toast_frame);
        return tintIcon(toastDrawable, tintColor);
    }

    public static Drawable tintIcon(@NonNull Drawable drawable, @ColorInt int tintColor) {
        drawable.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        return AppCompatResources.getDrawable(context, id);
    }

    /**
     * 设置背景图片
     *
     * @param view
     * @param drawable
     */
    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static int getColor(Context context, @ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }
}
