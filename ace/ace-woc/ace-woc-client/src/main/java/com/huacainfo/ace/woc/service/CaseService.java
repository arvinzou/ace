package com.huacainfo.ace.woc.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Case;
import com.huacainfo.ace.woc.vo.CaseVo;
import com.huacainfo.ace.woc.vo.CaseQVo;

/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description: TODO(案件)
 */
public interface CaseService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(案件分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<CaseVo>
     * @author: 王恩
     * @version: 2018-03-21
     */
    PageResult<CaseVo> findCaseList(CaseQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCase
     * @Description: TODO(添加案件)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    MessageResponse insertCase(Case obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCase
     * @Description: TODO(更新案件)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    MessageResponse updateCase(Case obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCaseByPrimaryKey
     * @Description: TODO(获取案件)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Case>
     * @author: 王恩
     * @version: 2018-03-21
     */
    SingleResult<CaseVo> selectCaseByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCaseByCaseId
     * @Description: TODO(删除案件)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    MessageResponse deleteCaseByCaseId(String id, UserProp userProp) throws Exception;


}
