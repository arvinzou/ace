package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.vo.WxCfgQVo;
import com.huacainfo.ace.portal.vo.WxCfgVo;

public interface WxCfgDao {
    int deleteByPrimaryKey(String WxCfgId);

    int insert(WxCfg record);


    WxCfgVo selectByPrimaryKey(String WxCfgId);


    int updateByPrimaryKey(WxCfg record);
    
    List<WxCfgVo> findList(@Param("condition") WxCfgQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") WxCfgQVo condition);

	int isExit(WxCfg record);

	int updateAccessToken(@Param("appId")String appId,@Param("accessToken")String accessToken,@Param("expiresIn")int expiresIn);
    List<Map<String,Object>> selectAppList();

}