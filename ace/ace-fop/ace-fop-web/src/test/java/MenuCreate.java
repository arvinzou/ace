import com.huacainfo.ace.common.plugins.wechat.api.WeChatApi;
import com.huacainfo.ace.common.plugins.wechat.constant.ApiURL;
import com.huacainfo.ace.common.plugins.wechat.constant.MenuButtonType;
import com.huacainfo.ace.common.plugins.wechat.entity.pojo.base.AccessToken;
import com.huacainfo.ace.common.plugins.wechat.entity.pojo.base.Button;
import com.huacainfo.ace.common.plugins.wechat.entity.pojo.base.Menu;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.JsonUtil;

/**
 * Created by HuaCai008 on 2018/6/8.
 */
public class MenuCreate {


    public static void main(String[] args) {
        Menu m = fopMenu();
        String accessToken = "11_BtCufjT5r0_Bw7I-MB3WHGCSz05xcjFyIv3217hAC9hCeNAZvs__-WgWaZAv9oJyEhnXOlrPIoZmm_qtw5nIivFr5ghZTbFufVwMUMhYIUdUynpDioHraTxDLe4tIzYx08JGcvQ1Z5PGXD4cUVBeABAIJP";
        // getAccessToken("wxfdb1e4819dee7b62", "dc51907c900e5bcb9527daec79d05e61");
        System.out.println(createMenu(accessToken, m));

//        hcwyMenu();
    }

    public static Menu fopMenu() {
        //方案产品
        Button btn1 = new Button(MenuButtonType.VIEW, "市联资讯",
                "https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzU1MTg1MTQzMA==#wechat_redirect");
        //华彩微站
        Button btn2 = new Button(MenuButtonType.MINI_PROGRAM, "掌上诉求",
                "http://baidu.com", "wxaa3c6d0676f4d11a", "page/home/index");
        //华彩资讯
        Button btn3 = new Button(MenuButtonType.VIEW, "企业中心",
                "https://mp.cdsgsl.org.cn/fop/www/view/me/index.jsp");

        //菜单json
        Menu menu = new Menu();
        menu.setButton(new Button[]{btn1, btn2, btn3});
        System.out.println(JsonUtil.toJson(menu));
        return menu;
    }


    public static Menu hcwyMenu() {
        //方案产品
        Button btn1Sub_a = new Button(MenuButtonType.VIEW, "直播案例",
                "http://zx.huacainfo.com/live/www/view/index.html?companyId=00010001");
        Button btn1Sub_b = new Button(MenuButtonType.VIEW, "百度推广",
                "http://m.huacainfo.com/2/65/p497112663f1446");
        Button btn1Sub_c = new Button(MenuButtonType.VIEW, "解决方案",
                "http://m.huacainfo.com/v2/covers/?site_id=1199139749");
        Button[] btn1Sub = new Button[]{btn1Sub_a, btn1Sub_b, btn1Sub_c};
        Button btn1 = new Button("方案产品", btn1Sub);
        //华彩微站
        Button btn2 = new Button(MenuButtonType.VIEW, "华彩微站", "http://m.huacainfo.com/");
        //华彩资讯
        Button btn3 = new Button(MenuButtonType.VIEW, "华彩资讯",
                "http://zx.huacainfo.com/portal/www/page/d797c47769414b2b97993f9b98b1b65aa/index.jsp?pageId=ce4db9f7737f4611a26fc3daf76040b8");
        //菜单json
        Menu menu = new Menu();
        menu.setButton(new Button[]{btn1, btn2, btn3});
        System.out.println(JsonUtil.toJson(menu));
        return menu;
    }

    public static String getAccessToken(String appid, String appSecret) {
//        "wxfdb1e4819dee7b62", "dc51907c900e5bcb9527daec79d05e61"
        AccessToken accessToken = WeChatApi.getAccessToken(appid, appSecret);
        return accessToken.getToken();
    }

    public static void menuApi(String accessToken) {
//        String accessToken = "10_HQmi_0ea3lEmOq6vxL26Nw3Jvr4YlohCvJyovFQHYFmTunnBXhc8Mm_jnwsJKB-n_ZtrZTecNYEKBa41EgNZZz6sROhYoBrDhEkf7-xuw_YMM4qnI3MeWF9bYdRhS9kEefDb7VnZVNvR5ovDIDZbADANIE";
        Menu m = hcwyMenu();
        String a = createMenu(accessToken, m);
        System.out.println(a);
    }

    /**
     * 创建菜单
     *
     * @param accessToken
     * @param menu
     * @return
     */
    public static String createMenu(String accessToken, Menu menu) {
        String url = ApiURL.MENU_CREATE_API_URL.replace("#ACCESS_TOKEN#", accessToken);
        return HttpKit.post(url, JsonUtil.toJson(menu));

    }


}
