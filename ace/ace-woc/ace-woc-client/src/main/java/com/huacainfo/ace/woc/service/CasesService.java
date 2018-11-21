package com.huacainfo.ace.woc.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Cases;
import com.huacainfo.ace.woc.vo.CasesQVo;
import com.huacainfo.ace.woc.vo.CasesVo;

/**
 * @author: Arvin
 * @version: 2018-03-28
 * @Description: TODO(案件管理)
 */
public interface CasesService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(案件管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<CasesVo>
     * @author: Arvin
     * @version: 2018-03-28
     */
    public abstract PageResult<CasesVo> findCasesList(CasesQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCases
     * @Description: TODO(添加案件管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    public abstract MessageResponse insertCases(Cases obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCases
     * @Description: TODO(更新案件管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    public abstract MessageResponse updateCases(Cases obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCasesByPrimaryKey
     * @Description: TODO(获取案件管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Cases>
     * @author: Arvin
     * @version: 2018-03-28
     */
    public abstract SingleResult<CasesVo> selectCasesByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCasesByCasesId
     * @Description: TODO(删除案件管理)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    public abstract MessageResponse deleteCasesByCasesId(String id, UserProp userProp) throws Exception;


    /**
     * 案件审核
     *
     * @param ids
     * @param curUserProp
     * @return
     */
    MessageResponse auditCase(String ids, UserProp curUserProp);

    /**
     * 案件提交审核
     *
     * @param ids
     * @param curUserProp
     * @return
     */
    MessageResponse submitAuditCase(String ids, UserProp curUserProp);

    /**
     * 案件撤销
     *
     * @param ids         ids串
     * @param curUserProp 登录用户信息
     * @return
     */
    MessageResponse repealCase(String ids, UserProp curUserProp);
}
