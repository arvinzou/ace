package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Device;
import com.huacainfo.ace.woc.vo.DeviceVo;
import com.huacainfo.ace.woc.vo.DeviceQVo;
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description:  TODO(设备档案)
 */
public interface DeviceService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(设备档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<DeviceVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract PageResult<DeviceVo> findDeviceList(DeviceQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertDevice
	    * @Description:  TODO(添加设备档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse insertDevice(Device obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateDevice
	    * @Description:  TODO(更新设备档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse updateDevice(Device obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectDeviceByPrimaryKey
	    * @Description:  TODO(获取设备档案)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Device>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract SingleResult<DeviceVo> selectDeviceByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteDeviceByDeviceId
	    * @Description:  TODO(删除设备档案)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse deleteDeviceByDeviceId(String id,UserProp userProp) throws Exception;

	
}
