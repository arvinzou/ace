import com.huacainfo.ace.common.plugins.sqlsever.SQLServerManager;
import com.huacainfo.ace.common.plugins.wechat.base.WxMsgController;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.junit.Test;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        String a =" https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=ACCESS_TOKEN";
//        WxCfgController wc = new WxCfgController();
//        String rst = HttpKit.post(a ,"");
//
//        System.out.println(rst);
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
