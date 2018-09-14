package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.Activity;
import com.huacainfo.ace.society.vo.ActivityVo;
import com.huacainfo.ace.society.vo.ActivityQVo;

/**
 * @author: huacai003
 * @version: 2018-09-11
 * @Description: TODO(线下活动)
 */
public interface ActivityService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(线下活动分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ActivityVo>
     * @author: huacai003
     * @version: 2018-09-11
     */
    PageResult
            <ActivityVo> findActivityList(ActivityQVo condition,
                                          int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertActivity
     * @Description: TODO(添加线下活动)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    MessageResponse insertActivity(Activity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateActivity
     * @Description: TODO(更新线下活动)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    MessageResponse updateActivity(Activity obj, UserProp userProp) throws Exception;
    MessageResponse softDelete(Activity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectActivityByPrimaryKey
     * @Description: TODO(获取线下活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Activity>
     * @author: huacai003
     * @version: 2018-09-11
     */
    SingleResult<ActivityVo> selectActivityByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteActivityByActivityId
     * @Description: TODO(删除线下活动)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    MessageResponse deleteActivityByActivityId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核线下活动)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;
}
