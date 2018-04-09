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
import com.huacainfo.ace.jxb.dao.TestPaperDao;
import com.huacainfo.ace.jxb.model.TestPaper;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.TestPaperService;
import com.huacainfo.ace.jxb.vo.TestPaperVo;
import com.huacainfo.ace.jxb.vo.TestPaperQVo;
@Service("testPaperService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(试卷)
 */
public class TestPaperServiceImpl implements TestPaperService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TestPaperDao testPaperDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(试卷分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TestPaperVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public PageResult<TestPaperVo> findTestPaperList(TestPaperQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<TestPaperVo> rst = new PageResult<TestPaperVo>();
		List<TestPaperVo> list = this.testPaperDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.testPaperDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertTestPaper
	    * @Description:  TODO(添加试卷)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertTestPaper(TestPaper o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {return new MessageResponse(1, "主键不能为空！");}if (CommonUtils.isBlank(o.getName())) {return new MessageResponse(1, "试卷名称不能为空！");}if (CommonUtils.isBlank(o.getIntroduce())) {return new MessageResponse(1, "介绍不能为空！");}if (CommonUtils.isBlank(o.getStatus())) {return new MessageResponse(1, "状态不能为空！");}if (CommonUtils.isBlank(o.getLastModifyDate())) {return new MessageResponse(1, "最后更新时间不能为空！");}
		int temp = this.testPaperDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "试卷名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.testPaperDao.insert(o);
		this.dataBaseLogService.log("添加试卷", "试卷", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加试卷完成！");
	}
    /**
	 *
	    * @Title:updateTestPaper
	    * @Description:  TODO(更新试卷)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateTestPaper(TestPaper o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {return new MessageResponse(1, "主键不能为空！");}if (CommonUtils.isBlank(o.getName())) {return new MessageResponse(1, "试卷名称不能为空！");}if (CommonUtils.isBlank(o.getIntroduce())) {return new MessageResponse(1, "介绍不能为空！");}if (CommonUtils.isBlank(o.getStatus())) {return new MessageResponse(1, "状态不能为空！");}if (CommonUtils.isBlank(o.getLastModifyDate())) {return new MessageResponse(1, "最后更新时间不能为空！");}
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.testPaperDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更试卷", "试卷", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更试卷完成！");
	}

    /**
	 *
	    * @Title:selectTestPaperByPrimaryKey
	    * @Description:  TODO(获取试卷)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TestPaper>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public SingleResult<TestPaperVo> selectTestPaperByPrimaryKey(String id) throws Exception {
		SingleResult<TestPaperVo> rst = new SingleResult<TestPaperVo>();
		rst.setValue(this.testPaperDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteTestPaperByTestPaperId
	    * @Description:  TODO(删除试卷)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteTestPaperByTestPaperId(String id,
			UserProp userProp) throws Exception {
		this.testPaperDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除试卷", "试卷", String.valueOf(id),
				String.valueOf(id), "试卷", userProp);
		return new MessageResponse(0, "试卷删除完成！");
	}
}
