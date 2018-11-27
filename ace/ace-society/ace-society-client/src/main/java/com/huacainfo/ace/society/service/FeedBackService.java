package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.FeedBack;
import com.huacainfo.ace.society.vo.FeedBackQVo;
import com.huacainfo.ace.society.vo.FeedBackVo;

/**
 * @author: Arvin
 * @version: 2018-11-27
 * @Description: TODO(问题反馈)
 */
public interface FeedBackService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(问题反馈分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FeedBackVo>
     * @author: Arvin
     * @version: 2018-11-27
     */
    PageResult<FeedBackVo> findFeedBackList(FeedBackQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFeedBack
     * @Description: TODO(添加问题反馈)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-27
     */
    MessageResponse insertFeedBack(FeedBack obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFeedBack
     * @Description: TODO(更新问题反馈)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-27
     */
    MessageResponse updateFeedBack(FeedBack obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFeedBackByPrimaryKey
     * @Description: TODO(获取问题反馈)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FeedBack>
     * @author: Arvin
     * @version: 2018-11-27
     */
    SingleResult<FeedBackVo> selectFeedBackByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFeedBackByFeedBackId
     * @Description: TODO(删除问题反馈)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-27
     */
    MessageResponse deleteFeedBackByFeedBackId(String id, UserProp userProp) throws Exception;

    /**
     * 提交问题反馈
     *
     * @param params 输入参数
     * @return ResultResponse
     */
    ResultResponse submit(FeedBackVo params);
}
