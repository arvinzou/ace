import com.huacainfo.ace.common.plugins.qyplugin.pojo.RecordRst;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.base.ApiRst;
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
//        String apiAcct = "77aa9bcb86d8d4f9646c3b2605b3e085";
//        String apiKey = "yunkaoqin123";
//        QYApiKit api = QYApiKit.getInstance(apiAcct, apiKey);
//
//        //3、api递归，获取云考勤数据
//        String json = api.syncEmployee("2666580,2666579", "A3MA184660202");
//        ApiRst rst = JsonUtil.toObject(json, ApiRst.class);
//
//        System.out.println(rst.toString());

//        String[] a =new String[]{"1","2","3","4"};
//        System.out.println();;
        String json = "{\"status\":1,\"error\":\"\",\"data\":[{},{}]}\n";
        ApiRst apiRst = JsonUtil.toObject(json, ApiRst.class);
        System.out.println(apiRst.toString());
        RecordRst rst = JsonUtil.toObject(json, RecordRst.class);

    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}


