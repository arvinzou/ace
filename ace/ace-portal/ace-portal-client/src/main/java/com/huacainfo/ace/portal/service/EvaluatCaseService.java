package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.EvaluatCase;
import com.huacainfo.ace.portal.vo.EvaluatCaseVo;
import com.huacainfo.ace.portal.vo.EvaluatCaseQVo;

import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(题目)
 */
public interface EvaluatCaseService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(题目分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatCaseVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract PageResult<EvaluatCaseVo> findEvaluatCaseList(EvaluatCaseQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertEvaluatCase
     * @Description: TODO(添加题目)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse insertEvaluatCase(EvaluatCase obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateEvaluatCase
     * @Description: TODO(更新题目)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse updateEvaluatCase(EvaluatCase obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectEvaluatCaseByPrimaryKey
     * @Description: TODO(获取题目)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatCase>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract SingleResult<EvaluatCaseVo> selectEvaluatCaseByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteEvaluatCaseByEvaluatCaseId
     * @Description: TODO(删除题目)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse deleteEvaluatCaseByEvaluatCaseId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(查询题目)
     * @param: @param params
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract Map<String, Object> getList(Map<String, Object> params) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取题目)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract Map<String, Object> getById(String id) throws Exception;


}
