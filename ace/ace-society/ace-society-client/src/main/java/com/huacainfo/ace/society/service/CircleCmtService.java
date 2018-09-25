package com.huacainfo.ace.society.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.CircleCmt;
import com.huacainfo.ace.society.vo.CircleCmtVo;
import com.huacainfo.ace.society.vo.CircleCmtQVo;
/**
* @author: 陈晓克
* @version: 2018-09-20
* @Description:  TODO(评论)
*/
public interface CircleCmtService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(评论分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<CircleCmtVo>
    * @throws
    * @author: 陈晓克
    * @version: 2018-09-20
    */
    PageResult
    <CircleCmtVo> findCircleCmtList(CircleCmtQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertCircleCmt
        * @Description: TODO(添加评论)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 陈晓克
        * @version: 2018-09-20
        */
        MessageResponse insertCircleCmt(CircleCmt obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateCircleCmt
        * @Description: TODO(更新评论)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 陈晓克
        * @version: 2018-09-20
        */
        MessageResponse updateCircleCmt(CircleCmt obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectCircleCmtByPrimaryKey
        * @Description: TODO(获取评论)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<CircleCmt>
        * @throws
        * @author: 陈晓克
        * @version: 2018-09-20
        */
        SingleResult
        <CircleCmtVo> selectCircleCmtByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteCircleCmtByCircleCmtId
            * @Description: TODO(删除评论)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 陈晓克
            * @version: 2018-09-20
            */
            MessageResponse deleteCircleCmtByCircleCmtId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核评论)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 陈晓克
            * @version: 2018-09-20
            */
            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;
            }
