package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.dao.SchedulerDao;
import com.huacainfo.ace.portal.model.Scheduler;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.SchedulerService;
import com.huacainfo.ace.portal.vo.SchedulerQVo;
import com.huacainfo.ace.portal.vo.SchedulerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("schedulerService")
/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 调度设置)
 */
public class SchedulerServiceImpl implements SchedulerService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SchedulerDao schedulerDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 调度设置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<SchedulerVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public PageResult<SchedulerVo> findSchedulerList(SchedulerQVo condition,
                                                     int start, int limit, String orderBy) throws Exception {
        PageResult<SchedulerVo> rst = new PageResult<>();
        List<SchedulerVo> list = schedulerDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.schedulerDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertDynamicScheduler
     * @Description: TODO(添加故障报警 - 调度设置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse insertScheduler(Scheduler o, UserProp userProp) throws Exception {


        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "调度名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCorn())) {
            return new MessageResponse(1, "corn表达式不能为空！");
        }


        int temp = this.schedulerDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "调度规则名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setClassPath("com.huacainfo.ace.glink");
        o.setValidState("0");
        o.setRestartState("0");
        o.setStatus("1");
        o.setSysId(userProp.getActiveSyId());
        o.setCreateDate(new Date());
        schedulerDao.insert(o);
        dataBaseLogService.log("添加调度设置",
                "调度设置", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateDynamicScheduler
     * @Description: TODO(更新故障报警 - 调度设置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse updateScheduler(Scheduler o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "调度名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getJobName())) {
            return new MessageResponse(1, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTriggerName())) {
            return new MessageResponse(1, "触发器名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getJobGroupName())) {
            return new MessageResponse(1, "任务组不能为空！");
        }
        if (CommonUtils.isBlank(o.getTriggerGroupName())) {
            return new MessageResponse(1, "触发器组不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassPath())) {
            return new MessageResponse(1, "类路径不能为空！");
        }
        if (CommonUtils.isBlank(o.getValidState())) {
            return new MessageResponse(1, "是否有效 不能为空！");
        }
        if (CommonUtils.isBlank(o.getRestartState())) {
            return new MessageResponse(1, "是否重启该任务 不能为空！");
        }
        if (CommonUtils.isBlank(o.getCorn())) {
            return new MessageResponse(1, "corn表达式不能为空！");
        }
        if (CommonUtils.isBlank(o.getSysId())) {
            return new MessageResponse(1, "系统识别ID不能为空！");
        }


        this.schedulerDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更调度设置", "调度设置", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectDynamicSchedulerByPrimaryKey
     * @Description: TODO(获取故障报警 - 调度设置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Scheduler>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public SingleResult<SchedulerVo> selectSchedulerByPrimaryKey(String id) throws Exception {
        SingleResult<SchedulerVo> rst = new SingleResult<>();
        rst.setValue(this.schedulerDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteDynamicSchedulerByDynamicSchedulerId
     * @Description: TODO(删除故障报警 - 调度设置)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @Override
    public MessageResponse deleteSchedulerBySchedulerId(String id, UserProp userProp) throws
            Exception {
        this.schedulerDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除调度设置", "调度设置", id, id,
                "调度设置", userProp);
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
        this.schedulerDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "调度设置", id, id,
                "调度设置", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * 更新调度规则有效状态
     *
     * @param id    主键
     * @param state 0-失效，1-生效
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse updateValidState(String id, String state, UserProp userProp) {

        schedulerDao.updateValidState(id, state);
        dataBaseLogService.log("变更有效状态", "调度设置", id, id, "调度设置", userProp);

        return new MessageResponse(ResultCode.SUCCESS, "成功！");
    }

}
