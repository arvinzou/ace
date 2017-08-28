package com.huacainfo.ace.gesp.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.Qualifications;
import com.huacainfo.ace.gesp.vo.QualificationsQVo;

public interface QualificationsService {

	public abstract PageResult<Map<String,Object>> findQualificationsList(
			QualificationsQVo condition, int start, int limit, String orderBy)
			throws Exception;

	public abstract MessageResponse insertQualifications(Qualifications obj,
			UserProp userProp) throws Exception;

	public abstract MessageResponse updateQualifications(Qualifications obj,
			UserProp userProp) throws Exception;

	public abstract SingleResult<Qualifications> selectQualificationsByPrimaryKey(
			String id) throws Exception;

	public abstract MessageResponse deleteQualificationsByQualificationsId(
			String id, UserProp userProp) throws Exception;

	public abstract ListResult<Map<String, Object>> selectQualificationsList(String detpId, String parentDeptId, String flag) throws Exception;
	
	public abstract MessageResponse updateAudit(List<Qualifications> list, UserProp userProp) throws Exception;
	
	/**
	 * 提交审核认证
	 * 
	 * @return MessageResponse
	 * @throws Exception
	 */
	public abstract MessageResponse updateQualifi(UserProp curUserProp) throws Exception;

	/**
	 * 清空文件地址
	 * 
	 * @param q
	 * @param curUserProp
	 * @return
	 */
	public abstract MessageResponse updateQualiByFileSrc(Qualifications q, UserProp curUserProp);

}
