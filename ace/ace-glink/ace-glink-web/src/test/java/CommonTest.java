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
//        404
        System.out.println(LeApiToolKit.change(1));
//        404
        System.out.println(LeApiToolKit.lightBrokenSum("", ""));


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
