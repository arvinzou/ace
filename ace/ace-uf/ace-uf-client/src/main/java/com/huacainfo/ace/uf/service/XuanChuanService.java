package com.huacainfo.ace.uf.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.XuanChuan;
import com.huacainfo.ace.uf.vo.XuanChuanQVo;
import com.huacainfo.ace.uf.vo.XuanChuanVo;

public interface XuanChuanService {

	public abstract PageResult<XuanChuanVo> findXuanChuanList(XuanChuanQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertXuanChuan(XuanChuan obj, UserProp userProp) throws Exception;
	public abstract MessageResponse updateXuanChuan(XuanChuan obj, UserProp userProp) throws Exception;
	public abstract SingleResult<XuanChuanVo> selectXuanChuanByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteXuanChuanByXuanChuanId(String id, UserProp userProp) throws Exception;

	
}
