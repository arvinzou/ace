package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.TestPaper;
import com.huacainfo.ace.jxb.vo.TestPaperVo;
import com.huacainfo.ace.jxb.vo.TestPaperQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(试卷)
 */
public interface TestPaperService {
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
	public abstract PageResult<TestPaperVo> findTestPaperList(TestPaperQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertTestPaper
	    * @Description:  TODO(添加试卷)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertTestPaper(TestPaper obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateTestPaper
	    * @Description:  TODO(更新试卷)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateTestPaper(TestPaper obj,UserProp userProp) throws Exception;
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
	public abstract SingleResult<TestPaperVo> selectTestPaperByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteTestPaperByTestPaperId
	    * @Description:  TODO(删除试卷)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteTestPaperByTestPaperId(String id,UserProp userProp) throws Exception;

	
}
