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
import com.huacainfo.ace.glink.api.pojo.le.GetBrokenLampDetailOut;
import com.huacainfo.ace.glink.constant.CommConstant;
import com.huacainfo.ace.glink.dao.LeBrokenLampDao;
import com.huacainfo.ace.glink.model.LeBrokenLamp;
import com.huacainfo.ace.glink.service.LeBrokenLampService;
import com.huacainfo.ace.glink.vo.LeBrokenLampQVo;
import com.huacainfo.ace.glink.vo.LeBrokenLampVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("leBrokenLampService")
/**
 * @author: Arvin
 * @version: 2019-04-22
 * @Description: TODO(弱电 - 故障设备情况)
 */
public class LeBrokenLampServiceImpl implements LeBrokenLampService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LeBrokenLampDao leBrokenLampDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(弱电 - 故障设备情况分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LeBrokenLampVo>
     * @author: Arvin
     * @version: 2019-04-22
     */
    @Override
    public PageResult<LeBrokenLampVo> findLeBrokenLampList(LeBrokenLampQVo condition,
                                                           int start, int limit, String orderBy) throws Exception {
        PageResult<LeBrokenLampVo> rst = new PageResult<>();
        List<LeBrokenLampVo> list = leBrokenLampDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.leBrokenLampDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLeBrokenLamp
     * @Description: TODO(添加弱电 - 故障设备情况)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-22
     */
    @Override
    public MessageResponse insertLeBrokenLamp(LeBrokenLamp o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCheckDate())) {
            return new MessageResponse(1, "检查日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getMediaArea())) {
            return new MessageResponse(1, "控制区域编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getController())) {
            return new MessageResponse(1, "控制器编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getChannelNo())) {
            return new MessageResponse(1, "通道编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getLampNo())) {
            return new MessageResponse(1, "灯组编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        o.setCreateDate(new Date());
        o.setStatus("1");
        leBrokenLampDao.insert(o);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateLeBrokenLamp
     * @Description: TODO(更新弱电 - 故障设备情况)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-22
     */
    @Override
    public MessageResponse updateLeBrokenLamp(LeBrokenLamp o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCheckDate())) {
            return new MessageResponse(1, "检查日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getMediaArea())) {
            return new MessageResponse(1, "控制区域编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getController())) {
            return new MessageResponse(1, "控制器编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getChannelNo())) {
            return new MessageResponse(1, "通道编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getLampNo())) {
            return new MessageResponse(1, "灯组编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        this.leBrokenLampDao.updateByPrimaryKey(o);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectLeBrokenLampByPrimaryKey
     * @Description: TODO(获取弱电 - 故障设备情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LeBrokenLamp>
     * @author: Arvin
     * @version: 2019-04-22
     */
    @Override
    public SingleResult<LeBrokenLampVo> selectLeBrokenLampByPrimaryKey(String id) throws Exception {
        SingleResult<LeBrokenLampVo> rst = new SingleResult<>();
        rst.setValue(this.leBrokenLampDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLeBrokenLampByLeBrokenLampId
     * @Description: TODO(删除弱电 - 故障设备情况)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-22
     */
    @Override
    public MessageResponse deleteLeBrokenLampByLeBrokenLampId(String id, UserProp userProp) throws Exception {
        this.leBrokenLampDao.deleteByPrimaryKey(id);

        return new MessageResponse(0, "删除成功！");
    }

    /**
     * 每天凌晨[1]点,调用一次弱电接口：   获取 武汉设备故障情况
     */
    @Override
    public MessageResponse getBrokenLampDetail() {

        //获取前一天数据
        //DateUtil.toDate("2019-04-16 00:00:00");//
        Date yesterday = DateUtil.getDateByDay(DateUtil.getNowDate(), -1);
        String date = DateUtil.toStr(yesterday, CommConstant.DATE_REGEX_LE);
        GetBrokenLampDetailOut rst = null;
        try {
            rst = LeApiToolKit.getBrokenLampDetail(date);
        } catch (Exception e) {
            logger.error("[" + this.getClass().getName() + ".getBrokenLampDetail]接口获取数据异常=>{}", e);
            return new MessageResponse(ResultCode.FAIL, "接口获取数据异常");
        }
        if (rst.getCode() == LeBaseOut.SUCCESS) {
            List<GetBrokenLampDetailOut.BrokenLamp> list = rst.getData();
            LeBrokenLamp record;
            for (GetBrokenLampDetailOut.BrokenLamp item : list) {
                record = new LeBrokenLamp();
                record.setId(GUIDUtil.getGUID());
                record.setCheckDate(date);
                record.setMediaArea(item.getMediaArea());
                record.setController(item.getController());
                record.setChannelNo(item.getChannelNo());
                record.setLampNo(item.getLampNo());
                record.setStatus("1");
                record.setCreateDate(DateUtil.getNowDate());
                leBrokenLampDao.insert(record);
            }

            return new MessageResponse(ResultCode.SUCCESS, "更新成功!");
        } else {

            logger.error("[弱电接口失败]-[武汉设备故障情况接口（getBrokenLampDetail）]=>{}", rst.toString());
            return new MessageResponse(ResultCode.FAIL, "弱电接口调用失败");
        }
    }


}
