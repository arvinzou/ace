import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {


    @Test
    public void test() {
//        String mobile = "13538077427";
//        System.out.println();

        //创建菜单
//        String accessToken = getAccessToken();
//        menuApi(accessToken);

//        System.out.println(GUIDUtil.getGUID());
//        String ACCESS_TOKEN = "10_Kr_BogFEbbEcIZdhIi8mVROtrEz-A_KtRr16Z5ojEBk4zpqWOPs-0zSz-zXem3_yHZlr9N7JzoLMWF8Nz4N2-Z2tlDxTYW3iIMrW9-NqlbeEaUAZCoZd2cIFqrxPNqUKZ1lXiFUH8kO_xENJCSBeADAQGP";
//        String a = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + ACCESS_TOKEN + "&next_openid=";
//        String r = HttpKit.get(a);
//

    }


    private void getData() {
//        String url = "http://59.231.66.49:8080/gateway/api/sgsl_sq_sgsj_xzcfxx/1.0?api_key=32303138303630343137333834313130323930302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd&CERTIFICATE_NUMBER=43250119840715352";
        String domain = "http://59.231.66.49:8080/gateway/api";
        String apiUrl = "/sgsl_sq_sgsj_xzcfxx";
        String apiVersion = "/1.0?";
        StringBuilder sb = new StringBuilder();
        sb.append(domain + apiUrl + apiVersion)
                .append("api_key=" + "32303138303630343137333834313130323930302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd")
                .append("&CERTIFICATE_NUMBER=43250119840715352X");

        String response = HttpKit.get(sb.toString());

        System.out.println(response);
    }

    private void postData() {
        String domain = "http://59.231.66.49:8080/gateway/api";
        String apiUrl = "/sgsl_sq_sgsj_xzcfxx";
        String apiVersion = "/1.0?";

        String url = domain + apiUrl + apiVersion;
        Map<String, String> queryParas = new HashMap<>();
        queryParas.put("api_key", "32303138303630343137333834313130323930302331383931656366342d316265302d346139322d393834362d3161f2f20dabf4af06cb6fcd");
        queryParas.put("CERTIFICATE_NUMBER", "43250119840715352");
        String response = HttpKit.post(url, queryParas, "");
        System.out.println(response);
    }
}
