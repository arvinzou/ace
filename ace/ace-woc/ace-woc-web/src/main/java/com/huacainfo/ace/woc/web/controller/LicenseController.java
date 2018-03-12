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
import com.huacainfo.ace.woc.model.License;
import com.huacainfo.ace.woc.service.LicenseService;
import com.huacainfo.ace.woc.vo.LicenseVo;
import com.huacainfo.ace.woc.vo.LicenseQVo;

@Controller
@RequestMapping("/license")
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description:  TODO(证件档案)
 */
public class LicenseController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LicenseService licenseService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(证件档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<LicenseVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/findLicenseList")
	@ResponseBody
	public PageResult<LicenseVo> findLicenseList(LicenseQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<LicenseVo> rst = this.licenseService
				.findLicenseList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertLicense
	    * @Description:  TODO(添加证件档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/insertLicense")
	@ResponseBody
	public MessageResponse insertLicense(String jsons) throws Exception {
		License obj = JSON.parseObject(jsons, License.class);
		return this.licenseService
				.insertLicense(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateLicense
	    * @Description:  TODO(更新证件档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/updateLicense")
	@ResponseBody
	public MessageResponse updateLicense(String jsons) throws Exception {
		License obj = JSON.parseObject(jsons, License.class);
		return this.licenseService
				.updateLicense(obj, this.getCurUserProp());
	}
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
	@RequestMapping(value = "/selectLicenseByPrimaryKey")
	@ResponseBody
	public SingleResult<LicenseVo> selectLicenseByPrimaryKey(String id)
			throws Exception {
		return this.licenseService.selectLicenseByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteLicenseByLicenseId
	    * @Description:  TODO(删除证件档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/deleteLicenseByLicenseId")
	@ResponseBody
	public MessageResponse deleteLicenseByLicenseId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.licenseService.deleteLicenseByLicenseId(id,
				this.getCurUserProp());
	}
}
