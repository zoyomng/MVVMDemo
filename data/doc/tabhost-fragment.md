# Android 应用 一般的布局形式 Tabhost+Fragment

![layout](../img/main-activity.png)

### 布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/ll_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.tangchaoke.hrhj.huarunhaojing.ui.view.MyFragmentTabHost
        android:id="@android:id/tabhost"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <FrameLayout
                android:id="@android:id/tabcontent"
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_weight="1" />

            <TabWidget
                android:id="@android:id/tabs"
                android:layout_width="match_parent"
                android:layout_height="@dimen/dp_50"
                android:background="@color/themeColor" />

        </LinearLayout>

    </com.tangchaoke.hrhj.huarunhaojing.ui.view.MyFragmentTabHost>
</FrameLayout>
```
> * MyFragmentTabHost 就是 Android 系统的FragmentTabHost
> * 这么写可以避免出现一些错误

### 初始化tab
```
private void initTabData() {
    mTableItemList = new ArrayList<>();
    mTableItemList.add(new TabItem(this, R.mipmap.icon_home_unchecked, R.mipmap.icon_home_checked, R.string.tab_home, Fragment_Home.class));
    mTableItemList.add(new TabItem(this, R.mipmap.icon_order_unchecked, R.mipmap.icon_order_checked, R.string.tab_order, Fragment_Order.class));
    myTabItem = new TabItem(this, R.mipmap.icon_mian_unchecked, R.mipmap.icon_main_checked, R.string.tab_main, Fragment_Mine.class);
    mTableItemList.add(myTabItem);
}

TabHost.OnTabChangeListener mOnTabChangeListener = new TabHost.OnTabChangeListener() {
    @Override
    public void onTabChanged(String tabId) {

        for (int i = 0; i < mTableItemList.size(); i++) {
            TabItem tabitem = mTableItemList.get(i);
            if (tabId.equals(tabitem.getTitleStr())) {
                tabitem.setChecked(true);
                currentValus = i;
            } else {
                tabitem.setChecked(false);
            }
        }
    }
};

private void initTabHost() {
    tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

    //去掉分割线
    tabhost.getTabWidget().setDividerDrawable(null);
    for (int i = 0; i < mTableItemList.size(); i++) {
        TabItem tabItem = mTableItemList.get(i);
        TabHost.TabSpec tabSpec = tabhost.newTabSpec(tabItem.getTitleStr()).setIndicator(tabItem.getView());
        tabhost.addTab(tabSpec, tabItem.getFragmentClass(), null);
        //给Tab按钮设置背景
//            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        if (i == 0) {
            tabItem.setChecked(true);
        }
    }

    tabhost.setOnTabChangedListener(mOnTabChangeListener);
}
```

### TabItem类

#### Tab 布局文件
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="center_vertical">

    <ImageView
        android:id="@+id/iv_tab_image"
        android:layout_width="@dimen/dp_20"
        android:layout_height="@dimen/dp_20"
        android:layout_centerHorizontal="true"
        tools:src="@mipmap/ic_logo" />

    <TextView
        android:id="@+id/tv_tab_text"
        android:layout_width="wrap_content"
        android:layout_height="@dimen/dp_14"
        android:layout_below="@id/iv_tab_image"
        android:layout_centerHorizontal="true"
        android:textColor="@color/tabfontGray"
        android:textSize="@dimen/sp_10"
        tools:text="@string/tab_home" />

    <View
        android:id="@+id/view_red_point"
        android:layout_width="@dimen/dp_10"
        android:layout_height="@dimen/dp_10"
        android:layout_toRightOf="@id/iv_tab_image"
        android:background="@drawable/shape_circle_red"
        android:visibility="gone" />
</RelativeLayout>
```

#### TabItem
```java
public class TabItem {

    private Activity mContext;
    private int imageNormal;
    private int imageChecked;
    private int title;
    private String titleStr;
    public Class<? extends Fragment> fragmentClass;
    public View view;
    public ImageView mImageView;
    public TextView mTextView;
    public View view_red_point;

    public TabItem(Activity mContext, int imageNormal, int imageChecked, int title, Class<? extends Fragment> fragmentClass) {
        this.mContext = mContext;
        this.imageNormal = imageNormal;
        this.imageChecked = imageChecked;
        this.title = title;
        this.fragmentClass = fragmentClass;
    }

    public int getImageNormal() {
        return imageNormal;
    }

    public int getImageChecked() {
        return imageChecked;
    }

    public int getTitle() {
       return title;
    }

    public String getTitleStr() {
        if (title == 0) {
            return "";
        }
        if (TextUtils.isEmpty(titleStr)) {
            titleStr = mContext.getString(title);
        }
        return titleStr;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public View getView() {
        if (this.view == null) {
            this.view = mContext.getLayoutInflater().inflate(R.layout.view_tab_home, null);
            this.mImageView = (ImageView) this.view.findViewById(R.id.iv_tab_image);
            this.mTextView = (TextView) this.view.findViewById(R.id.tv_tab_text);
            this.view_red_point = (View) this.view.findViewById(R.id.view_red_point);
            if (this.title == 0) {
                this.mTextView.setVisibility(View.GONE);
            } else {
                this.mTextView.setVisibility(View.VISIBLE);
                this.mTextView.setText(getTitleStr());
            }
            this.mImageView.setImageResource(imageNormal);
        }
        return this.view;
    }


    //切换tab的方法
    public void setChecked(boolean isChecked) {
        if (mImageView != null) {
            if (isChecked) {
                mImageView.setImageResource(imageChecked);
            } else {
                mImageView.setImageResource(imageNormal);
            }
        }
        if (mTextView != null && title != 0) {
            if (isChecked) {
                mTextView.setTextColor(mContext.getResources().getColor(R.color.tabfontGold));
            } else {
                mTextView.setTextColor(mContext.getResources().getColor(R.color.tabfontGray));
            }
        }
    }
}
```
