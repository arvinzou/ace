import com.huacainfo.ace.common.plugins.access.AccessConnector;
import com.huacainfo.ace.common.plugins.access.AccessHelper;
import com.huacainfo.ace.common.plugins.sqlsever.SQLServerManager;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {
        String connUrl = "jdbc:sqlserver://192.168.2.100:52006;DatabaseName=hnhr_currency";
        String sa = "sa";
        String pwd = "admin123";

        String sql = "select  u.Name,  t.* from dbo.BorrowCurrency t \n" +
                "left join dbo.ReaderCurrency u on t.ReaderCord = u.ReaderCord\n" +
                "where 1=1\n" +
                "and t.Signs='未还'\n" +
                "and u.ReaderBar in  ('Z0000012','','')\n" +
                "order by t.BorrowDate asc";//查询test表

        SQLServerManager manager = SQLServerManager.newInstance(connUrl, sa, pwd);
        manager.query(sql);


    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
