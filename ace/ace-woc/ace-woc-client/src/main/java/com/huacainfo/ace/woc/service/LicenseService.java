package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.License;
import com.huacainfo.ace.woc.vo.LicenseVo;
import com.huacainfo.ace.woc.vo.LicenseQVo;
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description:  TODO(证件档案)
 */
public interface LicenseService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(证件档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<LicenseVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract PageResult<LicenseVo> findLicenseList(LicenseQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertLicense
	    * @Description:  TODO(添加证件档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse insertLicense(License obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateLicense
	    * @Description:  TODO(更新证件档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse updateLicense(License obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectLicenseByPrimaryKey
	    * @Description:  TODO(获取证件档案)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<License>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract SingleResult<LicenseVo> selectLicenseByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteLicenseByLicenseId
	    * @Description:  TODO(删除证件档案)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse deleteLicenseByLicenseId(String id,UserProp userProp) throws Exception;

	
}
