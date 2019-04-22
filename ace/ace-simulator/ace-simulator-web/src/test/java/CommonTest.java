import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() throws IOException {
//        404
//        System.out.println(LeApiToolKit.change(1));
//        404
//        System.out.println(LeApiToolKit.lightBrokenSum("", ""));


        System.out.println(JSON.parseObject("{\n" +
                "    \"GateCount\": 6,\n" +
                "    \"GateData\": [\n" +
                "        {\n" +
                "            \"NodeID\": 1,\n" +
                "            \"Status\": \"1\",\n" +
                "            \"UpdateTime\": \"2018-11-20 12:33:15\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"NodeID\": 2,\n" +
                "            \"Status\": \"1\",\n" +
                "            \"UpdateTime\": \"2018-11-20 12:33:15\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"NodeID\": 3,\n" +
                "            \"Status\": \"0\",\n" +
                "            \"UpdateTime\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"NodeID\": 4,\n" +
                "            \"Status\": \"1\",\n" +
                "            \"UpdateTime\": \"2018-11-20 12:33:15\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"NodeID\": 5,\n" +
                "            \"Status\": \"0\",\n" +
                "            \"UpdateTime\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"NodeID\": 6,\n" +
                "            \"Status\": \"1\",\n" +
                "            \"UpdateTime\": \"2018-11-20 12:33:15\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"));

    }

    private void generatorGUID(int num) {

        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
