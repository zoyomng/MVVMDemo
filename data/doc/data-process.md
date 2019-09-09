# Android Data-Process 数据处理

## XML占位符

* 简单用法
```xml
<string name="data">%1$s %2$d %3$.2f</string>
```

```
mContext.getString(R.string.data, parame1,parame2,parame3)
```

> * %1$s : % 和 $ 固定格式
> * 1 2 3对应parame1 parame2 parame3填充位置
> * s-字符串 , d-int , .2f-保留两位小数的float类型数据



* 添加字体颜色-涉及Html标签
```xml
<string name="count">
    <Data>
        <![CDATA[总计: <font color="#C9AC51">¥%1$.2f</font>]]>
    </Data>
</string>
```


## 富文本

```java
/**
 * ---日期----------维护人-----------变更内容----------
 * 2016/12/4       zuoyouming          zozo
 */

public class ConvertDataUtil {

    /**
     * 带有图片的文本显示---加载图片的实际大小
     *
     * @param mcontext
     * @param text
     * @param drawableId
     */
    public static SpannableString showTextWithImage(Context mcontext, String text, int drawableId) {
        if (!(text.contains("[") && text.contains("]"))) {
            throw new IllegalStateException("输入的文本没有带[]");
        }

        Drawable drawable = mcontext.getResources().getDrawable(drawableId);

        return showTextWithImage(mcontext, text, drawableId, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

    }


    /**
     * 带有图片的文本显示---可设置图片的大小
     *
     * @param mcontext   上下文
     * @param text       文本
     * @param drawableId 图片资源Id
     * @param drawWidth  图片宽
     * @param drawHeight 图片高
     */
    public static SpannableString showTextWithImage(Context mcontext, String text, int drawableId, int drawWidth, int drawHeight) {

        return showTextWithImage(mcontext, text, drawableId, 0, 0, drawWidth, drawHeight);
    }


    /**
     * 带有图片的文本显示 ---可设置图片的大小以及图片的偏移
     *
     * @param mcontext
     * @param text       显示的文本(...[图片要取代的内容])
     * @param drawableId 图片的资源Id
     * @param offsetLeft --left
     * @param offsetTop  --top
     * @param drawWidth  --right
     * @param drawHeight --bottom
     */
    public static SpannableString showTextWithImage(Context mcontext, String text, int drawableId, int offsetLeft, int offsetTop, int drawWidth, int drawHeight) {

        if (!(text.contains("[") && text.contains("]"))) {
            throw new IllegalStateException("输入的文本没有带[]");
        }

        SpannableString spannableString = new SpannableString(text);

        Drawable drawable = mcontext.getResources().getDrawable(drawableId);

        drawable.setBounds(offsetLeft, offsetTop, drawWidth, drawHeight);
        ImageSpan imageSpan = new ImageSpan(drawable);

        //图片替换文字
        int start = text.indexOf("[");
        int end = text.indexOf("]");
        spannableString.setSpan(imageSpan, start, end + 1, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);

        return spannableString;
    }


    /**
     * 设置文本 部分文字 特定字体颜色
     *
     * @param mcontext
     * @param text     要显示的文本
     * @param start    文本变色开始位置
     * @param end      文本变色开始位置
     * @param colorId  颜色资源Id
     */
    public static SpannableString showTextWithColor(Context mcontext, String text, int start, int end, int colorId) {


        SpannableString spannableString = new SpannableString(text);

        int color = mcontext.getResources().getColor(colorId);
        //设置字体颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        //int end = string.indexOf("回复");   //可照此方法获取end值

        spannableString.setSpan(colorSpan, start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }

    /**
     * 与showTextWithColor联用,同一个文本 不同文字 显示 不同字体颜色
     *
     * @param mcontext
     * @param spannableString
     * @param start
     * @param end
     * @param colorId
     * @return
     */
    @Deprecated
    public static SpannableString showTextWithMultColor(Context mcontext, SpannableString spannableString, int start, int end, int colorId) {

        int color = mcontext.getResources().getColor(colorId);
        //设置字体颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        //int end = string.indexOf("回复");   //可照此方法获取end值

        spannableString.setSpan(colorSpan, start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }


    /**
     * 设置超链接文本
     *
     * @param mTextView
     * @param text
     */
    public static void setHyperlink(TextView mTextView, String text) {
        //  String text = "详情请点击<shape_diamond href='http://www.baidu.cm'>百度</shape_diamond>";
        Spanned spanned = Html.fromHtml(text);
        mTextView.setText(spanned);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
```