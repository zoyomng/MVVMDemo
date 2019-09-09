# Android PhotoView 图片全屏展示

```java
/**
 * ---日期----------维护人---------
 * 2017/12/2     zuoyouming
 * <p>
 * 大图片显示
 * <p>
 * 使用方式:
 * Intent intent = new Intent(mContext, Activity_PhotoView.class);
 * intent.putStringArrayListExtra("imageUrls", imageUrls);
 * intent.putExtra("index", 0);
 * startActivity(intent);
 * <p>
 */
public class Activity_PhotoView extends Activity_Base {
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;
    @BindView(R.id.state_layout)
    MultSateLayout stateLayout;
    @BindView(R.id.tv_position_photo)
    TextView tvPositionPhoto;
    private static List<String> imageUrls;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_img_download)
    ImageView ivImgDownload;

    private int index;

    private static final String IMAGE_URL_LIST = "imageUrls";
    private static final String IMAGE_LIST_INDEX = "index";
    private int size;
    private int currentPosition;


    @Override
    protected int getLayout() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void initEventAndData() {

        Intent intent = getIntent();
        if (null == intent) {
            return;
        }

        ivBack.setOnClickListener(this);
        ivImgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadPicture();
            }
        });


        imageUrls = intent.getStringArrayListExtra(IMAGE_URL_LIST);
        index = intent.getIntExtra(IMAGE_LIST_INDEX, 0);

        if (imageUrls != null) {
            size = imageUrls.size();
        }

        tvPositionPhoto.setText(getString(R.string.index_in_size, size > 0 ? (index + 1) : 0, size));

        viewPager.setAdapter(new SamplePagerAdapter());
        viewPager.setCurrentItem(index);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvPositionPhoto.setText(getString(R.string.index_in_size, position + 1, size));
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (imageUrls != null && imageUrls.size() > 0) {
                return imageUrls.size();
            } else {
                return 0;
            }
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {

            PhotoView photoView = new PhotoView(container.getContext());

            Glide.with(container.getContext()).load(imageUrls.get(position)).into(photoView);

            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
        }
    }
}

```

#### CustomViewPager
```java
/**
 * ScaleGestureDetector seems to mess up the touch events, which means that
 * ViewGroups which make use of onInterceptTouchEvent throw shape_diamond lot of
 * IllegalArgumentException: pointerIndex out of range.
 * There's not much I can do in my code for now, but we can mask the result by
 * just catching the problem and ignoring it.
 */
public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
```

#### 布局文件
```xml
<?xml version="1.0" encoding="utf-8"?>
<com.tangchaoke.hrhj.huarunhaojing.ui.view.MultSateLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/state_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/black_bg">


    <com.tangchaoke.hrhj.huarunhaojing.ui.view.CustomViewPager
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <TextView
        android:id="@+id/tv_position_photo"
        android:layout_width="match_parent"
        android:layout_height="@dimen/dp_40"
        android:layout_gravity="bottom"
        android:layout_marginBottom="@dimen/dp_40"
        android:gravity="center"
        android:text="1/10"
        android:textColor="@android:color/white" />


    <ImageView
        android:id="@+id/iv_back"
        android:layout_width="@dimen/dp_60"
        android:layout_height="@dimen/dp_50"
        android:layout_centerVertical="true"
        android:scaleType="center"
        android:src="@mipmap/ic_arrow_left" />

    <ImageView
        android:id="@+id/iv_img_download"
        android:layout_width="@dimen/dp_40"
        android:layout_height="@dimen/dp_40"
        android:layout_gravity="end|bottom"
        android:layout_marginBottom="@dimen/dp_40"
        android:layout_marginEnd="@dimen/dp_40"
        android:scaleType="center"
        android:src="@mipmap/ic_download" />

</com.tangchaoke.hrhj.huarunhaojing.ui.view.MultSateLayout>
```