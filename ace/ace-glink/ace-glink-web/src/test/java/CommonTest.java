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


//        System.out.println(SeApiToolKit.getBaseNodeInfo());

        //todo 1、缺少执行区域场景数据
//        System.out.println(SeApiToolKit.executePreset());

        //todo 2、待沟通确认，是否可以执行（考虑开关操作是否影响线上环境？）
//        System.out.println(SeApiToolKit.getAreaTaskInfo());
//        System.out.println(SeApiToolKit.executeTask());
//todo 3、类似问题2
//        System.out.println(SeApiToolKit.getTimerData());
//        System.out.println(SeApiToolKit.updateTimer());

//        System.out.println(SeApiToolKit.getGeneralCtrlCron());

//        todo 无法修改，接口返回：{"Status":"error"}
//        System.out.println(SeApiToolKit.updateDayCron(2));
    }

    private void generatorGUID(int num) {

        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
