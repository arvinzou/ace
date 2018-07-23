package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.vo.CounselorQVo;
import com.huacainfo.ace.jxb.vo.CounselorVo;

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

}
