import com.huacainfo.ace.common.tools.DateUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {

    @Test
    public void test() {


        Date payDate = DateUtil.getNowDate();

        Calendar c = Calendar.getInstance();
        c.setTime(payDate);

        System.out.println("payDate:" + payDate);
        int month = c.get(Calendar.MONTH) + 1;
        String quarter = getQuarter(month);
        System.out.println("year:" + c.get(Calendar.YEAR));
        System.out.println("quarter:" + quarter);

        System.out.println("month:" + month);

        System.out.println("day:" + c.get(Calendar.DATE));
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
