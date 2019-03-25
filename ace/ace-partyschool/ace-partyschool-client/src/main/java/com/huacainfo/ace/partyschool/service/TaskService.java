package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.partyschool.model.Task;
import com.huacainfo.ace.partyschool.vo.TaskVo;
import com.huacainfo.ace.partyschool.vo.TaskQVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.List;

/**
 * @author: 王恩
 * @version: 2019-03-08
 * @Description: TODO(任务管理)
 */
public interface TaskService {
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
    PageResult<TaskVo> findTaskList(TaskQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertTask
     * @Description: TODO(添加任务管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    MessageResponse insertTask(Task obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTask
     * @Description: TODO(更新任务管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    MessageResponse updateTask(Task obj, UserProp userProp) throws Exception;

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
    SingleResult<TaskVo> selectTaskByPrimaryKey(String id) throws Exception;

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
    MessageResponse deleteTaskByTaskId(String id, UserProp userProp) throws Exception;

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
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;


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
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


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
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


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
    public Map<String, Object> getListByCondition(Map<String, Object> params);

    SingleResult<List<Map<String,String>>> exportData(String id) throws Exception;


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
    public MessageResponse deleteTaskByTaskIds(String[] id, UserProp userProp) throws Exception;


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
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;
    MessageResponse releaseTask(String id,UserProp userProp) throws Exception;
}
