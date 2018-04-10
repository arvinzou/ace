package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Consult;
import com.huacainfo.ace.jxb.vo.ConsultVo;
import com.huacainfo.ace.jxb.vo.ConsultQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(咨询预约)
 */
public interface ConsultService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(咨询预约分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ConsultVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<ConsultVo> findConsultList(ConsultQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertConsult
	    * @Description:  TODO(添加咨询预约)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertConsult(Consult obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateConsult
	    * @Description:  TODO(更新咨询预约)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateConsult(Consult obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectConsultByPrimaryKey
	    * @Description:  TODO(获取咨询预约)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Consult>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<ConsultVo> selectConsultByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteConsultByConsultId
	    * @Description:  TODO(删除咨询预约)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteConsultByConsultId(String id,UserProp userProp) throws Exception;

	
}
