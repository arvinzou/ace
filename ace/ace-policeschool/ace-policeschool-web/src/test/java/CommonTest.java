import com.huacainfo.ace.common.tools.GUIDUtil;
import org.junit.Before;
import org.junit.Test;
import qyplugin.QYApiKit;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        String apiAcct = "511a92a59fe40d198cb60f6bf6b25078";
        String apiKey = "lanni123456";
        QYApiKit api = QYApiKit.getInstance(apiAcct, apiKey);
        //2.1
//        String a = api.getRecordLog("2019-03-01", "2019-03-14", "", "");
//        System.out.println(a);
//        2.5
//        System.out.println(api.getEmployee(""));
//        2.6
//        String a=api.getUserDetail("2662952");
//        System.out.println(a);
//        2.7
//        String a=api.addEmployee("测试CS","cs123456","18277177517","","","","","");
//        System.out.println(a);
//        2.8
//        String a=api.updateEmployee("2661969","测试CS","cs123456","18277177517","767476088@qq.com","","","1","");
//        System.out.println(a);
//        2.9
//        String a=api.layoffEmployee("2662952");
//        System.out.println(a);
//        2.12
//        String a=api.reCheck("2662952","2019-3-17 22:00:00");
//        System.out.println(a);
//        2.13
//        String a=api.Apply("2662952","2019-3-18 06:00:00","2019-3-18 09:00:00","2");
//        System.out.println(a);
//        2.14
//        String a=api.applyType();
//        System.out.println(a);
//        2.15
//        String a=api.Report("2662952","2019-2-18 06:00:00","2019-2-28 06:00:00",null);
//        System.out.println(a);
//        2.16
//        String a=api.dayReport("2662952","2019-2-18 06:00:00","2019-2-28 06:00:00");
//        System.out.println(a);
//        2.17
            String a=api.getDevice(null);
            System.out.println(a);



    }




    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}


