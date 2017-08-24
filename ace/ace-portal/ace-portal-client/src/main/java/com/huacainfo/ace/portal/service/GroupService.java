package com.huacainfo.ace.portal.service;

import java.util.List;
import java.util.Map;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.portal.vo.GroupQVo;
import com.huacainfo.ace.portal.vo.GroupVo;
import com.huacainfo.ace.portal.model.Group;
import com.huacainfo.ace.common.model.UserProp;
public interface GroupService {
	public abstract PageResult<GroupVo> findGroupList(GroupQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertGroup(Group o, UserProp userProp) throws Exception;
	public abstract MessageResponse updateGroup(Group o, UserProp userProp) throws Exception;
	public abstract MessageResponse deleteGroupByGroupId(String id, UserProp userProp) throws Exception;
	
	public abstract List<Tree>  selectGroupDepTreeByPid(String pid);
	public abstract List<Tree>  selectGroupGradeTreeByPid(String pid);
	public abstract List<Tree>  selectGroupDiscriblineTreeByPid(String pid);
	public abstract List<Tree> selectFreeGroupTreeRoot(String syid);

	public abstract List<Tree> selectFreeGroupTreeByPid(String pid);
	
	public abstract ListResult selectFreeGroupUsersListByGorupId(String groupId) throws Exception;
	public abstract MessageResponse batchSaveGroupUsersByUserIds(Map<String,Object> obj,UserProp userProp) throws Exception;

}
