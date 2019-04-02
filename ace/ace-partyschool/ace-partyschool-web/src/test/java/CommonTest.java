import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.model.AttRecord;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        AttRecord a = new AttRecord();
        a.setLatitude(new BigDecimal(29.015840).setScale(6, BigDecimal.ROUND_FLOOR));
        a.setLongitude(new BigDecimal(111.7287706).setScale(6, BigDecimal.ROUND_FLOOR));
        System.out.println(a.toString());
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
