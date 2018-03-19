import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.woc.model.License;
import com.huacainfo.ace.woc.model.Road;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arvin on 2018/3/8.
 */
public class CommonTest {

    @Test
    public void test() {
        String s = "{\"roadName\":\"1213\",\"roadCode\":\"s113\",\"\":\"\",\"roadLength\":\"\",\"roadStatus\":\"1\",\"remark\":\"\",\"areaCode\":\"43070202\",\"constructDate\":\"2018-03-06\",\"adminDepId\":\"00030001\",\"oper\":\"add\",\"id\":\"_empty\"}";
        Road u = JsonUtil.toObject(s, Road.class);
        System.out.println(JsonUtil.toJson(u));


    }
}
