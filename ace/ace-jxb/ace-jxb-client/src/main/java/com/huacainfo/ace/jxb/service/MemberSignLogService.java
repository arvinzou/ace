package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.MemberSignLog;
import com.huacainfo.ace.jxb.vo.MemberSignLogQVo;
import com.huacainfo.ace.jxb.vo.MemberSignLogVo;

import java.util.Map;

/**
 * @author: Arvin
 * @version: 2018-08-02
 * @Description: TODO(会员签到日志)
 */
public interface MemberSignLogService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员签到日志分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <MemberSignLogVo>
     * @author: Arvin
     * @version: 2018-08-02
     */
    PageResult<MemberSignLogVo> findMemberSignLogList(MemberSignLogQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertMemberSignLog
     * @Description: TODO(添加会员签到日志)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-02
     */
    MessageResponse insertMemberSignLog(MemberSignLog obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateMemberSignLog
     * @Description: TODO(更新会员签到日志)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-02
     */
    MessageResponse updateMemberSignLog(MemberSignLog obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectMemberSignLogByPrimaryKey
     * @Description: TODO(获取会员签到日志)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MemberSignLog>
     * @author: Arvin
     * @version: 2018-08-02
     */
    SingleResult<MemberSignLogVo> selectMemberSignLogByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteMemberSignLogByMemberSignLogId
     * @Description: TODO(删除会员签到日志)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-02
     */
    MessageResponse deleteMemberSignLogByMemberSignLogId(String id, UserProp userProp) throws Exception;


    /**
     * 每日签到
     *
     * @param counselorId 咨询师id
     * @return ResultResponse
     */
    ResultResponse signIn(String counselorId) throws Exception;

    /**
     * 获取用户签到信息
     *
     * @param userId   portal.users.user_id
     * @param dateTime 查询日期 demo:2018-08-02 20:43:43
     * @return map
     */
    Map<String, Object> getSignInfo(String userId, String dateTime);
}
