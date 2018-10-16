package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.Behavior;
import com.huacainfo.ace.society.vo.BehaviorQVo;
import com.huacainfo.ace.society.vo.BehaviorVo;

/**
 * @author: lcan
 * @version: 2018-09-11
 * @Description: TODO(市民行为详情)
 */
public interface BehaviorService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(市民行为详情分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BehaviorVo>
     * @author: lcan
     * @version: 2018-09-11
     */
    PageResult<BehaviorVo> findBehaviorList(BehaviorQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertBehavior
     * @Description: TODO(添加市民行为详情)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    MessageResponse insertBehavior(Behavior obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateBehavior
     * @Description: TODO(更新市民行为详情)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    MessageResponse updateBehavior(Behavior obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectBehaviorByPrimaryKey
     * @Description: TODO(获取市民行为详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Behavior>
     * @author: lcan
     * @version: 2018-09-11
     */
    SingleResult<BehaviorVo> selectBehaviorByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteBehaviorByBehaviorId
     * @Description: TODO(删除市民行为详情)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    MessageResponse deleteBehaviorByBehaviorId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核市民行为详情)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;

    /**
     * 提交文明随手拍
     *
     * @param params 提交参数
     */
    ResultResponse submit(BehaviorVo params);

    /**
     * 送审
     *
     * @param id 主键ID society.behavior.id
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse sendAudit(String id, String unionId);

    /**
     * 获取详情
     *
     * @param id 主键ID society.behavior.id
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse getDetail(String id, String unionId) throws Exception;
}
