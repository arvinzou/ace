package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.EvaluatType;
import com.huacainfo.ace.portal.vo.EvaluatTypeVo;
import com.huacainfo.ace.portal.vo.EvaluatTypeQVo;

import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(评测类型)
 */
public interface EvaluatTypeService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测类型分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatTypeVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract PageResult<EvaluatTypeVo> findEvaluatTypeList(EvaluatTypeQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertEvaluatType
     * @Description: TODO(添加评测类型)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse insertEvaluatType(EvaluatType obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateEvaluatType
     * @Description: TODO(更新评测类型)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse updateEvaluatType(EvaluatType obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectEvaluatTypeByPrimaryKey
     * @Description: TODO(获取评测类型)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatType>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract SingleResult<EvaluatTypeVo> selectEvaluatTypeByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteEvaluatTypeByEvaluatTypeId
     * @Description: TODO(删除评测类型)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse deleteEvaluatTypeByEvaluatTypeId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(查询评测类型)
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
     * @Description: TODO(获取评测类型)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract Map<String, Object> getById(String id) throws Exception;


}
