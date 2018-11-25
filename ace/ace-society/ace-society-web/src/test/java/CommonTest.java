import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.society.vo.SocietyOrgInfoVo;
import org.junit.Test;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {

        SocietyOrgInfoVo d = new SocietyOrgInfoVo();
        d.setOrgName("组织测试");
        d.setOrgType("1");
        d.setOrgCover("cover");
        d.setContactPerson("ArvinZou");
        d.setContactPhone("123456");
        d.setEmail("309@qq.com");

        System.out.println(JsonUtil.toJson(d));
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
