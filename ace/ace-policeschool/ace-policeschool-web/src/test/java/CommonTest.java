import com.huacainfo.ace.common.tools.GUIDUtil;
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
//2.5
        System.out.println(api.getEmployee(""));
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}


