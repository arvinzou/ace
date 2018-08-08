package com.huacainfo.ace.jxb.service.impl;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.BaseOrderDao;
import com.huacainfo.ace.jxb.dao.CounselorDao;
import com.huacainfo.ace.jxb.dao.CounselorPostLevelDao;
import com.huacainfo.ace.jxb.dao.OrderCalculationDao;
import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.model.OrderCalculation;
import com.huacainfo.ace.jxb.service.AccountFlowRecordService;
import com.huacainfo.ace.jxb.service.OrderCalculationService;
import com.huacainfo.ace.jxb.vo.CounselorPostLevelVo;
import com.huacainfo.ace.jxb.vo.OrderCalculationQVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/8/7 11:44
 * @Description:
 */
@Service("orderCalculationService")
public class OrderCalculationServiceImpl implements OrderCalculationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderCalculationDao orderCalculationDao;

    @Autowired
    private BaseOrderDao baseOrderDao;

    @Autowired
    private CounselorPostLevelDao counselorPostLevelDao;
    @Autowired
    private CounselorDao counselorDao;
    @Autowired
    private AccountFlowRecordService accountFlowRecordService;

    /**
     * 查询目标列表
     *
     * @param condition 查询条件
     * @param start     页码
     * @param limit     页数
     * @param orderBy   排序条件
     * @return list
     */
    @Override
    public List<OrderCalculation> findList(OrderCalculationQVo condition, int start, int limit, String orderBy) {
        return orderCalculationDao.findList(condition, start, start + limit, orderBy);
    }

    /**
     * 插入计算记录
     *
     * @param orderId     订单id
     * @param counselorId 咨询师id
     * @return count
     */
    @Override
    public int insertOrderCalculation(String orderId, String counselorId) {
        OrderCalculation record = new OrderCalculation();
        record.setId(GUIDUtil.getGUID());
        record.setOrderId(orderId);
        record.setCounselorId(counselorId);
        record.setCpuTag("0");
        record.setGrantTag("0");
        record.setStatus("1");
        record.setCreateDate(DateUtil.getNowDate());

        return orderCalculationDao.insertSelective(record);
    }

    /**
     * 计算分成金额
     *
     * @param record 分配记录
     */
    @Override
    public void compute(OrderCalculation record) {
        if (null == record) {
            return;
        }
        if ("0".equals(record.getCpuTag())) {
            BaseOrder order = baseOrderDao.selectByPrimaryKey(record.getOrderId());
            if (null == order) {
                record.setRemark("订单计算 - 订单数据异常");
                record.setUpdateDate(DateUtil.getNowDate());
                orderCalculationDao.updateByPrimaryKeySelective(record);
                return;
            }
            CounselorPostLevelVo cplVo = counselorPostLevelDao.findByCounselorId(record.getCounselorId());
            if (null == cplVo) {
                record.setRemark("订单计算 - 咨询师岗位数据异常");
                record.setUpdateDate(DateUtil.getNowDate());
                orderCalculationDao.updateByPrimaryKeySelective(record);
                return;
            }
            //订单支付金额
            BigDecimal payMoney = order.getPayMoney();
            //分成比例
            BigDecimal ratio = cplVo.getRatio();

            record.setRatio(ratio);
            record.setAmount(payMoney.multiply(ratio));
            record.setCpuTag("1");
            record.setRemark("计算完成");
            record.setUpdateDate(DateUtil.getNowDate());
            orderCalculationDao.updateByPrimaryKeySelective(record);

            // TODO: 2018/8/7 消息通知

        }
    }

    @Override
    public int updateByPrimaryKeySelective(OrderCalculation data) {
        return orderCalculationDao.updateByPrimaryKeySelective(data);
    }

    /**
     * 发放分成金额
     *
     * @param record 分配记录
     */
    @Override
    public void grant(OrderCalculation record) throws Exception {
        if (null == record) {
            return;
        }
        //计算完成，未发放
        if ("1".equals(record.getCpuTag()) && "0".equals(record.getGrantTag())) {
            Counselor counselor = counselorDao.selectByPrimaryKey(record.getCounselorId());
            if (null == counselor) {
                record.setRemark("订单发放 - 咨询师数据异常");
                record.setUpdateDate(DateUtil.getNowDate());
                orderCalculationDao.updateByPrimaryKeySelective(record);
                return;
            }
            BigDecimal income = null == counselor.getIncome() ? BigDecimal.ZERO : counselor.getIncome();
            BigDecimal account = null == counselor.getAccount() ? BigDecimal.ZERO : counselor.getAccount();
            BigDecimal amount = null == record.getAmount() ? BigDecimal.ZERO : record.getAmount();
            //增加累计收益&账户余额
            counselor.setIncome(income.add(amount));
            counselor.setAccount(account.add(amount));
            counselorDao.updateByPrimaryKeySelective(counselor);
            //账户流水记录
            accountFlowRecordService.insertRecord(counselor.getId(), "1", "1",
                    amount, record.getOrderId(), "1");
            // TODO: 2018/8/7 到账消息通知
        }
    }

}
