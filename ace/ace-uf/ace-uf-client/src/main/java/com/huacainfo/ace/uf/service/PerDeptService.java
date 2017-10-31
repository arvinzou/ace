package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.PerDept;
import com.huacainfo.ace.uf.vo.PerDeptVo;
import com.huacainfo.ace.uf.vo.PerDeptQVo;
public interface PerDeptService {
	
	public abstract PageResult<PerDeptVo> findPerDeptList(PerDeptQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPerDept(PerDept obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePerDept(PerDept obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PerDeptVo> selectPerDeptByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePerDeptByPerDeptId(String id,UserProp userProp) throws Exception;

	
}
