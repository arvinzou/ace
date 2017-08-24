package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.Group;
import com.huacainfo.ace.portal.vo.GroupQVo;
import com.huacainfo.ace.portal.vo.GroupVo;

public interface GroupMapper {
    int deleteByPrimaryKey(String groupId);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(String groupId);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
    List<GroupVo> findList(@Param("condition") GroupQVo condition,
                           @Param("start") int start, @Param("limit") int limit,
                           @Param("orderBy") String orderBy);

	int findCount(@Param("condition") GroupQVo condition);

	int isExitByName(@Param("name") String name);
	List<Map<String,Object>> selectGroupDepTreeByPid(@Param("pid") String pid);
	List<Map<String,Object>> selectGroupGradeTreeByPid(@Param("pid") String pid);
	List<Map<String,Object>> selectGroupDiscriblineTreeByPid(@Param("pid") String pid);
	List<Map<String,Object>> selectFreeGroupTreeRoot(@Param("syid") String syid);
	
	List<Map<String,Object>> selectFreeGroupUsersListByGorupId(String groupId);
	List<Map<String,Object>> selectFreeGroupUsersTreeByGorupId(String groupId);
	
    int batchSaveGroupUsersByUserIds(@Param("list") List<String> list, @Param("groupId") String groupId);
}