package com.huacainfo.ace.uf.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.uf.model.ActivityUser;

import java.util.List;
import java.util.Map;

public interface ActivityUserService {

	public abstract List<Map<String,Object>> selectListByActivityId(String id) throws Exception;
	public abstract MessageResponse insertActivityUser(List<ActivityUser> list,UserProp userProp) throws Exception;
	public abstract MessageResponse deleteActivityUserByActivityUserId(String id,UserProp userProp) throws Exception;

	
}
