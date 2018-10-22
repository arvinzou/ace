import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.RemindDateUtils;
import org.junit.Test;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        String nowTimeStr = DateUtil.getNow();
        String yearStr = nowTimeStr.substring(0, 4);
        String monthStr = nowTimeStr.substring(0, 7);

        System.out.println(nowTimeStr + "|" + yearStr + "|" + monthStr);

        System.out.println(DateUtil.toStr(RemindDateUtils.getCurrentSeasonStartTime(), DateUtil.DEFAULT_DATE_TIME_REGEX));
        System.out.println(DateUtil.toStr(RemindDateUtils.getCurrentSeasonEndTime(), DateUtil.DEFAULT_DATE_TIME_REGEX));
//        generatorGUID(2);
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }
}
