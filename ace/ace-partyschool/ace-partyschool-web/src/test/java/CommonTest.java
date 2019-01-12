import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        String tbNameTmpl = "ATT_WATER_INFO_";
        String nowDateTime = "2018-12-02";// DateUtil.getNow();
        String nowYear = nowDateTime.substring(0, 4);
        String nowMonth = nowDateTime.substring(0, 7);
        String iMonth = nowDateTime.substring(5, 7);

        String firstTbName = tbNameTmpl + nowMonth;
        List<String> container = new ArrayList<>();
        String tmp;
        for (int i = 1; i < Integer.parseInt(iMonth); i++) {
            if (i < 10) {
                tmp = tbNameTmpl + nowYear + "_0" + i;
            } else {
                tmp = tbNameTmpl + nowYear + "_" + i;
            }
            container.add(tmp);
        }
//        String[] tableNames = (String[]) container.toArray();

        System.out.println(iMonth);
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
