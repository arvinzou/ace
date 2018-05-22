package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopFinanceProject;
import com.huacainfo.ace.fop.vo.FopFinanceProjectQVo;
import com.huacainfo.ace.fop.vo.FopFinanceProjectVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(流程记录)
 */
public interface FopFinanceProjectService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(流程记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopFinanceProjectVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopFinanceProjectVo> findFopFinanceProjectList(FopFinanceProjectQVo condition, int start, int limit, String orderBy) throws Exception;

    public abstract ResultResponse findFinanceProjectList(FopFinanceProjectQVo condition, int page, int limit, String orderBy) throws Exception;


    /**
     * @throws
     * @Title:insertFopFinanceProject
     * @Description: TODO(添加流程记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopFinanceProject(FopFinanceProject obj, UserProp userProp) throws Exception;

    public abstract MessageResponse insertFinanceProject(FopFinanceProject obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopFinanceProject
     * @Description: TODO(更新流程记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopFinanceProject(FopFinanceProject obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopFinanceProjectByPrimaryKey
     * @Description: TODO(获取流程记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopFinanceProject>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopFinanceProjectVo> selectFopFinanceProjectByPrimaryKey(String id) throws Exception;

    public abstract ResultResponse selectFinanceProjectByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopFinanceProjectByFopFinanceProjectId
     * @Description: TODO(删除流程记录)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopFinanceProjectByFopFinanceProjectId(String id, UserProp userProp) throws Exception;


    /**
     * 功能描述:  审核
     *
     * @param auditOpinion 审核备注
     * @param auditResult  审核结果 -- 0-通过，1-不通过
     * @param: id Fop_Finance_Project.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    MessageResponse audit(String id, String auditResult, String auditOpinion, UserProp curUserProp) throws Exception;
}
