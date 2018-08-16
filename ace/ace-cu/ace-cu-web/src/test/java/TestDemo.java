import com.huacainfo.ace.common.tools.GUIDUtil;
import org.junit.Test;

import java.util.Date;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {


    @Test
    public void test() {

        for (int i = 0; i < 5; i++) {
            System.out.println(GUIDUtil.getGUID());
        }

//        System.out.println(DateUtil.getNow().substring(0, 10) + "|");
    }

    private long getDiffDays(Date begin, Date end) {
        long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
        return between / (24 * 3600);
    }
}
