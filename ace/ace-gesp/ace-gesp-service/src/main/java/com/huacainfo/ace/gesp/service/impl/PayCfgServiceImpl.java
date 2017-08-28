package com.huacainfo.ace.gesp.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.gesp.dao.MemberInfoDao;
import com.huacainfo.ace.gesp.dao.MemberLevelDao;
import com.huacainfo.ace.gesp.model.ChargingItem;
import com.huacainfo.ace.gesp.vo.PayCfgQVo;
import com.huacainfo.ace.gesp.vo.PayCfgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gesp.dao.ChargingItemDao;
import com.huacainfo.ace.gesp.dao.PayCfgDao;
import com.huacainfo.ace.gesp.model.MemberInfo;
import com.huacainfo.ace.gesp.model.MemberLevel;
import com.huacainfo.ace.gesp.model.PayCfg;
import com.huacainfo.ace.gesp.service.MemberLevelService;
import com.huacainfo.ace.gesp.service.PayCfgService;
import com.huacainfo.ace.gesp.service.RegService;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("payCfgService")
public class PayCfgServiceImpl implements PayCfgService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PayCfgDao payCfgDao;
	@Autowired
	private MemberInfoDao memberInfoDao;
	@Autowired
	private MemberLevelDao memberLevelDao;
	@Autowired
	private ChargingItemDao chargingItemDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private RegService regService;
	@Autowired
	private MemberLevelService memberLevelService;

	public PageResult<PayCfgVo> findPayCfgList(PayCfgQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
		PageResult<PayCfgVo> rst = new PageResult<PayCfgVo>();
		List<PayCfgVo> list = this.payCfgDao.findList(condition, start, start
				+ limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.payCfgDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	/* (non-Javadoc)
	 * @see PayCfgService#insertPayCfg(PayCfg, com.huacainfo.ace.common.model.UserProp)
	 */
	public MessageResponse insertPayCfg(PayCfg o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		o.setMemberCode(userProp.getCorpId());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		/*if (CommonUtils.isBlank(o.getMemberLevelId())) {
			return new MessageResponse(1, "收费级别ID不能为空！");
		}*/
		if (CommonUtils.isBlank(o.getChargingItemId())) {
			return new MessageResponse(1, "收费项目不能为空！");
		}
		/*if (CommonUtils.isBlank(o.getPayType())) {
			return new MessageResponse(1, "收费方式（1年度2月份3季度）不能为空！");
		}*/
		if (CommonUtils.isBlank(o.getPayNum())) {
			return new MessageResponse(1, "收费金额不能为空！");
		}
		List<Map<String, Object>> temp = this.payCfgDao.isExit(o);
		if (temp.size() > 0) {
			return new MessageResponse(1, "收费项目不可重复添加！");
		}
		o.setCreateUserId(userProp.getUserId());
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.payCfgDao.insert(o);
		MemberLevel m = new MemberLevel();
		if(CommonUtils.isBlank(o.getMemberLevelId())){
			m.setName("");
		}else{
			m = memberLevelDao.selectByPrimaryKey(o.getMemberLevelId());
			m.setName(",会员级别:"+m.getName());
		}
		this.dataBaseLogService.log("添加收费配置", "收费配置", "", "收费项目:"+chargingItemDao.selectByPrimaryKey(o.getChargingItemId()).getName()+m.getName()+",收费额度:"+o.getPayNum()+",编号:"+o.getId(),
				m.getName(), userProp);
		return new MessageResponse(0, "添加收费配置完成！");
	}

	public MessageResponse updatePayCfg(PayCfg o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "不能为空！");
		}
		o.setMemberCode(userProp.getCorpId());

		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		/*if (CommonUtils.isBlank(o.getMemberLevelId())) {
			return new MessageResponse(1, "收费级别ID不能为空！");
		}*/
		if (CommonUtils.isBlank(o.getChargingItemId())) {
			return new MessageResponse(1, "收费项目不能为空！");
		}
		/*if (CommonUtils.isBlank(o.getPayType())) {
			return new MessageResponse(1, "收费方式（1年度2月份3季度）不能为空！");
		}*/
		if (CommonUtils.isBlank(o.getPayNum())) {
			return new MessageResponse(1, "收费金额不能为空！");
		}
		List<Map<String, Object>> temp = this.payCfgDao.isExit(o);
		if (temp.size() > 0) {
			for (Map<String, Object> map : temp) {
				if(!o.getId().equals(map.get("id"))){
					return new MessageResponse(1, "收费项目重复！");
				}
			}
		}
		
		o.setLastModifyUserId(userProp.getUserId());
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		PayCfg p = payCfgDao.selectByPrimaryKey(o.getId());
		MemberLevel s = new MemberLevel();
		if(CommonUtils.isBlank(p.getMemberLevelId())){
			s.setName("");
		}else{
			s = memberLevelDao.selectByPrimaryKey(p.getMemberLevelId());
			s.setName(",会员级别:"+s.getName());
		}
		this.payCfgDao.updateByPrimaryKey(o);
		MemberLevel m = new MemberLevel();
		if(CommonUtils.isBlank(o.getMemberLevelId())){
			m.setName("");
		}else{
			m = memberLevelDao.selectByPrimaryKey(o.getMemberLevelId());
			m.setName(",会员级别:"+m.getName());
		}
		this.dataBaseLogService.log("变更收费配置", "收费配置", "收费项目:"+chargingItemDao.selectByPrimaryKey(p.getChargingItemId()).getName()+
				s.getName()+",收费额度:"+p.getPayNum(), 
				"收费项目:"+chargingItemDao.selectByPrimaryKey(o.getChargingItemId()).getName()+m.getName()+",收费额度:"+o.getPayNum(),
				m.getName(), userProp);
		return new MessageResponse(0, "变更收费配置完成！");
	}

	public SingleResult<PayCfg> selectPayCfgByPrimaryKey(String id)
			throws Exception {
		SingleResult<PayCfg> rst = new SingleResult<PayCfg>();
		rst.setValue(this.payCfgDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deletePayCfgByPayCfgId(String id, UserProp userProp)
			throws Exception {
		PayCfg p = payCfgDao.selectByPrimaryKey(id);
		MemberLevel m = new MemberLevel();
		if(CommonUtils.isBlank(p.getMemberLevelId())){
			m.setName("");
		}else{
			m = memberLevelDao.selectByPrimaryKey(p.getMemberLevelId());
			m.setName(",会员级别:"+m.getName());
		}
		this.payCfgDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除收费配置", "收费配置", "收费项目:"+chargingItemDao.selectByPrimaryKey(p.getChargingItemId()).getName()+m.getName()+",收费额度:"+p.getPayNum(),
				String.valueOf(id), m.getName(), userProp);
		return new MessageResponse(0, "收费配置删除完成！");
	}

	public List<PayCfg> selectPayCfgList(String memberCode) throws Exception {
		return this.payCfgDao.selectPayCfgList(memberCode);
	}

	public SingleResult<Map<String, Object>> getPayInfo(String memberCode,String memberLevelId,String chargingItemId,String deptId) throws Exception {
		SingleResult<Map<String, Object>> rst = new SingleResult<Map<String, Object>>();
		Map<String, Object> p = new HashMap<String, Object>();

		MemberInfo memberInfo = memberInfoDao.selectByMemberCode(memberCode);
		if (CommonUtils.isBlank(memberInfo)) {
			return new SingleResult<Map<String, Object>>(1, "会员信息不存在！");
		}
		if (CommonUtils.isBlank(memberLevelId)) {
			memberLevelId = memberInfo.getMemberLevel();
		}
		PayCfg o = this.payCfgDao.selectByCodeAndMemberCode(memberLevelId, memberCode, chargingItemId,deptId);
		if (CommonUtils.isBlank(o)) {
			return new SingleResult<Map<String, Object>>(1, "请在收费配置中设置收费金额！");
		}
		Date endDate = memberInfo.getEndDate();
		if (CommonUtils.isBlank(endDate)) {
			endDate = new Date();
		}

		ChargingItem c = this.chargingItemDao.selectByPrimaryKey(o.getChargingItemId());
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(endDate);

		/*switch (o.getPayType()) {
		case "1":
			rightNow.add(Calendar.YEAR, 1);
			break;
		case "2":
			rightNow.add(Calendar.MONTH, 1);
			break;
		case "3":
			rightNow.add(Calendar.MONTH, 3);
			break;
		default:
			rightNow.add(Calendar.YEAR, 1);
		}*/
		switch (c.getPeriod()) {
		case "1":
			rightNow.add(Calendar.YEAR, 1);//年
			break;
		case "2":
			rightNow.add(Calendar.MONTH, 3);//季度
			break;
		case "3":
			rightNow.add(Calendar.MONTH, 1);//月
			break;
		case "4":
			rightNow.add(Calendar.DATE, 7);//周
			break;
		case "5":
			rightNow.add(Calendar.DATE, 1);//天
			break;
		default:
			rightNow.add(Calendar.YEAR, 1);
		}
		p.put("memberLevelId", memberLevelId);
		p.put("chargingItemId", o.getChargingItemId());
		p.put("payType", o.getPayType());
		p.put("payAmount", o.getPayNum());
		p.put("endDate", rightNow.getTime());
		p.put("name", o.getId());
		rst.setValue(p);
		return rst;
	}

	
	/**
	 * 根据收费项目编号查询
	 * 
	 * (收费项目删除时，判断收费配置是否引用)
	 * @param chargingItemId 收费项目编号
	 * @param deptId 协会编号
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectPayCfgByChargingItem(String chargingItemId, String memberCode) {
		PayCfg record = new PayCfg();
		record.setChargingItemId(chargingItemId);
		record.setMemberCode(memberCode);
		return this.payCfgDao.isExit(record);
	}

	
	/**
	 * 校验收费配置是否配置完成
	 * 
	 * (收费处理中已引用)
	 * 
	 * @param memberCode
	 * @param chargingItemId
	 * @return MessageResponse
	 */
	public MessageResponse selectCount(String memberCode, String chargingItemId) throws Exception {
		List<Map<String, Object>> pList = this.payCfgDao.selectByChargindItemAndMemberCode(memberCode, chargingItemId);
		if("1".equals(chargingItemId)){
			List<Map<String, Object>> list = memberLevelService.selectListByDeptId(memberCode, null).getValue();
			logger.debug("pList:{}, list:{}", pList, list);
			if(list.size()!=pList.size()){
				return new MessageResponse(1, "会费在收费配置中还未配置完成！");
			}
		}
		return new MessageResponse(0, "已配置完成！");
	}
}
