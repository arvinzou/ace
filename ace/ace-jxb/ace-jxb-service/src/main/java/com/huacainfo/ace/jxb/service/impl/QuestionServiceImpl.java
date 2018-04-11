package com.huacainfo.ace.jxb.service.impl;


import java.util.Date;
import java.util.List;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.jxb.dao.QuestionDao;
import com.huacainfo.ace.jxb.model.Question;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.QuestionService;
import com.huacainfo.ace.jxb.vo.QuestionVo;
import com.huacainfo.ace.jxb.vo.QuestionQVo;
@Service("questionService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(考题)
 */
public class QuestionServiceImpl implements QuestionService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(考题分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<QuestionVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public PageResult<QuestionVo> findQuestionList(QuestionQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<QuestionVo> rst = new PageResult<QuestionVo>();
		List<QuestionVo> list = this.questionDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.questionDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertQuestion
	    * @Description:  TODO(添加考题)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertQuestion(Question o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getTestPaperId())) {
return new MessageResponse(1, "所属试卷不能为空！");
}
if (CommonUtils.isBlank(o.getCategory())) {
return new MessageResponse(1, "类别不能为空！");
}
if (CommonUtils.isBlank(o.getName())) {
return new MessageResponse(1, "题目名称不能为空！");
}
if (CommonUtils.isBlank(o.getSort())) {
return new MessageResponse(1, "顺序不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
if (CommonUtils.isBlank(o.getLastModifyDate())) {
return new MessageResponse(1, "最后更新时间不能为空！");
}
		int temp = this.questionDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "考题名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.questionDao.insert(o);
		this.dataBaseLogService.log("添加考题", "考题", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加考题完成！");
	}
    /**
	 *
	    * @Title:updateQuestion
	    * @Description:  TODO(更新考题)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateQuestion(Question o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getTestPaperId())) {
return new MessageResponse(1, "所属试卷不能为空！");
}
if (CommonUtils.isBlank(o.getCategory())) {
return new MessageResponse(1, "类别不能为空！");
}
if (CommonUtils.isBlank(o.getName())) {
return new MessageResponse(1, "题目名称不能为空！");
}
if (CommonUtils.isBlank(o.getSort())) {
return new MessageResponse(1, "顺序不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
if (CommonUtils.isBlank(o.getLastModifyDate())) {
return new MessageResponse(1, "最后更新时间不能为空！");
}
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.questionDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更考题", "考题", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更考题完成！");
	}

    /**
	 *
	    * @Title:selectQuestionByPrimaryKey
	    * @Description:  TODO(获取考题)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Question>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public SingleResult<QuestionVo> selectQuestionByPrimaryKey(String id) throws Exception {
		SingleResult<QuestionVo> rst = new SingleResult<QuestionVo>();
		rst.setValue(this.questionDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteQuestionByQuestionId
	    * @Description:  TODO(删除考题)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteQuestionByQuestionId(String id,
			UserProp userProp) throws Exception {
		this.questionDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除考题", "考题", String.valueOf(id),
				String.valueOf(id), "考题", userProp);
		return new MessageResponse(0, "考题删除完成！");
	}
}
