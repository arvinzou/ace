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

        List<Map<String, Object>> a = DataSwapperApi.sswj_swlycxjysfqy("白东明");
        System.out.println(a);
    }




}
