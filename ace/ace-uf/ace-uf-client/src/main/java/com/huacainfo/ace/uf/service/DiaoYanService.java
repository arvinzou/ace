package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.DiaoYan;
import com.huacainfo.ace.uf.vo.DiaoYanVo;
import com.huacainfo.ace.uf.vo.DiaoYanQVo;
public interface DiaoYanService {
	
	public abstract PageResult<DiaoYanVo> findDiaoYanList(DiaoYanQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertDiaoYan(DiaoYan obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateDiaoYan(DiaoYan obj,UserProp userProp) throws Exception;
	public abstract SingleResult<DiaoYanVo> selectDiaoYanByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteDiaoYanByDiaoYanId(String id,UserProp userProp) throws Exception;

	
}
