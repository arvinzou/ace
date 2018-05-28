package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.IntegrityPublicity;
import com.huacainfo.ace.fop.vo.IntegrityPublicityVo;
import com.huacainfo.ace.fop.vo.IntegrityPublicityQVo;

/**
 * @author: huacai003
 * @version: 2018-05-28
 * @Description: TODO(诚信公示)
 */
public interface IntegrityPublicityService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(诚信公示分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <IntegrityPublicityVo>
     * @author: huacai003
     * @version: 2018-05-28
     */
    PageResult
            <IntegrityPublicityVo> findIntegrityPublicityList(IntegrityPublicityQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertIntegrityPublicity
     * @Description: TODO(添加诚信公示)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-28
     */
    MessageResponse insertIntegrityPublicity(IntegrityPublicity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateIntegrityPublicity
     * @Description: TODO(更新诚信公示)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-28
     */
    MessageResponse updateIntegrityPublicity(IntegrityPublicity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectIntegrityPublicityByPrimaryKey
     * @Description: TODO(获取诚信公示)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<IntegrityPublicity>
     * @author: huacai003
     * @version: 2018-05-28
     */
    SingleResult
            <IntegrityPublicityVo> selectIntegrityPublicityByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteIntegrityPublicityByIntegrityPublicityId
     * @Description: TODO(删除诚信公示)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-28
     */
    MessageResponse deleteIntegrityPublicityByIntegrityPublicityId(String id, UserProp userProp) throws Exception;

}
