import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.model.ConsultOrder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    public static SqlSession getSqlSession() {
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration(); //反射得到configuration ,然后
        configuration.setSafeResultHandlerEnabled(false); // 设置为false
        return session;
    }

    @Test
    public void test() {


    }

    public void orderParams() {
        BaseOrder baseOrder = new BaseOrder();
        baseOrder.setCategory("1");//1-咨询订单 2-课程订单
        baseOrder.setConsumerId("o6qFn1EofA_YlWe0h4rUjF5Ksopk");//买家id
        baseOrder.setCommodityId("1");//商品id
        baseOrder.setAmount(1);


        ConsultOrder consultInfo = new ConsultOrder();
        consultInfo.setTel("Tel18000");
        consultInfo.setName("Name");
        consultInfo.setAge(1);
        consultInfo.setSex("Sex");
        consultInfo.setInfo("Info");
        consultInfo.setSecName("SecName");
        consultInfo.setRelationship("Relationship");
        consultInfo.setSecTel("SecTel");

        Map<String, Object> params = new HashMap<>();
        params.put("base", baseOrder);
        params.put("consult", consultInfo);

        System.out.println(JsonUtil.toJson(params));
    }

}
