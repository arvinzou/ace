package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.qyplugin.QYApiKit;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.RecordData;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.RecordLog;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.RecordRst;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.policeschool.dao.EnrollRosterDao;
import com.huacainfo.ace.policeschool.model.EnrollRoster;
import com.huacainfo.ace.policeschool.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
     * 每隔15分钟 执行一次 :自动注册报名表中，符合要求的学员数据
     */
    @Scheduled(cron = "0 0/15 * * * ?")
    @Transactional(propagation = Propagation.REQUIRED)
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
                if (ms.getStatus() == ResultCode.FAIL) {
                    logger.info("自动注册学员失败[did={}]:{}", item.getId(), ms.getErrorMessage());
                }
            } catch (Exception e) {
                logger.error("自动注册学员异常[did={}]: \n{}", item.getId(), e);
                continue;
            }
        }
    }

    /**
     * 自动下载群英云服务器考勤数据
     * 1、每5min执行一次一次；
     * 2、数据更新形式为增量更新
     */
//    @Scheduled(cron = "0 /5 * * * ?")
    public void downloadYunKQ() {
        //1.获取库中最近一次拉取数据时间
        String lastDate = "";//mysql search
        String endDate = DateUtil.getNow();
        String startDate = StringUtil.isEmpty(lastDate)
                ? DateUtil.getNow(DateUtil.DEFAULT_DATE_REGEX) + " 00:00:00" : lastDate;
        //2、获取接口配置参数
        String apiAcct = PropertyUtil.getProperty("qy_api_acct");
        String apiKey = PropertyUtil.getProperty("qy_api_key");
        QYApiKit api = QYApiKit.getInstance(apiAcct, apiKey);
        //3、api递归，获取云考勤数据
        List<RecordLog> list = apiRecursion(api, startDate, endDate);
        //4、把已获得的考勤数据，储存到数据库
        if (!CollectionUtils.isEmpty(list)) {
            //todo insert into qy_att_record

        }
    }

    private List<RecordLog> apiRecursion(QYApiKit api, String startDate, String endDate) {
        String json = api.getRecordLog(startDate, endDate, "", "");
        RecordRst rst = JsonUtil.toObject(json, RecordRst.class);
        RecordData temp;
        List<RecordLog> data = new ArrayList<>();
        int page;//当前页
        int totalPage;//总页数
        int total = 0;//总记录条数
        boolean errorTag = false;
        String error = "未知错误";
        //接口成功标志  ： 1 - 成功 其他 -- 失败
        if (rst.getStatus() == 1) {
            //暂存list
            temp = rst.getData();
            total = temp.getTotal();
            page = temp.getPage();
            totalPage = temp.getTotalpage();
            data.addAll(temp.getAttendata());
            //递归循环
            while (totalPage > page && !errorTag) {
                page = page + 1;
                json = api.getRecordLog(startDate, endDate, page + "", "");
                rst = JsonUtil.toObject(json, RecordRst.class);
                if (rst.getStatus() == 1) {
                    temp = rst.getData();
                    page = temp.getPage();
                    totalPage = temp.getTotalpage();
                    data.addAll(temp.getAttendata());
                } else {
                    errorTag = true;
                    error = rst.getError();
                }
            }
            if (errorTag) {
                logger.error("【警官培训学校】获取考勤数据失败，时间[{}]~[{}],错误原因：{}", startDate, endDate, error);
            }
        } else {
            logger.error("【警官培训学校】获取考勤数据失败，时间[{}]~[{}],错误原因：{}", startDate, endDate, rst.getError());
        }
        //当前获取数据记录与服务器不一致
        if (data.size() != total) {
            return null;
        }

        return data;
    }


}
