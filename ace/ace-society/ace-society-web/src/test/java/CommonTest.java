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

        generatorGUID(1);
    }


    private void generatorGUID(int num) {
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }
}
