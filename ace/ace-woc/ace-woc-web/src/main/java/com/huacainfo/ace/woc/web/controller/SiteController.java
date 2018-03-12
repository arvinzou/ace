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
import com.huacainfo.ace.woc.model.Site;
import com.huacainfo.ace.woc.service.SiteService;
import com.huacainfo.ace.woc.vo.SiteVo;
import com.huacainfo.ace.woc.vo.SiteQVo;

@Controller
@RequestMapping("/site")
/**
 * @author: 王恩
 * @version: 2018-03-09
 * @Description:  TODO(人员信息)
 */
public class SiteController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SiteService siteService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(人员信息分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<SiteVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/findSiteList")
	@ResponseBody
	public PageResult<SiteVo> findSiteList(SiteQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<SiteVo> rst = this.siteService
				.findSiteList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertSite
	    * @Description:  TODO(添加人员信息)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/insertSite")
	@ResponseBody
	public MessageResponse insertSite(String jsons) throws Exception {
		Site obj = JSON.parseObject(jsons, Site.class);
		return this.siteService
				.insertSite(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateSite
	    * @Description:  TODO(更新人员信息)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/updateSite")
	@ResponseBody
	public MessageResponse updateSite(String jsons) throws Exception {
		Site obj = JSON.parseObject(jsons, Site.class);
		return this.siteService
				.updateSite(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectSiteByPrimaryKey
	    * @Description:  TODO(获取人员信息)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Site>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/selectSiteByPrimaryKey")
	@ResponseBody
	public SingleResult<SiteVo> selectSiteByPrimaryKey(String id)
			throws Exception {
		return this.siteService.selectSiteByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteSiteBySiteId
	    * @Description:  TODO(删除人员信息)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/deleteSiteBySiteId")
	@ResponseBody
	public MessageResponse deleteSiteBySiteId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.siteService.deleteSiteBySiteId(id,
				this.getCurUserProp());
	}
}
