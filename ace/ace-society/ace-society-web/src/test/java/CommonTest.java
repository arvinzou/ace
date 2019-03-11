import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.apache.commons.lang.StringUtils;
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
        String apppid="wx7e6a369d8fa12b12";
//        SingleResult<WxCfgVo> rst = wxCfgService.selectWxCfgByPrimaryKey(apppid);
//        WxCfgVo obj =rst.getValue();
        String token="19_pKHfVfJSRKYpPfYAAvFSfW86leul-2Ei_0fn6KHBbU0oyDFY8gen2Japa5Poj0hLcjk_6_9VsrqTyD1tDQz0GDeZpel4nxxhqSFkYywgCuMdA-89x7ZHbQVvyQoRMGiADAKFZ";//obj.getAccessToken();


        //api URL =>  https://developers.weixin.qq.com/miniprogram/dev/api-backend/getWXACodeUnlimit.html
        String api ="https://api.weixin.qq.com/wxa/getwxacodeunlimit";
        Map<String,Object> params=new HashMap<>();
        params.put("access_token",token);
        params.put("scene","123");//最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
        params.put("page","page/index/index");//必须是已经发布的小程序存在的页面（否则报错），例如 pages/index/index, 根路径前不要填加 /,不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面
        params.put("width","280px");//二维码的宽度，单位 px，最小 280px，最大 1280px
//        params.put("auto_color","false");//自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false
//        params.put("line_color","{\"r\":0,\"g\":0,\"b\":0}");//auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} 十进制表示
//        params.put("is_hyaline","false");//是否需要透明底色，为 true 时，生成透明底色的小程序


        String rsStr = HttpKit.post(api ,getUrlParamsByMap(params));
        System.out.println(rsStr);
    }

    /**
     * 将map转换成url
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
