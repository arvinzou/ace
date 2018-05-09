import com.huacainfo.ace.common.tools.GUIDUtil;
import org.junit.Test;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {

    @Test
    public void test() {

        System.out.println(GUIDUtil.getGUID());
    }

    private String getQuarter(int month) {
        if (month >= 1 && month <= 3) {
            return "第一季度";
        }
        if (month >= 4 && month <= 6) {
            return "第二季度";
        }
        if (month >= 7 && month <= 9) {
            return "第三季度";
        }
        if (month >= 10 && month <= 12) {
            return "第四季度";
        }

        return "";
    }
}
