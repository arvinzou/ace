package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Person;
import com.huacainfo.ace.woc.vo.PersonVo;
import com.huacainfo.ace.woc.vo.PersonQVo;
/**
 * @author: 王恩
 * @version: 2018-03-09
 * @Description:  TODO(人员信息)
 */
public interface PersonService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(人员信息分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<PersonVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	public abstract PageResult<PersonVo> findPersonList(PersonQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertPerson
	    * @Description:  TODO(添加人员信息)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	public abstract MessageResponse insertPerson(Person obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updatePerson
	    * @Description:  TODO(更新人员信息)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	public abstract MessageResponse updatePerson(Person obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectPersonByPrimaryKey
	    * @Description:  TODO(获取人员信息)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Person>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	public abstract SingleResult<PersonVo> selectPersonByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deletePersonByPersonId
	    * @Description:  TODO(删除人员信息)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	public abstract MessageResponse deletePersonByPersonId(String id,UserProp userProp) throws Exception;

	
}
