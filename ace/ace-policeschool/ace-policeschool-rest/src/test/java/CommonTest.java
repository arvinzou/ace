import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.plugins.sqlsever.SQLServerManager;
import org.junit.Test;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        SQLServerManager.test();
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
