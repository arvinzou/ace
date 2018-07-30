package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.vo.CounselorQVo;
import com.huacainfo.ace.jxb.vo.CounselorVo;

import java.util.Map;

/**
 * @author: Arvin
 * @version: 2018-07-20
 * @Description: TODO(咨询师)
 */
public interface CounselorService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CounselorVo>
     * @author: Arvin
     * @version: 2018-07-20
     */
    PageResult<CounselorVo> findCounselorList(CounselorQVo condition,
                                              int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCounselor
     * @Description: TODO(添加咨询师)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    MessageResponse insertCounselor(Counselor obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCounselor
     * @Description: TODO(更新咨询师)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    MessageResponse updateCounselor(Counselor obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCounselorByPrimaryKey
     * @Description: TODO(获取咨询师)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Counselor>
     * @author: Arvin
     * @version: 2018-07-20
     */
    SingleResult<CounselorVo> selectCounselorByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCounselorByCounselorId
     * @Description: TODO(删除咨询师)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    MessageResponse deleteCounselorByCounselorId(String id, UserProp userProp) throws Exception;

    /**
     * 注册老师资料
     *
     * @param mobile   手机号码
     * @param studioId 推荐人userid （portal.users.id）
     * @param userinfo 微信基本信息
     * @return
     */
    ResultResponse register(String mobile, String studioId, Userinfo userinfo);

    /**
     * 获取咨询师统计数据
     *
     * @param counselorId 咨询师ID
     * @return map
     */
    Map<String, Object> statistic(String counselorId);

    /**
     * 咨询师"我"的账户信息
     *
     * @param counselorId 咨询师id
     * @return ResultResponse data=>Map
     */
    ResultResponse accountInfo(String counselorId);
}
