package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.policeschool.model.Notice;
import com.huacainfo.ace.policeschool.vo.NoticeQVo;
import com.huacainfo.ace.policeschool.vo.NoticeStatusQVo;
import com.huacainfo.ace.policeschool.vo.NoticeVo;

/**
 * @author: Arvin
 * @version: 2019-01-06
 * @Description: TODO(通知公告)
 */
public interface SclNoticeService {
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
     * <NoticeVo>
     * @author: Arvin
     * @version: 2019-01-06
     */
    PageResult<NoticeVo> findNoticeList(NoticeQVo condition,
                                        int start, int limit, String orderBy) throws Exception;


    ResultResponse findNoticeById(String id) throws Exception;

    ResultResponse findNoticeLists(NoticeStatusQVo condition, int start, int Limit, String orderBy, UserProp userProp) throws Exception;

    PageResult<NoticeVo> findCardNoticeList(NoticeQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

    ResultResponse findPulicNoticeLists(String classesId,String server) throws Exception;

    /**
     * @throws
     * @Title:insertNotice
     * @Description: TODO(添加通知公告)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-06
     */
    MessageResponse insertNotice(Notice obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateNotice
     * @Description: TODO(更新通知公告)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-06
     */
    MessageResponse updateNotice(Notice obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectNoticeByPrimaryKey
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Notice>
     * @author: Arvin
     * @version: 2019-01-06
     */
    SingleResult<NoticeVo> updateAndSelectNoticeVoById(String id, String server, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:deleteNoticeByNoticeId
     * @Description: TODO(删除通知公告)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-06
     */
    MessageResponse deleteNoticeByNoticeId(String id, UserProp userProp) throws Exception;
}
