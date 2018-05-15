package com.huacainfo.ace.common.plugins.wechat.util;

/**
 * Created by ovrn on 2016/1/31.
 */
public class StringUtil {

    public static final String CHARSET_UTF_8 = "utf-8";
    private static final String TEL_REGEX = "^1[3|4|5|7|8]\\d{9}$";

    public StringUtil() {
        super();
    }

    /**
     * 字符串是否为空，null, "", " " 都返回true
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断两个字符串是否相等，都是null返回true
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 返回str是否包含searchStr
     *
     * @param str
     * @param searchStr
     * @return
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    /**
     * 传入的所有参数都不是空的
     *
     * @param strs
     * @return
     */
    public static boolean areNotEmpty(String... strs) {
        for (String str : strs) {
            if (isEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 转换成字符串，如果是null，返回默认值def
     *
     * @param str
     * @param def
     * @return
     */
    public static String valueOf(Object str, String def) {
        return null != str ? String.valueOf(str) : def;
    }

    /**
     * 传入的所有参数 只要有一个不为空，就返回true
     *
     * @param strs
     * @return
     */
    public static boolean oneIsNotEmty(String... strs) {
        for (String str : strs) {
            if (isNotEmpty(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 校验手机号码格式
     *
     * @param tel
     * @return
     */
    public static boolean isValidTel(String tel) {
        if (isNotEmpty(tel) && tel.matches(TEL_REGEX)) {
            return true;
        }
        return false;
    }

    public static String convertTel(String tel) {
        if (StringUtil.isNotEmpty(tel) && tel.length() == 11) {
            return tel.substring(0, 3) + "****" + tel.substring(7, 11);
        }
        return tel;
    }


    /**
     * 模糊字符串
     *
     * @param str   原字符串
     * @param regex 正则表达式 "--***"
     * @return String
     */
    public static String fuzzyStr(String str, String regex) {
        int length = str.length();

        if (isEmpty(str))
            return str;
        else if (regex.startsWith("-") && regex.endsWith("-")) {
            regex = regex.substring(1, regex.length() - 1);
            return str.substring(0, 1) + regex + str.substring(length - 1);
        } else if (regex.startsWith("--"))
            return regex.replace("--", str.substring(0, 2));
        else if (regex.startsWith("-"))
            return regex.replace("-", str.substring(0, 1));


        return str;
    }
}
