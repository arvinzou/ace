package com.huacainfo.ace.gesp.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.huacainfo.ace.gesp.dao.MemberInfoDao;
import com.huacainfo.ace.gesp.dao.RegDao;
import com.huacainfo.ace.gesp.model.Department;
import com.huacainfo.ace.gesp.model.MemberInfo;
import com.huacainfo.ace.gesp.model.MemberLevel;
import com.huacainfo.ace.gesp.service.MemberInfoService;
import com.huacainfo.ace.gesp.vo.MemberInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("memberInfoService")
public class MemberInfoServiceImpl implements MemberInfoService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberInfoDao memberInfoDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private com.huacainfo.ace.gesp.dao.MemberLevelDao MemberLevelDao;
	
	@Autowired
	private RegDao regDao;

	public PageResult<MemberInfoVo> findMemberInfoList(MemberInfoQVo condition,
                                                       int start, int limit, String orderBy) throws Exception {
		PageResult<MemberInfoVo> rst = new PageResult<MemberInfoVo>();
		List<MemberInfoVo> list = this.memberInfoDao.findList(condition, start,
				start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.memberInfoDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertMemberInfo(MemberInfo o, UserProp userProp)
			throws Exception {
		o.setMemberLevel("1");
		//o.setMemberCode(userProp.getCorpId());
		/*if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "id不能为空！");
		}*/
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员证号不能为空！");
		}
		int temp = this.memberInfoDao.isExit(o.getMemberCode());
		if (temp > 0) {
			return new MessageResponse(1, "重复申请！");
		}
		o.setJoinDate(new Date());
		o.setEndDate(new Date());
		o.setCreateUserId(userProp.getUserId());
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getName());
		this.memberInfoDao.insert(o);
		Department d = this.regDao.selectDepartInfoByPrimaryKey(o.getMemberCode());
		MemberLevel m = this.MemberLevelDao.selectByPrimaryKey(o.getMemberLevel());
		this.dataBaseLogService.log("申请成为会员", "会员", "", "会员证号："+o.getMemberNo()!=null?o.getMemberNo():""+",会员单位："+
				m!=null?m.getName():""+",企业编号:"+d.getDepartmentId(),
				d.getDepartmentName(), userProp);
		return new MessageResponse(0, "申请会员完成！");
	}
	public MessageResponse insertMemberInfoByAdmin(MemberInfo o, UserProp userProp)
			throws Exception {
		String id=String.valueOf(new Date().getTime());
		o.setId(id);
		if (CommonUtils.isBlank(o.getMemberLevel())) {
			return new MessageResponse(1, "会员级别不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberNo())) {
			return new MessageResponse(1, "会员证号不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		int temp = this.memberInfoDao.isExit(o.getMemberCode());
		if (temp > 0) {
			return new MessageResponse(1, "申请重复！");
		}
		int t =this.memberInfoDao.isExitMemberNo(o.getMemberNo(), userProp.getCorpId(),o.getId());
		if(t>0){
			return new MessageResponse(1, "会员证号重复！");
		}
		o.setStatus("1");
		o.setAuditDate(new Date());
		o.setAuditUserId(userProp.getUserId());
		o.setAuditUserName(userProp.getName());
		o.setJoinDate(new Date());
		o.setEndDate(new Date());
		o.setCreateUserId(userProp.getUserId());
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getName());
		this.memberInfoDao.insert(o);
		Department d = this.regDao.selectDepartInfoByPrimaryKey(o.getMemberCode());
		MemberLevel m = this.MemberLevelDao.selectByPrimaryKey(o.getMemberLevel());
		this.dataBaseLogService.log("申请成为会员", "会员", "", "会员证号："+o.getMemberNo()+",会员单位："+m.getName()+",企业编号:"+o.getMemberCode(),
				d.getDepartmentName(), userProp);
		return new MessageResponse(0, "申请会员完成！");
	}


	public MessageResponse updateAudit(MemberInfo o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "id不能为空！");
		}
		if(CommonUtils.isBlank(o.getJoinDate())){
			o.setJoinDate(new Date());
		}
		Calendar c = Calendar.getInstance(); 
		c.add(Calendar.YEAR, 1);
		Date date = c.getTime();
		o.setEndDate(date);
		o.setAuditDate(new Date());
		o.setAuditUserId(userProp.getUserId());
		o.setAuditUserName(userProp.getName());
		this.memberInfoDao.updateByPrimaryKey(o);
		Department d = this.regDao.selectDepartInfoByPrimaryKey(o.getMemberCode());
		MemberLevel m = this.MemberLevelDao.selectByPrimaryKey(o.getMemberLevel());
		this.dataBaseLogService.log("会员审核", "会员", "", "会员证号："+o.getMemberNo()!=null?o.getMemberNo():""+",会员单位："+m!=null?m.getName():""+",企业编号:"+o.getMemberCode()!=null?o.getMemberCode():"",
				d!=null?d.getDepartmentName():"", userProp);
		return new MessageResponse(0, "审核成功！");
	}
	
	public SingleResult<MemberInfo> selectMemberInfoByPrimaryKey(String id)
			throws Exception {
		SingleResult<MemberInfo> rst = new SingleResult<MemberInfo>();
		rst.setValue(this.memberInfoDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteMemberInfoByMemberInfoId(String id,
			UserProp userProp) throws Exception {
		this.memberInfoDao.deleteByPrimaryKey(id);
		MemberInfo m = this.memberInfoDao.selectByPrimaryKey(id);
		Department d = this.regDao.selectDepartInfoByPrimaryKey(m.getMemberCode());
		this.dataBaseLogService.log("删除会员", "会员", "会员名称:"+d.getDepartmentName()+",会员等级:"+this.MemberLevelDao.selectByPrimaryKey(m.getMemberLevel()).getName()+
				",会员编号:"+m.getId(),
				d.getDepartmentName(), "会员", userProp);
		return new MessageResponse(0, "会员删除完成！");
	}
	public  SingleResult<Department> loadMemberBaseInfo(String id,String parentDepartmentId) throws Exception{
		SingleResult<Department> rst = new SingleResult<Department>();
		rst.setValue(this.regDao.selectByPrimaryKey(id,parentDepartmentId));
		return rst;
	}
	
	/**
	 * 会员审计
	 */
	public MessageResponse updateByPrimaryKeySelective(Department o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getDepartmentId())) {
			return new MessageResponse(1, "id不能为空！");
		}
		this.regDao.updateByPrimaryKeySelective(o);
		Department d = this.regDao.selectDepartInfoByPrimaryKey(o.getDepartmentId());
		this.dataBaseLogService.log("变更企业信息", "企业信息", "企业名称："+d.getDepartmentName()+",<br>营业执照号："+d.getBussLicenseNo(),  
				"企业名称："+o.getDepartmentName()+",<br>营业执照号："+o.getBussLicenseNo(),
				o.getDepartmentName(), userProp);
		return new MessageResponse(0, "成功！");
	}
	
	public MessageResponse updateMemberBaseByPrimaryKey(MemberInfo o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "id不能为空！");
		}
		if(CommonUtils.isNotBlank(o.getMemberNo())){
			int t =this.memberInfoDao.isExitMemberNo(o.getMemberNo(), userProp.getCorpId(),o.getId());
			if(t>0){
				return new MessageResponse(1, "会员证号重复！");
			}
		}
		if (CommonUtils.isBlank(o.getMemberLevel())) {
			return new MessageResponse(1, "会员级别不能为空！");
		}
		String sta = o.getStatus();
		if("20".equals(sta)){
			o.setStatus("1");
		}
		o.setAuditDate(new Date());
		o.setAuditUserId(userProp.getCorpId());
		o.setAuditUserName(userProp.getCorpName());
		this.memberInfoDao.updateMemberBaseByPrimaryKey(o);
		MemberInfo m = this.memberInfoDao.selectByPrimaryKey(o.getId());
		Department d = this.regDao.selectDepartInfoByPrimaryKey(o.getMemberCode());
		this.dataBaseLogService.log("会员信息", "会员", "入会时间："+CommonUtils.formatDate(m.getJoinDate())+",<br>会员证号:"+m.getMemberNo()!=null?m.getMemberNo():""+
				",<br>会员到期时间:"+CommonUtils.formatDate(m.getEndDate())+",<br>会员等级:"+MemberLevelDao.selectByPrimaryKey(m.getMemberLevel())!=null?MemberLevelDao.selectByPrimaryKey(m.getMemberLevel()).getName():"", 
				 "入会时间："+o.getJoinDate()!=null?CommonUtils.formatDate(o.getJoinDate()):""+",<br>会员证号:"+o.getMemberNo()+",<br>会员到期时间:"+CommonUtils.formatDate(o.getEndDate())+
				 ",<br>会员等级:"+MemberLevelDao.selectByPrimaryKey(o.getMemberLevel())!=null?MemberLevelDao.selectByPrimaryKey(o.getMemberLevel()).getName():"",
				d.getDepartmentName(), userProp);
		return new MessageResponse(0, "成功！");
	}

	
	/**
	 * 会员退会
	 * 
	 * @param o 会员信息对象
	 * @param curUserProp
	 * @return MessageResponse
	 */
	public MessageResponse updateState(MemberInfo o, UserProp userProp) {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "id不能为空！");
		}
		o.setRemark(new Date()+"退会");
		this.memberInfoDao.updateState(o);
		MemberInfo m = this.memberInfoDao.selectByPrimaryKey(o.getId());
		Department d = this.regDao.selectDepartInfoByPrimaryKey(m.getMemberCode());
		this.dataBaseLogService.log("会员退会", "会员", "", d.getDepartmentName(),
				d.getDepartmentName(), userProp);
		return new MessageResponse(0, "退会成功！");
	}

}
