package com.huacainfo.ace.portal.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.AttachMapper;
import com.huacainfo.ace.portal.dao.NoticeMapper;
import com.huacainfo.ace.portal.dao.TaskMapper;
import com.huacainfo.ace.portal.model.Notice;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.FilesService;
import com.huacainfo.ace.portal.service.NoticeService;
import com.huacainfo.ace.portal.vo.AttachQVo;
import com.huacainfo.ace.portal.vo.AttachVo;
import com.huacainfo.ace.portal.vo.NoticeQVo;
import com.huacainfo.ace.portal.vo.NoticeVo;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService, Serializable {

	private static final long serialVersionUID = 8914025046850561346L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TaskMapper taskMapper;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	
	@Autowired
	private FilesService filesService;
	
	

	public PageResult<NoticeVo> findNoticeList(NoticeQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<NoticeVo> rst = new PageResult<NoticeVo>();
		
		List<NoticeVo> list = this.noticeMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.noticeMapper.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertNotice(Notice o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getNoticeId())) {
			return new MessageResponse(1, "公告编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getTitle())) {
			return new MessageResponse(1, "标题不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "类别不能为空！");
		}
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "内容不能为空！");
		}
		if (o.getDeadline() == null) {
			return new MessageResponse(1, "有效日期不能为空！");
		}
		o.setPublisher(userProp.getUserId());
		o.setCreateTime(new Date());
		o.setDepartmentId(userProp.getCorpId());
		o.setSyid(userProp.getActiveSyId());
		int temp = this.noticeMapper.isExitByTitle(o.getTitle());
		if (temp > 0) {
			return new MessageResponse(1, "已存在此标题的数据！");
		}
		this.noticeMapper.insert(o);
		AttachQVo condition =new AttachQVo();
		condition.setNoticeId(o.getNoticeId());
		List<AttachVo> list=attachMapper.findList(condition);
		for(AttachVo av:list){
			filesService.updateFiles(av.getFileUrl(), userProp);
		}
		this.dataBaseLogService.log("添加公告", "公告", "", o.getTitle(),
				o.getTitle(), userProp);
		return new MessageResponse(0, "添加公告完成！");
	}

	public MessageResponse updateNotice(Notice o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getNoticeId())) {
			return new MessageResponse(1, "公告编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getTitle())) {
			return new MessageResponse(1, "标题不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "类别不能为空！");
		}
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "内容不能为空！");
		}
		if (o.getDeadline() == null) {
			return new MessageResponse(1, "有效日期不能为空！");
		}
		o.setPublisher(userProp.getUserId());
		o.setCreateTime(new Date());
		o.setDepartmentId(userProp.getCorpId());
		o.setSyid(userProp.getActiveSyId());
		this.noticeMapper.updateByPrimaryKeyWithBLOBs(o);
		AttachQVo condition =new AttachQVo();
		condition.setNoticeId(o.getNoticeId());
		List<AttachVo> list=attachMapper.findList(condition);
		for(AttachVo av:list){
			filesService.updateFiles(av.getFileUrl(), userProp);
		}
		this.dataBaseLogService.log("变更公告", "公告", "", o.getTitle(),
				o.getTitle(), userProp);
		return new MessageResponse(0, "变更公告完成！");
	}

	public SingleResult<NoticeVo> selectNoticeByPrimaryKey(String id) throws Exception {
		SingleResult<NoticeVo> rst = new SingleResult<NoticeVo>();
		rst.setValue(this.noticeMapper.selectVoByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteNoticeByNoticeId(String id, UserProp userProp)
			throws Exception {
		MessageResponse rst = new MessageResponse(0,"删除成功！");

		this.noticeMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除公告", "公告", String.valueOf(id),
				String.valueOf(id), "公告", userProp);
		return rst;
	}

	public MessageResponse saveOrUpdateNotice(Notice o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getNoticeId())) {
			return new MessageResponse(1, "公告编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getTitle())) {
			return new MessageResponse(1, "标题不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "类别不能为空！");
		}
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "内容不能为空！");
		}
		if (o.getDeadline() == null) {
			return new MessageResponse(1, "有效时间不能为空！");
		}
		o.setCreateTime(new Date());
		o.setDepartmentId(userProp.getCorpId());
		this.noticeMapper.saveOrUpdateNotice(o);
		this.dataBaseLogService.log("变更公告", "公告", "", o.getTitle(),
				o.getTitle(), userProp);
		return new MessageResponse(0, "变更公告完成！");
	}

	public MessageResponse updateForTopByPrimaryKey(String noticeId,
			UserProp userProp) throws Exception {
		MessageResponse rst = new MessageResponse(0,"成功！");
		Notice o = this.noticeMapper.selectByPrimaryKey(noticeId);
		this.noticeMapper.updateForTopByPrimaryKey(noticeId, o.getCategory());
		this.dataBaseLogService.log("公告置顶", o.getTitle(), noticeId, noticeId,
				"公告", userProp);
		return rst;
	}

	public MessageResponse updateForStatusByPrimaryKey(String noticeId,
			String status, String departmentId, String groupId,
			String contextPath, UserProp userProp) throws Exception {
		this.logger.debug("userProp->", userProp);
		Notice o = this.noticeMapper.selectByPrimaryKey(noticeId);
		o.setGroupsId(groupId);
		o.setDepartmentId(departmentId);
		o.setPublishTime(new Date());
		this.noticeMapper.updateByPrimaryKey(o);
		this.noticeMapper.updateForStatusByPrimaryKey(noticeId, status,userProp.getCorpId());

		String msg = "公告发布";
		if (status.equals("2")) {
			msg = "公告下线";
		}
		// NoticeServiceImpl.Plushlet.send("notice");
		this.dataBaseLogService.log(msg, o.getTitle(), noticeId, noticeId,
				"公告", userProp);
		return new MessageResponse(0, "发布成功！");
	}

	/*
	 * public static class Plushlet extends EventPullSource { private static
	 * boolean status=false; private static String category="";
	 * 
	 * @Override protected long getSleepTime() { return 8000; }
	 * 
	 * @Override protected Event pullEvent() { Event event =
	 * Event.createDataEvent("/message/plushlet"); event.setField("time", new
	 * Date().toLocaleString()); event.setField("status","0");
	 * event.setField("category",category); if(status){
	 * System.out.println(category+"/"+status); event.setField("status","1");
	 * status=false; }
	 * 
	 * return event; } public static void send(String arg){ status=true;
	 * category=arg; } }
	 */
	public ListResult<NoticeVo> findListTop(UserProp userProp,String category) throws Exception {
		ListResult<NoticeVo> rst = new ListResult<NoticeVo>();
		List<NoticeVo> list = this.noticeMapper.findListTop(category,userProp.getCorpId());
		rst.setValue(list);
		return rst;
	}
}
