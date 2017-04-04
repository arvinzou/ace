package com.huacainfo.ace.common.tools;

 
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 功能描述：关于字符串的一些实用操作
 */
public class StringUtils {
	
	
	/**
	 * 删除最后的字符
	 * @param source
	 * @return
	 */
	public static String deleteEndChar(String source)
	{
		return source .substring(0, source .length()-1);
	}
	
	/**
	 * 删除最1的字符
	 * @param source
	 * @return
	 */
	public static String insert(String source,int index,String substr)
	{
		if(source.length()<=index)
			return source+substr;
		else
		{
			String start = source.substring(0, index);
			String end = source.substring(index, source.length());
			return start+substr+end;
			
		}
	}
	
	/*
	 * 拆分成Map
	 */
	public static Map<String,String> splitToMap(String source,String splitChar)
	{
		Map<String,String> m=new HashMap<String, String>();
		if(StringUtils.isNullOrWhiteSpace(source))
		{
			return m;
		}
		else {
			 String[] split = source.split(splitChar);
			 for (String s : split) {
				
				 m.put(s, s);
			}
		}
		return m;
	}
	
	/*
	 * 拆分成列表
	 */
	public static List<String> splitToList(String source,String splitChar)
	{
		List<String> list=new ArrayList<String>();
		if(StringUtils.isNullOrWhiteSpace(source))
		{
			return list;
		}
		else {
			 String[] split = source.split(splitChar);
			 for (String s : split) {
				
				 list.add(s);
			}
		}
		return list;
	}
	public  static String [] split(String source,String splitChar)
	{
		 
		return source.split(splitChar);
		
	}
	 /**
	  * null 或者空白 "  "
	  * @param s
	  * @return
	  */
	 public static boolean isNullOrWhiteSpace(String s)
	 {
		 if(s==null) return true;
		 if(s.trim().equals(""))
			 return true;
		 else
			 return false;
	 }
	/**
	 * 字符串格式化,用{0},{1}....
	 * @param s
	 * @param objects
	 * @return
	 */
      public static String format(String s,Object ...objects)
      {
    	  String source=s;
    	   for (int i = 0; i < objects.length; i++) {
    		   source= source.replace("{"+i+"}",  objects[i]==null?"":objects[i].toString());
    		   
		}
    	   return source;
      }
      /**
       * 是否符合正则表达式
       * @param pattern
       * @param str
       * @return
       */
      public static   boolean isMatch(String pattern, String str)   
      {
        try
        {
          Pattern p = Pattern.compile(pattern);
          Matcher m = p.matcher(str);
          return m.find();
        }
        catch (PatternSyntaxException e)
        {
          return false;
        }
      }
      /**
  	 * 将字符串中特定模式的字符转换成map中对应的值
  	 *
  	 * @param s
  	 *            需要转换的字符串
  	 * @param map
  	 *            转换所需的键值对集合
  	 * @return 转换后的字符串
  	 */
  	public static String replace(String s, Map<String, Object> map) {
  		StringBuilder ret = new StringBuilder((int)(s.length() * 1.5));
  		int cursor = 0;
  		for (int start, end; (start = s.indexOf("${", cursor)) != -1
  				&& (end = s.indexOf("}", start)) != -1;) {
  			ret.append(s.substring(cursor, start)).append(
  					map.get(s.substring(start + 2, end)));
  			cursor = end + 1;
  		}
  		ret.append(s.substring(cursor, s.length()));
  		return ret.toString();
  	}

  	public static String replace(String s, Object...objs) {
  		if(objs == null || objs.length == 0)
  			return s;
  		if(s.indexOf("{}") == -1)
  			return s;

  		StringBuilder ret = new StringBuilder((int)(s.length() * 1.5));
  		int cursor = 0;
  		int index = 0;
  		for(int start; (start = s.indexOf("{}", cursor)) != -1 ;) {
  			ret.append(s.substring(cursor, start));
  			if(index < objs.length)
  				ret.append(objs[index]);
  			else
  				ret.append("{}");
  			cursor = start + 2;
  			index++;
  		}
  		ret.append(s.substring(cursor, s.length()));
  		return ret.toString();
  	}
  	
  	/**
  	 * 倘若超过8个字符  余下的用...替换
  	 * @param args
  	 */
    public static String replace(String string){
    	if(string!=null&&string.length()>8){
    		return string.substring(8)+"...";
    	}
    	return string;
    }
      
      public static void main(String[] args) {
       
    	  String s=format("#{{0}}","22");
    	  System.out.println(s);
    	   
      }
	public static boolean isEmpty(String value) {

		if(value==null||value.length()==0)
			return true;
		return false;
	}

	public static boolean IsNullOrEmpty(String val) {
		 if(val==null)
			 return true;
		 if(val.trim().length()==0)
		 {
			 return true;
		 }
		 return false;
	}

	public static String repeat(String string, int count) {
		StringBuilder sb=new StringBuilder();
		for (int j = 0; j < count; j++) {
			sb.append(string);
		}
		return sb.toString();
	}
	
private static final String CHARSET_NAME = "UTF-8";
    
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static byte[] getBytes(String str){
    	if (str != null){
    		try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
    	}else{
    		return null;
    	}
    }
}
