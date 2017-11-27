package com.huacainfo.ace.face.web.controller;
import com.huacainfo.ace.common.fastdfs.IFile;
import org.springframework.web.bind.annotation.RequestParam;
import com.huacainfo.ace.common.tools.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.face.model.Person;
import com.huacainfo.ace.face.service.PersonService;
import com.huacainfo.ace.face.vo.PersonVo;
import com.huacainfo.ace.face.vo.PersonQVo;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/person")
/**
 * @author: 陈晓克
 * @version: 2017-11-25
 * @Description:  TODO(人脸建档)
 */
public class PersonController extends FaceBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PersonService personService;

	@Autowired
	private IFile fileSaver;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(人脸建档分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<PersonVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2017-11-25
	 */
	@RequestMapping(value = "/findPersonList.do")
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
	    * @Description:  TODO(添加人脸建档)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2017-11-25
	 */
	@RequestMapping(value = "/insertPerson.do")
	@ResponseBody
	public MessageResponse insertPerson(String jsons) throws Exception {
		Person obj = JSON.parseObject(jsons, Person.class);
		obj.setCategory("1");
		return this.personService
				.insertPerson(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updatePerson
	    * @Description:  TODO(更新人脸建档)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2017-11-25
	 */
	@RequestMapping(value = "/updatePerson.do")
	@ResponseBody
	public MessageResponse updatePerson(String jsons) throws Exception {
		Person obj = JSON.parseObject(jsons, Person.class);
		return this.personService
				.updatePerson(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectPersonByPrimaryKey
	    * @Description:  TODO(获取人脸建档)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Person>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2017-11-25
	 */
	@RequestMapping(value = "/selectPersonByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PersonVo> selectPersonByPrimaryKey(String id)
			throws Exception {
		return this.personService.selectPersonByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deletePersonByPersonId
	    * @Description:  TODO(删除人脸建档)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2017-11-25
	 */
	@RequestMapping(value = "/deletePersonByPersonId.do")
	@ResponseBody
	public MessageResponse deletePersonByPersonId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.personService.deletePersonByPersonId(id,
				this.getCurUserProp());
	}

	/**
	 *
	 * @Title:updatePersonFaceToken
	 * @Description:  TODO(更新faceToken)
	 * @param:        @param id
	 * @param:        @param faceFoken
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2017-11-25
	 */
	@RequestMapping(value = "/updatePersonFaceToken.do")
	@ResponseBody
	public  MessageResponse updatePersonFaceToken(String id,String faceFoken) throws Exception{
		return this.personService.updatePersonFaceToken(id,faceFoken,this.getCurUserProp());
	}
	/**
	 *
	 * @Title:updatePersonAttributes
	 * @Description:  TODO(更新检测结果)
	 * @param:        @param id
	 * @param:        @param attributes
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2017-11-25
	 */
	@RequestMapping(value = "/updatePersonFaceTokenAttributes.do")
	@ResponseBody
	public  MessageResponse updatePersonFaceTokenAttributes(String id,String  faceToken,String attributes) throws Exception{
		return this.personService.updatePersonFaceTokenAttributes(id,faceToken,attributes,this.getCurUserProp());

	}
	/**
	 *
	 * @Title:updatePersonAttributes
	 * @Description:  TODO(更新检测结果)
	 * @param:        @param id
	 * @param:        @param attributes
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2017-11-25
	 */
	@RequestMapping(value = "/updatePersonAttributes.do")
	@ResponseBody
	public  MessageResponse updatePersonAttributes(String id,String attributes) throws Exception{
		return this.personService.updatePersonAttributes(id,attributes,this.getCurUserProp());

	}
	/**
	 *
	 * @Title:selectPersonAttributes
	 * @Description:  TODO(获取检测结果)
	 * @param:        @param id
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2017-11-25
	 */
	@RequestMapping(value = "/selectPersonAttributes.do")
	@ResponseBody
	public  JSONObject selectPersonAttributes(String id) throws Exception{
		SingleResult<PersonVo> rst=this.personService.selectPersonByPrimaryKey(id);
		PersonVo o=rst.getValue();
		if(CommonUtils.isNotEmpty(o)&&CommonUtils.isNotEmpty(o.getAttributes())){
			return JSON.parseObject(o.getAttributes());
		}else{
			return JSON.parseObject("{status:1}");
		}
	}

	/**
	 *
	 * @Title:updatePersonStatus
	 * @Description:  TODO(更新状态)
	 * @param:        @param id
	 * @param:        @param status
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2017-11-25
	 */
	@RequestMapping(value = "/updatePersonStatus.do")
	@ResponseBody
	public  MessageResponse updatePersonStatus(String id,String status) throws Exception{
		return this.personService.updatePersonStatus(id,status,this.getCurUserProp());

	}

	@RequestMapping(value = "/uploadFile.do")
	@ResponseBody
	public SingleResult<String[]> uploadFile(
			@RequestParam MultipartFile[] file, String category)
			throws Exception {
		SingleResult<String[]> rst = new SingleResult<String[]>(0, "上传成功！");
		String[] fileNames = new String[file.length];
		String dir = this.getRequest().getSession().getServletContext()
				.getRealPath(File.separator)
				+ "tmp";
		File tmp = new File(dir);
		if (!tmp.exists()) {
			tmp.mkdirs();
		}
		int i = 0;
		for (MultipartFile o : file) {
			File dest = new File(dir + File.separator + o.getName());
			o.transferTo(dest);
			fileNames[i] = this.fileSaver.saveFile(dest,
					o.getOriginalFilename());
			dest.delete();

			Person obj =new Person();
			obj.setPhoto(fileNames[i]);
			obj.setName(UUID.randomUUID().toString()+"#"+o.getOriginalFilename().substring(0,o.getOriginalFilename().indexOf(".")));
			obj.setCategory(category);
			MessageResponse response=this.personService.insertPerson(obj,this.getCurUserProp());
			if(!response.getState()){
				SingleResult<String[]> srt=new SingleResult<String[]>(1,"");
				srt.setErrorMessage(response.getErrorMessage());
				srt.setDetail(response.getDetail());
				return srt;
			}
			i++;
		}
		rst.setValue(fileNames);
		return rst;
	}
	/**
	 *
	 * @Title:selectListByFaceTokens
	 * @Description:  TODO(根据faceToken搜索人员信息)
	 * @param:        @param faceTokens
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2017-11-25
	 */
	@RequestMapping(value = "/selectListByFaceTokens.do")
	@ResponseBody
	public List<Map<String,Object>> selectListByFaceTokens(String faceTokens) throws Exception{
		String[] p=faceTokens.split(",");

		return this.personService.selectListByFaceTokens(p);
	}
}
