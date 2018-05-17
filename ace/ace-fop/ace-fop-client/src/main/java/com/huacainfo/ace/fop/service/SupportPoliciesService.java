package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.SupportPolicies;
import com.huacainfo.ace.fop.vo.SupportPoliciesVo;
import com.huacainfo.ace.fop.vo.SupportPoliciesQVo;

/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(扶持政策)
 */
public interface SupportPoliciesService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(扶持政策分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SupportPoliciesVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    PageResult
            <SupportPoliciesVo> findSupportPoliciesList(SupportPoliciesQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertSupportPolicies
     * @Description: TODO(添加扶持政策)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse insertSupportPolicies(SupportPolicies obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSupportPolicies
     * @Description: TODO(更新扶持政策)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse updateSupportPolicies(SupportPolicies obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSupportPoliciesByPrimaryKey
     * @Description: TODO(获取扶持政策)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SupportPolicies>
     * @author: huacai003
     * @version: 2018-05-17
     */
    SingleResult
            <SupportPoliciesVo> selectSupportPoliciesByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSupportPoliciesBySupportPoliciesId
     * @Description: TODO(删除扶持政策)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse deleteSupportPoliciesBySupportPoliciesId(String id, UserProp userProp) throws Exception;

}
