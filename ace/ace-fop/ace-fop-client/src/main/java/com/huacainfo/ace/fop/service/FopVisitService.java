package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopVisit;
import com.huacainfo.ace.fop.vo.FopVisitQVo;
import com.huacainfo.ace.fop.vo.FopVisitVo;

/**
 * @author: 陈晓克
 * @version: 2018-11-02
 * @Description: TODO(企业走访)
 */
public interface FopVisitService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业走访分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopVisitVo>
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    PageResult
            <FopVisitVo> findFopVisitList(FopVisitQVo condition,
                                          int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopVisit
     * @Description: TODO(添加企业走访)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    MessageResponse insertFopVisit(FopVisit obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopVisit
     * @Description: TODO(更新企业走访)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    MessageResponse updateFopVisit(FopVisit obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopVisitByPrimaryKey
     * @Description: TODO(获取企业走访)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopVisit>
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    SingleResult
            <FopVisitVo> selectFopVisitByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopVisitByFopVisitId
     * @Description: TODO(删除企业走访)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    MessageResponse deleteFopVisitByFopVisitId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核企业走访)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;
}
