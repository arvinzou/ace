import com.huacainfo.ace.fop.common.api.DataSwapperApi;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {

    @Test
    public void test() {
//        System.out.println(RandomValidateCode.CreateRadom(8, 2));

        List<Map<String, Object>> a = DataSwapperApi.ssfj_khhgdlsswsxx("1");//"湖南朝阳律师事务所");

        System.out.println(a.size());
    }

    @Test
    public void test1() {
        String addProcess = "";
        if (null != addProcess && addProcess.trim() != "") {
            System.out.println("1");
        }

    }

}
