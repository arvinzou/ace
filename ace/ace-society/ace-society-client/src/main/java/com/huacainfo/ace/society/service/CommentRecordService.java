package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.CommentRecord;
import com.huacainfo.ace.society.vo.CommentRecordQVo;
import com.huacainfo.ace.society.vo.CommentRecordVo;

/**
 * @author: ArvinZou
 * @version: 2018-10-24
 * @Description: TODO(评论管理)
 */
public interface CommentRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评论管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CommentRecordVo>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    PageResult<CommentRecordVo> findCommentRecordList(CommentRecordQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCommentRecord
     * @Description: TODO(添加评论管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    MessageResponse insertCommentRecord(CommentRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCommentRecord
     * @Description: TODO(更新评论管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    MessageResponse updateCommentRecord(CommentRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCommentRecordByPrimaryKey
     * @Description: TODO(获取评论管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CommentRecord>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    SingleResult<CommentRecordVo> selectCommentRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCommentRecordByCommentRecordId
     * @Description: TODO(删除评论管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    MessageResponse deleteCommentRecordByCommentRecordId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核评论管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;

    /**
     * 提交评论
     *
     * @param params 表单参数 ：   bisType  bisId   content;
     * @return
     * @throws Exception
     */
    ResultResponse submit(CommentRecordVo params);
}
