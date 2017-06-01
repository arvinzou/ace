package com.huacainfo.ace.luohua.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.luohua.model.Writing;
import com.huacainfo.ace.luohua.vo.WritingVo;
import com.huacainfo.ace.luohua.vo.WritingQVo;
public interface WritingService {
	
	public abstract PageResult<WritingVo> findWritingList(WritingQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertWriting(Writing obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateWriting(Writing obj,UserProp userProp) throws Exception;
	public abstract SingleResult<Writing> selectWritingByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteWritingByWritingId(String id,UserProp userProp) throws Exception;

	
}
