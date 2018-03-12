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
import com.huacainfo.ace.woc.model.Person;
import com.huacainfo.ace.woc.service.PersonService;
import com.huacainfo.ace.woc.vo.PersonVo;
import com.huacainfo.ace.woc.vo.PersonQVo;

@Controller
@RequestMapping("/person")
/**
 * @author: 王恩
 * @version: 2018-03-09
 * @Description:  TODO(人员信息)
 */
public class PersonController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PersonService personService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(人员信息分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<PersonVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/findPersonList")
	@ResponseBody
	public PageResult<PersonVo> findPersonList(PersonQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<PersonVo> rst = this.personService
				.findPersonList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertPerson
	    * @Description:  TODO(添加人员信息)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/insertPerson")
	@ResponseBody
	public MessageResponse insertPerson(String jsons) throws Exception {
		Person obj = JSON.parseObject(jsons, Person.class);
		return this.personService
				.insertPerson(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updatePerson
	    * @Description:  TODO(更新人员信息)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/updatePerson")
	@ResponseBody
	public MessageResponse updatePerson(String jsons) throws Exception {
		Person obj = JSON.parseObject(jsons, Person.class);
		return this.personService
				.updatePerson(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectPersonByPrimaryKey
	    * @Description:  TODO(获取人员信息)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Person>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/selectPersonByPrimaryKey")
	@ResponseBody
	public SingleResult<PersonVo> selectPersonByPrimaryKey(String id)
			throws Exception {
		return this.personService.selectPersonByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deletePersonByPersonId
	    * @Description:  TODO(删除人员信息)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/deletePersonByPersonId")
	@ResponseBody
	public MessageResponse deletePersonByPersonId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.personService.deletePersonByPersonId(id,
				this.getCurUserProp());
	}
}
