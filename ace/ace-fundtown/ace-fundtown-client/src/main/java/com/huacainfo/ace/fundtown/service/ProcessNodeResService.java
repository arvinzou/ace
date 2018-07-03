package com.huacainfo.ace.fundtown.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fundtown.model.ProcessNodeRes;
import com.huacainfo.ace.fundtown.vo.ProcessNodeResQVo;
import com.huacainfo.ace.fundtown.vo.ProcessNodeResVo;

/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻流程节点-资源/附件)
 */
public interface ProcessNodeResService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻流程节点-资源/附件分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ProcessNodeResVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    PageResult<ProcessNodeResVo> findProcessNodeResList(ProcessNodeResQVo condition,
                                                        int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertProcessNodeRes
     * @Description: TODO(添加入驻流程节点-资源/附件)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse insertProcessNodeRes(ProcessNodeRes obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateProcessNodeRes
     * @Description: TODO(更新入驻流程节点-资源/附件)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse updateProcessNodeRes(ProcessNodeRes obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectProcessNodeResByPrimaryKey
     * @Description: TODO(获取入驻流程节点-资源/附件)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ProcessNodeRes>
     * @author: Arvin
     * @version: 2018-07-03
     */
    SingleResult<ProcessNodeResVo> selectProcessNodeResByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteProcessNodeResByProcessNodeResId
     * @Description: TODO(删除入驻流程节点-资源/附件)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse deleteProcessNodeResByProcessNodeResId(String id, UserProp userProp) throws Exception;

}
