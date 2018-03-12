package com.huacainfo.ace.woc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Device;
import com.huacainfo.ace.woc.service.DeviceService;
import com.huacainfo.ace.woc.vo.DeviceVo;
import com.huacainfo.ace.woc.vo.DeviceQVo;

@Controller
@RequestMapping("/device")
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description:  TODO(设备档案)
 */
public class DeviceController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeviceService deviceService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(设备档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<DeviceVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/findDeviceList")
	@ResponseBody
	public PageResult<DeviceVo> findDeviceList(DeviceQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<DeviceVo> rst = this.deviceService
				.findDeviceList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertDevice
	    * @Description:  TODO(添加设备档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/insertDevice")
	@ResponseBody
	public MessageResponse insertDevice(String jsons) throws Exception {
		Device obj = JSON.parseObject(jsons, Device.class);
		return this.deviceService
				.insertDevice(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateDevice
	    * @Description:  TODO(更新设备档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/updateDevice")
	@ResponseBody
	public MessageResponse updateDevice(String jsons) throws Exception {
		Device obj = JSON.parseObject(jsons, Device.class);
		return this.deviceService
				.updateDevice(obj, this.getCurUserProp());
	}
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
	@RequestMapping(value = "/selectDeviceByPrimaryKey")
	@ResponseBody
	public SingleResult<DeviceVo> selectDeviceByPrimaryKey(String id)
			throws Exception {
		return this.deviceService.selectDeviceByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteDeviceByDeviceId
	    * @Description:  TODO(删除设备档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/deleteDeviceByDeviceId")
	@ResponseBody
	public MessageResponse deleteDeviceByDeviceId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.deviceService.deleteDeviceByDeviceId(id,
				this.getCurUserProp());
	}
}
