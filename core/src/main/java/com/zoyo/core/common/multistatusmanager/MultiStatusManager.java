package com.zoyo.core.common.multistatusmanager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.zoyo.core.R;

/**
 * @Description: 加载中, 加载错误, 空数据...页面显示的管理器
 * https://github.com/Bakumon/StatusLayoutManager
 * @CreateDate: 2019/9/18 14:28
 */
public class MultiStatusManager {


    /**
     * 三种默认布局 ID
     */
    private static final int DEFAULT_LOADING_LAYOUT_ID = R.layout.layout_status_layout_manager_loading;
    private static final int DEFAULT_EMPTY_LAYOUT_ID = R.layout.layout_status_layout_manager_empty;
    private static final int DEFAULT_ERROR_LAYOUT_ID = R.layout.layout_status_layout_manager_error;

    /**
     * 默认布局中可点击的 view ID
     */
    private static final int DEFAULT_EMPTY_CLICKED_ID = R.id.status_layout_manager_bt_status_empty_click;
    private static final int DEFAULT_ERROR_CLICKED_ID = R.id.status_layout_manager_bt_status_error_click;

    /**
     * 默认颜色
     */
    private static final int DEFAULT_CLICKED_TEXT_COLOR = R.color.status_layout_manager_click_view_text_color;
    private static final int DEFAULT_BACKGROUND_COLOR = R.color.status_layout_manager_background_color;

    /**
     * 默认图片
     */
    private static final int DEFAULT_EMPTY_IMG_ID = R.drawable.status_layout_manager_ic_empty;
    private static final int DEFAULT_ERROR_IMG_ID = R.drawable.status_layout_manager_ic_error;

    //顶层布局
    private View contentLayout;
    @LayoutRes
    private int loadingLayoutId;
    private View loadingLayout;
    private String loadingTip;

    @LayoutRes
    private int emptyLayoutId;
    private View emptyLayout;
    private String emptyTip;

    @IdRes
    private int emptyClickViewId;
    private String emptyClickViewTip;
    private int emptyClickViewTextColor;
    private boolean isEmptyClickViewVisible;
    @DrawableRes
    private int emptyImgId;


    @LayoutRes
    private int errorLayoutId;
    private View errorLayout;
    private String errorText;

    @IdRes
    private int errorClickViewId;
    private String errorClickViewTip;
    private int errorClickViewTextColor;
    private boolean isErrorClickViewVisible;
    @DrawableRes
    private int errorImgId;

    private int defaultBackgroundColor;
    private OnStatusChildClickListener onStatusChildClickListener;
    private LayoutInflater inflater;
    private ReplaceLayoutHelper replaceLayoutHelper;


    private MultiStatusManager(Builder builder) {

        this.contentLayout = builder.contentLayout;

        this.loadingLayoutId = builder.loadingLayoutId;
        this.loadingLayout = builder.loadingLayout;
        this.loadingTip = builder.loadingTip;

        this.emptyClickViewId = builder.emptyClickViewId;
        this.emptyLayoutId = builder.emptyLayoutId;
        this.emptyLayout = builder.emptyLayout;
        this.emptyTip = builder.emptyTip;
        this.emptyClickViewTip = builder.emptyClickViewTip;
        this.emptyClickViewTextColor = builder.emptyClickViewTextColor;
        this.isEmptyClickViewVisible = builder.isEmptyClickViewVisible;
        this.emptyImgId = builder.emptyImgId;

        this.errorClickViewId = builder.errorClickViewId;
        this.errorLayoutId = builder.errorLayoutId;
        this.errorLayout = builder.errorLayout;
        this.errorText = builder.errorText;
        this.errorClickViewTip = builder.errorClickViewTip;
        this.errorClickViewTextColor = builder.errorClickViewTextColor;
        this.isErrorClickViewVisible = builder.isErrorClickViewVisible;
        this.errorImgId = builder.errorImgId;

        this.defaultBackgroundColor = builder.defaultBackgroundColor;

        this.onStatusChildClickListener = builder.onStatusChildClickListener;

        this.replaceLayoutHelper = new ReplaceLayoutHelper(contentLayout);
    }

