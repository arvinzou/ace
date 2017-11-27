package com.huacainfo.ace.face.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.face.model.Person;
import com.huacainfo.ace.face.vo.PersonVo;
import com.huacainfo.ace.face.vo.PersonQVo;
/**
 * @author: 陈晓克
 * @version: 2017-11-25
 * @Description:  TODO(人脸建档)
 */
public interface PersonService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(人脸建档分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<PersonVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2017-11-25
	 */
	public abstract PageResult<PersonVo> findPersonList(PersonQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertPerson
	    * @Description:  TODO(添加人脸建档)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2017-11-25
	 */
	public abstract MessageResponse insertPerson(Person obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updatePerson
	    * @Description:  TODO(更新人脸建档)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2017-11-25
	 */
	public abstract MessageResponse updatePerson(Person obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectPersonByPrimaryKey
	    * @Description:  TODO(获取人脸建档)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Person>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2017-11-25
	 */
	public abstract SingleResult<PersonVo> selectPersonByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deletePersonByPersonId
	    * @Description:  TODO(删除人脸建档)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2017-11-25
	 */
	public abstract MessageResponse deletePersonByPersonId(String id,UserProp userProp) throws Exception;

	/**
	 *
	 * @Title:updatePersonFaceToken
	 * @Description:  TODO(更新faceToken)
	 * @param:        @param id
	 * @param:        @param faceFoken
	 * @param:        @param  userProp
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2017-11-25
	 */
	public abstract MessageResponse updatePersonFaceToken(String id,String faceFoken,UserProp userProp) throws Exception;
	/**
	 *
	 * @Title:updatePersonAttributes
	 * @Description:  TODO(跟新检测结果)
	 * @param:        @param id
	 * @param:        @param attributes
	 * @param:        @param  userProp
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2017-11-25
	 */
	public abstract MessageResponse updatePersonAttributes(String id,String attributes,UserProp userProp) throws Exception;


	 /** @Title:updatePersonAttributes
	 * @Description:  TODO(跟新检测结果)
	 * @param:        @param id
	 * @param:        @param attributes
	 * @param:        @param  userProp
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws* @author: 陈晓克
	 * @version: 2017-11-25
			*/
	public abstract MessageResponse updatePersonFaceTokenAttributes(String id,String faceFoken,String attributes,UserProp userProp) throws Exception;


	/**
	 *
	 * @Title:updatePersonStatus
	 * @Description:  TODO(更新状态)
	 * @param:        @param id
	 * @param:        @param status
	 * @param:        @param  userProp
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2017-11-25
	 */
	public abstract MessageResponse updatePersonStatus(String id,String status,UserProp userProp) throws Exception;

	
}
