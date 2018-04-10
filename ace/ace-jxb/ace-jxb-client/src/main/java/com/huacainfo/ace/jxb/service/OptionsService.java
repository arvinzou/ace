package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Options;
import com.huacainfo.ace.jxb.vo.OptionsVo;
import com.huacainfo.ace.jxb.vo.OptionsQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(选项)
 */
public interface OptionsService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(选项分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<OptionsVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<OptionsVo> findOptionsList(OptionsQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertOptions
	    * @Description:  TODO(添加选项)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertOptions(Options obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateOptions
	    * @Description:  TODO(更新选项)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateOptions(Options obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectOptionsByPrimaryKey
	    * @Description:  TODO(获取选项)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Options>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<OptionsVo> selectOptionsByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteOptionsByOptionsId
	    * @Description:  TODO(删除选项)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteOptionsByOptionsId(String id,UserProp userProp) throws Exception;

	
}
