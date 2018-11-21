package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopActivity;
import com.huacainfo.ace.fop.vo.FopActivityQVo;
import com.huacainfo.ace.fop.vo.FopActivityVo;

/**
 * @author: Arvin
 * @version: 2018-05-09
 * @Description: TODO(企业/协会活动)
 */
public interface FopActivityService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业/协会活动分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopActivityVo>
     * @author: Arvin
     * @version: 2018-05-09
     */
    PageResult<FopActivityVo> findFopActivityList(FopActivityQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopActivity
     * @Description: TODO(添加企业/协会活动)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    MessageResponse insertFopActivity(FopActivity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopActivity
     * @Description: TODO(更新企业/协会活动)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    MessageResponse updateFopActivity(FopActivity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopActivityByPrimaryKey
     * @Description: TODO(获取企业/协会活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopActivity>
     * @author: Arvin
     * @version: 2018-05-09
     */
    SingleResult<FopActivityVo> selectFopActivityByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopActivityByFopActivityId
     * @Description: TODO(删除企业/协会活动)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    MessageResponse deleteFopActivityByFopActivityId(String id, UserProp userProp) throws Exception;

    /**
     * 功能描述:  发布审核
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/9 15:12
     */
    MessageResponse audit(String id, UserProp userProp) throws Exception;

    FopActivity selectByPrimaryKey(String id);
}
