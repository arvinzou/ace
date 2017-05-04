package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.UserCfg;
import com.huacainfo.ace.portal.vo.UserCfgQVo;
import com.huacainfo.ace.portal.vo.UserCfgVo;
import java.util.List;
import java.util.Map;
public interface UserCfgService {
	public abstract MessageResponse saveOrUpdateUserCfg(List<UserCfg> list,UserProp userProp) throws Exception;

	public abstract Map<String,Object> selectUserCfgByUserId(UserProp userProp) throws Exception;

}
