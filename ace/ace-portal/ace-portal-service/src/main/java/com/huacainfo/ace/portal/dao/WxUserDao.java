package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.portal.vo.WxUserQVo;
import com.huacainfo.ace.portal.vo.WxUserVo;

public interface WxUserDao {
    int deleteByPrimaryKey(String WxUserId);

    int insert(WxUser record);


    WxUser selectByPrimaryKey(String WxUserId);


    int updateByPrimaryKey(WxUser record);
    
    List<WxUserVo> findList(@Param("condition") WxUserQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") WxUserQVo condition);

	int isExit(WxUser record);

    int updateReg(WxUser record);

    int updateRole(@Param("unionId") String unionId,@Param("role") String role);


    java.util.Map<String,Object> selectPersonageByMobile(String mobile);

    List<Map<String,Object>> selectWxUser(@Param("condition") Map<String,Object> condition);

    WxUser selectByMobile(String mobile);

}