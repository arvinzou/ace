package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.Activity;
import com.huacainfo.ace.uf.vo.ActivityVo;
import com.huacainfo.ace.uf.vo.ActivityQVo;

import java.util.List;
import java.util.Map;

public interface ActivityService {

	public abstract PageResult<ActivityVo> findActivityList(ActivityQVo condition, int start, int limit, String orderBy)
			throws Exception;
	public abstract MessageResponse insertActivity(Activity obj, UserProp userProp) throws Exception;
	public abstract MessageResponse updateActivity(Activity obj, UserProp userProp) throws Exception;
	public abstract SingleResult<ActivityVo> selectActivityByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteActivityByActivityId(String id, UserProp userProp) throws Exception;

	List<Map<String,Object>> selectActivityPageList(Map<String,Object> p);
	List<Map<String,Object>> selectPhotoListById(String id);
	Map<String,Object> selectActivityById(String id);

	public  MessageResponse updateActivity(String id,String type,WxUser user) throws Exception;

}
