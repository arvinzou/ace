package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.ErrWarnSomeone;
import com.huacainfo.ace.glink.vo.ErrWarnSomeoneQVo;
import com.huacainfo.ace.glink.vo.ErrWarnSomeoneVo;

/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 送报人)
 */
public interface ErrWarnSomeoneService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 送报人分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<ErrWarnSomeoneVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    PageResult<ErrWarnSomeoneVo> findErrWarnSomeoneList(ErrWarnSomeoneQVo condition,
                                                        int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertErrWarnSomeone
     * @Description: TODO(添加故障报警 - 送报人)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse insertErrWarnSomeone(ErrWarnSomeone obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateErrWarnSomeone
     * @Description: TODO(更新故障报警 - 送报人)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse updateErrWarnSomeone(ErrWarnSomeone obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectErrWarnSomeoneByPrimaryKey
     * @Description: TODO(获取故障报警 - 送报人)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ErrWarnSomeone>
     * @author: Arvin
     * @version: 2019-04-11
     */
    SingleResult<ErrWarnSomeoneVo> selectErrWarnSomeoneByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteErrWarnSomeoneByErrWarnSomeoneId
     * @Description: TODO(删除故障报警 - 送报人)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse deleteErrWarnSomeoneByErrWarnSomeoneId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;
}
