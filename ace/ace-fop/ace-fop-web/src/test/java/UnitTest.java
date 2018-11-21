import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.fop.common.api.DataSwapperApi;
import org.junit.Test;

import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/11/6 15:02
 * @Description:
 */
public class UnitTest {

    @Test
    public void test() {
//430721052016062700021
        List list = DataSwapperApi.sgsj_dcdydjxx("430702198704016527");
        System.out.println(JsonUtil.toJson(list));
    }
}



