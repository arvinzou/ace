import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.junit.Test;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        String date = DateUtil.getNow(DateUtil.DEFAULT_DATE_REGEX);

        System.out.println(date);

//        String apiAcct = "77aa9bcb86d8d4f9646c3b2605b3e085";
//        String apiKey = "yunkaoqin123";
//        QYApiKit api = QYApiKit.getInstance(apiAcct, apiKey);
////        2.1
//        String a = api.getRecordLog("2019-03-01", "2019-03-18", "", "");
//////        2.5
//        String b = api.getEmployee("");
////        2.6
//        String c = api.getUserDetail("2662952");
////        2.7
//        String d = api.addEmployee("测试CS", "cs123456", "18277177517", "", "", "", "", "");

//        System.out.println(a);
//        RecordRst rst = JsonUtil.toObject(a, RecordRst.class);
//        System.out.println(rst.getStatus());
//        System.out.println(rst.getData().getAttendata().get(0).getDepartname());


    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}


