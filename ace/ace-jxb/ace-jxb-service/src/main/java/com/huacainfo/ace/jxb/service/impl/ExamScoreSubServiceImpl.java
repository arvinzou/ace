package com.huacainfo.ace.woc.service.impl;


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
import com.huacainfo.ace.jxb.dao.ExamScoreSubDao;
import com.huacainfo.ace.jxb.model.ExamScoreSub;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.ExamScoreSubService;
import com.huacainfo.ace.jxb.vo.ExamScoreSubVo;
import com.huacainfo.ace.jxb.vo.ExamScoreSubQVo;
@Service("examScoreSubService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(评测结果)
 */
public class ExamScoreSubServiceImpl implements ExamScoreSubService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExamScoreSubDao examScoreSubDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测结果分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ExamScoreSubVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public PageResult<ExamScoreSubVo> findExamScoreSubList(ExamScoreSubQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<ExamScoreSubVo> rst = new PageResult<ExamScoreSubVo>();
		List<ExamScoreSubVo> list = this.examScoreSubDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.examScoreSubDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertExamScoreSub
	    * @Description:  TODO(添加评测结果)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertExamScoreSub(ExamScoreSub o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getExamScoreId())) {
return new MessageResponse(1, "评测主键不能为空！");
}
if (CommonUtils.isBlank(o.getQuestionId())) {
return new MessageResponse(1, "考题主键不能为空！");
}
if (CommonUtils.isBlank(o.getOptionsId())) {
return new MessageResponse(1, "选项主键不能为空！");
}
		int temp = this.examScoreSubDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "评测结果名称重复！");
		}
		o.setCreateDate(new Date());
		//o.setStatus("1");
		//o.setCreateUserName(userProp.getName());
		//o.setCreateUserId(userProp.getUserId());
		this.examScoreSubDao.insert(o);
		this.dataBaseLogService.log("添加评测结果", "评测结果", "", o.getExamScoreId(),
				o.getExamScoreId(), userProp);
		return new MessageResponse(0, "添加评测结果完成！");
	}
    /**
	 *
	    * @Title:updateExamScoreSub
	    * @Description:  TODO(更新评测结果)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateExamScoreSub(ExamScoreSub o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getExamScoreId())) {
return new MessageResponse(1, "评测主键不能为空！");
}
if (CommonUtils.isBlank(o.getQuestionId())) {
return new MessageResponse(1, "考题主键不能为空！");
}
if (CommonUtils.isBlank(o.getOptionsId())) {
return new MessageResponse(1, "选项主键不能为空！");
}
		
		//o.setLastModifyDate(new Date());
		//o.setLastModifyUserName(userProp.getName());
		//o.setLastModifyUserId(userProp.getUserId());
		this.examScoreSubDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更评测结果", "评测结果", "", o.getExamScoreId(),
				o.getExamScoreId(), userProp);
		return new MessageResponse(0, "变更评测结果完成！");
	}

    /**
	 *
	    * @Title:selectExamScoreSubByPrimaryKey
	    * @Description:  TODO(获取评测结果)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<ExamScoreSub>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public SingleResult<ExamScoreSubVo> selectExamScoreSubByPrimaryKey(String id) throws Exception {
		SingleResult<ExamScoreSubVo> rst = new SingleResult<ExamScoreSubVo>();
		rst.setValue(this.examScoreSubDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteExamScoreSubByExamScoreSubId
	    * @Description:  TODO(删除评测结果)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteExamScoreSubByExamScoreSubId(String id,
			UserProp userProp) throws Exception {
		this.examScoreSubDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除评测结果", "评测结果", String.valueOf(id),
				String.valueOf(id), "评测结果", userProp);
		return new MessageResponse(0, "评测结果删除完成！");
	}
}
