package com.zoyo.core.common.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ---日期----------维护人---------
 * 2017/11/30     zuoyouming
 */

public class StringUtil {

    /**
     * 验证手机格式
     * String num = "[1][358]\\d{9}";
     */
    public static boolean isPhoneNumber(String number) {
        String num = "[1]\\d{10}";
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            return number.matches(num);
        }
    }

    /**
     * 6-20位数字密码混合
     * 验证密码格式
     */
    public static boolean checkPwdFormat(String pwd) {
        String pwdRegular = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";//密码正则
        if (TextUtils.isEmpty(pwd)) {
            return false;
        } else {
            return pwd.matches(pwdRegular);
        }
    }

    /**
     * 检查特殊字符,如emoji
     *
     * @param c
     * @return true-为特殊字符
     */
    public static boolean checkSpecialChar(String c) {
        String regEx = "[`~!@#$%^&*+=|{}':;',-\\[\\].<>/?~！@#¥%……&*——+|{}【】‘；：”“'。，、？]";

        if (TextUtils.isEmpty(c)) {
            return false;
        } else {
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(c);
            return m.find();
        }
    }

    /**
     * 检查特殊字符
     *
     * @param string
     * @return true-为正常字符
     */
    public static boolean isNotConSpeCharacters(String string) {
        if (string.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() == 0) {
            //如果不包含特殊字符
            return true;
        }
        return false;
    }

    /**
     * 加密手机号
     * 187****2488
     *
     * @param phone
     */
    public static String encryptPhoneNum(String phone) {

        if (TextUtils.isEmpty(phone)) {
            return "";
        } else {
            return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
    }

    //邮箱验证
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }

    /**
     * 将字符串格式化为带两位小数的字符串
     *
     * @param str 字符串
     * @return
     */
    public static String format2Decimals(String str) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (df.format(stringToDouble(str)).startsWith(".")) {
            return "0" + df.format(stringToDouble(str));
        } else {
            return df.format(stringToDouble(str));
        }
    }

    /**
     * 字符串转换成double ,转换失败将会 return 0;
     *
     * @param str 字符串
     * @return
     */
    public static double stringToDouble(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        } else {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
}
