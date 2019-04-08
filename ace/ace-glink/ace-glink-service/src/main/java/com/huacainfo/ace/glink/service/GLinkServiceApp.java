package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;

public class GLinkServiceApp {
    private static Logger LOGGER = LoggerFactory.getLogger(com.huacainfo.ace.glink.service.GLinkServiceApp.class);

    public static void main(String[] args) {
        AbstractApplicationContext ctx = null;
        String lan = "111.6", lat = "29.0";

        try {
            // ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            for (int i = 0; i < 1000; i++) {
                lan = "111.6";
                lat = "29.0";
                int num = (int) (Math.random() * 10);
                int num0 = (int) (Math.random() * 10000);
                int num1 = (int) (Math.random() * 10000);
                lan = lan + num0;
                lat = lat + num1;
                String sql = "insert into `glink`.`tra_acc` ( `deadthToll`, `lastModifyUserName`, `status`, `lastModifyUserId`, `latitude`, `cause`, `roadSectionId`, `roadManId`, `accidentTime`, `createUserName`, `vehicleType`, `id`, `longitude`, `injuries`, `createDate`, `weather`, `areaCode`, `lastModifyDate`, `address`, `createUserId`) values ( '" + num + "', null, '1', null, '" + lat + "', '02', '08dba7fce4a541c9a7fd34211213cb2b', 'e2ae77cf167f4a7fb5b85008b05da950', '2019-01-10 05:05:54', '唐教', '01', '" + (GUIDUtil.getGUID() + i) + "', '" + lan + "', '0', '2019-01-20 13:53:26', '02', '430702', null, '湖南省常德市武陵区龙港路', '1546658773348');";
                System.out.println(sql);
            }
            LOGGER.info("Taa服务启动成功");
            synchronized (ctx) {
                //ctx.wait();
            }
        } catch (Exception ex) {
            LOGGER.error("Taa服务启动失败", ex);
            if (ctx != null) {
                ctx.destroy();
            }
            System.exit(1);
        }
    }
}
