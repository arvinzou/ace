package com.huacainfo.ace.society.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.Subject;
import com.huacainfo.ace.society.vo.SubjectVo;
import com.huacainfo.ace.society.vo.SubjectQVo;
/**
* @author: lcan
* @version: 2018-09-12
* @Description:  TODO(方案提议)
*/
public interface SubjectService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(方案提议分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<SubjectVo>
    * @throws
    * @author: lcan
    * @version: 2018-09-12
    */
    PageResult
    <SubjectVo> findSubjectList(SubjectQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertSubject
        * @Description: TODO(添加方案提议)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: lcan
        * @version: 2018-09-12
        */
        MessageResponse insertSubject(Subject obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateSubject
        * @Description: TODO(更新方案提议)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: lcan
        * @version: 2018-09-12
        */
        MessageResponse updateSubject(Subject obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectSubjectByPrimaryKey
        * @Description: TODO(获取方案提议)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<Subject>
        * @throws
        * @author: lcan
        * @version: 2018-09-12
        */
        SingleResult
        <SubjectVo> selectSubjectByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteSubjectBySubjectId
            * @Description: TODO(删除方案提议)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: lcan
            * @version: 2018-09-12
            */
            MessageResponse deleteSubjectBySubjectId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核方案提议)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: lcan
            * @version: 2018-09-12
            */
            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;
            }
