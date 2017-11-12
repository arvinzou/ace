package com.huacainfo.ace.portal.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.service.ConfigService;
import com.huacainfo.ace.portal.vo.ConfigQVo;
import com.huacainfo.ace.portal.vo.ConfigVo;

@Controller
@RequestMapping("/config")
public class ConfigController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ConfigService configService;
	/**
	 * 
	    * @Title:findConfigList 
	    * @Description:  TODO(系统参数分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<ConfigVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:36:28
	 */
	@RequestMapping(value = "/findConfigList.do")
	@ResponseBody
	public PageResult<ConfigVo> findConfigList(ConfigQVo condition,
			PageParam page) throws Exception {
		condition.setSyid(this.getCurUserProp().getActiveSyId());
		PageResult<ConfigVo> rst = this.configService.findConfigList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	/**
	 * 
	    * @Title:insertConfig 
	    * @Description:  TODO(添加系统参数) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:36:42
	 */
	@RequestMapping(value = "/insertConfig.do")
	@ResponseBody
	public MessageResponse insertConfig(String jsons) throws Exception {
		Config obj = JSON.parseObject(jsons, Config.class);
		return this.configService.insertConfig(obj, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:updateConfig 
	    * @Description:  TODO(更新系统参数) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:36:53
	 */
	@RequestMapping(value = "/updateConfig.do")
	@ResponseBody
	public MessageResponse updateConfig(String jsons) throws Exception {
		Config obj = JSON.parseObject(jsons, Config.class);
		return this.configService.updateConfig(obj, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:selectConfigByPrimaryKey 
	    * @Description:  TODO(获取系统参数) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<Config>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:37:03
	 */
	@RequestMapping(value = "/selectConfigByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Config> selectConfigByPrimaryKey(String id)
			throws Exception {
		return this.configService.selectConfigByPrimaryKey(id);
	}
	/**
	 * 
	    * @Title:deleteConfigByConfigId 
	    * @Description:  TODO(删除系统参数) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:37:14
	 */
	@RequestMapping(value = "/deleteConfigByConfigId.do")
	@ResponseBody
	public MessageResponse deleteConfigByConfigId(String id)
			throws Exception {
		return this.configService.deleteConfigByConfigId(id,
				this.getCurUserProp());
	}
}
