package com.huacainfo.ace.portal.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.tools.CommonBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.service.ResourcesService;
import com.huacainfo.ace.portal.vo.MongoFile;
import com.huacainfo.ace.portal.vo.ResourcesVo;

@Controller
@RequestMapping("/resources")
public class ResourcesController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ResourcesService resourcesService;
	/**
	 * 
	    * @Title:findResourcesList 
	    * @Description:  TODO(资源分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<ResourcesVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:09:16
	 */
	@RequestMapping(value = "/findResourcesList.do")
	@ResponseBody
	public PageResult<ResourcesVo> findResourcesList(Resources condition,
			PageParam page) throws Exception {
		PageResult<ResourcesVo> rst = this.resourcesService
				.findResourcesList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
	/**
	 * 
	    * @Title:insertResources 
	    * @Description:  TODO(添加资源) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:09:27
	 */
	@RequestMapping(value = "/insertResources.do")
	@ResponseBody
	public MessageResponse insertResources(String jsons) throws Exception {
		Resources obj = JSON.parseObject(jsons, Resources.class);
		return this.resourcesService
				.insertResources(obj, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:updateResources 
	    * @Description:  TODO(更新资源) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:09:35
	 */
	@RequestMapping(value = "/updateResources.do")
	@ResponseBody
	public MessageResponse updateResources(String jsons) throws Exception {
		Resources obj = JSON.parseObject(jsons, Resources.class);
		return this.resourcesService
				.updateResources(obj, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:selectResourcesByPrimaryKey 
	    * @Description:  TODO(根据主键获取资源信息) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<Resources>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:11:12
	 */
	@RequestMapping(value = "/selectResourcesByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Resources> selectResourcesByPrimaryKey(String id)
			throws Exception {
		return this.resourcesService.selectResourcesByPrimaryKey(id);
	}
	/**
	 * 
	    * @Title:deleteResourcesByResourcesId 
	    * @Description:  TODO(删除资源信息) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:11:31
	 */
	@RequestMapping(value = "/deleteResourcesByResourcesId.do")
	@ResponseBody
	public MessageResponse deleteResourcesByResourcesId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.resourcesService.deleteResourcesByResourcesId(id,
				this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:selectResourcesTreeList 
	    * @Description:  TODO(获取资源树) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<Tree>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:11:44
	 */
	@RequestMapping(value = "/selectResourcesTreeList.do")
	@ResponseBody
	public List<Tree> selectResourcesTreeList() throws Exception {
		return this.resourcesService.selectResourcesTreeList();
	}
	/**
	 * 
	    * @Title:importXls 
	    * @Description:  TODO(导入资源) 
	 		* @param:        @param file
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:13:03
	 */
	@RequestMapping(value = "/importXls.do")
	@ResponseBody
	public MessageResponse importXls(@RequestParam MultipartFile[] file,
			String jsons) throws Exception {
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
				list = utils.readExcelByJXL(obj.getInputStream(), 1);
			}
			if (ext.equals(".xlsx")) {
				list = utils.readExcelByPOI(obj.getInputStream(), 1);
			}
		}
		return this.resourcesService.importXls(list, this.getCurUserProp());
	}
	@RequestMapping(value = "/updateSequence.do")
	@ResponseBody
	public MessageResponse updateSequence(String jsons)
			throws Exception {
		JSONArray jsonArray=JSON.parseArray(jsons);
		List<Map<String, Object>> list=	this.handleJSONArray(jsonArray);
		return this.resourcesService.updateSequence(list,
				this.getCurUserProp());
	}

	private  List<Map<String, Object>> handleJSONArray(JSONArray jsonArray){
		List list = new ArrayList();
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject) object;
			Map map = new HashMap<String, Object>();
			for (Map.Entry entry : jsonObject.entrySet()) {
				if(entry.getValue() instanceof  JSONArray){
					map.put((String)entry.getKey(), handleJSONArray((JSONArray)entry.getValue()));
				}else{
					map.put((String)entry.getKey(), entry.getValue());
				}
			}
			list.add(map);
		}
		return list;
	}
}
