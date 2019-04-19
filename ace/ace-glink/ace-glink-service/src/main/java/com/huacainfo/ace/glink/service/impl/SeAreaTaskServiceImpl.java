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
import com.huacainfo.ace.glink.api.pojo.fe.AreaTaskOut;
import com.huacainfo.ace.glink.dao.SeAreaTaskDao;
import com.huacainfo.ace.glink.model.SeAreaTask;
import com.huacainfo.ace.glink.service.SeAreaTaskService;
import com.huacainfo.ace.glink.vo.SeAreaTaskQVo;
import com.huacainfo.ace.glink.vo.SeAreaTaskVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("seAreaTaskService")
/**
 * @author: Arvin
 * @version: 2019-04-18
 * @Description: TODO(区域任务数据)
 */
public class SeAreaTaskServiceImpl implements SeAreaTaskService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeAreaTaskDao seAreaTaskDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(区域任务数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<SeAreaTaskVo>
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public PageResult<SeAreaTaskVo> findSeAreaTaskList(SeAreaTaskQVo condition,
                                                       int start, int limit, String orderBy) throws Exception {
        PageResult<SeAreaTaskVo> rst = new PageResult<>();
        List<SeAreaTaskVo> list = seAreaTaskDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.seAreaTaskDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSeAreaTask
     * @Description: TODO(添加区域任务数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse insertSeAreaTask(SeAreaTask o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaNodeID())) {
            return new MessageResponse(1, "区域编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTaskNo())) {
            return new MessageResponse(1, "任务号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTaskName())) {
            return new MessageResponse(1, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        o.setCreateDate(new Date());
        o.setStatus("1");
        this.seAreaTaskDao.insert(o);
        this.dataBaseLogService.log("添加区域任务数据", "区域任务数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSeAreaTask
     * @Description: TODO(更新区域任务数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateSeAreaTask(SeAreaTask o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaNodeID())) {
            return new MessageResponse(1, "区域编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTaskNo())) {
            return new MessageResponse(1, "任务号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTaskName())) {
            return new MessageResponse(1, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        this.seAreaTaskDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更区域任务数据", "区域任务数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSeAreaTaskByPrimaryKey
     * @Description: TODO(获取区域任务数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeAreaTask>
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public SingleResult<SeAreaTaskVo> selectSeAreaTaskByPrimaryKey(String id) throws Exception {
        SingleResult<SeAreaTaskVo> rst = new SingleResult<>();
        rst.setValue(this.seAreaTaskDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSeAreaTaskBySeAreaTaskId
     * @Description: TODO(删除区域任务数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeAreaTaskBySeAreaTaskId(String id, UserProp userProp) throws
            Exception {
        this.seAreaTaskDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除区域任务数据", "区域任务数据", id, id,
                "区域任务数据", userProp);
        return new MessageResponse(0, "删除成功！");
    }

    /**
     * 同步区域任务数据
     *
     * @param userProp 操作人
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse syncData(UserProp userProp) {
        //0-接口请求
        AreaTaskOut out = getTestAreaTask();// SeApiToolKit.getAreaTaskInfo();
        //1-清空库存
        this.seAreaTaskDao.allClear();
        //2-放入库存
        SeAreaTask task;
        for (AreaTaskOut.TaskData item : out.getTaskData()) {
            task = new SeAreaTask();
            task.setId(GUIDUtil.getGUID());
            task.setRemark("");
            task.setStatus("1");
            task.setCreateDate(DateUtil.getNowDate());
            //
            task.setAreaNodeID(item.getAreaNodeID());
            task.setTaskNo(item.getTaskNo());
            task.setTaskName(item.getTaskName());
            this.seAreaTaskDao.insert(task);
        }

        return new MessageResponse(ResultCode.SUCCESS, "同步成功");
    }

    private AreaTaskOut getTestAreaTask() {
        List<AreaTaskOut.TaskData> list = new ArrayList<>();
        AreaTaskOut.TaskData item;
        int index;
        for (int i = 0; i < 6; i++) {
            index = i + 1;
            item = new AreaTaskOut.TaskData();
            item.setAreaNodeID("0-" + i); //区域编号
            item.setTaskNo(i + 1);//任务编号
            item.setTaskName("任务" + index);//任务名称
            list.add(item);
        }

        return new AreaTaskOut(6, list);
    }

}
