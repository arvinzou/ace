import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.SortList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {


    @Test
    public void test() {

        List<Demo> list = new LinkedList<>();
        Demo p3 = new Demo(2);
        list.add(p3);
        Demo p1 = new Demo(4);
        list.add(p1);
        Demo p2 = new Demo(3);
        list.add(p2);
        Demo p4 = new Demo(1);
        list.add(p4);

        SortList<Demo> sortList = new SortList<>();
        sortList.sort(list, "getDistance", "");

        System.out.println(list.toString());
    }


    private void generatorGUID(int num) {
        //
        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
