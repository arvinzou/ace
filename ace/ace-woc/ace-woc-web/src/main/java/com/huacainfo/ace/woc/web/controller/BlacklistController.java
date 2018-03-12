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
import com.huacainfo.ace.woc.model.Blacklist;
import com.huacainfo.ace.woc.service.BlacklistService;
import com.huacainfo.ace.woc.vo.BlacklistVo;
import com.huacainfo.ace.woc.vo.BlacklistQVo;

@Controller
@RequestMapping("/blacklist")
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description:  TODO(黑名单档案)
 */
public class BlacklistController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BlacklistService blacklistService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(黑名单档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<BlacklistVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/findBlacklistList")
	@ResponseBody
	public PageResult<BlacklistVo> findBlacklistList(BlacklistQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<BlacklistVo> rst = this.blacklistService
				.findBlacklistList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertBlacklist
	    * @Description:  TODO(添加黑名单档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/insertBlacklist")
	@ResponseBody
	public MessageResponse insertBlacklist(String jsons) throws Exception {
		Blacklist obj = JSON.parseObject(jsons, Blacklist.class);
		return this.blacklistService
				.insertBlacklist(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateBlacklist
	    * @Description:  TODO(更新黑名单档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/updateBlacklist")
	@ResponseBody
	public MessageResponse updateBlacklist(String jsons) throws Exception {
		Blacklist obj = JSON.parseObject(jsons, Blacklist.class);
		return this.blacklistService
				.updateBlacklist(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectBlacklistByPrimaryKey
	    * @Description:  TODO(获取黑名单档案)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Blacklist>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/selectBlacklistByPrimaryKey")
	@ResponseBody
	public SingleResult<BlacklistVo> selectBlacklistByPrimaryKey(String id)
			throws Exception {
		return this.blacklistService.selectBlacklistByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteBlacklistByBlacklistId
	    * @Description:  TODO(删除黑名单档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/deleteBlacklistByBlacklistId")
	@ResponseBody
	public MessageResponse deleteBlacklistByBlacklistId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.blacklistService.deleteBlacklistByBlacklistId(id,
				this.getCurUserProp());
	}
}
