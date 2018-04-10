package com.huacainfo.ace.jxb.web.controller;

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
import com.huacainfo.ace.jxb.model.Consult;
import com.huacainfo.ace.jxb.service.ConsultService;
import com.huacainfo.ace.jxb.vo.ConsultVo;
import com.huacainfo.ace.jxb.vo.ConsultQVo;

@Controller
@RequestMapping("/consult")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(咨询预约)
 */
public class ConsultController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ConsultService consultService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(咨询预约分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ConsultVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findConsultList")
	@ResponseBody
	public PageResult<ConsultVo> findConsultList(ConsultQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ConsultVo> rst = this.consultService
				.findConsultList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertConsult
	    * @Description:  TODO(添加咨询预约)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertConsult")
	@ResponseBody
	public MessageResponse insertConsult(String jsons) throws Exception {
		Consult obj = JSON.parseObject(jsons, Consult.class);
		return this.consultService
				.insertConsult(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateConsult
	    * @Description:  TODO(更新咨询预约)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateConsult")
	@ResponseBody
	public MessageResponse updateConsult(String jsons) throws Exception {
		Consult obj = JSON.parseObject(jsons, Consult.class);
		return this.consultService
				.updateConsult(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectConsultByPrimaryKey
	    * @Description:  TODO(获取咨询预约)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Consult>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/selectConsultByPrimaryKey")
	@ResponseBody
	public SingleResult<ConsultVo> selectConsultByPrimaryKey(String id)
			throws Exception {
		return this.consultService.selectConsultByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteConsultByConsultId
	    * @Description:  TODO(删除咨询预约)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteConsultByConsultId")
	@ResponseBody
	public MessageResponse deleteConsultByConsultId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.consultService.deleteConsultByConsultId(id,
				this.getCurUserProp());
	}
}
