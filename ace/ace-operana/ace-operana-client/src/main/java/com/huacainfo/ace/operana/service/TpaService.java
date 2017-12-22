package com.huacainfo.ace.operana.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.operana.model.Tpa;
import com.huacainfo.ace.operana.vo.TpaVo;
import com.huacainfo.ace.operana.vo.TpaQVo;
public interface TpaService {

	public abstract PageResult<TpaVo> findTpaList(TpaQVo condition, int start, int limit, String orderBy)
			throws Exception;
	public abstract MessageResponse insertTpa(Tpa obj, UserProp userProp) throws Exception;
	public abstract MessageResponse updateTpa(Tpa obj, UserProp userProp) throws Exception;
	public abstract SingleResult<Tpa> selectTpaByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteTpaByTpaId(String id, UserProp userProp) throws Exception;

	public abstract PageResult<TpaVo> findTpaUserTaskList(TpaQVo condition, int start, int limit, String orderBy)
			throws Exception;
	public abstract MessageResponse updateTpaById(Tpa obj, UserProp userProp) throws Exception;


	public abstract PageResult<TpaVo> findTpaListCommon(TpaQVo condition, int start, int limit, String orderBy)
			throws Exception;

}
