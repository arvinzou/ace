import com.huacainfo.ace.common.plugins.wechat.util.RandomValidateCode;
import org.junit.Test;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {

    @Test
    public void test() {
        System.out.println(RandomValidateCode.CreateRadom(8, 2));

    }

    @Test
    public void test1() {
        String addProcess = "";
        if (null != addProcess && addProcess.trim() != "") {
            System.out.println("1");
        }

    }

}
