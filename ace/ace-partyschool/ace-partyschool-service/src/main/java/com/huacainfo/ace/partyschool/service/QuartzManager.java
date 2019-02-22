package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.partyschool.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Auther: Arvin
 * @Date: 2018/11/1 14:58
 * @Description:
 */
@Component("QuartzManager")
public class QuartzManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassesService classesService;


    /**
     * 每天凌晨1点执行
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void breakup() {
        /**
         * 每天凌晨1点执行
         * 1、关闭时间已到期的班级
         * 2、注销已毕业班级的学员账户信息
         */
        classesService.graduation();
    }
}
