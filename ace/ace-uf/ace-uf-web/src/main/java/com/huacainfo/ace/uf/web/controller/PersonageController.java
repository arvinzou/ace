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

}
