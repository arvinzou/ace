package com.huacainfo.ace.gesp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.gesp.service.MemberPayInfoService;
import com.huacainfo.ace.gesp.vo.MemberPayInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberPayInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gesp.dao.ChargingItemDao;
import com.huacainfo.ace.gesp.dao.MemberInfoDao;
import com.huacainfo.ace.gesp.dao.MemberLevelDao;
import com.huacainfo.ace.gesp.dao.MemberPayInfoDao;
import com.huacainfo.ace.gesp.dao.RegDao;
import com.huacainfo.ace.gesp.model.ChargingItem;
import com.huacainfo.ace.gesp.model.Department;
import com.huacainfo.ace.gesp.model.MemberInfo;
import com.huacainfo.ace.gesp.model.MemberLevel;
import com.huacainfo.ace.gesp.model.MemberPayInfo;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("memberPayInfoService")
public class MemberPayInfoServiceImpl implements MemberPayInfoService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberPayInfoDao memberPayInfoDao;
	@Autowired
	private MemberInfoDao memberInfoDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private RegDao regDao;
	@Autowired
	private MemberLevelDao memberLevelDao;
	@Autowired
	private ChargingItemDao chargingItemDao;
	

	public PageResult<MemberPayInfoVo> findMemberPayInfoList(
            MemberPayInfoQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<MemberPayInfoVo> rst = new PageResult<MemberPayInfoVo>();
		List<MemberPayInfoVo> list = this.memberPayInfoDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.memberPayInfoDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertMemberPayInfo(MemberPayInfo o,
			UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		o.setCreateUserId(userProp.getUserId());
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getName());
		o.setStatus("0");
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getChargingItemId())) {
			return new MessageResponse(1, "收费项目不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberLevelId())) {
			return new MessageResponse(1, "缴费级别不能为空！");
		}
		if (CommonUtils.isBlank(o.getPayAmount())) {
			return new MessageResponse(1, "缴费金额不能为空！");
		}
		if (CommonUtils.isBlank(o.getPayDate())) {
			return new MessageResponse(1, "收费时间不能为空！");
		}
		if (CommonUtils.isBlank(o.getEndDate())) {
			return new MessageResponse(1, "到期日期不能为空！");
		}
		MemberInfo memberInfo=this.memberInfoDao.selectByMemberCode(o.getMemberCode());
		o.setOrginEndDate(memberInfo.getEndDate());
		o.setOrginMemberLevel(memberInfo.getMemberLevel());
		this.memberPayInfoDao.insert(o);
		ChargingItem c = chargingItemDao.selectByPrimaryKey(o.getChargingItemId());
		Department d = this.regDao.selectDepartInfoByPrimaryKey(o.getMemberCode());
		this.dataBaseLogService.log("缴费", "缴费", "", "会员名称:"+d.getDepartmentName()+",收费项目:"+c.getName()+
				",会员级别:"+memberLevelDao.selectByPrimaryKey(memberInfo.getMemberLevel()).getName()+",截止日期："+memberInfo.getEndDate()+",缴费金额:"+o.getPayAmount(),
				d.getDepartmentName(), userProp);
		return new MessageResponse(0, "收款完成！");
	}

	public MessageResponse updateMemberPayInfo(MemberPayInfo o,
			UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "id不能为空！");
		}
		if (CommonUtils.isBlank(o.getEndDate())) {
			return new MessageResponse(1, "到期日期不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberLevelId())) {
			return new MessageResponse(1, "缴费级别不能为空！");
		}
		if (CommonUtils.isBlank(o.getPayAmount())) {
			return new MessageResponse(1, "缴费金额不能为空！");
		}
		MemberPayInfo m = this.memberPayInfoDao.selectByPrimaryKey(o.getId());
		MemberLevel me = memberLevelDao.selectByPrimaryKey(m.getMemberLevelId());
		this.memberPayInfoDao.updateByPrimaryKey(o);
		Department d = this.regDao.selectDepartInfoByPrimaryKey(o.getMemberCode());
		this.dataBaseLogService.log("变更缴费", "缴费",  "会员名称:"+d.getDepartmentName()+",收费项目:"+m.getChargingItemId()+","
				+ "会员级别:"+me.getName()+",截止日期："+m.getEndDate()+",缴费金额:"+m.getPayAmount(),
				"会员名称:"+d.getDepartmentName()+",收费项目:"+m.getChargingItemName()+","
				+ "会员级别:"+memberLevelDao.selectByPrimaryKey(o.getMemberLevelId()).getName()+",截止日期："+o.getEndDate()+",缴费金额:"+o.getPayAmount(),
				d.getDepartmentName(), userProp);
		return new MessageResponse(0, "变更缴费完成！");
	}

	public SingleResult<MemberPayInfo> selectMemberPayInfoByPrimaryKey(String id)
			throws Exception {
		SingleResult<MemberPayInfo> rst = new SingleResult<MemberPayInfo>();
		rst.setValue(this.memberPayInfoDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteMemberPayInfoByMemberPayInfoId(String id,
			UserProp userProp) throws Exception {
		MemberPayInfo o=this.memberPayInfoDao.selectByPrimaryKey(id);
		this.memberPayInfoDao.deleteByPrimaryKey(id,o.getOrginEndDate(),o.getOrginMemberLevel());
		MemberPayInfo record=new MemberPayInfo();
		record.setMemberCode(o.getMemberCode());
		record.setMemberLevelId(o.getOrginMemberLevel());
		record.setEndDate(o.getOrginEndDate());
		if(o.getStatus().equals("1")){
			this.memberPayInfoDao.updateMemberPayInfo(record);
		}
		this.dataBaseLogService.log("缴费冲正", "缴费", "编号:"+String.valueOf(id),
				"已冲正", "缴费", userProp);
		return new MessageResponse(0, "冲正完成！");
	}

	public MessageResponse updateAudit(MemberPayInfo o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "id不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "审核状态不能为空！");
		}
		o.setAuditDate(new Date());
		o.setAuditUserId(userProp.getUserId());
		o.setAuditUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		MemberPayInfo e=this.memberPayInfoDao.selectByPrimaryKey(o.getId());
		Department d = this.regDao.selectDepartInfoByPrimaryKey(e.getMemberCode());
		this.memberPayInfoDao.updateAudit(o);
		String flag = "审核失败";
		if(o.getStatus().equals("1")){
			this.memberPayInfoDao.updateMemberPayInfo(e);
			flag = "审核成功";
			MemberInfo m = new MemberInfo();
			m.setId(e.getMemberCode());
			m.setMemberLevel(e.getMemberLevelId());
			m.setEndDate(e.getEndDate());
			memberInfoDao.updateMemberBaseByPrimaryKey(m);
			this.dataBaseLogService.log("修改会员信息", "会员信息", "会员等级:"+memberLevelDao.selectByPrimaryKey(e.getMemberLevelId()).getName(), flag,
					d.getDepartmentName(), userProp);
		}
		this.dataBaseLogService.log("收款审核", "会员", "收费项目名称:"+e.getChargingItemName(), flag,
				d.getDepartmentName(), userProp);
		return new MessageResponse(0, "审核成功！");
	}
	
	public  List<Map<String,Object>> selectAnaysePayMentByMonth( String year,String deptId,String deptIdchargingItemId) throws Exception{
		return this.memberPayInfoDao.selectAnaysePayMentByMonth(year, deptId, deptIdchargingItemId);
	}

	
	/**
	 * 根据编号查询费用信息
	 * 
	 * @param id 编号
	 * @return MemberPayInfo
	 * @throws Exception
	 */
	public MemberPayInfo selectByPrimarkey(String id) throws Exception {
		return this.memberPayInfoDao.selectByPrimaryKey(id);
	}


	/**
	 * 判断费用没有收取时是否重复收
	 * 
	 * 已引用（会费收取时判断）
	 * @param memberCode 企业编号
	 * @param chargingItemId 收费项目编号
	 * @param status 审核状态
	 * @return MessageResponse
	 */
	public MessageResponse isExitPayInfo(String memberCode, String chargingItemId, String status) throws Exception {
		int result = this.memberPayInfoDao.isExitPayInfo(memberCode,chargingItemId,status);
		if(result>0){
			return new MessageResponse(1, "请在收费审核中审核之后在收取费用");
		}
		return new MessageResponse(0, "");
	}
	
	
}
