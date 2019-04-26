import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.LeApiToolKit;
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
        System.out.println(LeApiToolKit.getBuildingDetail());
    }

    private void generatorGUID(int num) {

        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }



}
