import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.fundtown.vo.VipDepartmentVo;
import org.junit.Test;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {


    @Test
    public void test() {
        VipDepartmentVo vo = new VipDepartmentVo();
        vo.setDepartmentName("华彩伟业");
        vo.setContactEmail("30123@qq.com");
        vo.setContactMobile("18570629027");

        System.out.println(JsonUtil.toJson(vo));
    }

}
