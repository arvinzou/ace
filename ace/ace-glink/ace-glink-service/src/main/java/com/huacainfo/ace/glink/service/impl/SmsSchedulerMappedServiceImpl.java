package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.dao.SmsSchedulerMappedDao;
import com.huacainfo.ace.glink.model.SmsSchedulerMapped;
import com.huacainfo.ace.glink.service.SmsSchedulerMappedService;
import com.huacainfo.ace.glink.vo.SmsSchedulerMappedQVo;
import com.huacainfo.ace.glink.vo.SmsSchedulerMappedVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("smsSchedulerMappedService")
/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 短信 - 调度映射关系)
 */
public class SmsSchedulerMappedServiceImpl implements SmsSchedulerMappedService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SmsSchedulerMappedDao smsSchedulerMappedDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 短信 - 调度映射关系分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<SmsSchedulerMappedVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public PageResult<SmsSchedulerMappedVo> findSmsSchedulerMappedList(SmsSchedulerMappedQVo condition,
                                                                       int start, int limit, String orderBy) throws Exception {
        PageResult<SmsSchedulerMappedVo> rst = new PageResult<>();
        List<SmsSchedulerMappedVo> list = smsSchedulerMappedDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.smsSchedulerMappedDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSmsSchedulerMapped
     * @Description: TODO(添加故障报警 - 短信 - 调度映射关系)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse insertSmsSchedulerMapped(SmsSchedulerMapped o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSmsId())) {
            return new MessageResponse(1, "短信模板ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getSchedulerId())) {
            return new MessageResponse(1, "调度设置ID不能为空！");
        }


        int temp = this.smsSchedulerMappedDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "故障报警-短信-调度映射关系名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        this.smsSchedulerMappedDao.insert(o);
        this.dataBaseLogService.log("添加故障报警-短信-调度映射关系", "故障报警-短信-调度映射关系", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSmsSchedulerMapped
     * @Description: TODO(更新故障报警 - 短信 - 调度映射关系)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse updateSmsSchedulerMapped(SmsSchedulerMapped o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSmsId())) {
            return new MessageResponse(1, "短信模板ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getSchedulerId())) {
            return new MessageResponse(1, "调度设置ID不能为空！");
        }


        this.smsSchedulerMappedDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更故障报警-短信-调度映射关系", "故障报警-短信-调度映射关系", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSmsSchedulerMappedByPrimaryKey
     * @Description: TODO(获取故障报警 - 短信 - 调度映射关系)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SmsSchedulerMapped>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public SingleResult<SmsSchedulerMappedVo> selectSmsSchedulerMappedByPrimaryKey(String id) throws Exception {
        SingleResult<SmsSchedulerMappedVo> rst = new SingleResult<>();
        rst.setValue(this.smsSchedulerMappedDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSmsSchedulerMappedBySmsSchedulerMappedId
     * @Description: TODO(删除故障报警 - 短信 - 调度映射关系)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse deleteSmsSchedulerMappedBySmsSchedulerMappedId(String id, UserProp userProp) throws
            Exception {
        this.smsSchedulerMappedDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除故障报警-短信-调度映射关系", "故障报警-短信-调度映射关系", id, id,
                "故障报警-短信-调度映射关系", userProp);
        return new MessageResponse(0, "删除成功！");
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.smsSchedulerMappedDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "故障报警-短信-调度映射关系", id, id,
                "故障报警-短信-调度映射关系", userProp);
        return new MessageResponse(0, "成功！");
    }

}
