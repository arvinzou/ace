import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
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
        String text = "{\"areaCode\":\"0000\",\"code\":\"22\",\"createDate\":\"20190430\",\"createUserId\":\"b7a29e42e42d4f61b1ed2342b6984c1d\",\"createUserName\":\"系统管理员\",\"id\":\"087c786b6d97496ca7bcc284c094b00c\",\"isWeek\":1,\"lastModifyDate\":\"20190430\",\"lastModifyUserId\":\"b7a29e42e42d4f61b1ed2342b6984c1d\",\"lastModifyUserName\":\"系统管理员\",\"months\":\"\",\"name\":\"22\",\"pattern\":\"1\",\"remark\":\"222\",\"startTime\":\"20190430\",\"status\":\"1\",\"stopTime\":\"20190510\",\"weeks\":\"1\",\"strategy\":\"SHGL#0003\",\"area\":\"22\"}";
        LightStrategyIn in = JsonUtil.toObject(text, LightStrategyIn.class);
        System.out.println(LeApiToolKit.lightStrategy(in));


//        LeApiToolKit.lightStrategy();
    }

    private void generatorGUID(int num) {

        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
