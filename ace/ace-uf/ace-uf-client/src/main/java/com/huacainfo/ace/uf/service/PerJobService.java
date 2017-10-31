package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.PerJob;
import com.huacainfo.ace.uf.vo.PerJobVo;
import com.huacainfo.ace.uf.vo.PerJobQVo;
public interface PerJobService {
	
	public abstract PageResult<PerJobVo> findPerJobList(PerJobQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPerJob(PerJob obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePerJob(PerJob obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PerJobVo> selectPerJobByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePerJobByPerJobId(String id,UserProp userProp) throws Exception;

	
}
