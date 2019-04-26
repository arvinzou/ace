package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.LeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;
import com.huacainfo.ace.glink.api.pojo.le.LampStatusOut;
import com.huacainfo.ace.glink.dao.LeLampStatusDao;
import com.huacainfo.ace.glink.model.LeLampStatus;
import com.huacainfo.ace.glink.service.LeLampStatusService;
import com.huacainfo.ace.glink.vo.LeLampStatusQVo;
import com.huacainfo.ace.glink.vo.LeLampStatusVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("leLampStatusService")
/**
 * @author: Arvin
 * @version: 2019-04-25
 * @Description: TODO(弱电 - 设备状态)
 */
public class LeLampStatusServiceImpl implements LeLampStatusService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LeLampStatusDao leLampStatusDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(弱电 - 设备状态分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LeLampStatusVo>
     * @author: Arvin
     * @version: 2019-04-25
     */
    @Override
    public PageResult<LeLampStatusVo> findLeLampStatusList(LeLampStatusQVo condition,
                                                           int start, int limit, String orderBy) throws Exception {
        PageResult<LeLampStatusVo> rst = new PageResult<>();
        List<LeLampStatusVo> list = leLampStatusDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.leLampStatusDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLeLampStatus
     * @Description: TODO(添加弱电 - 设备状态)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-25
     */
    @Override
    public MessageResponse insertLeLampStatus(LeLampStatus o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCheckDate())) {
            return new MessageResponse(1, "检查日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getLampCount())) {
            return new MessageResponse(1, "总设备数不能为空！");
        }
        if (CommonUtils.isBlank(o.getBrokenLampCount())) {
            return new MessageResponse(1, "故障设备数不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        o.setCreateDate(new Date());
        o.setStatus("1");
        this.leLampStatusDao.insert(o);
        this.dataBaseLogService.log("添加弱电-设备状态", "弱电-设备状态", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }


    /**
     * @throws
     * @Title:selectLeLampStatusByPrimaryKey
     * @Description: TODO(获取弱电 - 设备状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LeLampStatus>
     * @author: Arvin
     * @version: 2019-04-25
     */
    @Override
    public SingleResult<LeLampStatusVo> selectLeLampStatusByPrimaryKey(String id) throws Exception {
        SingleResult<LeLampStatusVo> rst = new SingleResult<>();
        rst.setValue(this.leLampStatusDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * 同步设备接口数据
     *
     * @return MessageResponse
     */
    @Override
    public MessageResponse syncData() {
        LampStatusOut rst;
        try {
            rst = LeApiToolKit.getLampStatus();
        } catch (Exception e) {
            logger.error("[" + this.getClass().getName() + ".getLampStatus]接口获取数据异常=>{}", e);
            return new MessageResponse(ResultCode.FAIL, "接口获取数据异常");
        }

        if (rst.getCode() == LeBaseOut.SUCCESS) {

            Date now = DateUtil.getNowDate();
            //0、删除指定日期数据
            int i = leLampStatusDao.deleteByCheckDate(DateUtil.toStr(now, DateUtil.DEFAULT_DATE_REGEX));
            //1、请求接口数据
            LampStatusOut.LampStatus out = rst.getData();
            //
            LeLampStatus r = new LeLampStatus();
            r.setId(GUIDUtil.getGUID());
            r.setStatus("1");
            r.setCreateDate(now);
            //
            r.setCheckDate(DateUtil.toStr(now, DateUtil.DEFAULT_DATE_REGEX));
            r.setCheckYear(DateUtil.toStr(now, "yyyy"));
            r.setCheckMonth(DateUtil.toStr(now, "MM"));
            r.setCheckDay(DateUtil.toStr(now, "dd"));
            r.setLampCount(out.getLampCount());
            r.setBrokenLampCount(out.getBrokenLampCount());
            leLampStatusDao.insert(r);

            return new MessageResponse(ResultCode.SUCCESS, "同步成功!");
        } else {
            logger.error("[弱电接口失败]-[武汉设备状态接口（GetLampStatus）]=>{}", rst.toString());
            return new MessageResponse(ResultCode.FAIL, "弱电接口调用失败");
        }
    }

    /**
     * 大屏展示数据接口 -3
     *
     * @return Map<String, Object>
     */
    @Override
    public List<LeLampStatusVo> getErrorChartData() {

        LeLampStatusQVo condition = new LeLampStatusQVo();
        return leLampStatusDao.findList(condition,0,12,"");
    }


}
