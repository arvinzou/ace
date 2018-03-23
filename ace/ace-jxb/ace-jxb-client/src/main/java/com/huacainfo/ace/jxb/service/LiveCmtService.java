package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Live;
import com.huacainfo.ace.jxb.model.LiveCmt;
import com.huacainfo.ace.jxb.vo.LiveCmtVo;
import com.huacainfo.ace.jxb.vo.LiveCmtQVo;

import java.util.List;

/**
 * @author: 陈晓克
 * @version: 2018-01-13
 * @Description: TODO(评论)
 */
public interface LiveCmtService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评论分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LiveCmtVo>
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    public abstract PageResult<LiveCmtVo> findLiveCmtList(LiveCmtQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertLiveCmt
     * @Description: TODO(添加评论)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    public abstract MessageResponse insertLiveCmt(LiveCmt obj, String corpId) throws Exception;

    /**
     * @throws
     * @Title:updateLiveCmt
     * @Description: TODO(更新评论)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    public abstract MessageResponse updateLiveCmt(String id, String status) throws Exception;

    /**
     * @throws
     * @Title:selectLiveCmtByPrimaryKey
     * @Description: TODO(获取评论)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveCmt>
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    public abstract SingleResult<LiveCmtVo> selectLiveCmtByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteLiveCmtByLiveCmtId
     * @Description: TODO(删除评论)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    public abstract MessageResponse deleteLiveCmtByLiveCmtId(String id, UserProp userProp) throws Exception;

    List<String> findSensitiveWordsList(String deptId) throws Exception;

}
