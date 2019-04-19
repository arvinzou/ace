package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.SeAreaTask;
import com.huacainfo.ace.glink.vo.SeAreaTaskQVo;
import com.huacainfo.ace.glink.vo.SeAreaTaskVo;

/**
 * @author: Arvin
 * @version: 2019-04-18
 * @Description: TODO(区域任务数据)
 */
public interface SeAreaTaskService {
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
    PageResult<SeAreaTaskVo> findSeAreaTaskList(SeAreaTaskQVo condition,
                                                int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSeAreaTask
     * @Description: TODO(添加区域任务数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    MessageResponse insertSeAreaTask(SeAreaTask obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSeAreaTask
     * @Description: TODO(更新区域任务数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    MessageResponse updateSeAreaTask(SeAreaTask obj, UserProp userProp) throws Exception;

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
    SingleResult<SeAreaTaskVo> selectSeAreaTaskByPrimaryKey(String id) throws Exception;

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
    MessageResponse deleteSeAreaTaskBySeAreaTaskId(String id, UserProp userProp) throws Exception;

    /**
     * 同步区域任务数据
     *
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse syncData(UserProp curUserProp);
}
