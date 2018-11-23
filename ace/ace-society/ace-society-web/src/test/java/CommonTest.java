import com.huacainfo.ace.common.plugins.wechat.api.WeChatCustomerMsgApi;
import com.huacainfo.ace.common.plugins.wechat.util.ApiResult;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import org.junit.Test;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        String token = "15_ocQycMihxwZQLm2_t1Q_jdFYeQ5k9hsa-2eBkCBZ0zODLqAcf-Y99m4vvMwXngT08_5Y_MPbicn_VyZElkYJzGv8KqgyueQ4VJy8m-2WzY8YOfOEc5L6FIl1vBv5jh3cKJ5_B1TD-vTHROXBTJSaAFABRA";
        String openId = "oBj7Dvo5XzAYo6dlKlkj_4OQXXgM";
        ApiResult a = WeChatCustomerMsgApi.sendText(token, openId, "hello world \nhello world ");

        System.out.println(JsonUtil.toJson(a));

//        System.out.println(DateUtil.getNow().substring(0, 10));
//        generatorGUID(1);
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }
}
