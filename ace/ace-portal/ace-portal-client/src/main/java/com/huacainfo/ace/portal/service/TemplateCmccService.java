package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.portal.vo.TemplateCmccQVo;
import com.huacainfo.ace.portal.vo.TemplateCmccVo;
import com.huacainfo.ace.portal.model.TemplateCmcc;
import com.huacainfo.ace.common.result.PageResult;
public interface TemplateCmccService {
	public abstract PageResult<TemplateCmccVo> findTemplateCmccList(TemplateCmccQVo condition, int start, int limit,
			String orderBy) throws Exception;
	public abstract MessageResponse insertTemplateCmcc(TemplateCmcc o, UserProp userProp) throws Exception;
	public abstract MessageResponse updateTemplateCmcc(TemplateCmcc o, UserProp userProp) throws Exception;
	public abstract MessageResponse deleteTemplateCmccByTemplateCmccId(String id, UserProp userProp) throws Exception;

}
