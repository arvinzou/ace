package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.SubjectIdea;
import com.huacainfo.ace.society.vo.SubjectIdeaQVo;
import com.huacainfo.ace.society.vo.SubjectIdeaVo;

/**
 * @author: Arvin
 * @version: 2018-09-13
 * @Description: TODO(议题点子)
 */
public interface SubjectIdeaService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(议题点子分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SubjectIdeaVo>
     * @author: Arvin
     * @version: 2018-09-13
     */
    PageResult<SubjectIdeaVo> findSubjectIdeaList(SubjectIdeaQVo condition,
                                                  int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSubjectIdea
     * @Description: TODO(添加议题点子)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse insertSubjectIdea(SubjectIdea obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSubjectIdea
     * @Description: TODO(更新议题点子)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse updateSubjectIdea(SubjectIdea obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSubjectIdeaByPrimaryKey
     * @Description: TODO(获取议题点子)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SubjectIdea>
     * @author: Arvin
     * @version: 2018-09-13
     */
    SingleResult<SubjectIdeaVo> selectSubjectIdeaByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSubjectIdeaBySubjectIdeaId
     * @Description: TODO(删除议题点子)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse deleteSubjectIdeaBySubjectIdeaId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核议题点子)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    MessageResponse audit(String id, String userId, String orgType, String rst, String remark, UserProp userProp) throws Exception;

    /**
     * 提交“我的点子”
     *
     * @param params 表单参数
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse submit(SubjectIdeaVo params);

    /**
     * “我的点子” 送审
     *
     * @param ideaId  点子ID
     * @param unionId
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse sendAudit(String ideaId, String unionId);

    /**
     * 获取点子详情
     *
     * @param ideaId 点子ID
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse getIdeaDetail(String ideaId) throws Exception;
}
