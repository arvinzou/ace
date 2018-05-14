import com.huacainfo.ace.common.tools.XmlConverUtil;
import org.junit.Test;

import java.util.Map;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {

    @Test
    public void test() {
        String body = "<wechat>\n" +
                "<first>first</first>\n" +
                "<keyword1>keyword1</keyword1>\n" +
                "<keyword2>keyword2</keyword2>\n" +
                "<keyword3>keyword3</keyword3>\n" +
                "<keyword4>keyword4</keyword4>\n" +
                "<keyword5>keyword5</keyword5>\n" +
                "<remark>remark</remark>\n" +
                "</wecaht>\n" +
                "<sms>\n" +
                "你猜猜我是谁?\n" +
                "</sms>";

        Map<String, Object> map = XmlConverUtil.xmltoLinkMap(body);


        System.out.println("111111111111111");
    }

}
