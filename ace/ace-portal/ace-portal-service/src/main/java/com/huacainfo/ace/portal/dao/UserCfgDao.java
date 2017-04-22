package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.UserCfg;
import com.huacainfo.ace.portal.vo.UserCfgQVo;
import com.huacainfo.ace.portal.vo.UserCfgVo;

public interface UserCfgDao {

    int deleteByPrimaryKey(String UserCfgId);

    int insert(UserCfg record);

    UserCfg selectByPrimaryKey(String UserCfgId);

    int updateByPrimaryKey(UserCfg record);
    
    List<UserCfgVo> findList(@Param("condition") UserCfgQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") UserCfgQVo condition);

	int isExit(UserCfg record);

    List<Map<String,Object>> selectUserCfgByUserId(String userId);

}