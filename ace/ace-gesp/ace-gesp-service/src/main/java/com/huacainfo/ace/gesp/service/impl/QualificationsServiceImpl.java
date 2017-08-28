package com.huacainfo.ace.gesp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.gesp.dao.QualificationsDao;
import com.huacainfo.ace.gesp.dao.RegDao;
import com.huacainfo.ace.gesp.model.Department;
import com.huacainfo.ace.gesp.model.Qualifications;
import com.huacainfo.ace.gesp.service.QualificationsService;
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
import com.huacainfo.ace.gesp.vo.QualificationsQVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.FilesService;


@Service("qualificationsService")
public class QualificationsServiceImpl implements QualificationsService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired 
	private QualificationsDao qualificationsDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private FilesService filesService;
	@Autowired
	private RegDao regDao;
	
	public PageResult<Map<String,Object>> findQualificationsList(
			QualificationsQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<Map<String,Object>> rst = new PageResult<Map<String,Object>>();
		List<Map<String,Object>> list = this.qualificationsDao.findList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.qualificationsDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertQualifications(Qualifications o, UserProp userProp) throws Exception {
		o.setDeptId(userProp.getCorpId());
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "资质类型不能为空！");
		}
		int temp = this.qualificationsDao.isExit(o);
		if (temp > 0) {
			Qualifications e=this.qualificationsDao.selectByDeptIdAndCategory(userProp.getCorpId(), o.getCategory());
			e.setFileSrc(o.getFileSrc());
			e.setLastModifyTime(new Date());
			e.setStatus("1");
			e.setLastModifyUserName(userProp.getCorpName());
			e.setLastModifyUserId(userProp.getUserId());
			this.qualificationsDao.updateByPrimaryKey(e);
			this.dataBaseLogService.log("更新资质", "资质", "", o.getCategory(),
					o.getCategory(), userProp);
		}else{
			o.setId(UUID.randomUUID().toString());
			o.setCreateTime(new Date());
			o.setStatus("1");
			o.setCreateUserName(userProp.getCorpName());
			o.setCreateUserId(userProp.getUserId());
			this.qualificationsDao.insert(o);
			this.filesService.updateFiles(o.getFileSrc(), userProp);
			Department d = this.regDao.selectDepartInfoByPrimaryKey(o.getDeptId());
			this.dataBaseLogService.log("上传企业认证文件", "认证文件", "", "需上传的文件名称:"+o.getFileName(),
					d.getDepartmentName(), userProp);
		}
		return new MessageResponse(0, "添加资质完成！");
	}

	public MessageResponse updateQualifications(Qualifications o,
			UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "编号不能为空！");
		}
		o.setLastModifyTime(new Date());
		o.setLastModifyUserName(userProp.getCorpName());
		o.setLastModifyUserId(userProp.getUserId());
		this.qualificationsDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更资质", "资质", "", o.getCategory(),
				o.getCategory(), userProp);
		return new MessageResponse(0, "变更资质完成！");
	}

	public SingleResult<Qualifications> selectQualificationsByPrimaryKey(
			String id) throws Exception {
		SingleResult<Qualifications> rst = new SingleResult<Qualifications>();
		rst.setValue(this.qualificationsDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteQualificationsByQualificationsId(String id,
			UserProp userProp) throws Exception {
		this.qualificationsDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除资质", "资质", String.valueOf(id),
				String.valueOf(id), "资质", userProp);
		return new MessageResponse(0, "资质删除完成！");
	}

	public ListResult<Map<String, Object>> selectQualificationsList(
			String deptId, String parentDeptId, String flag) throws Exception {
		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
		rst.setValue(this.qualificationsDao.selectQualificationsList(deptId,parentDeptId,flag));
		return rst;
	}
	
	public  MessageResponse updateAudit(List<Qualifications> list, UserProp userProp) throws Exception{
		for(Qualifications o:list){
			if (CommonUtils.isBlank(o.getId())) {
				return new MessageResponse(1, "资质审核编号不能为空");
			}
			if (CommonUtils.isBlank(o.getStatus())) {
				return new MessageResponse(1, "资质审核状态不能为空");
			}
			Qualifications e=this.qualificationsDao.selectByPrimaryKey(o.getId());
			if(!e.getStatus().equals("2")){
				return new MessageResponse(1, "资质材料已审核，不可重复审核");
			}
			o.setAuditDate(new Date());
			o.setAuditUserName(userProp.getCorpName());
			o.setAuditUserId(userProp.getUserId());
			this.qualificationsDao.updateAudit(o);
			this.dataBaseLogService.log("企业资质审核", "资质审核", "", o.getCategory(),
					o.getCategory(), userProp);
		}
		
		return new MessageResponse(0, "资质审核完成");
		
		
		//for(Qualifications o:list){
		/*	if(CommonUtils.isBlank(flag)&&flag.equals("1")){
				if (CommonUtils.isBlank(o.getDeptId())) {
					return new MessageResponse(1, "企业编号不能为空！");
				}
				List<Map<String, Object>> list = qualificationsDao.selectQualificationsList(o.getDeptId(),userProp.getCorpId(),"2");
				for(Map<String, Object> s:list){
					o.setStatus("3");
					o.setAuditDate(new Date());
					o.setAuditUserName(userProp.getCorpName());
					o.setAuditUserId(userProp.getUserId());
					o.setId(s.get("id").toString());
					this.qualificationsDao.updateAudit(o);
					this.dataBaseLogService.log("审核资质", "资质", "", o.getCategory(),
							o.getCategory(), userProp);
				}
			}else{
				if (CommonUtils.isBlank(o.getId())) {
					return new MessageResponse(1, "编号不能为空！");
				}
				if (CommonUtils.isBlank(o.getStatus())) {
					return new MessageResponse(1, "审核状态不能为空！");
				}
				Qualifications e=this.qualificationsDao.selectByPrimaryKey(o.getId());
				if(e.getStatus().equals("4")){
					return new MessageResponse(1, "已审核过的数据！");
				}
				
				o.setAuditDate(new Date());
				o.setAuditUserName(userProp.getCorpName());
				o.setAuditUserId(userProp.getUserId());
				
				this.qualificationsDao.updateAudit(o);
				this.dataBaseLogService.log("审核资质", "资质", "", o.getCategory(),
						o.getId()+"已审核", userProp);
			}
			
		//}
		
		return new MessageResponse(0, "审核完成！");*/
	}

	
	/**
	 * 提交审核认证
	 * 
	 * @return MessageResponse
	 * @throws Exception
	 */
	public MessageResponse updateQualifi(UserProp userProp) throws Exception {
		Map<String, Object> m = this.qualificationsDao.selectCountByDeptID(userProp.getCorpId(),userProp.getParentCorpId());
		int qTotal = Integer.valueOf(m.get("qtoal").toString());
		int mTotal =Integer.valueOf(m.get("mtotal").toString());
		if(qTotal>=mTotal){
			this.qualificationsDao.updateBystatus(userProp.getCorpId(), "1");
			return new MessageResponse(0, "提交成功");
		}else{
			return new MessageResponse(1, "还有文件未上传！");
		}
	}

	
	/**
	 * 清空文件地址
	 * 
	 * @return MessageResponse
	 * @throws Exception
	 */
	public MessageResponse updateQualiByFileSrc(Qualifications q, UserProp curUserProp) {
		q.setLastModifyTime(new Date());
		q.setLastModifyUserName(curUserProp.getCorpName());
		q.setLastModifyUserId(curUserProp.getUserId());
		this.qualificationsDao.updateQualiByFileSrc(q);
		this.dataBaseLogService.log("企业资质文件删除", "资质文件删除", "","",
				"", curUserProp);
		return new MessageResponse(0, "文件删除成功!");
	}

}