    private View inflate(@LayoutRes int layoutResId) {
        if (inflater == null) {
            inflater = LayoutInflater.from(contentLayout.getContext());
        }
        return inflater.inflate(layoutResId, null);
    }

    /**
     * 显示原有布局
     */
    public void showSuccessLayout() {
        replaceLayoutHelper.restoreLayout();
    }

    /**
     * 创建加载中布局
     */
    private void createLoadingLayout() {
        if (loadingLayout == null) {
            loadingLayout = inflate(loadingLayoutId);
        }
        if (loadingLayoutId == DEFAULT_LOADING_LAYOUT_ID) {
            loadingLayout.setBackgroundColor(defaultBackgroundColor);
        }
        if (!TextUtils.isEmpty(loadingTip)) {
            TextView loadingTextView = loadingLayout.findViewById(R.id.status_layout_manager_tv_status_loading_content);
            if (loadingTextView != null) {
                loadingTextView.setText(loadingTip);
            }
        }
    }

    /**
     * 获取加载中布局
     *
     * @return 加载中布局
     */
    public View getLoadingLayout() {
        createLoadingLayout();
        return loadingLayout;
    }

    /**
     * 显示加载中布局
     */
    public void showLoadingLayout() {
        createLoadingLayout();
        replaceLayoutHelper.showStatusLayout(loadingLayout);
    }

