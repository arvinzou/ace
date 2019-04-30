package com.huacainfo.ace.simulator.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.tools.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ApiController
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/22 9:59
 */
@RestController
@RequestMapping("/api")
public class ApiController extends SamulatorBaseController {

    @RequestMapping("/invoke")
    public Object api(int InterFaceType, String Appkey) throws Exception {
        if (!StringUtil.areNotEmpty(InterFaceType + "", Appkey)) {
            return "";
        }


        java.net.URL uri = this.getClass().getResource("/"); //获取文件路径
        String path = uri.getPath() + "/fe/";
        switch (InterFaceType) {
            //3.1-获取项目区域数据（InterFaceType=1）
            case 1:
                return JSON.parseObject(FileUtil.ReadFile(path + "01-项目区域数据.Json", FileUtil.UTF_8));
            //3.2-获取配电箱基础数据（InterFaceType=2）
            case 2:
                return JSON.parseObject(FileUtil.ReadFile(path + "02-配电箱节点数据.Json", FileUtil.UTF_8));
            //3.3-获取单个配电箱监测数据（InterFaceType=3）
            case 3:
                return JSON.parseObject(FileUtil.ReadFile(path + "03-单个配电箱数据.Json", FileUtil.UTF_8));
            //3.4-获取多个配电箱监测数据（InterFaceType=4）
            case 4:
                return JSON.parseObject(FileUtil.ReadFile(path + "04-多个配电箱数据.Json", FileUtil.UTF_8));
            //3.5-获取全部配电箱电表数据（InterFaceType=5）
            case 5:
                return JSON.parseObject(FileUtil.ReadFile(path + "05-所有电表数据.Json", FileUtil.UTF_8));
            //3.6-获取4G 路由器运行状态（InterFaceType=6）  ------
            case 6:
                return JSON.parseObject(FileUtil.ReadFile(path + "06-4G状态数据.Json", FileUtil.UTF_8));
            //3.7-获取网关设备运行状态（InterFaceType=7）
            case 7:
                return JSON.parseObject(FileUtil.ReadFile(path + "07-网关状态数据.Json", FileUtil.UTF_8));
            //3.8-获取逻辑区定义数据（InterFaceType=8）
            case 8:
                return JSON.parseObject(FileUtil.ReadFile(path + "08-逻辑区数据.Json", FileUtil.UTF_8));
            //3.9-获取区域任务数据（InterFaceType=9）
            case 9:
                return JSON.parseObject(FileUtil.ReadFile(path + "09-区域任务数据.Json", FileUtil.UTF_8));
            //3.10-获取场景定义列表（InterFaceType=10）
            case 10:
                return JSON.parseObject(FileUtil.ReadFile(path + "10-场景定义列表.Json", FileUtil.UTF_8));
            //3.11-获取系统定时任务数据（InterFaceType=11）
            case 11:
                return JSON.parseObject(FileUtil.ReadFile(path + "11-系统定时数据.Json", FileUtil.UTF_8));
            //3.15-系统定时计划修改（InterFaceType=14）
            case 14:
                return rtnOk();//JSON.parseObject(FileUtil.ReadFile(path + "14-系统定时修改.Json", FileUtil.UTF_8));
            //3.16-获取逻辑区当前场景（InterFaceType=15）
            case 15:
                return JSON.parseObject(FileUtil.ReadFile(path + "15-逻辑区域场景反馈.Json", FileUtil.UTF_8));

            //3.17-查询总控定时计划（InterFaceType=17）
            case 17:
                return JSON.parseObject(FileUtil.ReadFile(path + "17-总控定时计划.Json", FileUtil.UTF_8));
            //3.19-查询总控全年排程（InterFaceType=19）
            case 19:
                return JSON.parseObject(FileUtil.ReadFile(path + "19-总控全年排程.Json", FileUtil.UTF_8));

            /**
             ***************************************
             */
            /**
             * 3.13、执行一键控制任务（InterFaceType=13）   --  页面手动调用
             */
            case 13:
                return rtnOk();
            //3.14-报警信息推送
//            case 99:
//                return JSON.parseObject(FileUtil.ReadFile(path + "17-总控定时计划.Json", FileUtil.UTF_8));
//            //3.18-修改总控定时计划（InterFaceType=18）
//            case 18:
//                return JSON.parseObject(FileUtil.ReadFile(path + "03-单个配电箱数据.Json", FileUtil.UTF_8));
//
//            //3.20-修改总控全年排程（InterFaceType=20）
            case 20:
                return rtnOk();//JSON.parseObject(FileUtil.ReadFile(path + "03-单个配电箱数据.Json", FileUtil.UTF_8));
            //3.21-修改当天总控模式（InterFaceType=21）
            case 21:
                return rtnOk();
        }


        return "hello world!~";
    }

    private Map<String, String> rtnOk() {
        Map<String, String> map = new HashMap<>();
        map.put("Status", "ok");

        return map;
    }
}
