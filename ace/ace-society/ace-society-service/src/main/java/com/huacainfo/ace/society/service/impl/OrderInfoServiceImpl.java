package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.dao.OrderInfoDao;
import com.huacainfo.ace.society.model.OrderDetail;
import com.huacainfo.ace.society.model.OrderInfo;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.OrderInfoService;
import com.huacainfo.ace.society.vo.OrderInfoQVo;
import com.huacainfo.ace.society.vo.OrderInfoVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("orderInfoService")
/**
 * @author: Arvin
 * @version: 2018-09-17
 * @Description: TODO(订单管理)
 */
public class OrderInfoServiceImpl implements OrderInfoService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    @Autowired
    private SqlSessionTemplate sqlSession;

    private SqlSession getSqlSession() {
        SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);

        return session;
    }

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(订单管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <OrderInfoVo>
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public PageResult<OrderInfoVo> findOrderInfoList(OrderInfoQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        //sql
        SqlSession session = getSqlSession();
        OrderInfoDao dao = session.getMapper(OrderInfoDao.class);
        //query
        PageResult<OrderInfoVo> rst = new PageResult<>();
        try {
            List<OrderInfoVo> list = dao.findList(condition, start, limit, orderBy);
            rst.setRows(list);
            if (start <= 1) {
                int allRows = orderInfoDao.findCount(condition);
                rst.setTotal(allRows);
            }
            return rst;
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
        return rst;
    }

    /**
     * @throws
     * @Title:insertOrderInfo
     * @Description: TODO(添加订单管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse insertOrderInfo(OrderInfo o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "客户主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayType())) {
            return new MessageResponse(1, "付款方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayAmount())) {
            return new MessageResponse(1, "付款金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getReceiveType())) {
            return new MessageResponse(1, "收货类型1-自取2-配送不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderState())) {
            return new MessageResponse(1, "订单状态不能为空！");
        }


        int temp = this.orderInfoDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "订单管理名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.orderInfoDao.insert(o);
        this.dataBaseLogService.log("添加订单管理", "订单管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加订单管理完成！");
    }

    /**
     * @throws
     * @Title:updateOrderInfo
     * @Description: TODO(更新订单管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse updateOrderInfo(OrderInfo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "客户主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayType())) {
            return new MessageResponse(1, "付款方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayAmount())) {
            return new MessageResponse(1, "付款金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getReceiveType())) {
            return new MessageResponse(1, "收货类型1-自取2-配送不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderState())) {
            return new MessageResponse(1, "订单状态不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.orderInfoDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更订单管理", "订单管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更订单管理完成！");
    }

    /**
     * @throws
     * @Title:selectOrderInfoByPrimaryKey
     * @Description: TODO(获取订单管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<OrderInfo>
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public SingleResult<OrderInfoVo> selectOrderInfoByPrimaryKey(String id) throws Exception {
        //sql
        SqlSession session = getSqlSession();
        OrderInfoDao dao = session.getMapper(OrderInfoDao.class);
        SingleResult<OrderInfoVo> rst = new SingleResult<>();
        try {
            rst.setValue(dao.selectVoByPrimaryKey(id));

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

        return rst;
    }

    /**
     * @throws
     * @Title:deleteOrderInfoByOrderInfoId
     * @Description: TODO(删除订单管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse deleteOrderInfoByOrderInfoId(String id, UserProp userProp) throws
            Exception {
        this.orderInfoDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除订单管理", "订单管理", id, id,
                "订单管理", userProp);
        return new MessageResponse(0, "订单管理删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核订单管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception {

        OrderInfo obj = orderInfoDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "订单管理数据丢失");
        }

        //更改审核记录
//        MessageResponse auditRs =
//                auditRecordService.audit(BisType.SOCIETY_ORG_INFO, obj.getId(), obj.getId(), rst, remark,
//                        userProp);
//        if (ResultCode.FAIL == auditRs.getStatus()) {
//            return auditRs;
//        }
//
//        obj.setStatus(rst);
//        obj.setLastModifyDate(DateUtil.getNowDate());
//        obj.setLastModifyUserId(userProp.getUserId());
//        obj.setLastModifyUserName(userProp.getName());
//        orderInfoDao.updateStatus(obj);


        dataBaseLogService.log("审核订单管理", "订单管理", id, id,
                "订单管理", userProp);
        return new MessageResponse(0, "订单管理审核完成！");
    }

    /**
     * 创建订单
     *
     * @param info 订单信息
     * @return ResultResponse
     */
    @Override
    public ResultResponse create(OrderInfoVo info) {
        if (CollectionUtils.isEmpty(info.getDetailList())) {
            return new ResultResponse(ResultCode.FAIL, "缺少订单明细");
        }
        BigDecimal payAmount = BigDecimal.ZERO;
        for (OrderDetail item : info.getDetailList()) {

        }


        return null;
    }

}
