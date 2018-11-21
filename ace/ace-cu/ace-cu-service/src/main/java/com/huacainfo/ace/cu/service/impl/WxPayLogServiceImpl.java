package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.cu.dao.WxPayLogDao;
import com.huacainfo.ace.cu.model.WxPayLog;
import com.huacainfo.ace.cu.service.WxPayLogService;
import com.huacainfo.ace.cu.vo.WxPayLogQVo;
import com.huacainfo.ace.cu.vo.WxPayLogVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("wxPayLogService")
/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(捐款支付订单)
 */
public class WxPayLogServiceImpl implements WxPayLogService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WxPayLogDao wxPayLogDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(捐款支付订单分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <WxPayLogVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public PageResult<WxPayLogVo> findWxPayLogList(WxPayLogQVo condition, int start,
                                                   int limit, String orderBy) throws Exception {
        PageResult<WxPayLogVo> rst = new PageResult<>();
        List<WxPayLogVo> list = this.wxPayLogDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.wxPayLogDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertWxPayLog
     * @Description: TODO(添加捐款支付订单)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse insertWxPayLog(WxPayLog o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.wxPayLogDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "捐款支付订单名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.wxPayLogDao.insertSelective(o);
        this.dataBaseLogService.log("添加捐款支付订单", "捐款支付订单", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加捐款支付订单完成！");
    }

    /**
     * @throws
     * @Title:updateWxPayLog
     * @Description: TODO(更新捐款支付订单)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse updateWxPayLog(WxPayLog o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.wxPayLogDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更捐款支付订单", "捐款支付订单", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更捐款支付订单完成！");
    }

    /**
     * @throws
     * @Title:selectWxPayLogByPrimaryKey
     * @Description: TODO(获取捐款支付订单)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WxPayLog>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public SingleResult<WxPayLogVo> selectWxPayLogByPrimaryKey(String id) throws Exception {
        SingleResult<WxPayLogVo> rst = new SingleResult<>();
        rst.setValue(this.wxPayLogDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteWxPayLogByWxPayLogId
     * @Description: TODO(删除捐款支付订单)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse deleteWxPayLogByWxPayLogId(String id, UserProp userProp) throws Exception {
        this.wxPayLogDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除捐款支付订单", "捐款支付订单",
                String.valueOf(id),
                String.valueOf(id), "捐款支付订单", userProp);
        return new MessageResponse(0, "捐款支付订单删除完成！");
    }

}
