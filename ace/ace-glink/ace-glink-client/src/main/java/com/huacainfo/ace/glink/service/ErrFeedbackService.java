package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.ErrFeedback;
import com.huacainfo.ace.glink.vo.ErrFeedbackVo;
import com.huacainfo.ace.glink.vo.ErrFeedbackQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: Arvin
 * @version: 2019-04-10
 * @Description: TODO(故障报警)
 */
public interface ErrFeedbackService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult <ErrFeedbackVo>
     * @author: Arvin
     * @version: 2019-04-10
     */
    PageResult<ErrFeedbackVo> findErrFeedbackList(ErrFeedbackQVo condition,
                                                  int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertErrFeedback
     * @Description: TODO(添加故障报警)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    MessageResponse insertErrFeedback(ErrFeedback obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateErrFeedback
     * @Description: TODO(更新故障报警)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    MessageResponse updateErrFeedback(ErrFeedback obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectErrFeedbackByPrimaryKey
     * @Description: TODO(获取故障报警)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ErrFeedback>
     * @author: Arvin
     * @version: 2019-04-10
     */
    SingleResult<ErrFeedbackVo> selectErrFeedbackByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteErrFeedbackByErrFeedbackId
     * @Description: TODO(删除故障报警)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    MessageResponse deleteErrFeedbackByErrFeedbackId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核故障报警)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
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
     * @author: Arvin
     * @version: 2019-04-10
     */
    MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult
     * <Map
     * <String
     * ,Object>>
     * @author: Arvin
     * @version: 2019-04-10
     */
    ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: Arvin
     * @version: 2019-04-10
     */
    Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除故障报警 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    MessageResponse deleteErrFeedbackByErrFeedbackIds(String[] id, UserProp userProp) throws
            Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 根据选择年，月获取当月下面的每天故障数据统计
     * @param year
     * @param month
     * @return
     */
    public List<Map<String, Object>> getDayErrCount(String year, String month) throws Exception;
}
