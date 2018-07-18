package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.EvaluatData;
import com.huacainfo.ace.portal.vo.EvaluatDataVo;
import com.huacainfo.ace.portal.vo.EvaluatDataQVo;

import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(测评结果)
 */
public interface EvaluatDataService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测评结果分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatDataVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract PageResult<EvaluatDataVo> findEvaluatDataList(EvaluatDataQVo condition, int start, int limit, String orderBy) throws Exception;

    public abstract ResultResponse findEvaluatDataLists(EvaluatDataQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertEvaluatData
     * @Description: TODO(添加测评结果)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse insertEvaluatData(EvaluatData obj, UserProp userProp) throws Exception;

    public abstract MessageResponse insertData(EvaluatData obj) throws Exception;

    /**
     * @throws
     * @Title:updateEvaluatData
     * @Description: TODO(更新测评结果)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse updateEvaluatData(EvaluatData obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectEvaluatDataByPrimaryKey
     * @Description: TODO(获取测评结果)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatData>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract SingleResult<EvaluatDataVo> selectEvaluatDataByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteEvaluatDataByEvaluatDataId
     * @Description: TODO(删除测评结果)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse deleteEvaluatDataByEvaluatDataId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(查询测评结果)
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
     * @Description: TODO(获取测评结果)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract Map<String, Object> getById(String id) throws Exception;


}