    /**
     * 创建空布局
     */
    private void createEmptyLayout() {
        if (emptyLayout == null) {
            emptyLayout = inflate(emptyLayoutId);
        }
        if (emptyLayoutId == DEFAULT_EMPTY_LAYOUT_ID) {
            emptyLayout.setBackgroundColor(defaultBackgroundColor);
        }

        ImageView emptyImageView = emptyLayout.findViewById(R.id.status_layout_manager_iv_status_empty_img);
        if (emptyImageView != null) {
            emptyImageView.setImageResource(emptyImgId);
        }

        if (!TextUtils.isEmpty(emptyTip)) {
            TextView emptyTextView = emptyLayout.findViewById(R.id.status_layout_manager_tv_status_empty_content);
            if (emptyTextView != null) {
                emptyTextView.setText(emptyTip);
            }
        }

        //点击控件不一定是默认的,所以单独设置默认点击控件的文本及可见性
        TextView emptyClickViewTextView = emptyLayout.findViewById(DEFAULT_EMPTY_CLICKED_ID);
        if (emptyClickViewTextView != null) {
            //设置点击控件的文本和可见性
            if (isEmptyClickViewVisible) {
                emptyClickViewTextView.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(emptyClickViewTip)) {
                    emptyClickViewTextView.setText(emptyClickViewTip);
                }
            } else {
                emptyClickViewTextView.setVisibility(View.GONE);
            }
        }
        //设置点击事件
        View view = emptyLayout.findViewById(emptyClickViewId);
        if (view != null && onStatusChildClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStatusChildClickListener.onEmptyChildClick(view);
                }
            });
        }
    }

    /**
     * 获取空数据布局
     *
     * @return 空数据布局
     * @since v1.0.0
     */
    public View getEmptyLayout() {
        createEmptyLayout();
        return emptyLayout;
    }

    /**
     * 显示空数据布局
     *
     * @since v1.0.0
     */
    public void showEmptyLayout() {
        createEmptyLayout();
        replaceLayoutHelper.showStatusLayout(emptyLayout);
    }

    /**
     * 创建出错布局
     */
    private void createErrorLayout() {
        if (errorLayout == null) {
            errorLayout = inflate(errorLayoutId);
        }
        if (errorLayoutId == DEFAULT_ERROR_LAYOUT_ID) {
            errorLayout.setBackgroundColor(defaultBackgroundColor);
        }

        View view = errorLayout.findViewById(errorClickViewId);
        if (view != null && onStatusChildClickListener != null) {
            // 设置点击按钮点击时事件回调
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStatusChildClickListener.onErrorChildClick(view);
                }
            });
        }

        // 设置默认出错布局的提示文本
        if (!TextUtils.isEmpty(errorText)) {
            TextView errorTextView = errorLayout.findViewById(R.id.status_layout_manager_tv_status_error_content);
            if (errorTextView != null) {
                errorTextView.setText(errorText);
            }
        }

        // 设置默认出错布局的图片
        ImageView errorImageView = errorLayout.findViewById(R.id.status_layout_manager_iv_status_error_image);
        if (errorImageView != null) {
            errorImageView.setImageResource(errorImgId);
        }

        TextView errorClickViewTextView = errorLayout.findViewById(DEFAULT_ERROR_CLICKED_ID);
        if (errorClickViewTextView != null) {
            // 设置点击按钮的文本和可见性
            if (isErrorClickViewVisible) {
                errorClickViewTextView.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(errorClickViewTip)) {
                    errorClickViewTextView.setText(errorClickViewTip);
                }
                errorClickViewTextView.setTextColor(errorClickViewTextColor);
            } else {
                errorClickViewTextView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 获取出错布局
     *
     * @return 出错布局
     * @since v1.0.0
     */
    public View getErrorLayout() {
        createErrorLayout();
        return errorLayout;
    }

    /**
     * 显示出错布局
     *
     * @since v1.0.0
     */
    public void showErrorLayout() {
        createErrorLayout();
        replaceLayoutHelper.showStatusLayout(errorLayout);
    }


    /**
     * 显示自定义布局
     *
     * @param customLayout 自定义布局
     */
    public void showCustomLayout(@NonNull View customLayout) {
        replaceLayoutHelper.showStatusLayout(customLayout);
    }

    /**
     * 显示自定义状态布局
     *
     * @param customLayoutId
     * @return
     */
    public void showCustomLayout(@LayoutRes int customLayoutId) {
        View customView = inflate(customLayoutId);
        showCustomLayout(customView);
    }

    public void showCustomLayout(@NonNull View customLayout, @IdRes int... clickViewId) {
        replaceLayoutHelper.showStatusLayout(customLayout);
        if (onStatusChildClickListener == null) {
            return;
        }
        for (int aClickViewId : clickViewId) {
            View clickView = customLayout.findViewById(aClickViewId);
            if (clickView == null) {
                return;
            }
            //设置点击控件点击事件回调
            clickView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStatusChildClickListener.onCustomerChildClick(view);
                }
            });
        }
    }

    /**
     * 显示自定义状态布局
     *
     * @param customLayoutID 自定义布局 ID
     * @param clickViewID    点击按钮 ID
     * @since v1.0.0
     */
    public View showCustomLayout(@LayoutRes int customLayoutID, @IdRes int... clickViewID) {
        View customLayout = inflate(customLayoutID);
        showCustomLayout(customLayout, clickViewID);
        return customLayout;
    }


    public static class Builder {
        //顶层布局
        private View contentLayout;
        @LayoutRes
        private int loadingLayoutId;
        private View loadingLayout;
        private String loadingTip;

        @LayoutRes
        private int emptyLayoutId;
        private View emptyLayout;
        private String emptyTip;

        @IdRes
        private int emptyClickViewId;
        private String emptyClickViewTip;
        private int emptyClickViewTextColor;
        private boolean isEmptyClickViewVisible;
        @DrawableRes
        private int emptyImgId;


        @LayoutRes
        private int errorLayoutId;
        private View errorLayout;
        private String errorText;

        @IdRes
        private int errorClickViewId;
        private String errorClickViewTip;
        private int errorClickViewTextColor;
        private boolean isErrorClickViewVisible;
        @DrawableRes
        private int errorImgId;

        private int defaultBackgroundColor;
        private OnStatusChildClickListener onStatusChildClickListener;


        /**
         * @param contentLayout 原有布局
         */
        public Builder(@NonNull View contentLayout) {
            this.contentLayout = contentLayout;
            //设置默认布局
            this.loadingLayoutId = DEFAULT_LOADING_LAYOUT_ID;
            this.emptyLayoutId = DEFAULT_EMPTY_LAYOUT_ID;
            this.errorLayoutId = DEFAULT_ERROR_LAYOUT_ID;
            //默认图片布局
            this.emptyImgId = DEFAULT_EMPTY_IMG_ID;
            this.errorImgId = DEFAULT_ERROR_IMG_ID;
            //设置迷人点击控件id
            this.emptyClickViewId = DEFAULT_EMPTY_CLICKED_ID;
            this.errorClickViewId = DEFAULT_ERROR_CLICKED_ID;
            //设置默认点击控件属性
            this.isEmptyClickViewVisible = true;
            this.isErrorClickViewVisible = true;
            //设置默认点击控件提示语色值
            this.emptyClickViewTextColor = contentLayout.getContext().getResources().getColor(DEFAULT_CLICKED_TEXT_COLOR);
            this.errorClickViewTextColor = contentLayout.getContext().getResources().getColor(DEFAULT_CLICKED_TEXT_COLOR);
            //设置默认背景色
            this.defaultBackgroundColor = contentLayout.getContext().getResources().getColor(DEFAULT_CLICKED_TEXT_COLOR);

        }

        /**
         * 设置加载中布局
         *
         * @param loadingLayoutID 加载中布局 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setLoadingLayout(@LayoutRes int loadingLayoutID) {
            this.loadingLayoutId = loadingLayoutID;
            return this;
        }

        /**
         * 设置加载中布局
         *
         * @param loadingLayout 加载中布局
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setLoadingLayout(@NonNull View loadingLayout) {
            this.loadingLayout = loadingLayout;
            return this;
        }

        /**
         * 设置默认加载中布局提示文本
         *
         * @param loadingText 加载中布局提示文本
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultLoadingText(String loadingText) {
            this.loadingTip = loadingText;
            return this;
        }

        /**
         * 设置默认加载中布局提示文本
         *
         * @param loadingTextStrID 加载中布局提示文本 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultLoadingText(@StringRes int loadingTextStrID) {
            this.loadingTip = contentLayout.getContext().getResources().getString(loadingTextStrID);
            return this;
        }


        ///////////////////////////////////////////
        ////////////////空数据布局///////////////////
        ///////////////////////////////////////////

        /**
         * 设置空数据布局
         *
         * @param emptyLayoutResId 空数据布局 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setEmptyLayout(@LayoutRes int emptyLayoutResId) {
            this.emptyLayoutId = emptyLayoutResId;
            return this;
        }

        /**
         * 设置空数据布局
         *
         * @param emptyLayout 空数据布局
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setEmptyLayout(@NonNull View emptyLayout) {
            this.emptyLayout = emptyLayout;
            return this;
        }

        /**
         * 设置空数据布局点击按钮 ID
         *
         * @param emptyClickViewResId 空数据布局点击按钮 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setEmptyClickViewId(@IdRes int emptyClickViewResId) {
            this.emptyClickViewId = emptyClickViewResId;
            return this;
        }

        /**
         * 设置默认空数据布局点击按钮文本
         *
         * @param emptyClickViewText 点击按钮文本
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultEmptyClickViewText(String emptyClickViewText) {
            this.emptyClickViewTip = emptyClickViewText;
            return this;
        }

        /**
         * 设置默认空数据布局点击按钮文本
         *
         * @param emptyClickViewTextID 点击按钮文本 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultEmptyClickViewText(@StringRes int emptyClickViewTextID) {
            this.emptyClickViewTip = contentLayout.getContext().getResources().getString(emptyClickViewTextID);
            return this;
        }

        /**
         * 设置默认空数据布局点击按钮文本颜色
         *
         * @param emptyClickViewTextColor 点击按钮文本颜色
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultEmptyClickViewTextColor(int emptyClickViewTextColor) {
            this.emptyClickViewTextColor = emptyClickViewTextColor;
            return this;
        }

        /**
         * 设置默认空数据布局点击按钮是否可见
         *
         * @param isEmptyClickViewVisible true：可见 false：不可见
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultEmptyClickViewVisible(boolean isEmptyClickViewVisible) {
            this.isEmptyClickViewVisible = isEmptyClickViewVisible;
            return this;
        }

        /**
         * 设置空数据布局图片
         *
         * @param emptyImgID 空数据布局图片 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultEmptyImg(@DrawableRes int emptyImgID) {
            this.emptyImgId = emptyImgID;
            return this;
        }

        ///////////////////////////////////////////
        /////////////////出错布局////////////////////
        ///////////////////////////////////////////

        /**
         * 设置空数据布局提示文本
         *
         * @param emptyText 空数据布局提示文本
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultEmptyText(String emptyText) {
            this.emptyTip = emptyText;
            return this;
        }

        /**
         * 设置空数据布局提示文本
         *
         * @param emptyTextStrID 空数据布局提示文本 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultEmptyText(@StringRes int emptyTextStrID) {
            this.emptyTip = contentLayout.getContext().getResources().getString(emptyTextStrID);
            return this;
        }


        /**
         * 设置出错布局
         *
         * @param errorLayoutResId 出错布局 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setErrorLayout(@LayoutRes int errorLayoutResId) {
            this.errorLayoutId = errorLayoutResId;
            return this;
        }

        /**
         * 设置出错布局
         *
         * @param errorLayout 出错布局
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setErrorLayout(@NonNull View errorLayout) {
            this.errorLayout = errorLayout;
            return this;
        }

        /**
         * 设置出错布局点击按钮 ID
         *
         * @param errorClickViewResId 出错布局点击按钮 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setErrorClickViewID(@IdRes int errorClickViewResId) {
            this.errorClickViewId = errorClickViewResId;
            return this;
        }

        /**
         * 设置出错布局提示文本
         *
         * @param errorText 出错布局提示文本
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultErrorText(String errorText) {
            this.errorText = errorText;
            return this;
        }

        /**
         * 设置出错布局提示文本
         *
         * @param errorTextStrID 出错布局提示文本 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultErrorText(@StringRes int errorTextStrID) {
            this.errorText = contentLayout.getContext().getResources().getString(errorTextStrID);
            return this;
        }

        /**
         * 设置默认出错布局点击按钮文本
         *
         * @param errorClickViewText 点击按钮文本
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultErrorClickViewText(String errorClickViewText) {
            this.errorClickViewTip = errorClickViewText;
            return this;
        }

        /**
         * 设置默认出错布局点击按钮文本
         *
         * @param errorClickViewTextID 点击按钮文本 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultErrorClickViewText(@StringRes int errorClickViewTextID) {
            this.errorClickViewTip = contentLayout.getContext().getResources().getString(errorClickViewTextID);
            return this;
        }

        /**
         * 设置默认出错布局点击按钮文本颜色
         *
         * @param errorClickViewTextColor 点击按钮文本颜色
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultErrorClickViewTextColor(int errorClickViewTextColor) {
            this.errorClickViewTextColor = errorClickViewTextColor;
            return this;
        }

        /**
         * 设置出错布局点击按钮可见行
         *
         * @param isErrorClickViewVisible true：可见 false：不可见
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultErrorClickViewVisible(boolean isErrorClickViewVisible) {
            this.isErrorClickViewVisible = isErrorClickViewVisible;
            return this;
        }

        /**
         * 设置出错布局图片
         *
         * @param errorImgID 出错布局图片 ID
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultErrorImg(@DrawableRes int errorImgID) {
            this.errorImgId = errorImgID;
            return this;
        }

        /**
         * 设置默认布局的背景颜色，包括加载中、空数据和出错布局
         *
         * @param defaultBackgroundColor 默认布局的背景颜色
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setDefaultLayoutsBackgroundColor(int defaultBackgroundColor) {
            this.defaultBackgroundColor = defaultBackgroundColor;
            return this;
        }

        /**
         * 设置点击事件监听器
         *
         * @param listener 点击事件监听器
         * @return 状态布局 Build 对象
         * @since v1.0.0
         */
        public Builder setOnStatusChildClickListener(OnStatusChildClickListener listener) {
            this.onStatusChildClickListener = listener;
            return this;
        }

        /**
         * 创建状态布局管理器
         *
         * @return 状态布局管理器
         * @since v1.0.0
         */
        @NonNull
        @CheckResult
        public MultiStatusManager build() {
            return new MultiStatusManager(this);
        }
    }
}
