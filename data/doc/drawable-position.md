# Android drawable-start / end / top / bottom

#### xml中设置文本图标
```java
<TextView
    android:id="@+id/tv_first_order"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:drawablePadding="@dimen/dp_5"
    android:drawableStart="@mipmap/icon_first_order"
    tools:text="首单免减" />
```

#### 代码中替换图标
```java
Drawable firstOrderDrawable = mContext.getDrawable(R.mipmap.icon_first_order);
firstOrderDrawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//非常重要，必须设置，否则图片不会显示

tvFirstOrder.setCompoundDrawables(firstOrderDrawable, null, null, null);
```
>* 左上右下
