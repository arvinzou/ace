package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopNotice;
import com.huacainfo.ace.fop.vo.FopNoticeQVo;
import com.huacainfo.ace.fop.vo.FopNoticeVo;

/**
 * @author: Arvin
 * @version: 2018-05-03
 * @Description: TODO(通知公告)
 */
public interface FopNoticeService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopNoticeVo>
     * @author: Arvin
     * @version: 2018-05-03
     */
    public abstract PageResult
            <FopNoticeVo> findFopNoticeList(FopNoticeQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertFopNotice
     * @Description: TODO(添加通知公告)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-03
     */
    public abstract MessageResponse insertFopNotice(FopNotice obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopNotice
     * @Description: TODO(更新通知公告)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-03
     */
    public abstract MessageResponse updateFopNotice(FopNotice obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopNoticeByPrimaryKey
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopNotice>
     * @author: Arvin
     * @version: 2018-05-03
     */
    public abstract SingleResult
            <FopNoticeVo> selectFopNoticeByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopNoticeByFopNoticeId
     * @Description: TODO(删除通知公告)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-03
     */
    public abstract MessageResponse deleteFopNoticeByFopNoticeId(String id, UserProp userProp) throws Exception;
}
