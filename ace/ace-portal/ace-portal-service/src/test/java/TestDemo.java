import com.huacainfo.ace.portal.service.impl.WeChatApiServiceImpl;
import org.junit.Test;

/**
 * @Auther: Arvin
 * @Date: 2018/11/12 15:14
 * @Description:
 */
public class TestDemo {
    @Test
    public void test() throws Exception {
        WeChatApiServiceImpl obj = new WeChatApiServiceImpl();

        obj.synUserList("society");

    }
}
