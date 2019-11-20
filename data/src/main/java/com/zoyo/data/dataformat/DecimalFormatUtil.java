package com.zoyo.data.dataformat;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.FieldPosition;

/**
 * @Description: 数据格式化
 * @CreateDate: 2019/11/4 9:09
 */
public class DecimalFormatUtil {

    /**
     * @param format eg."#.00"
     * @param obj    需要格式的数据
     * @return 格式化的结果
     */
    public static String decimalFormat(String format, Object obj) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(obj);
    }

    /**
     * 需要append后缀
     *
     * @param format
     * @param obj
     * @return
     */
    public static StringBuffer decimalFormatAppend(String format, Object obj) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(obj, new StringBuffer(), new FieldPosition(0));
    }

    public static String decimalFormat(String string) {
        if (string == null || TextUtils.equals("0.0", string) || TextUtils.equals("0.00", string)) {
            return "0";
        }

        double parseDouble = Double.parseDouble(string);

        //如果obj是0.0或者0.00会报错
        DecimalFormat df = new DecimalFormat("0.00");

        String result = df.format(parseDouble);
        if (TextUtils.equals("0.00", result)) {
            return "0";
        }
        return result;
    }

    public static StringBuffer decimalFormatAppend(String string) {
        return new StringBuffer(decimalFormat(string));
    }

}
