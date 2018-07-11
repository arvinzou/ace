import com.huacainfo.ace.common.tools.GUIDUtil;
import org.junit.Test;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {


    @Test
    public void test() {

        for (int i = 0; i < 4; i++) {
            System.out.println(GUIDUtil.getGUID());
        }


    }

}
