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
        Menu m = jxbMenu();
        String accessToken = "12_O0clZZaexchOO0sMEwjpJKnpHKe0yDGhJ6qL_0YcAgmsGCPsMWLV_letb2mM79F5IuiuKw5UeN5lRiVihfGLcOZlGTtvnUot-m1AwPP6pa0LvY2Nw7_v6f64Q8MDJLfAEAQWY";
//        String accessToken = getAccessToken("wxfdb1e4819dee7b62", "dc51907c900e5bcb9527daec79d05e61");
        System.out.println(createMenu(accessToken, m));
    }

    public static Menu jxbMenu() {
        //==========关于我们==========
        //协会简介
        Button btn1Sub_a = new Button(MenuButtonType.VIEW, "协会简介",
                "https://www.huacainfo.com/jxb/app/index.php?i=10&c=entry&id=37&do=detail&m=amouse_article");
        Button[] btn1Sub = new Button[]{btn1Sub_a};
        Button btn1 = new Button("关于我们", btn1Sub);

        //==========心阳光联盟==========
        //顾问在线
        Button btn2Sub_a = new Button(MenuButtonType.VIEW, "顾问在线",
                "http://zx.huacainfo.com/jxb/www/view/consultant/index.jsp");
        //我的课程
        Button btn2Sub_b = new Button(MenuButtonType.VIEW, "我的课程",
                "http://zx.huacainfo.com/jxb/www/view/course/index.jsp");
        //心理评测
        Button btn2Sub_c = new Button(MenuButtonType.VIEW, "心理评测",
                "http://zx.huacainfo.com/jxb/www/view/test/testindex.html");

        Button[] btn2Sub = new Button[]{btn2Sub_a, btn2Sub_b, btn2Sub_c};
        Button btn2 = new Button("心阳光联盟", btn2Sub);

        //==========个人中心==========
        Button btn3 = new Button(MenuButtonType.VIEW, "个人中心",
                "http://zx.huacainfo.com/jxb/www/view/mine/mine.jsp");

        //菜单json
        Menu menu = new Menu();
        menu.setButton(new Button[]{btn1, btn2, btn3});

        System.out.println(JsonUtil.toJson(menu));
        return menu;
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
