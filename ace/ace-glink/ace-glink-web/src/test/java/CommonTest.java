import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.URLKit;
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
        String mobile = "18570629027";//联通
        mobile = "17398762955";//移动
        mobile = "13719064337";//电信
        String msg = "邹先生您好，，在您管辖区内，共计发生交通事故115起，其中受伤8人，死亡8人。【常德市路长制信息管理平台】";
        msg = URLKit.strEncoder(msg, URLKit.UTF_8);
        String url = "http://localhost/portal/www/util/sms?mobile=" + mobile + "&msg=" + msg;
        System.out.println(url);
        System.out.println(HttpKit.get(url));
    }

    private void generatorGUID(int num) {

        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
