import com.huacainfo.ace.common.plugins.ccb.pojo.CallBackResp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.Date;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {

    String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "\t<head>\n" +
            "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
            "\t\t<script language=javascript>\n" +
            "\t\t\tfunction postdo(){\n" +
            "\t\t\t\tdocument.jhform.submit();\n" +
            "\t\t\t}\n" +
            "\t\t</script>\n" +
            "\t</head>\n" +
            "\t<body onload=\"postdo();\">\n" +
            "\t\t<form name=\"jhform\" action='https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlatP1_EPAY?REMARK1=&REMARK2=&BRANCHID=430000000&TXCODE=530550&CCB_IBSVersion=V6&CURCODE=01&MERCHANTID=105000083980372&RETURNTYPE=2&ORDERID=30str&POSID=023328365&PAYMENT=0.01&MAC=1d36801c189ad128d97da87747e32b6d&TIMEOUT=&QRCODE=1&CHANNEL=1' method=\"post\">\n" +
            "\t\t</form>\n" +
            "\t</body>\n" +
            "</html>";

    @Test
    public void test() {

        CallBackResp rs = new CallBackResp();
//        rs.setAccType("12");
        System.out.println(rs.toString());
    }

    private long getDiffDays(Date begin, Date end) {
        long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
        return between / (24 * 3600);
    }


    private String parseHtml(String html) {
        Document doc = Jsoup.parse(html);
        Elements rows = doc.select("form");//.get(0).select("tr");
        if (rows.size() == 1) {
            System.out.println("没有结果");
        }

        return "";
    }
}
