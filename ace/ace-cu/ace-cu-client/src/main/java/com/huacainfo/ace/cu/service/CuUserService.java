package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CuUser;
import com.huacainfo.ace.cu.vo.CuUserQVo;
import com.huacainfo.ace.cu.vo.CuUserVo;

/**
 * @author: Arvin
 * @version: 2018-06-13
 * @Description: TODO(会员资料)
 */
public interface CuUserService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员资料分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuUserVo>
     * @author: Arvin
     * @version: 2018-06-13
     */
    PageResult<CuUserVo> findCuUserList(CuUserQVo condition,
                                        int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCuUser
     * @Description: TODO(添加会员资料)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    MessageResponse insertCuUser(CuUser obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCuUser
     * @Description: TODO(更新会员资料)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    MessageResponse updateCuUser(CuUser obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCuUserByPrimaryKey
     * @Description: TODO(获取会员资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuUser>
     * @author: Arvin
     * @version: 2018-06-13
     */
    SingleResult<CuUserVo> selectCuUserByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCuUserByCuUserId
     * @Description: TODO(删除会员资料)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    MessageResponse deleteCuUserByCuUserId(String id, UserProp userProp) throws Exception;

    /**
     * 查询会员信息
     *
     * @param openId 微信openid
     * @return
     * @throws Exception
     */
    CuUserVo findByOpenId(String openId);
}
