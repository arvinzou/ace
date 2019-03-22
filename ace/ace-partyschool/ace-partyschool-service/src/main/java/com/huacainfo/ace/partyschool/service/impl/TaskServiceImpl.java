package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.partyschool.dao.NoticeDao;
import com.huacainfo.ace.partyschool.dao.TaskDao;
import com.huacainfo.ace.partyschool.model.Notice;
import com.huacainfo.ace.partyschool.model.Task;
import com.huacainfo.ace.partyschool.service.NoticeStatusService;
import com.huacainfo.ace.partyschool.service.TaskService;
import com.huacainfo.ace.partyschool.vo.TaskQVo;
import com.huacainfo.ace.partyschool.vo.TaskVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("taskService")
/**
 * @author: 王恩
 * @version: 2019-03-08
 * @Description: TODO(任务管理)
 */
public class TaskServiceImpl implements TaskService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private NoticeStatusService  noticeStatusService;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(任务管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TaskVo>
     * @author: 王恩
     * @version: 2019-03-08
     */
    @Override
    public PageResult<TaskVo> findTaskList(TaskQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<TaskVo> rst = new PageResult<>();
        List<TaskVo> list = this.taskDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.taskDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTask
     * @Description: TODO(添加任务管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse insertTask(Task o, UserProp userProp) throws Exception {
        String taskId=GUIDUtil.getGUID();
        o.setId(taskId);
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTestId())) {
            return new MessageResponse(1, "test主键不能为空！");
        }if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "任务介绍不能为空！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.taskDao.insert(o);
        Notice notice=new Notice();
        notice.setId(taskId);
        notice.setTitle(o.getName());
        notice.setContent("<p>"+o.getIntroduce()+"：<a href=\""+PropertyUtil.getProperty("fastdfs_server")+"partyschool/www/registered/test/test.jsp?testId="+o.getTestId()+"&taskId="+taskId+"\" target=\"_blank\" class=\"\">点击开始</a></p>");
        notice.setCategory("3");
        notice.setStatus("1");
        notice.setPublisher(userProp.getName());
        notice.setPushDate(new java.util.Date());
        this.noticeDao.insert(notice);
        this.dataBaseLogService.log("添加任务管理", "任务管理", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * @throws
     * @Title:updateTask
     * @Description: TODO(更新任务管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse updateTask(Task o, UserProp userProp) throws Exception {
        String taskId=o.getId();
        if (CommonUtils.isBlank(taskId)) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTestId())) {
            return new MessageResponse(1, "test主键不能为空！");
        }if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "任务介绍不能为空！");
        }
        o.setStatus("1");
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.taskDao.updateByPrimaryKey(o);
        Notice notice=new Notice();
        notice.setId(taskId);
        notice.setTitle(o.getName());
        notice.setContent("<p>"+o.getIntroduce()+"：<a href=\""+PropertyUtil.getProperty("fastdfs_server")+"partyschool/www/registered/test/test.jsp?testId="+o.getTestId()+"&taskId="+taskId+"\" target=\"_blank\" class=\"\">点击开始</a></p>");
        notice.setStatus("1");
        this.noticeDao.updateByPrimaryKey(notice);
        this.dataBaseLogService.log("变更任务管理", "任务管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTaskByPrimaryKey
     * @Description: TODO(获取任务管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Task>
     * @author: 王恩
     * @version: 2019-03-08
     */
    @Override
    public SingleResult<TaskVo> selectTaskByPrimaryKey(String id) throws Exception {
        SingleResult<TaskVo> rst = new SingleResult<>();
        rst.setValue(this.taskDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTaskByTaskId
     * @Description: TODO(删除任务管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse deleteTaskByTaskId(String id, UserProp userProp) throws
            Exception {
        this.taskDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除任务管理", "任务管理", id, id,
                "任务管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核任务管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核任务管理", "任务管理", id, id,
                "任务管理", userProp);
        return new MessageResponse(0, "审核成功！");
    }


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            Task o = new Task();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setCreateUserName(userProp.getName());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "任务名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getTestId())) {
                return new MessageResponse(1, "test主键不能为空！");
            }

            int t = this.taskDao.isExit(o);
            if (t > 0) {
                this.taskDao.updateByPrimaryKey(o);

            } else {
                this.taskDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("任务管理导入", "任务管理", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: 王恩
     * @version: 2019-03-08
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.taskDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 王恩
     * @version: 2019-03-08
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.taskDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除任务管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse deleteTaskByTaskIds(String[] id, UserProp userProp) throws Exception {

        this.taskDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除任务管理", "任务管理", id[0], id[0], "任务管理", userProp);
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
     * @author: 王恩
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.taskDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "任务管理", id, id, "任务管理", userProp);
        return new MessageResponse(0, "成功！");
    }

    @Override
    public MessageResponse releaseTask(String id,UserProp userProp) throws Exception {
        Notice notice=new Notice();
        TaskVo taskVo=this.taskDao.selectVoByPrimaryKey(id);
        if(CommonUtils.isBlank(taskVo)){
            return new MessageResponse(ResultCode.FAIL,"任务丢失");
        }
        //"+request.getHeader("host")+"
        notice.setId(GUIDUtil.getGUID());
        notice.setTitle(taskVo.getName());
        notice.setContent("<p>"+taskVo.getIntroduce()+" 点击下方链接开始：<a href=\""+PropertyUtil.getProperty("fastdfs_server")+"partyschool/www/registered/test/test.jsp?testId="+taskVo.getTid()+"&taskId="+id+"\" target=\"_blank\" class=\"\">"+taskVo.getTname()+"</a></p>");
        notice.setCategory("3");
        notice.setStatus("1");
        notice.setPublisher(userProp.getName());
        notice.setPushDate(new java.util.Date());
        taskVo.setStatus("2");
        this.noticeDao.insert(notice);
        this.taskDao.updateByPrimaryKey(taskVo);
        this.dataBaseLogService.log("跟新状态", "任务管理", id, id, "任务管理", userProp);
        return new MessageResponse(0, "成功！");
    }
}
