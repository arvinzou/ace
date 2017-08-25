package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Province;
import com.huacainfo.ace.portal.vo.ProvinceVo;
import com.huacainfo.ace.portal.vo.ProvinceQVo;
public interface ProvinceService {
	
	public abstract PageResult<ProvinceVo> findProvinceList(ProvinceQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertProvince(Province obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateProvince(Province obj,UserProp userProp) throws Exception;
	public abstract SingleResult<Province> selectProvinceByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteProvinceByProvinceId(String id,UserProp userProp) throws Exception;

	
}
