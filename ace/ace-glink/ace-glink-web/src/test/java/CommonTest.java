import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.LeApiToolKit;
import com.huacainfo.ace.glink.api.SeApiToolKit;
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

        seApiInvoke();      //强电联调
//        leApiInvoke();        //弱电联调

    }

    private void leApiInvoke() {

        //分区灯光启动仪式
//        System.out.println(LeApiToolKit.completionCeremony(1));

        //模式的紧急停止(恢复) --done
        System.out.println(LeApiToolKit.stopRegain(2, "002"));

//        System.out.println(LeApiToolKit.stats(2,"JH068"));

//        System.out.println(LeApiToolKit.strategysDetail());

//
//        LightStrategyIn in = new LightStrategyIn();
//        in.setArea("002");
//        in.setPattern(LightStrategyIn.MODE_HOLIDAY);
//        in.setStopDate("20190509");
//        in.setStartDate("20190507");
//        in.setStrategy("SHGL#0052");
//        in.setStartTime("20190508");
//        in.setStopTime("20190509");
//
//        LeApiToolKit.lightStrategy(in);
    }

    private void seApiInvoke() {


//        System.out.println(SeApiToolKit.getBaseNodeInfo());

        //todo 1、缺少执行区域场景数据
        System.out.println(SeApiToolKit.getPresetData());
//        System.out.println(SeApiToolKit.executePreset());

        //todo 2、待沟通确认，是否可以执行（考虑开关操作是否影响线上环境？）
//        System.out.println(SeApiToolKit.getAreaTaskInfo());
//        System.out.println(SeApiToolKit.executeTask());
//todo 3、类似问题2
//        System.out.println(SeApiToolKit.getTimerData());
//        System.out.println(SeApiToolKit.updateTimer());



//        todo 无法修改，接口返回：{"Status":"error"}
//        System.out.println(SeApiToolKit.updateDayCron(1));
//        System.out.println(SeApiToolKit.getGeneralCtrlCron());
    }

    private void generatorGUID(int num) {

        for (int i = 0; i < num; i++) {
            System.out.println(GUIDUtil.getGUID());
        }
    }


}
