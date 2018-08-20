package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.ConsultOrderDao;
import com.huacainfo.ace.jxb.model.ConsultOrder;
import com.huacainfo.ace.jxb.service.ConsultOrderService;
import com.huacainfo.ace.jxb.vo.ConsultOrderQVo;
import com.huacainfo.ace.jxb.vo.ConsultOrderVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("consultOrderService")
/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(咨询预约订单详情)
 */
public class ConsultOrderServiceImpl implements ConsultOrderService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ConsultOrderDao consultOrderDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询预约订单详情分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ConsultOrderVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public PageResult<ConsultOrderVo> findConsultOrderList(ConsultOrderQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<ConsultOrderVo> rst = new PageResult<>();
        List<ConsultOrderVo> list = this.consultOrderDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.consultOrderDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertConsultOrder
     * @Description: TODO(添加咨询预约订单详情)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse insertConsultOrder(ConsultOrder o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "订单主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTel())) {
            return new MessageResponse(1, "联系方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getAge())) {
            return new MessageResponse(1, "年龄不能为空！");
        }
        if (CommonUtils.isBlank(o.getSex())) {
            return new MessageResponse(1, "性别不能为空！");
        }
        if (CommonUtils.isBlank(o.getInfo())) {
            return new MessageResponse(1, "问题类型及描述不能为空！");
        }
        if (CommonUtils.isBlank(o.getSecName())) {
            return new MessageResponse(1, "紧急联系人不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationship())) {
            return new MessageResponse(1, "关系不能为空！");
        }
        if (CommonUtils.isBlank(o.getSecTel())) {
            return new MessageResponse(1, "联系电话不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.consultOrderDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "咨询预约订单详情名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.consultOrderDao.insertSelective(o);
        this.dataBaseLogService.log("添加咨询预约订单详情", "咨询预约订单详情", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加咨询预约订单详情完成！");
    }

    /**
     * @throws
     * @Title:updateConsultOrder
     * @Description: TODO(更新咨询预约订单详情)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse updateConsultOrder(ConsultOrder o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "订单主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTel())) {
            return new MessageResponse(1, "联系方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getAge())) {
            return new MessageResponse(1, "年龄不能为空！");
        }
        if (CommonUtils.isBlank(o.getSex())) {
            return new MessageResponse(1, "性别不能为空！");
        }
        if (CommonUtils.isBlank(o.getInfo())) {
            return new MessageResponse(1, "问题类型及描述不能为空！");
        }
        if (CommonUtils.isBlank(o.getSecName())) {
            return new MessageResponse(1, "紧急联系人不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationship())) {
            return new MessageResponse(1, "关系不能为空！");
        }
        if (CommonUtils.isBlank(o.getSecTel())) {
            return new MessageResponse(1, "联系电话不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        this.consultOrderDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更咨询预约订单详情", "咨询预约订单详情", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更咨询预约订单详情完成！");
    }

    /**
     * @throws
     * @Title:selectConsultOrderByPrimaryKey
     * @Description: TODO(获取咨询预约订单详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ConsultOrder>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public SingleResult<ConsultOrderVo> selectConsultOrderByPrimaryKey(String id) throws Exception {
        SingleResult<ConsultOrderVo> rst = new SingleResult<>();
        rst.setValue(this.consultOrderDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteConsultOrderByConsultOrderId
     * @Description: TODO(删除咨询预约订单详情)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse deleteConsultOrderByConsultOrderId(String id, UserProp userProp) throws
            Exception {
        this.consultOrderDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除咨询预约订单详情", "咨询预约订单详情",
                String.valueOf(id),
                String.valueOf(id), "咨询预约订单详情", userProp);
        return new MessageResponse(0, "咨询预约订单详情删除完成！");
    }

}
