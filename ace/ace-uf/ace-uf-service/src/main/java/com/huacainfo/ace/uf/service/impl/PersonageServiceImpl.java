package com.huacainfo.ace.uf.service.impl;

import java.util.*;

import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.model.view.CheckTree;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CheckTreeUtils;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.uf.model.interest;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.uf.dao.PersonageDao;
import com.huacainfo.ace.uf.model.Personage;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.service.PersonageService;
import com.huacainfo.ace.uf.vo.PersonageVo;
import com.huacainfo.ace.uf.vo.PersonageQVo;
@Service("personageService")
public class PersonageServiceImpl implements PersonageService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PersonageDao personageDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Override
	public PageResult<PersonageVo> findPersonageList(PersonageQVo condition, int start, int limit, String orderBy)
			throws Exception {
		condition.setCategory(this.getDictCodeBYId(condition.getCategory()));
		PageResult<PersonageVo> rst = new PageResult<PersonageVo>();
		List<PersonageVo> list = this.personageDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.personageDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	@Override
	public MessageResponse insertPersonage(Personage o, UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getAreaCode())) {
			return new MessageResponse(1, "所属辖区不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "类别不能为空！");
		}
		o.setCategory(this.getDictCodeBYId(o.getCategory()));
		if (CommonUtils.isBlank(o.getCareerType())) {
			//return new MessageResponse(1, "职业类别不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "姓名不能为空！");
		}
		if (CommonUtils.isBlank(o.getSex())) {
			return new MessageResponse(1, "性别不能为空！");
		}
		if (CommonUtils.isBlank(o.getBirthday())) {
			return new MessageResponse(1, "出生日期不能为空！");
		}
		if (CommonUtils.isBlank(o.getPlaceOfOrigin())) {
			return new MessageResponse(1, "籍贯不能为空！");
		}
		if (CommonUtils.isBlank(o.getNation())) {
			return new MessageResponse(1, "民族不能为空！");
		}

		int temp = this.personageDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "统战人士名称重复！");
		}
		o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.personageDao.insert(o);
		this.dataBaseLogService.log("添加统战人士", "统战人士", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "添加统战人士完成！");
	}


	@Override
	public MessageResponse updatePersonage(Personage o, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getAreaCode())) {
			return new MessageResponse(1, "所属辖区不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {

			return new MessageResponse(1, "类别不能为空！");
		}
		o.setCategory(this.getDictCodeBYId(o.getCategory()));
		if (CommonUtils.isBlank(o.getCareerType())) {
			//return new MessageResponse(1, "职业类别不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "姓名不能为空！");
		}
		if (CommonUtils.isBlank(o.getSex())) {
			return new MessageResponse(1, "性别不能为空！");
		}
		if (CommonUtils.isBlank(o.getBirthday())) {
			return new MessageResponse(1, "出生日期不能为空！");
		}
		if (CommonUtils.isBlank(o.getPlaceOfOrigin())) {
			return new MessageResponse(1, "籍贯不能为空！");
		}
		if (CommonUtils.isBlank(o.getNation())) {
			return new MessageResponse(1, "民族不能为空！");
		}

		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "状态不能为空！");
		}
		o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.personageDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更统战人士", "统战人士", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "变更统战人士完成！");
	}

	@Override
	public SingleResult<PersonageVo> selectPersonageByPrimaryKey(String id) throws Exception {
		SingleResult<PersonageVo> rst = new SingleResult<PersonageVo>();
		rst.setValue(this.personageDao.selectByPrimaryKey(id));
		return rst;
	}

	@Override
	public MessageResponse deletePersonageByPersonageId(String id, UserProp userProp) throws Exception {
		this.personageDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除统战人士", "统战人士", String.valueOf(id), String.valueOf(id), "统战人士", userProp);
		return new MessageResponse(0, "统战人士删除完成！");
	}

	@Override
	public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
		int i = 1;
		for (Map<String, Object> row : list) {
			Personage o = new Personage();
			CommonBeanUtils.copyMap2Bean(o, row);
			if(CommonUtils.isNotEmpty(o.getName())){
				o.setCreateDate(new Date());
				o.setStatus("1");
				o.setCreateUserName(userProp.getName());
				o.setCreateUserId(userProp.getUserId());
				this.logger.info(o.toString());
				o.setId(UUID.randomUUID().toString());

				if (CommonUtils.isBlank(o.getAreaCode())) {
					o.setAreaCode(userProp.getAreaCode());
				}
				if (CommonUtils.isBlank(o.getCategory())) {
					o.setCategory("01");
				}
				if (CommonUtils.isBlank(o.getBirthday())) {
					o.setBirthday(new Date());
				}
				if (CommonUtils.isBlank(o.getPlaceOfOrigin())) {
					o.setPlaceOfOrigin(userProp.getAreaCode());
				}
				if (CommonUtils.isBlank(o.getNation())) {
					o.setNation("01");
				}
				if (CommonUtils.isBlank(o.getAreaCode())) {
					return new MessageResponse(1, "行" + i + ",所属辖区不能为空！");
				}
				if (CommonUtils.isBlank(o.getCategory())) {
					return new MessageResponse(1, "行" + i + ",类别不能为空！");
				}
				if (CommonUtils.isBlank(o.getCareerType())) {
					//return new MessageResponse(1, "行" + i + ",职业类别不能为空！");
				}
				if (CommonUtils.isBlank(o.getName())) {
					return new MessageResponse(1, "行" + i + ",姓名不能为空！");
				}
				if (CommonUtils.isBlank(o.getSex())) {
					return new MessageResponse(1, "行" + i + ",性别不能为空！");
				}
				if (CommonUtils.isBlank(o.getBirthday())) {
					return new MessageResponse(1, "行" + i + ",出生日期不能为空！");
				}
				if (CommonUtils.isBlank(o.getPlaceOfOrigin())) {
					return new MessageResponse(1, "行" + i + ",籍贯不能为空！");
				}
				if (CommonUtils.isBlank(o.getNation())) {
					return new MessageResponse(1, "行" + i + ",民族不能为空！");
				}
				if (CommonUtils.isBlank(o.getMobile())) {
					return new MessageResponse(1, "行" + i + ",移动电话不能为空！");
				}
				o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
				int t = personageDao.isExit(o);
				if (t > 0) {
					this.personageDao.updateByPrimaryKey(o);
				} else {
					this.personageDao.insert(o);
				}
				i++;
			}

		}
		this.dataBaseLogService.log("统战人士导入", "统战人士", "", "rs.xls", "rs.xls", userProp);
		return new MessageResponse(0, "导入完成！");
	}

	@Override
	public List<Map<String,Object>> selectPersonageListMap(WxUser user,String longitude,String latitude,String q){
		return this.personageDao.selectPersonageList(longitude,latitude,q,user);
	}


	@Override
	public  List<Map<String,Object>> selectPersonageList(String q,WxUser user) throws Exception{

		List<Map<String,Object>> category=this.personageDao.selectPersonageCategoryList(user);
		List<Map<String,Object>> list=this.personageDao.selectPersonageListByText(q,user);
		int i=0;
		if(CommonUtils.isNotBlank(q)){
			category=new ArrayList<Map<String,Object>>();
			Map<String,Object> e=new HashedMap();
			e.put("id","01");
			e.put("name","搜索");
			e.put("icon","search.png");
			e.put("open",true);
			e.put("type","category");
			e.put("num",list.size());
			e.put("list",list);
			category.add(e);
			return category;
		}
		for(Map<String,Object> e:category){

			if(i==0){
				e.put("open",true);
			}else{
				e.put("open",false);
			}
			if(((String)e.get("id")).equals("01")){
				e.put("list",this.getGroup2((String) e.get("id"),list,user));
			}else{
				e.put("list",this.getGroup((String) e.get("id"),list));
			}

			i++;
		}
		return category;
	}
	private List<Map<String,Object>> getGroup(String category,List<Map<String,Object>> list){
		List<Map<String,Object>> rst=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> e:list){
			if(((String)e.get("category")).equals(category)){
				rst.add(e);
			}
		}
		return  rst;

	}
	private List<Map<String,Object>> getGroup2(String category,List<Map<String,Object>> list,WxUser user){
		List<Map<String,Object>> partyList=this.personageDao.selectPersonagePartyList(user);
		for(Map<String,Object> party:partyList){
			List<Map<String,Object>> p=new ArrayList<Map<String,Object>>();
			for(Map<String,Object> person:list){
				if(((String)person.get("category")).equals(category)){
					if(person.get("party")!=null){
						if(((String)person.get("party")).equals((String)party.get("id"))){
							p.add(person);
						}
					}
				}
			}
			party.put("list",p);
		}
		return partyList;
	}


	@Override
	public Map<String,Object> selectPersonageById(String id){
		return  this.personageDao.selectPersonageById(id);
	}


	@Override
	public Map<String, Object> selectPersonage(String q){
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.personageDao.selectPersonage(q);
		rst.put("total", list.size());
		rst.put("rows", list);
		return rst;
	}


	@Override
	public  List<Tree>  selectPersonageTreeList(String q,WxUser user) throws Exception{
		if(user.getCategory()!=null){
			if(user.getCategory().length()>2){
				user.setCategory(user.getCategory().substring(0,2));
			}
		}
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(this.personageDao.selectPersonageTreeList(q,user));
		return commonTreeUtils.getTreeList("0");
	}

	@Override
	public List<CheckTree> selectPersonageCheckTreeList() throws Exception{
		CheckTreeUtils commonTreeUtils = new CheckTreeUtils(this.personageDao.selectPersonageCheckTreeList());
		return commonTreeUtils.getCheckTreeList("0");
	}

	private String getDictCodeBYId(String id){
		/*if(CommonUtils.isBlank(id)){
			return null;
		}
		Map<String,String> p=this.personageDao.selectDictCodeBYId( id);
		if(CommonUtils.isNotEmpty(p)){
			return p.get("code");
		}*/
		return id;
	}

	@Override
	public int isExitPersonageByMobile(String mobile){
		return this.personageDao.isExitPersonageByMobile(mobile);
	}



	@Override
	public Map<String,Object> selectPersonageCfgById(String id){
		return  this.personageDao.selectPersonageCfgById(id);
	}



}
