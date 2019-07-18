# Data 资料

## 存储路径

```

System.out.println(Environment.getDataDirectory());

System.out.println(Environment.getDownloadCacheDirectory());

System.out.println(Environment.getRootDirectory());

System.out.println(getApplication().getPackageCodePath());

System.out.println(getApplication().getPackageResourcePath());

System.out.println(getApplication().getCacheDir());

System.out.println(getApplication().getFilesDir());

System.out.println(getApplication().getDatabasePath("test"));

System.out.println(getApplication().getDir("test", Context.MODE_PRIVATE));


System.out.println(Environment.getExternalStorageDirectory());

System.out.println(Environment.getExternalStoragePublicDirectory("test"));

System.out.println(getApplication().getExternalCacheDir());

System.out.println(getApplication().getExternalFilesDir("test") );

System.out.println(getApplication().getExternalFilesDir(null));

```

*  Environment.getDataDirectory() -> /data

*  Environment.getDownloadCacheDirectory() -> /data/cache
 
*  Environment.getRootDirectory() -> /system
 
*  getApplication().getPackageCodePath() -> /data/app/com.zoyo.mvvmdemo-2/base.apk
 
*  getApplication().getPackageResourcePath() -> /data/app/com.zoyo.mvvmdemo-2/base.apk
 
*  getApplication().getCacheDir() -> /data/user/0/com.zoyo.mvvmdemo/cache
 
*  getApplication().getFilesDir() -> /data/user/0/com.zoyo.mvvmdemo/files
 
*  getApplication().getDatabasePath("test") -> /data/user/0/com.zoyo.mvvmdemo/databases/test
 
*  getApplication().getDir("test", Context.MODE_PRIVATE) -> /data/user/0/com.zoyo.mvvmdemo/app_test

*  Environment.getExternalStorageDirectory() -> /storage/emulated/0

*  Environment.getExternalStoragePublicDirectory("test") -> /storage/emulated/0/test

*  getApplication().getExternalCacheDir() -> /storage/emulated/0/Android/data/com.zoyo.mvvmdemo/cache

*  getApplication().getExternalFilesDir("test") -> /storage/emulated/0/Android/data/com.zoyo.mvvmdemo/files/test

*  getApplication().getExternalFilesDir(null) -> /storage/emulated/0/Android/data/com.zoyo.mvvmdemo/files


### [ConstraintLayout + ConstraintSet 实现动画](http://www.uwanttolearn.com/android/constraint-layout-animations-dynamic-constraints-ui-java-hell/)

* 只使用一个布局,通过ConstraintSet设置约束
 ```java
public class ConstraintActivity extends AppCompatActivity { 
    private ConstraintSet applyConstraintSet = new ConstraintSet(); 
    private ConstraintSet resetConstraintSet = new ConstraintSet(); 
    private ConstraintLayout constraintLayout;
        
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        // R.id.constraintLayout:根布局
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        //原布局
        resetConstraintSet.clone(constraintLayout);
        //新布局
        applyConstraintSet.clone(constraintLayout);
    }

    public void apply(View view) {
        TransitionManager.beginDelayedTransition(constraintLayout);

        //设置Margin
        applyConstraintSet.setMargin(R.id.textView4, ConstraintSet.TOP, 200);
        //设置宽高
        applyConstraintSet.constrainWidth(R.id.textView4, 600);
        //清除所有约束
        applyConstraintSet.clear(R.id.textView3);
        applyConstraintSet.applyTo(constraintLayout);
    }

    public void reset(View view) {
        TransitionManager.beginDelayedTransition(constraintLayout);

        resetConstraintSet.applyTo(constraintLayout);
    }
}
        
```
        
* 使用两个布局,实现过渡动画

```java
 public class ConstraintActivity extends AppCompatActivity {

    private ConstraintSet applyConstraintSet = new ConstraintSet();
    private ConstraintSet resetConstraintSet = new ConstraintSet();
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        //两个布局中需要动画过渡的控件id必须一致,才能实现动画;如果id不一致,原布局控件不会过渡到新布局控件
        // R.id.constraintLayout:根布局
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        //原布局
        resetConstraintSet.clone(constraintLayout);
        //新布局
        applyConstraintSet.clone(this, R.layout.activity_constraint_apply);
    }

    public void apply(View view) {
        TransitionManager.beginDelayedTransition(constraintLayout);
        applyConstraintSet.applyTo(constraintLayout);
    }

    public void reset(View view) {
        TransitionManager.beginDelayedTransition(constraintLayout);

        resetConstraintSet.applyTo(constraintLayout);
    }
}
```
