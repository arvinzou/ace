package com.huacainfo.ace.uf.web.controller;

import com.huacainfo.ace.common.model.view.CheckTree;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.portal.vo.MongoFile;
import com.huacainfo.ace.uf.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.service.PersonageService;
import com.huacainfo.ace.uf.vo.PersonageVo;
import com.huacainfo.ace.uf.vo.PersonageQVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/personage")
public class PersonageController extends UfBaseController {

	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PersonageService personageService;

	/**
	 * 【统战人士】获取统战人士列表
	 * @param condition
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPersonageList.do")
	@ResponseBody
	public PageResult<PersonageVo> findPersonageList(PersonageQVo condition, PageParamNoChangeSord page) throws Exception {
		if(CommonUtils.isBlank(condition.getAreaCode())){

			condition.setAreaCode(this.getCurUserProp().getAreaCode());
		}
		PageResult<PersonageVo> rst = this.personageService.findPersonageList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}



	/**
	 *【统战人士】 更新统战人士信息
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePersonage.do")
	@ResponseBody
	public MessageResponse updatePersonage(String jsons) throws Exception {
		Personage obj = JSON.parseObject(jsons, Personage.class);
		return this.personageService
				.updatePersonage(obj, this.getCurUserProp());
	}

	/**
	 *【统战人士】 添加新的统战人士
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertPersonage.do")
	@ResponseBody
	public MessageResponse insertPersonage(String jsons) throws Exception {
		Personage obj = JSON.parseObject(jsons, Personage.class);
		return this.personageService
				.insertPersonage(obj, this.getCurUserProp());
	}



	/**
	 * 【统战人士】获取person详细资料。
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectPersonageByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PersonageVo> selectPersonageByPrimaryKey(String id) throws Exception {
		return this.personageService.selectPersonageByPrimaryKey(id);
	}


	/**
	 * 【统战人士】删除统战人士。
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletePersonageByPersonageId.do")
	@ResponseBody
	public MessageResponse deletePersonageByPersonageId(String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.personageService.deletePersonageByPersonageId(id,
				this.getCurUserProp());
	}

	/**
	 * Excel表导入数据。
	 * @param file
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importXls.do")
	@ResponseBody
	public MessageResponse importXls(@RequestParam MultipartFile[] file, String jsons) throws Exception {
		ExcelUtils utils = new ExcelUtils();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		MongoFile[] files = new MongoFile[file.length];
		int i = 0;
		for (MultipartFile o : file) {
			MongoFile obj = new MongoFile();
			obj.setInputStream(o.getInputStream());
			obj.setFilename(o.getOriginalFilename());
			obj.setLength(o.getSize());
			files[i] = obj;
			i++;
			String ext = obj
					.getFilename()
					.toLowerCase()
					.substring(
							obj.getFilename().toLowerCase()
									.lastIndexOf("."));
			this.logger.info(ext);
			if (ext.equals(".xls")) {
				List<Map<String, Object>> t = utils.readExcelByJXL(obj.getInputStream(), 2);
				list.addAll(t);
			}
			if (ext.equals(".xlsx")) {
				List<Map<String, Object>> t  = utils.readExcelByPOI(obj.getInputStream(), 2);
				list.addAll(t);
			}
		}
		return this.personageService.importXls(list, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectPersonage.do")
	@ResponseBody
	public Map<String,Object> selectPersonage(String q,String id)throws Exception {
		if(CommonUtils.isNotBlank(id)){
			q=id;
		}
		return this.personageService.selectPersonage(q);
	}
	@RequestMapping(value = "/selectPersonageCheckTreeList.do")
	@ResponseBody
	public  List<CheckTree>  selectPersonageCheckTreeList() throws Exception{
		return this.personageService.selectPersonageCheckTreeList();
	}

	/**
	 * 【奖惩信息】获取奖惩信息及发送到前端
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPerJC.do")
	@ResponseBody
	public List<PerJiangCheng> findperJc(String id) throws Exception {
		return this.personageService.selectPerJCById(id);
	}

	/**
	 * 【奖惩信息】更新统战人士的奖惩信息
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePerJC.do")
	@ResponseBody
	public MessageResponse updatePerJc(String jsons) throws Exception {
		PerJiangCheng obj=JSON.parseObject(jsons,PerJiangCheng.class);
		return this.personageService.updatePerJC(obj,this.getCurUserProp());
	}

	/**
	 * 【奖惩信息】删除统战人士的一条奖惩信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletePerJC.do")
	@ResponseBody
	public MessageResponse deletePerJc(String id) throws Exception {
		return this.personageService.deletePerJCById(id, this.getCurUserProp());
	}

	/**
	 *【奖惩信息】 添加统战人士新的奖惩信息
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPerJc.do")
	@ResponseBody
	public MessageResponse addPerJc(String jsons) throws Exception {
		PerJiangCheng obj = JSON.parseObject(jsons, PerJiangCheng.class);
		return this.personageService.insertPerJc(obj, this.getCurUserProp());
	}

	/**
	 * 【社会职务】获取统战人士社会职务及发送到前端
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPerZw.do")
	@ResponseBody
	public List<PerZhiWu> findPerZw(String id) throws Exception {
		return this.personageService.selectPerZwById(id);
	}

	/**
	 * 【社会职务】更新统战人士的社会职务
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePerZw.do")
	@ResponseBody
	public MessageResponse updatePerZw(String jsons) throws Exception {
		PerZhiWu obj=JSON.parseObject(jsons,PerZhiWu.class);
		return this.personageService.updatePerZwById(obj,this.getCurUserProp());
	}

	/**
	 * 【社会职务】删除统战人士的一条社会职务
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletePerZw.do")
	@ResponseBody
	public MessageResponse deletePerZw(String id) throws Exception {
		return this.personageService.deletePerZwById(id, this.getCurUserProp());
	}

	/**
	 *【社会职务】 添加统战人士新的社会职务
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPerZw.do")
	@ResponseBody
	public MessageResponse addPerZw(String jsons) throws Exception {
		PerZhiWu obj = JSON.parseObject(jsons, PerZhiWu.class);
		return this.personageService.insertPerZw(obj, this.getCurUserProp());
	}



	/**
	 * 【人士类型】获取统战人士人士类型及发送到前端
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPerCategory.do")
	@ResponseBody
	public List<PerCategory> findPerCategory(String id) throws Exception {
		return this.personageService.selectPerCategoryById(id);
	}

	/**
	 * 【人士类型】更新统战人士的人士类型
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePerCategory.do")
	@ResponseBody
	public MessageResponse updatePerCategory(String jsons) throws Exception {
		PerCategory obj=JSON.parseObject(jsons,PerCategory.class);
		return this.personageService.updatePerCategoryById(obj,this.getCurUserProp());
	}

	/**
	 * 【人士类型】删除统战人士的一条人士类型
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletePerCategory.do")
	@ResponseBody
	public MessageResponse deletePerCategory(String id) throws Exception {
		return this.personageService.deletePerCategoryById(id, this.getCurUserProp());
	}

	/**
	 *【人士类型】 添加统战人士新的人士类型
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPerCategory.do")
	@ResponseBody
	public MessageResponse addPerCategory(String jsons) throws Exception {
		PerCategory obj = JSON.parseObject(jsons, PerCategory.class);
		return this.personageService.insertPerCategory(obj, this.getCurUserProp());
	}



	/**
	 * 【兴趣爱好】获取统战人士兴趣爱好及发送到前端
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findbeet.do")
	@ResponseBody
	public List<interest> findbeet(String id) throws Exception {
		return this.personageService.selectInterestById(id);
	}

	/**
	 * 【兴趣爱好】更新统战人士的兴趣爱好
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatebeet.do")
	@ResponseBody
	public MessageResponse updatebeet(String jsons) throws Exception {
		interest obj=JSON.parseObject(jsons,interest.class);
		return this.personageService.updateInterestById(obj,this.getCurUserProp());
	}

	/**
	 * 【兴趣爱好】删除统战人士的一条兴趣爱好
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletebeet.do")
	@ResponseBody
	public MessageResponse deletebeet(String id) throws Exception {
		return this.personageService.deleteInterestById(id, this.getCurUserProp());
	}

	/**
	 *【兴趣爱好】 添加统战人士新的兴趣爱好
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addbeet.do")
	@ResponseBody
	public MessageResponse addbeet(String jsons) throws Exception {
		interest obj = JSON.parseObject(jsons, interest.class);
		return this.personageService.insertInterest(obj, this.getCurUserProp());
	}
}
