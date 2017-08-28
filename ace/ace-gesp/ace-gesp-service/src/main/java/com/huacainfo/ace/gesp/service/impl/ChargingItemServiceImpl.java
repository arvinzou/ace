package com.huacainfo.ace.gesp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.gesp.model.ChargingItem;
import com.huacainfo.ace.gesp.service.ChargingItemService;
import com.huacainfo.ace.gesp.service.PayCfgService;
import com.huacainfo.ace.gesp.service.RegService;
import com.huacainfo.ace.gesp.vo.ChargingItemVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gesp.dao.ChargingItemDao;
import com.huacainfo.ace.gesp.vo.ChargingItemQVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("chargingItemService")
public class ChargingItemServiceImpl implements ChargingItemService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChargingItemDao chargingItemDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private PayCfgService payCfgService;
	@Autowired
	private RegService regService;
	
	public PageResult<ChargingItemVo> findChargingItemList(
			ChargingItemQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<ChargingItemVo> rst = new PageResult<ChargingItemVo>();
		List<ChargingItemVo> list = this.chargingItemDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.chargingItemDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertChargingItem(ChargingItem o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		o.setMemberCode(userProp.getCorpId());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "收费项目名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			o.setStatus("0");
		}
		if(CommonUtils.isBlank(o.getPid())){
			o.setPid("0");
		}
		int temp = this.chargingItemDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "收费项目名称重复！");
		}
		ChargingItem recod = new ChargingItem();
		recod.setItemType("1");
		recod.setMemberCode(o.getMemberCode());
		List<Map<String, Object>> list = this.chargingItemDao.isExitItemType(recod);
		if(o.getItemType().equals("1")){
			if(list.size()>0){
				return new MessageResponse(1, "会费不可以重复添加！");
			}
		}
		if(CommonUtils.isBlank(o.getWhetherCustom())){
			o.setWhetherCustom("0");
		}
		if(CommonUtils.isBlank(o.getWhetherMemberlevel())){
			o.setWhetherMemberlevel("0");
		}
		if(CommonUtils.isBlank(o.getWhetherPeriod())||"0".equals(o.getWhetherPeriod())){
			o.setWhetherPeriod("0");
			o.setYear(0);
			o.setTimes("0");
			o.setPeriod("1");
		}
		
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.chargingItemDao.insert(o);
		this.dataBaseLogService.log("添加收费项目", "收费项目", "", "收费项目名称："+o.getName()+",是否按会员等级收费:"+regService.selectDicBy("82", o.getWhetherMemberlevel()).get("name").toString()
				+",是否按周期收费:"+regService.selectDicBy("82", o.getWhetherPeriod()).get("name").toString()+",收费周期："+o.getYear()+
				regService.selectDicBy("104",o.getPeriod()).get("name").toString()+o.getTimes()+"次",
				o.getName(), userProp);
		return new MessageResponse(0, "添加收费项目完成！");
	}

	public MessageResponse updateChargingItem(ChargingItem o, UserProp userProp)
			throws Exception {
		o.setMemberCode(userProp.getCorpId());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "收费项目名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			o.setStatus("0");
		}
		if(CommonUtils.isBlank(o.getPid())){
			o.setPid("0");
		}
		ChargingItem recod = new ChargingItem();
		recod.setItemType("1");
		recod.setMemberCode(o.getMemberCode());
		List<Map<String, Object>> list = this.chargingItemDao.isExitItemType(recod);
		if(list.size()>0){
			for (Map<String, Object> map : list) {
				if(!map.get("id").equals(o.getId())&&"1".equals(o.getItemType())){
					return new MessageResponse(1, "不能修改成会费，在项目中只能有一个会费！");
				}
			}
		}
		if(CommonUtils.isBlank(o.getWhetherCustom())){
			o.setWhetherCustom("0");
		}
		if(CommonUtils.isBlank(o.getWhetherMemberlevel())){
			o.setWhetherMemberlevel("0");
		}
		if(CommonUtils.isBlank(o.getWhetherPeriod())||"0".equals(o.getWhetherPeriod())){
			o.setWhetherPeriod("0");
			o.setYear(0);
			o.setTimes("0");
			o.setPeriod("1");
		}
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		
		ChargingItem c = this.chargingItemDao.selectByPrimaryKey(o.getId());
		logger.debug("收费项目：{},{}", c,c.getPeriod());
		String period = regService.selectDicBy("104",c.getPeriod()).get("name").toString();
		this.chargingItemDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更收费项目", "收费项目", "收费项目:"+c.getName()+",是否按会员等级收费:"+regService.selectDicBy("82", c.getWhetherMemberlevel()).get("name").toString()
				+",是否按周期收费:"+regService.selectDicBy("82", c.getWhetherPeriod()).get("name").toString()+
				",收费周期："+c.getYear()+period+c.getTimes()+"次", 
				"收费项目:"+o.getName()+",是否按会员等级收费:"+regService.selectDicBy("82", o.getWhetherMemberlevel()).get("name")
				+",是否按周期收费:"+regService.selectDicBy("82", o.getWhetherPeriod()).get("name").toString()+
				",收费周期："+o.getYear()+regService.selectDicBy("104",o.getPeriod()).get("name").toString()+o.getTimes()+"次,编号:"+o.getId(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更收费项目完成！");
	}

	public SingleResult<ChargingItem> selectChargingItemByPrimaryKey(String id)
			throws Exception {
		SingleResult<ChargingItem> rst = new SingleResult<ChargingItem>();
		rst.setValue(this.chargingItemDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteChargingItemByChargingItemId(String id,
			UserProp userProp) throws Exception {
		List<Map<String, Object>> list = this.payCfgService.selectPayCfgByChargingItem(id,userProp.getParentCorpId());
		if(list.size()>0){
			return new MessageResponse(0, "收费项目已应用,不可删除！");
		}
		ChargingItem c = this.chargingItemDao.selectByPrimaryKey(id);
		this.chargingItemDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除收费项目", "收费项目", "项目名称："+c.getName()+",是否按会员等级收费:"+regService.selectDicBy("82", c.getWhetherMemberlevel()).get("name").toString()
				+",是否按周期收费:"+regService.selectDicBy("82", c.getWhetherPeriod()).get("name").toString()+
				",收费周期："+c.getYear()+regService.selectDicBy("104",c.getPeriod()).get("name").toString()+c.getTimes()+"次,编号："+c.getId(),"", "收费项目", userProp);
		return new MessageResponse(0, "收费项目删除完成！");
	}

	public ListResult<Map<String,Object>> selectListByDeptId(String deptId,String selected) throws Exception {
		ListResult<Map<String,Object>> rst = new ListResult<Map<String,Object>>();
		rst.setValue(this.chargingItemDao.selectListByDeptId( deptId));
		if(CommonUtils.isNotBlank(selected)&&selected.equals("true")){
			for (Map<String,Object> dict : rst.getValue()) {
				dict.put("selected",true);
				dict.put("code",dict.get("code"));
				break;
			}
		}else if(CommonUtils.isNotBlank(selected)&&"false".equals(selected)){
			Map<String,Object> e=new HashMap<String,Object>();
			e.put("code","");
			e.put("name","--全部--");
			//e.put("selected",true);
			rst.getValue().add(0, e);
		}
		return rst;
	}
	
	public List<Tree> selectChargingItemList(String id,UserProp userProp,String flag) throws Exception {
		if(CommonUtils.isBlank(id)){
			id="0";
		}
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
				this.chargingItemDao.selectChargingItemTreeList(id,userProp.getCorpId()));
		return commonTreeUtils.getTreeList(id);
	}

}
