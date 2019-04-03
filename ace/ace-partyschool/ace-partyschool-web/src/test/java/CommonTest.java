import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.URLKit;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("http://www.cdswdx.top/group1/M00/00/00/wKgQJFykNg6ALVz9AAN4Zjxp6TY67.jpeg?filename=139F2236-4E0A-4759-A5B3-2CB9CA5AAACD.jpeg");
        map.put("api_key", "-5Wf1CueJ8FffHLeEap4RtVOE77P6IQT");
        map.put("api_secret", "dxWQqNdaXugnohd021ba1Cu_g4tfLmW3");
        map.put("image_url", sb.toString());

        String url = "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard?" + URLKit.mapToStr(map);
        System.out.println(JsonUtil.toObject(HttpKit.post(url, ""), OCRBean.class).getCards().get(0).getId_card_number());
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
