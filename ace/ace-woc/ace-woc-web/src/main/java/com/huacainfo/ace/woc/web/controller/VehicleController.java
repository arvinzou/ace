package com.huacainfo.ace.woc.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Vehicle;
import com.huacainfo.ace.woc.service.VehicleService;
import com.huacainfo.ace.woc.vo.VehicleQVo;
import com.huacainfo.ace.woc.vo.VehicleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/vehicle")
/**
 * @author: 王恩
 * @version: 2018-03-09
 * @Description:  TODO(人员信息)
 */
public class VehicleController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VehicleService vehicleService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(人员信息分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<VehicleVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/findVehicleList")
	@ResponseBody
	public PageResult<VehicleVo> findVehicleList(VehicleQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<VehicleVo> rst = this.vehicleService
				.findVehicleList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertVehicle
	    * @Description:  TODO(添加人员信息)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/insertVehicle")
	@ResponseBody
	public MessageResponse insertVehicle(String jsons) throws Exception {
		Vehicle obj = JSON.parseObject(jsons, Vehicle.class);
		return this.vehicleService
				.insertVehicle(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateVehicle
	    * @Description:  TODO(更新人员信息)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/updateVehicle")
	@ResponseBody
	public MessageResponse updateVehicle(String jsons) throws Exception {
		Vehicle obj = JSON.parseObject(jsons, Vehicle.class);
		return this.vehicleService
				.updateVehicle(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectVehicleByPrimaryKey
	    * @Description:  TODO(获取人员信息)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Vehicle>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/selectVehicleByPrimaryKey")
	@ResponseBody
	public SingleResult<VehicleVo> selectVehicleByPrimaryKey(String id)
			throws Exception {
		return this.vehicleService.selectVehicleByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteVehicleByVehicleId
	    * @Description:  TODO(删除人员信息)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/deleteVehicleByVehicleId")
	@ResponseBody
	public MessageResponse deleteVehicleByVehicleId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.vehicleService.deleteVehicleByVehicleId(id,
				this.getCurUserProp());
	}


    @RequestMapping(value = "/selectListByKeyWord")
    @ResponseBody
    public Map<String, Object> selectListByKeyWord(String q) throws Exception {
        return vehicleService.selectListByKeyWord(q);
    }
}
