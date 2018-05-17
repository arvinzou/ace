package com.huacainfo.ace.portal.web.controller;

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
import com.huacainfo.ace.portal.model.Appeal;
import com.huacainfo.ace.portal.service.AppealService;
import com.huacainfo.ace.portal.vo.AppealVo;
import com.huacainfo.ace.portal.vo.AppealQVo;

@Controller
@RequestMapping("/appeal")
/**
 * @author: 陈晓克
 * @version: 2018-05-14
 * @Description:  TODO(诉求模板)
 */
public class AppealController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AppealService appealService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(诉求模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<AppealVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	@RequestMapping(value = "/findAppealList.do")
	@ResponseBody
	public PageResult<AppealVo> findAppealList(AppealQVo condition,
			PageParamNoChangeSord page) throws Exception {
		condition.setCorpId(this.getCurUserProp().getCorpId());
		PageResult<AppealVo> rst = this.appealService
				.findAppealList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertAppeal
	    * @Description:  TODO(添加诉求模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	@RequestMapping(value = "/insertAppeal.do")
	@ResponseBody
	public MessageResponse insertAppeal(String jsons) throws Exception {
		Appeal obj = JSON.parseObject(jsons, Appeal.class);
		return this.appealService
				.insertAppeal(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateAppeal
	    * @Description:  TODO(更新诉求模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	@RequestMapping(value = "/updateAppeal.do")
	@ResponseBody
	public MessageResponse updateAppeal(String jsons) throws Exception {
		Appeal obj = JSON.parseObject(jsons, Appeal.class);
		return this.appealService
				.updateAppeal(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectAppealByPrimaryKey
	    * @Description:  TODO(获取诉求模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Appeal>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	@RequestMapping(value = "/selectAppealByPrimaryKey.do")
	@ResponseBody
	public SingleResult<AppealVo> selectAppealByPrimaryKey(String id)
			throws Exception {
		return this.appealService.selectAppealByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteAppealByAppealId
	    * @Description:  TODO(删除诉求模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	@RequestMapping(value = "/deleteAppealByAppealId.do")
	@ResponseBody
	public MessageResponse deleteAppealByAppealId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.appealService.deleteAppealByAppealId(id,
				this.getCurUserProp());
	}
}
