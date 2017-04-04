package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Notice;
import com.huacainfo.ace.portal.vo.NoticeQVo;
import com.huacainfo.ace.portal.vo.NoticeVo;

public interface NoticeService {

	/**
	 * 
	 * @Title:findNoticeList
	 * @Description: TODO(公告分页查询)
	 * @param: @param condition
	 * @param: @param start
	 * @param: @param limit
	 * @param: @param orderBy
	 * @param: @return
	 * @param: @throws Exception
	 * @return: PageResult<NoticeVo>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:33:37
	 */
	public abstract PageResult<NoticeVo> findNoticeList(NoticeQVo condition,
			int start, int limit, String orderBy) throws Exception;

	/**
	 * 
	 * @Title:insertNotice
	 * @Description: TODO(添加公告)
	 * @param: @param obj
	 * @param: @param userProp
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:33:51
	 */
	public abstract MessageResponse insertNotice(Notice obj, UserProp userProp)
			throws Exception;

	/**
	 * 
	 * @Title:updateNotice
	 * @Description: TODO(更新公告)
	 * @param: @param obj
	 * @param: @param userProp
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:34:03
	 */
	public abstract MessageResponse updateNotice(Notice obj, UserProp userProp)
			throws Exception;

	/**
	 * 
	 * @Title:selectNoticeByPrimaryKey
	 * @Description: TODO(更具主键获取公告信息)
	 * @param: @param id
	 * @param: @return
	 * @param: @throws Exception
	 * @return: SingleResult<NoticeVo>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:34:17
	 */
	public abstract SingleResult<NoticeVo> selectNoticeByPrimaryKey(String id)
			throws Exception;

	/**
	 * 
	 * @Title:deleteNoticeByNoticeId
	 * @Description: TODO(删除公告信息)
	 * @param: @param id
	 * @param: @param userProp
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:34:41
	 */
	public abstract MessageResponse deleteNoticeByNoticeId(String id,
			UserProp userProp) throws Exception;

	/**
	 * 
	 * @Title:saveOrUpdateNotice
	 * @Description: TODO(根据主键是存在，更新或保存公告信息)
	 * @param: @param obj
	 * @param: @param userProp
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:35:26
	 */
	public abstract MessageResponse saveOrUpdateNotice(Notice obj,
			UserProp userProp) throws Exception;

	/**
	 * 
	 * @Title:updateForTopByPrimaryKey
	 * @Description: TODO(公告置顶)
	 * @param: @param noticeId
	 * @param: @param userProp
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:35:51
	 */
	public abstract MessageResponse updateForTopByPrimaryKey(String noticeId,
			UserProp userProp) throws Exception;

	/**
	 * 
	 * @Title:updateForStatusByPrimaryKey
	 * @Description: TODO(更新公告状态)
	 * @param: @param noticeId
	 * @param: @param status
	 * @param: @param departmentId
	 * @param: @param groupId
	 * @param: @param contextPath
	 * @param: @param userProp
	 * @param: @return
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:36:09
	 */
	public abstract MessageResponse updateForStatusByPrimaryKey(
			String noticeId, String status, String departmentId,
			String groupId, String contextPath, UserProp userProp)
			throws Exception;

	/**
	 * 
	 * @Title:findListTop
	 * @Description: TODO(获取TOP公告列表)
	 * @param: @param userProp
	 * @param: @param category
	 * @param: @return
	 * @param: @throws Exception
	 * @return: ListResult<NoticeVo>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2016年11月17日 上午11:36:50
	 */
	public abstract ListResult<NoticeVo> findListTop(UserProp userProp,
			String category) throws Exception;

}
