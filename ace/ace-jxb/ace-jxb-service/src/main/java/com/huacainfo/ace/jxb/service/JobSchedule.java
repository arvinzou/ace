package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.jxb.dao.ConsultOrderRemindLogDao;
import com.huacainfo.ace.jxb.model.ConsultOrderRemindLog;
import com.huacainfo.ace.jxb.model.OrderCalculation;
import com.huacainfo.ace.jxb.vo.BaseOrderVo;
import com.huacainfo.ace.jxb.vo.OrderCalculationQVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/8/8 16:29
 * @Description:
 */
@Component("JobSchedule")
public class JobSchedule {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private OrderCalculationService orderCalculationService;
    @Autowired
    private ConsultOrderRemindLogDao consultOrderRemindLogDao;
    @Autowired
    private BisMsgNoticeService bisMsgNoticeService;

    @Autowired
    private SqlSessionTemplate sqlSession;

    private SqlSession getSqlSession() {
        SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);

        return session;
    }

    /**
     * 每30分钟 计算一批订单
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void computeSchedule() {
        OrderCalculationQVo condition = new OrderCalculationQVo();
        condition.setCpuTag("0");

        List<OrderCalculation> dataList = orderCalculationService.findList(condition, 0, 500, "");
        for (OrderCalculation data : dataList) {
            try {
                orderCalculationService.compute(data);
            } catch (Exception e) {
                logger.error("订单计算异常：{}", e);
                data.setRemark(e.getMessage());
                orderCalculationService.updateByPrimaryKeySelective(data);
            }
        }
    }

    /**
     * 每10分钟 发放一批奖励
     */
//    @Scheduled(cron = " 0 0/10 * * * ?")
    public void grantSchedule() {
        OrderCalculationQVo condition = new OrderCalculationQVo();
        condition.setGrantTag("0");

        List<OrderCalculation> dataList = orderCalculationService.findList(condition, 0, 500, "");
        for (OrderCalculation data : dataList) {
            try {
                orderCalculationService.grant(data);
            } catch (Exception e) {
                logger.error("订单发放异常：{}", e);
                data.setRemark(e.getMessage());
                orderCalculationService.updateByPrimaryKeySelective(data);
            }
        }
    }

    /**
     * 咨询订单
     * 每1分钟 轮询一次数据库，发送通知
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void consultOrderRemind() {
        logger.debug("咨询订单-提醒通知-starting：" + DateUtil.getNow());
        //sql
        SqlSession session = getSqlSession();
        ConsultOrderRemindLogDao dao = session.getMapper(ConsultOrderRemindLogDao.class);
        List<BaseOrderVo> list = new ArrayList<>();
        //query
        try {
            //待发送数据列表
            list = dao.findRemindList();
        } catch (Exception e) {
            logger.error("{}", e);
            if (session != null) {
                session.close();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        ResultResponse msgRst;
        ConsultOrderRemindLog sendLog;
        int isExist;
        for (BaseOrderVo orderVo : list) {
            //推送日志记录
            sendLog = new ConsultOrderRemindLog();
            sendLog.setId(orderVo.getId());
            sendLog.setCreateDate(DateUtil.getNowDate());
            //记录重复校验
            isExist = consultOrderRemindLogDao.isExist(sendLog);
            if (isExist == 0) {
                try {
                    //消息推送
                    msgRst = bisMsgNoticeService.consultOrderRemind(orderVo);
                    if (msgRst.getStatus() == ResultCode.FAIL) {
                        sendLog.setTag("0");
                    } else {
                        sendLog.setTag("1");
                    }
                    sendLog.setInfo(msgRst.toString());
                    consultOrderRemindLogDao.insert(sendLog);
                } catch (Exception e) {
                    logger.error("咨询订单再次提醒-任务调度异常：{}", e);
                    continue;
                }
            }
        }
    }
}
