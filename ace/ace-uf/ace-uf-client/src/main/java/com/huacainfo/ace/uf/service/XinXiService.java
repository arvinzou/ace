package com.huacainfo.ace.uf.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.XinXi;
import com.huacainfo.ace.uf.vo.XinXiQVo;
import com.huacainfo.ace.uf.vo.XinXiVo;

public interface XinXiService {

	public abstract PageResult<XinXiVo> findXinXiList(XinXiQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertXinXi(XinXi obj, UserProp userProp) throws Exception;
	public abstract MessageResponse updateXinXi(XinXi obj, UserProp userProp) throws Exception;
	public abstract SingleResult<XinXiVo> selectXinXiByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteXinXiByXinXiId(String id, UserProp userProp) throws Exception;

	
}
