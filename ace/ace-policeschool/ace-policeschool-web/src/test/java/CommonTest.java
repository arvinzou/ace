import com.huacainfo.ace.common.plugins.qyplugin.QYApiKit;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.NewEmployeeRst;
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
        String apiAcct = "77aa9bcb86d8d4f9646c3b2605b3e085";
        String apiKey = "yunkaoqin123";
        QYApiKit api = QYApiKit.getInstance(apiAcct, apiKey);
        String txt = api.addEmployee("测试3", "0123", "18570629017",
                "", "", "247630", "", "A3MA184660202");
        NewEmployeeRst obj = JsonUtil.toObject(txt, NewEmployeeRst.class);
        System.out.println(obj.getData().getAccount());
//        System.out.println(api.getEmployee(""));
//        ApiRst rst = JsonUtil.toObject(txt, ApiRst.class);
//        System.out.println(rst.toString());
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}


