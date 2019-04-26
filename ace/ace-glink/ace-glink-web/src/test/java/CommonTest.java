import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.LeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.le.LightStrategyIn;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() throws IOException {

        //无数据返回
//        System.out.println(LeApiToolKit.getBuildingDetail());
//        String date = DateUtil.toStr(DateUtil.toDate("2019-04-16 00:00:00"), CommConstant.DATE_REGEX_LE);

//        System.out.println(LeApiToolKit.getBrokenLampDetail(date));
        System.out.println(LeApiToolKit.getLampStatus());
    }

    private void generatorGUID(int num) {

        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }

    private void lightStrategy() {

        LightStrategyIn p = new LightStrategyIn("01932EF", "1111", "20190107", "20190107",
                1, "[1,2,3]", 0, "[]");
        System.out.println(LeApiToolKit.lightStrategy(p));
    }


}
