package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.EvaluatTpl;
import com.huacainfo.ace.portal.vo.EvaluatTplVo;
import com.huacainfo.ace.portal.vo.EvaluatTplQVo;

import java.util.List;
import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(评测)
 */
public interface EvaluatTplService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatTplVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract PageResult<EvaluatTplVo> findEvaluatTplList(EvaluatTplQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertEvaluatTpl
     * @Description: TODO(添加评测)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse insertEvaluatTpl(String jsons, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateEvaluatTpl
     * @Description: TODO(更新评测)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse updateEvaluatTpl(String jsons, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectEvaluatTplByPrimaryKey
     * @Description: TODO(获取评测)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatTpl>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract SingleResult<EvaluatTplVo> selectEvaluatTplByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteEvaluatTplByEvaluatTplId
     * @Description: TODO(删除评测)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract MessageResponse deleteEvaluatTplByEvaluatTplId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(查询评测)
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
     * @Description: TODO(获取评测)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    public abstract Map<String, Object> getById(String id) throws Exception;

    public abstract List<Tree> selectEvaluatTplTreeList(String pid, String syid) throws Exception;


}
