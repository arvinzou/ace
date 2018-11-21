package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.dao.OrderDetailDao;
import com.huacainfo.ace.society.model.OrderDetail;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.OrderDetailService;
import com.huacainfo.ace.society.vo.OrderDetailQVo;
import com.huacainfo.ace.society.vo.OrderDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("orderDetailService")
/**
 * @author: Arvin
 * @version: 2018-09-17
 * @Description: TODO(订单详情)
 */
public class OrderDetailServiceImpl implements OrderDetailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(订单详情分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <OrderDetailVo>
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public PageResult<OrderDetailVo> findOrderDetailList(OrderDetailQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult<OrderDetailVo> rst = new PageResult<>();
        List<OrderDetailVo> list = this.orderDetailDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.orderDetailDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertOrderDetail
     * @Description: TODO(添加订单详情)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse insertOrderDetail(OrderDetail o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderId())) {
            return new MessageResponse(1, "订单ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityId())) {
            return new MessageResponse(1, "商品主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSalePrice())) {
            return new MessageResponse(1, "商品售价不能为空！");
        }
        if (CommonUtils.isBlank(o.getPurchaseQty())) {
            return new MessageResponse(1, "购买数量不能为空！");
        }


        int temp = this.orderDetailDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "订单详情名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.orderDetailDao.insert(o);
        this.dataBaseLogService.log("添加订单详情", "订单详情", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加订单详情完成！");
    }

    /**
     * @throws
     * @Title:updateOrderDetail
     * @Description: TODO(更新订单详情)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse updateOrderDetail(OrderDetail o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderId())) {
            return new MessageResponse(1, "订单ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getCommodityId())) {
            return new MessageResponse(1, "商品主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSalePrice())) {
            return new MessageResponse(1, "商品售价不能为空！");
        }
        if (CommonUtils.isBlank(o.getPurchaseQty())) {
            return new MessageResponse(1, "购买数量不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.orderDetailDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更订单详情", "订单详情", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更订单详情完成！");
    }

    /**
     * @throws
     * @Title:selectOrderDetailByPrimaryKey
     * @Description: TODO(获取订单详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<OrderDetail>
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public SingleResult<OrderDetailVo> selectOrderDetailByPrimaryKey(String id) throws Exception {
        SingleResult<OrderDetailVo> rst = new SingleResult<>();
        rst.setValue(this.orderDetailDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteOrderDetailByOrderDetailId
     * @Description: TODO(删除订单详情)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse deleteOrderDetailByOrderDetailId(String id, UserProp userProp) throws Exception {
        this.orderDetailDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除订单详情", "订单详情", id, id,
                "订单详情", userProp);
        return new MessageResponse(0, "订单详情删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核订单详情)
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

//        OrderDetail obj = orderDetailDao.selectByPrimaryKey(id);
//        if (obj == null) {
//            return new MessageResponse(ResultCode.FAIL, "订单详情数据丢失");
//        }
//
//        //更改审核记录
//        MessageResponse auditRs =
//                auditRecordService.audit(BisType.SOCIETY_ORG_INFO, obj.getId(), obj.getId(), rst, remark, userProp);
//        if (ResultCode.FAIL == auditRs.getStatus()) {
//            return auditRs;
//        }
//
//        obj.setStatus(rst);
//        obj.setLastModifyDate(DateUtil.getNowDate());
//        obj.setLastModifyUserId(userProp.getUserId());
//        obj.setLastModifyUserName(userProp.getName());
//        orderDetailDao.updateStatus(obj);


        dataBaseLogService.log("审核订单详情", "订单详情", id, id,
                "订单详情", userProp);
        return new MessageResponse(0, "订单详情审核完成！");
    }

}
