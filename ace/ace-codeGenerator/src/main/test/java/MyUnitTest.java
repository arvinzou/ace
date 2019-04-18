import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName MyUnitTest
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/18 11:28
 */
public class MyUnitTest {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    //首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    //首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    @Test
    public void test() {
        getBeanName("se_node_monitor_device_ch");
    }

    private void getBeanName(String tableName) {
        String beanName = lineToHump(tableName);
        beanName = toUpperCaseFirstOne(beanName);
        System.out.println("TableName=>" + tableName);
        System.out.println("ModelName=>" + beanName);
        System.out.println("DaoName=>" + beanName + "Dao");
    }
}
