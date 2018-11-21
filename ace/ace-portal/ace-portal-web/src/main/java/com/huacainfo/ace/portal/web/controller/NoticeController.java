package com.huacainfo.ace.portal.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Notice;
import com.huacainfo.ace.portal.service.NoticeService;
import com.huacainfo.ace.portal.vo.NoticeQVo;
import com.huacainfo.ace.portal.vo.NoticeVo;

@Controller
@RequestMapping("/notice")
public class NoticeController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NoticeService noticeService;
	/**
	 * 
	    * @Title:findNoticeList 
	    * @Description:  TODO(公告分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<NoticeVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:55:18
	 */
	@RequestMapping(value = "/findNoticeList.do")
	@ResponseBody
	public PageResult<NoticeVo> findNoticeList(NoticeQVo condition,
			PageParam page) throws Exception {
		condition.setDepartmentId(this.getCurUserProp().getCorpId());
		condition.setSyid(this.getCurUserProp().getActiveSyId());
		PageResult<NoticeVo> rst = this.noticeService.findNoticeList(
				condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
	/**
	 * 
	    * @Title:insertNotice 
	    * @Description:  TODO(添加公告) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:55:29
	 */
	@RequestMapping(value = "/insertNotice.do")
	@ResponseBody
	public MessageResponse insertNotice(String jsons) throws Exception {
		Notice obj = JSON.parseObject(jsons, Notice.class);
		return this.noticeService.insertNotice(obj, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:updateNotice 
	    * @Description:  TODO(更新公告) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:55:48
	 */
	@RequestMapping(value = "/updateNotice.do")
	@ResponseBody
	public MessageResponse updateNotice(String jsons) throws Exception {
		Notice obj = JSON.parseObject(jsons, Notice.class);
		return this.noticeService.updateNotice(obj, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:selectNoticeByPrimaryKey 
	    * @Description:  TODO(根据主键获取公告信息) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<NoticeVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:55:57
	 */
	@RequestMapping(value = "/selectNoticeByPrimaryKey.do")
	@ResponseBody
	public SingleResult<NoticeVo> selectNoticeByPrimaryKey(String id)
			throws Exception {
		return this.noticeService.selectNoticeByPrimaryKey(id);
	}
	/**
	 * 
	    * @Title:deleteNoticeByNoticeId 
	    * @Description:  TODO(删除公告信息) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:56:41
	 */
	@RequestMapping(value = "/deleteNoticeByNoticeId.do")
	@ResponseBody
	public MessageResponse deleteNoticeByNoticeId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.noticeService.deleteNoticeByNoticeId(id,
				this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:saveOrUpdateTeamPrepare 
	    * @Description:  TODO(添加或删除公告) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:57:39
	 */
	@RequestMapping(value = "/saveOrUpdateNotice.do")
	@ResponseBody
	public MessageResponse saveOrUpdateTeamPrepare(String jsons)
			throws Exception {
		Notice obj = JSON.parseObject(jsons, Notice.class);
		return this.noticeService
				.saveOrUpdateNotice(obj, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:updateForTopByPrimaryKey 
	    * @Description:  TODO(公告置顶) 
	 		* @param:        @param noticeId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:57:53
	 */
	@RequestMapping(value = "/updateForTopByPrimaryKey.do")
	@ResponseBody
	public MessageResponse updateForTopByPrimaryKey(String noticeId)
			throws Exception {
		return this.noticeService.updateForTopByPrimaryKey(noticeId,
				this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:updateForStatusByPrimaryKey 
	    * @Description:  TODO(更改公告状态) 
	 		* @param:        @param request
	 		* @param:        @param noticeId
	 		* @param:        @param status
	 		* @param:        @param departmentId
	 		* @param:        @param groupId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:58:50
	 */
	@RequestMapping(value = "/updateForStatusByPrimaryKey.do")
	@ResponseBody
	public MessageResponse updateForStatusByPrimaryKey(
			HttpServletRequest request, String noticeId, String status,
			String groupId) throws Exception {
		String contextPath = request.getContextPath();
		return this.noticeService.updateForStatusByPrimaryKey(noticeId, status,
				this.getCurUserProp().getCorpId(), groupId, contextPath, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:findListTop 
	    * @Description:  TODO(获取TOP公告) 
	 		* @param:        @param category
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       ListResult<NoticeVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:59:02
	 */
	@RequestMapping(value = "/findListTop.do")
	@ResponseBody
	public ListResult<NoticeVo> findListTop(String category) throws Exception {
		ListResult<NoticeVo> rst = this.noticeService.findListTop(this
				.getCurUserProp(),category);

		return rst;
	}
}
