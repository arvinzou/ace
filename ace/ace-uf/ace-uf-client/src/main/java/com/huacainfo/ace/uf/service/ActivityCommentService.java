package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.uf.model.ActivityComment;
import java.util.Map;
import java.util.List;
public interface ActivityCommentService {

	public abstract List<Map<String,Object>> selectListByActivityId(String id) throws Exception;
	public abstract MessageResponse insertActivityComment(ActivityComment obj,WxUser user) throws Exception;
	public abstract MessageResponse deleteActivityCommentByActivityCommentId(String id, UserProp userProp)
			throws Exception;



}
