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
import com.huacainfo.ace.jxb.dao.ExamScoreDao;
import com.huacainfo.ace.jxb.model.ExamScore;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.ExamScoreService;
import com.huacainfo.ace.jxb.vo.ExamScoreVo;
import com.huacainfo.ace.jxb.vo.ExamScoreQVo;
@Service("examScoreService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(评测)
 */
public class ExamScoreServiceImpl implements ExamScoreService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExamScoreDao examScoreDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ExamScoreVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public PageResult<ExamScoreVo> findExamScoreList(ExamScoreQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<ExamScoreVo> rst = new PageResult<ExamScoreVo>();
		List<ExamScoreVo> list = this.examScoreDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.examScoreDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertExamScore
	    * @Description:  TODO(添加评测)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertExamScore(ExamScore o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getTestPaperId())) {
return new MessageResponse(1, "考卷不能为空！");
}
if (CommonUtils.isBlank(o.getUserId())) {
return new MessageResponse(1, "考试人员不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
		int temp = this.examScoreDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "评测名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		//o.setCreateUserName(userProp.getName());
		o.setUserId(userProp.getUserId());
		this.examScoreDao.insert(o);
		this.dataBaseLogService.log("添加评测", "评测", "", o.getTestPaperId(),
				o.getTestPaperId(), userProp);
		return new MessageResponse(0, "添加评测完成！");
	}
    /**
	 *
	    * @Title:updateExamScore
	    * @Description:  TODO(更新评测)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateExamScore(ExamScore o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getTestPaperId())) {
return new MessageResponse(1, "考卷不能为空！");
}
if (CommonUtils.isBlank(o.getUserId())) {
return new MessageResponse(1, "考试人员不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
		
		o.setCreateDate(new Date());
		//o.setLastModifyUserName(userProp.getName());
		o.setUserId(userProp.getUserId());
		this.examScoreDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更评测", "评测", "", o.getTestPaperId(),
				o.getTestPaperId(), userProp);
		return new MessageResponse(0, "变更评测完成！");
	}

    /**
	 *
	    * @Title:selectExamScoreByPrimaryKey
	    * @Description:  TODO(获取评测)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<ExamScore>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public SingleResult<ExamScoreVo> selectExamScoreByPrimaryKey(String id) throws Exception {
		SingleResult<ExamScoreVo> rst = new SingleResult<ExamScoreVo>();
		rst.setValue(this.examScoreDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteExamScoreByExamScoreId
	    * @Description:  TODO(删除评测)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteExamScoreByExamScoreId(String id,
			UserProp userProp) throws Exception {
		this.examScoreDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除评测", "评测", String.valueOf(id),
				String.valueOf(id), "评测", userProp);
		return new MessageResponse(0, "评测删除完成！");
	}
}
