package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopProject;
import com.huacainfo.ace.fop.vo.FopProjectQVo;
import com.huacainfo.ace.fop.vo.FopProjectVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(合作项目)
 */
public interface FopProjectService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(合作项目分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopProjectVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopProjectVo> findFopProjectList(FopProjectQVo condition, int start, int limit, String orderBy) throws Exception;

    public abstract ResultResponse findProjectList(FopProjectQVo condition, int page, int limit, String orderBy, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:insertFopProject
     * @Description: TODO(添加合作项目)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopProject(FopProject obj, UserProp userProp) throws Exception;

    public abstract MessageResponse insertProject(FopProject obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopProject
     * @Description: TODO(更新合作项目)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopProject(FopProject obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopProjectByPrimaryKey
     * @Description: TODO(获取合作项目)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopProject>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopProjectVo> selectFopProjectByPrimaryKey(String id) throws Exception;

    public abstract ResultResponse selectProjectByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopProjectByFopProjectId
     * @Description: TODO(删除合作项目)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopProjectByFopProjectId(String id, UserProp userProp) throws Exception;

    /**
     * 功能描述: 审核发布项目
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 15:47
     */
    MessageResponse audit(String id, String auditResult, String auditOpinion, UserProp curUserProp) throws Exception;

    /**
     * 功能描述:  根据主键查询记录-单表
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 17:11
     */
    FopProject selectByPrimaryKey(String fromId);
}
