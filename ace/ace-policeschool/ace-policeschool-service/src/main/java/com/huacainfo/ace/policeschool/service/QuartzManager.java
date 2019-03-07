package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.policeschool.dao.EnrollRosterDao;
import com.huacainfo.ace.policeschool.model.EnrollRoster;
import com.huacainfo.ace.policeschool.model.Student;
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
    private EnrollRosterDao enrollRosterDao;
    @Autowired
    private StudentService studentService;

    private UserProp userProp() {
        UserProp userProp = new UserProp();
        userProp.setUserId("root");
        userProp.setName("root");
        userProp.setActiveSyId("policeschool");

        return userProp;
    }

    /**
     * 每隔5分钟 执行一次 :自动注册报名表中，符合要求的学员数据
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void autoRegister() {
        List<EnrollRoster> list = enrollRosterDao.findUnRegisterList();
        Student student;
        MessageResponse ms;
        for (EnrollRoster item : list) {
            student = new Student();
            student.setName(item.getName());
            student.setSex(item.getSex());
            student.setNativePlace(item.getAreaCode());
            student.setMobile(item.getMobile());
            student.setIdCard(item.getIdCard());
            student.setPolitical(item.getPolitical());
            student.setWorkUnitName(item.getWorkUnitName());
            student.setPostName(item.getPostName());
            student.setClassId(item.getClsId());
            student.setBadgeNum(item.getBadgeNum());
            student.setCollege(item.getCollege());
            student.setBirthDate(item.getBirthDate());
            try {
                ms = studentService.addStudent(student, userProp());
                logger.info("自动注册学员失败[did={}]:{}", item.getId(), ms.getErrorMessage());
            } catch (Exception e) {
                logger.error("自动注册学员异常[did={}]: \n{}", item.getId(), e);
                continue;
            }
        }
    }
}
