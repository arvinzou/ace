package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.PerResume;
import com.huacainfo.ace.uf.vo.PerResumeVo;
import com.huacainfo.ace.uf.vo.PerResumeQVo;
public interface PerResumeService {
	
	public abstract PageResult<PerResumeVo> findPerResumeList(PerResumeQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPerResume(PerResume obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePerResume(PerResume obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PerResumeVo> selectPerResumeByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePerResumeByPerResumeId(String id,UserProp userProp) throws Exception;

	
}
