package com.huacainfo.ace.common.tools;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class CommonUtils extends StringUtils {
    public static String parseDate2String(Date date, String formatStr)
            throws ParseException {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                formatStr);
        return formatter.format(date);
    }

    public static Date parseString2Date(String dateStr, String formatStr)
            throws ParseException {
        Date date = null;
        if (isNotEmpty(dateStr)) {
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                    formatStr);
            date = formatter.parse(dateStr);
        }
        return date;
    }

    public static void writeStringToFile(String filePath, String content) {
        File file = new File(filePath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(content);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Object getPropValue(Object obj, String prop)
            throws IllegalArgumentException, IllegalAccessException {
        Object val = null;
        if (obj != null) {
            Field[] fs = obj.getClass().getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                if (f.getName().equals(prop)) {
                    f.setAccessible(true); // 设置些属性是可以访问的
                    val = f.get(obj); // 得到此属性的值
                    break;
                }
            }
        }
        return val;

    }

    /**
     * 获取异常堆栈信息
     *
     * @param ex
     * @return
     */
    public static String getExceptionStack(Exception ex) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        StringBuffer error = stringWriter.getBuffer();
        return error.toString();
    }

    /**
     * 非空判断
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(Object str) {
        return str != null && !str.toString().trim().equals("");
    }

    public static boolean isValidEmail(String paramStr) {
        String email = paramStr;
        String check = "\\w+@(\\w+.)+[a-z]{2,3}";
        check = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[a-z]+(\\.[a-z]+)?";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidParam(String paramStr) {
        String email = paramStr;
        String check = "^([a-z0-9A-Z]+[-|\\.]?)";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidTel(String paramStr) {
        String str = paramStr;
        String check = "([0-9]{3,4})-([0-9]{7,8})";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidFigures(String paramStr) {
        String str = paramStr;
        String check = "[0-9]{1,50}";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidQQ(String paramStr) {
        String str = paramStr;
        String check = "[0-9]{4,11}";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidMobile(String paramStr) {
        String str = paramStr;
        String check = "^0?1[3,5][0-9]\\d{8}$";
        check = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidMinLength(String paramStr, int paramMin) {
        String str = paramStr;
        int min = paramMin;
        boolean isValidMinLength;
        isValidMinLength = false;
        if (str != null) {
            if (str.length() >= min) {
                isValidMinLength = true;
            }
        }
        return (isValidMinLength);
    }

    public static boolean isValidMaxLength(String paramStr, int paramMax) {
        String str = paramStr;
        int max = paramMax;
        boolean isValidMaxLength;
        isValidMaxLength = false;
        if (str != null) {
            if (str.length() <= max) {
                isValidMaxLength = true;
            }
        }
        return (isValidMaxLength);
    }

    public static boolean isValidUrl(String paramStr, String[] endStr) {
        paramStr = paramStr.toLowerCase();
        if (paramStr.indexOf("http://") != 0) {
            return false;
        }
        for (int i = 0; i < endStr.length; i++) {

            if (paramStr.endsWith(endStr[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidDate(String dateStr) {
        try {
            if (dateStr.length() > 10) {
                dateStr.substring(0, 10);
            }
            java.sql.Date.valueOf(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getMd5(String plainText) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * 将首字母大字
     *
     * @param str
     * @return 转化后后字符
     */
    public static String firstToUpper(String str) {
        char[] chs = str.toCharArray();
        chs[0] = Character.toUpperCase(chs[0]);
        return new String(chs);
    }

    /**
     * 首字母小字
     *
     * @param str
     * @return 转化后后字符
     */
    public static String firstToLower(String str) {
        char[] chs = str.toCharArray();
        chs[0] = Character.toLowerCase(chs[0]);
        return new String(chs);
    }

    public static String getJavaName(String column) {
        if (column == null) {
            return "";
        }
        if (!column.contains("_")) {
            return column;
        }
        String[] names = column.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder(names[0]);
        for (int i = 1; i < names.length; i++) {
            sb.append(CommonUtils.firstToUpper(names[i]));
        }
        return sb.toString();
    }

    public static boolean isValidIdCard(String idCard) {
        try {
            if (idCard.length() == 15 || idCard.length() == 18) {
                String temp = null;
                if (idCard.length() == 15) {
                    temp = "19" + idCard.substring(6, 6 + 6);
                } else {
                    temp = idCard.substring(6, 6 + 8);
                }
                String dateStr = temp.substring(0, 4) + "-"
                        + temp.substring(4, 6) + "-" + temp.substring(6, 8);
                SimpleDateFormat sdf = null;
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.parse(dateStr);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对象属性转换为字段 例如：userName to user_name
     *
     * @param property 字段名
     * @return
     */
    public static String propertyToField(String property) {
        if (null == property) {
            return "";
        }
        char[] chars = property.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            if (CharUtils.isAsciiAlphaUpper(c)) {
                sb.append("_" + StringUtils.lowerCase(CharUtils.toString(c)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 字段转换成对象属性 例如：user_name to userName
     *
     * @param field
     * @return
     */
    public static String fieldToProperty(String field) {
        if (null == field) {
            return "";
        }
        char[] chars = field.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '_') {
                int j = i + 1;
                if (j < chars.length) {
                    sb.append(StringUtils.upperCase(CharUtils
                            .toString(chars[j])));
                    i++;
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String genRandomNum(int pwd_len) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数，

            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }

    public static String getStringByExpression(String expression,
                                               Map<String, Object> valueMap) {
        String regex = "\\$\\{[^\\}]+\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(expression);
        String paramKey;
        while (m.find()) {
            paramKey = m.group(0);
            paramKey = paramKey.substring(2, paramKey.length() - 1);
            Object paramValue = valueMap.get(paramKey);
            expression = m.replaceFirst(String.valueOf(paramValue));
            m = p.matcher(expression);
        }
        return expression;
    }

    private static int getIndex(String[] tmpByte, int length, int index) {

        int byteTotleMin = 0;

        int myIndex = -1;

        for (int i = 0; i < length; i++) {

            byteTotleMin = byteTotleMin + tmpByte[i].getBytes().length;

            if (byteTotleMin >= index) {

                myIndex = i;

                break;

            }

        }

        return myIndex;

    }

    private static byte[] getBytesByIndex(String[] tmpByte, int index) {

        if (index > tmpByte.length) {

            return null;

        }

        index++;

        byte[][] tmp = new byte[index][];

        int count = 0;

        for (int i = 0; i < index; i++) {

            tmp[i] = tmpByte[i].getBytes();

            count = count + tmp[i].length;

        }

        byte[] result = new byte[count];

        int one = tmp.length;

        int indexTmp = 0;

        for (int i = 0; i < one; i++) {

            int two = tmp[i].length;

            for (int j = 0; j < two; j++) {

                result[indexTmp] = tmp[i][j];

                indexTmp++;

            }

        }

        return result;

    }

    public static String spiltString(String resource, int index) {
        if (resource == null) {
            return null;
        }
        byte[] strByte = resource.getBytes();
        int byteLength = strByte.length;
        if (index <= 0 || index > byteLength) {
            return null;
        }
        int strLength = resource.length();
        String[] tmpByte = new String[strLength];
        for (int i = 0; i < strLength; i++) {
            tmpByte[i] = resource.substring(i, i + 1);
        }
        int myIndex = getIndex(tmpByte, strLength, index);
        byte[] resultByte = getBytesByIndex(tmpByte, myIndex);
        if (resultByte == null) {
            return null;
        }
        String result = new String(resultByte);
        return result;
    }

    public static String[] spiltChineseString(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) + "").getBytes().length > 1) {
                sb.append(str.charAt(i));
                sb.append(";");
            }
        }
        return sb.toString().split(";");
    }

    public static double compareChineseString(String orig, String dest) {
        double i = 0;
        if (null == orig || dest == null) {
            return 0;
        }
        for (String o : CommonUtils.spiltChineseString(orig)) {
            if (dest.indexOf(o) > -1) {
                i++;
            }
        }
        return i / CommonUtils.spiltChineseString(orig).length;
    }

    public static boolean isBlank(Object o) {
        boolean struts;
        struts = false;
        if (o == null) {
            struts = true;
        }

        if (o != null) {
            if (o instanceof String) {
                if (((String) o).trim().equals("")) {
                    struts = true;
                }
            } else {
                struts = false;
            }

        }
        return (struts);
    }

    /**
     * 获取某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat(pattern).format(cal.getTime());
    }

    /**
     * 获取某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat(pattern).format(cal.getTime());
    }

    public static Object[] toArrays(Collection<?> coll) {
        Object[] collValues = new Object[coll.size()];
        collValues = coll.toArray(collValues);
        return collValues;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String formatDate(Date date, String arg0) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat(arg0);
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static Date parseDate(String dateStr) {
        try {
            if (dateStr.length() > 10) {
                dateStr.substring(0, 10);
            }
            return (java.util.Date) java.sql.Date.valueOf(dateStr);
        } catch (Exception e) {
            return new java.util.Date();
        }
    }

    public static Date parseDate(String dateStr, String arg0) {
        try {
            if (dateStr.length() > 10) {
                SimpleDateFormat sdf = new SimpleDateFormat(arg0);
                return sdf.parse(dateStr);
            }
            return (java.util.Date) java.sql.Date.valueOf(dateStr);
        } catch (Exception e) {
            return new java.util.Date();
        }
    }

    public static String getStringByTemplate(String vmName, Object model)
            throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine
                .setProperty("class.resource.loader.class",
                        "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(vmName);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("o", model);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    public static Map<String, Object> sortMapByKey(Map<String, Object> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Object> sortedMap = new TreeMap<String, Object>(
                new Comparator<String>() {
                    public int compare(String key1, String key2) {
                        return key1.compareTo(key2);
                    }
                });
        sortedMap.putAll(oriMap);
        return sortedMap;
    }

    private static int getInt(String str) {
        int i = 0;
        try {
            Pattern p = Pattern.compile("^\\d+");
            Matcher m = p.matcher(str);
            if (m.find()) {
                i = Integer.valueOf(m.group());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
                    oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Map.Entry<String, String>>() {
                        public int compare(Entry<String, String> entry1,
                                           Entry<String, String> entry2) {
                            int value1 = 0, value2 = 0;
                            try {
                                value1 = getInt(entry1.getValue());
                                value2 = getInt(entry2.getValue());
                            } catch (NumberFormatException e) {
                                value1 = 0;
                                value2 = 0;
                            }
                            return value2 - value1;
                        }
                    });
            Iterator<Map.Entry<String, String>> iter = entryList.iterator();
            Map.Entry<String, String> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }

    public static Map<String, String> converMap(Map<String, Object> orgin) throws Exception {
        if (orgin == null) {
            return null;
        }
        Map<String, String> dest = new HashMap<String, String>();
        for (Map.Entry<String, Object> entry : orgin.entrySet()) {
            if (entry.getValue() instanceof String) {
                dest.put(entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Date) {
                dest.put(entry.getKey(), CommonUtils.parseDate2String((Date) entry.getValue(), "yyyy-mm-dd"));
            }
            if (entry.getValue() instanceof Double) {
                dest.put(entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Long) {
                dest.put(entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Integer) {
                dest.put(entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Float) {
                dest.put(entry.getKey(), (String) entry.getValue());
            }
        }
        return dest;
    }

    public static String getPinYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        // System.out.println(t1.length);
        String[] t2 = new String[t1.length];
        // System.out.println(t2.length);
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                // System.out.println(t1[i]);
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
                    t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
                } else {
                    // 如果不是汉字字符，直接取出字符并连接到字符串t4后
                    t4 += Character.toString(t1[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t4;
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 将字符串转换成ASCII码
     *
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i] & 0xff));
            // 将每个字符转换成ASCII码
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff) + " ");
        }
        return strBuf.toString();


    }
}
