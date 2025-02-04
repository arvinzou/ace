package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.constant.OrderState;
import com.huacainfo.ace.society.constant.PayType;
import com.huacainfo.ace.society.dao.*;
import com.huacainfo.ace.society.model.*;
import com.huacainfo.ace.society.service.OrderInfoService;
import com.huacainfo.ace.society.service.PointsRecordService;
import com.huacainfo.ace.society.service.RegService;
import com.huacainfo.ace.society.vo.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderInfoService")
/**
 * @author: Arvin
 * @version: 2018-09-17
 * @Description: TODO(订单管理)
 */
public class OrderInfoServiceImpl implements OrderInfoService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrgAdminDao orgAdminDao;
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CommodityDao commoditydao;
    @Autowired
    private RegService regService;
    @Autowired
    private PersonInfoDao personInfoDao;
    @Autowired
    private SocietyOrgInfoDao societyOrgInfoDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private PointsRecordService pointsRecordService;
    @Autowired
    private SpaceOccupyInfoDao spaceOccupyInfoDao;


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
    public ResultResponse create(OrderInfoVo info) throws Exception {
        if (CollectionUtils.isEmpty(info.getDetailList())) {
            return new ResultResponse(ResultCode.FAIL, "缺少订单明细");
        }
        //预设orderId
        info.setId(GUIDUtil.getGUID());
        //用户资料
        CustomerVo customerVo = regService.findByUserId(info.getUserId());
        if (null == customerVo) {
            return new ResultResponse(ResultCode.FAIL, "用户资料缺失");
        }
        //支付金额计算
        BigDecimal subTotal;
        BigDecimal salePrice;
        BigDecimal payAmount = BigDecimal.ZERO;
        CommodityVo commodity;
        for (OrderDetail item : info.getDetailList()) {
            commodity = commoditydao.selectVoByPrimaryKey(item.getCommodityId());
            if (null == commodity || !"1".equals(commodity.getState())) {
                return new ResultResponse(ResultCode.FAIL, "商品资料有误");
            }
            if (item.getPurchaseQty() <= 0) {
                return new ResultResponse(ResultCode.FAIL, "商品购买数量有误");
            }
            //爱心场地
            if ("1".equals(commodity.getCommodityType())) {
                ResultResponse rs = checkSpaceOccupyInfo(commodity, item, info.getSpaceOccupyInfo());
                if (rs.getStatus() == ResultCode.FAIL) {
                    return rs;
                }
                //插入预定记录
                spaceOccupyRecord(info, commodity);
            }
            //获取销售价格
            if (PayType.POINTS.equals(info.getPayType())) {
                salePrice = new BigDecimal(null == commodity.getCostPoints() ? 0 : commodity.getCostPoints());
            } else {
                salePrice = null == item.getSalePrice() ? BigDecimal.ZERO : item.getSalePrice();
            }
            salePrice = salePrice.setScale(2, BigDecimal.ROUND_HALF_UP);

            subTotal = salePrice.multiply(new BigDecimal(item.getPurchaseQty()));
            item.setCommodityName(commodity.getCommodityName());
            item.setCommodityCover(commodity.getCommodityCover());
            item.setSalePrice(new BigDecimal(commodity.getCostPoints()).setScale(2, BigDecimal.ROUND_HALF_UP));
            item.setSubtotal(subTotal);

            payAmount = payAmount.add(subTotal);
        }
        info.setPayAmount(payAmount);
        //扣除积分
        if (PayType.POINTS.equals(info.getPayType())) {
            ResultResponse rs = createPointsOrder(info, customerVo);
            if (rs.getStatus() == ResultCode.FAIL) {
                throw new CustomException(rs.getInfo());
            }
            return rs;
        } else {
            throw new CustomException("支付类型不支持");
        }
    }

    /**
     * 记录场地预定信息
     *
     * @param info      订单信息
     * @param commodity 下单场地信息
     */
    private void spaceOccupyRecord(OrderInfoVo info, CommodityVo commodity) {
        SpaceOccupyInfo params = info.getSpaceOccupyInfo();
        params.setId(GUIDUtil.getGUID());
        params.setSpaceId(commodity.getId());
        params.setOrderId(info.getId());
        spaceOccupyInfoDao.insert(params);
    }

    /**
     * 检查场地占用情况
     *
     * @param commodity       商品信息
     * @param item            下单明细
     * @param spaceOccupyInfo 预约情况
     * @return
     */
    private ResultResponse checkSpaceOccupyInfo(CommodityVo commodity,
                                                OrderDetail item,
                                                SpaceOccupyInfo spaceOccupyInfo) {
        if (null == spaceOccupyInfo) {
            return new ResultResponse(ResultCode.FAIL, "预约信息不能为空");
        }
        if (!StringUtil.areNotEmpty(spaceOccupyInfo.getSpaceId(),
                spaceOccupyInfo.getYear(),
                spaceOccupyInfo.getMonth(),
                spaceOccupyInfo.getDay(),
                spaceOccupyInfo.getReserveInterval())) {
            return new ResultResponse(ResultCode.FAIL, "预约信息不能为空");
        }

        int isExist = spaceOccupyInfoDao.isExist(spaceOccupyInfo);
        if (isExist > 0) {
            return new ResultResponse(ResultCode.FAIL, "当前时间已被预约，请重新选择时间段");
        }

        return new ResultResponse(ResultCode.SUCCESS, "可以预约");
    }

    /**
     * 创建积分订单
     *
     * @param info       订单信息
     * @param customerVo 客户信息
     * @return ResultResponse
     */
    private ResultResponse createPointsOrder(OrderInfoVo info, CustomerVo customerVo) {
        int payPoints = info.getPayAmount().intValue();
        int updCount;
        String feeType = info.getFeeType();
        //个人积分扣除
        if ("1".equals(feeType)) {
            PersonInfo personInfo = personInfoDao.selectByPrimaryKey(info.getUserId());
            if (null == personInfo || payPoints > personInfo.getValidPoints()) {
                return new ResultResponse(ResultCode.FAIL, "用户爱心币不足");
            }
            personInfo.setValidPoints(personInfo.getValidPoints() - payPoints);
            personInfo.setLastModifyDate(DateUtil.getNowDate());
            personInfo.setLastModifyUserId("0000-0000");
            personInfo.setLastModifyUserName("system");

            updCount = personInfoDao.updateByPrimaryKey(personInfo);
            if (updCount != 1) {
                throw new CustomException("更新用户爱心币失败");
            }
            //积分流水记录
            pointsRecordService.addPointsRecord(info.getUserId(),
                    BisType.POINTS_ORDER_CONSUME, info.getId(), payPoints);
        }//组织积分扣除
        else if ("2".equals(feeType)) {
            OrgAdminVo orgAdminVo = orgAdminDao.findByUserId(info.getUserId());
            if (orgAdminVo == null) {
                return new ResultResponse(ResultCode.FAIL, "用户组织信息不存在");
            }
            SocietyOrgInfo org = societyOrgInfoDao.selectByPrimaryKey(orgAdminVo.getOrgId());
            if (org == null || payPoints > org.getValidPoints()) {
                return new ResultResponse(ResultCode.FAIL, "组织爱心币不足");
            }
            org.setValidPoints(org.getValidPoints() - payPoints);
            org.setLastModifyDate(DateUtil.getNowDate());
            org.setLastModifyUserId("0000-0000");
            org.setLastModifyUserName("system");
            updCount = societyOrgInfoDao.updateByPrimaryKey(org);
            if (updCount != 1) {
                throw new CustomException("更新用户爱心币失败");
            }
            //积分流水记录
            pointsRecordService.addPointsRecord(orgAdminVo.getOrgId(),
                    BisType.POINTS_ORDER_CONSUME, info.getId(), payPoints);
        } else {
            return new ResultResponse(ResultCode.FAIL, "用户爱心币不足");
        }
        //入库订单信息
        info.setPayAmount(info.getPayAmount());
        info.setPayDate(DateUtil.getNowDate());
        info.setReceiveType("1");
        info.setOrderState(OrderState.ORDER_STATE_PAID);
        info = insertOrder(info);

        //返回信息
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("orderId", info.getId());
        rtnMap.put("payAmount", info.getPayAmount());
        return new ResultResponse(ResultCode.SUCCESS, "订单创建成功", rtnMap);
    }

    /**
     * 订单数据入库
     *
     * @param info
     * @return
     */
    private OrderInfoVo insertOrder(OrderInfoVo info) {
        String orderId = StringUtil.isEmpty(info.getId()) ? GUIDUtil.getGUID() : info.getId();
        String orderState = StringUtil.isEmpty(info.getOrderState())
                ? OrderState.ORDER_STATE_NEW : info.getOrderState();
        info.setId(orderId);
        info.setOrderNo(StringUtil.genOrderNo18Str(orderId));
        info.setCreateDate(DateUtil.getNowDate());
        info.setCreateUserId("0000-0000");
        info.setCreateUserName("system");
        info.setStatus("1");
        info.setOrderState(orderState);
        orderInfoDao.insert(info);
        //明细入库
        for (OrderDetail detail : info.getDetailList()) {
            detail.setId(GUIDUtil.getGUID());
            detail.setOrderId(orderId);
            detail.setDetailState(orderState);
            detail.setStatus("1");
            detail.setCreateDate(DateUtil.getNowDate());
            detail.setCreateUserId("0000-0000");
            detail.setCreateUserName("system");
            orderDetailDao.insert(detail);
        }

        return info;
    }

}
