package com.huacainfo.ace.uf.service.impl;

import java.util.*;

import com.huacainfo.ace.common.tools.CommonBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.SysInfoService;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.uf.dao.FeedbackDao;
import com.huacainfo.ace.uf.model.Feedback;
import com.huacainfo.ace.uf.service.FeedbackService;
import com.huacainfo.ace.uf.vo.FeedbackQVo;
import com.huacainfo.ace.uf.vo.FeedbackVo;
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FeedbackDao feedbackDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private SysInfoService sysInfoService;
	@Autowired
	private KafkaProducerService kafkaProducerService;

	public PageResult<FeedbackVo> findFeedbackList(FeedbackQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<FeedbackVo> rst = new PageResult<FeedbackVo>();
		List<FeedbackVo> list = this.feedbackDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.feedbackDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertFeedback(Feedback o, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			o.setId(UUID.randomUUID().toString());
		}
		if (CommonUtils.isBlank(o.getTitle())) {
			o.setTitle("主人繁忙没有填写主题");
		}
		if (CommonUtils.isBlank(o.getDocText())) {
			return new MessageResponse(1, "内容不能为空！");
		}

		if (CommonUtils.isBlank(o.getName())) {
			o.setName("匿名");
		}
		if (CommonUtils.isNotBlank(o.getTel())) {
			if(o.getTel().length()!=11){
				return new MessageResponse(1, "手机号格式错误！");
			}
		}
		if (CommonUtils.isNotBlank(o.getEmail())) {
			if(!CommonUtils.isValidEmail(o.getEmail())){
				return new MessageResponse(1, "电子邮箱格式错误！");
			}
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		this.feedbackDao.insert(o);
		//this.dataBaseLogService.log("添加留言建议", "留言建议", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "留言建议成功！");
	}

	public MessageResponse updateFeedback(Feedback o, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getReplyText())) {
			return new MessageResponse(1, "答复内容不能为空！");
		}
		o.setStatus("2");
		o.setReplyTime(new Date());
		o.setReplyUserId(userProp.getUserId());
		o.setReplyUserName(userProp.getName());
		this.feedbackDao.updateByPrimaryKey(o);
		FeedbackVo obj=this.feedbackDao.selectByPrimaryKey(o.getId());
		this.logger.info("{}",obj);
		this.dataBaseLogService.log("留言建议答复", "留言建议", "", o.getName(), o.getName(), userProp);
		Map<String,Object> model=new HashMap<String,Object>();
		model.put("to",obj.getEmail());
		model.put("name",obj.getName());
		model.put("title",obj.getTitle());
		model.put("docText",obj.getDocText());
		model.put("replyText",obj.getReplyText());
		this.sendEamil(model);
		return new MessageResponse(0, "答复完成！");
	}
	public void sendEamil(Map<String,Object> model) throws Exception {
		model.put("sysDate", CommonUtils.formatDate(new Date()));
		String subject =CommonUtils.getStringByTemplate("email.subject.feedbak.vm", model);
		String content=CommonUtils.getStringByTemplate("email.feedbak.vm", model);
		Map<String, String> data = new HashMap<String, String>();
		data.put("subject", subject);
		data.put("content", content);
		data.put("to", (String)model.get("to"));
		this.logger.info("{}",data);
		this.kafkaProducerService.sendMsg("GESP_SYS_INFO", data);
	}
	public SingleResult<FeedbackVo> selectFeedbackByPrimaryKey(String id) throws Exception {
		SingleResult<FeedbackVo> rst = new SingleResult<FeedbackVo>();
		rst.setValue(this.feedbackDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteFeedbackByFeedbackId(String id, UserProp userProp) throws Exception {
		this.feedbackDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除留言建议", "留言建议", String.valueOf(id), String.valueOf(id), "留言建议", userProp);
		return new MessageResponse(0, "留言建议删除完成！");
	}
	public static void main(String[] args) throws Exception{
		Map<String,Object> model=new HashMap<String,Object>();
		model.put("title","431241324122412");
		model.put("docText","删除留言建议");
		model.put("replyText","留言建议删除完成");
		System.out.println(CommonUtils.getStringByTemplate("email.subject.feedbak.vm", model));
		System.out.println(CommonUtils.getStringByTemplate("email.feedbak.vm", model));

	}
}
