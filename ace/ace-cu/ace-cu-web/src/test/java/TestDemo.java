import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.XmlUtil;
import org.junit.Test;

import java.util.Date;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {


    @Test
    public void test() {
        String xml = "<xml><appid><![CDATA[wx637ad1e5d12d32c4]]></appid>\n" +
                "<attach><![CDATA[7cd9dbc7924944deaa40c721c94178ad]]></attach>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[1]]></cash_fee>\n" +
                "<device_info><![CDATA[WEB]]></device_info>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1501772221]]></mch_id>\n" +
                "<nonce_str><![CDATA[6uwuzgzseu2d1v6auagsts4irzen01gt]]></nonce_str>\n" +
                "<openid><![CDATA[oWjAS1ry8ASioiQ3Id0lEyuZRPV4]]></openid>\n" +
                "<out_trade_no><![CDATA[7164f85fa19847fa86e17bc6da1881de]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[86CFD2FFF786849C9DC56949FF49EB54]]></sign>\n" +
                "<time_end><![CDATA[20180623132832]]></time_end>\n" +
                "<total_fee>1</total_fee>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000116201806234163736705]]></transaction_id>\n" +
                "</xml>";
        String json = XmlUtil.xmltoJson(xml);


        System.out.println(JsonUtil.toJson(json));
    }

    private long getDiffDays(Date begin, Date end) {
        long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
        return between / (24 * 3600);
    }
}
