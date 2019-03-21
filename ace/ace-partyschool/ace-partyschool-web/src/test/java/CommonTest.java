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
        AttRecord r = new AttRecord();
        r.setLatitude(new BigDecimal(29.015656).setScale(6, BigDecimal.ROUND_HALF_DOWN));
        r.setLongitude(new BigDecimal(111.729777).setScale(6, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(r.toString());
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
