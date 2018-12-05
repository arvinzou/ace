
import com.huacainfo.ace.common.tools.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        System.out.println(getQuarter(DateUtil.getNowDate()));


//        String outTradeNo = GUIDUtil.getGUID();
//        System.out.println(outTradeNo);
//        String openId = "ogxN71k1hAUwgYDDIhzMplqWbo4U";
//        String realName = "罗灿";
//        String amount = "1";
//        String desc = "企业付款测试";
//        String mchAppId = "wx6a39a6f86925a42d";
//        String mchId = "1500768651";
//        String apiKey = "8fbcb758e1074713bfd39ffd88beb5e1";
//        byte[] certBytes = FileUtil.File2byte("F:\\cert\\apiclient_cert.p12");
//        Map<String, Object> rst = WeChatPayApi.mchPay(outTradeNo, openId, realName, amount, desc,
//                mchAppId, mchId, apiKey, certBytes);
//        System.out.println(rst);
//
//        if ("SUCCESS".equals(rst.get("return_code"))
//                && "200".equals(rst.get("rst_code"))) {
//            Map<String, Object> map = (Map<String, Object>) rst.get("body");
//            System.out.println(map);
//        }

//        System.out.println(DateUtil.getNow().substring(8, 10));

    }

    private String getQuarter(Date dt) {
        int myMonth = dt.getMonth() + 1;
        System.out.println(myMonth);
        if (myMonth >= 1 && myMonth <= 3) {
            return "第一季度";//return 1;
        }
        if (myMonth >= 4 && myMonth <= 6) {
            return "第二季度";// return 2;
        }
        if (myMonth >= 7 && myMonth <= 9) {
            return "第三季度";//return 3;
        }
        if (myMonth >= 10 && myMonth <= 12) {
            return "第四季度";//return 4;
        }
        return "第一季度";
    }


}
