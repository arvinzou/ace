package com.huacainfo.ace.society.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.SubjectIdeaAnnex;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexVo;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexQVo;
/**
* @author: Arvin
* @version: 2018-09-13
* @Description:  TODO(议题点子附件)
*/
public interface SubjectIdeaAnnexService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(议题点子附件分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<SubjectIdeaAnnexVo>
    * @throws
    * @author: Arvin
    * @version: 2018-09-13
    */
    PageResult
    <SubjectIdeaAnnexVo> findSubjectIdeaAnnexList(SubjectIdeaAnnexQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertSubjectIdeaAnnex
        * @Description: TODO(添加议题点子附件)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: Arvin
        * @version: 2018-09-13
        */
        MessageResponse insertSubjectIdeaAnnex(SubjectIdeaAnnex obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateSubjectIdeaAnnex
        * @Description: TODO(更新议题点子附件)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: Arvin
        * @version: 2018-09-13
        */
        MessageResponse updateSubjectIdeaAnnex(SubjectIdeaAnnex obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectSubjectIdeaAnnexByPrimaryKey
        * @Description: TODO(获取议题点子附件)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<SubjectIdeaAnnex>
        * @throws
        * @author: Arvin
        * @version: 2018-09-13
        */
        SingleResult
        <SubjectIdeaAnnexVo> selectSubjectIdeaAnnexByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteSubjectIdeaAnnexBySubjectIdeaAnnexId
            * @Description: TODO(删除议题点子附件)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: Arvin
            * @version: 2018-09-13
            */
            MessageResponse deleteSubjectIdeaAnnexBySubjectIdeaAnnexId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核议题点子附件)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: Arvin
            * @version: 2018-09-13
            */
            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;
            }
