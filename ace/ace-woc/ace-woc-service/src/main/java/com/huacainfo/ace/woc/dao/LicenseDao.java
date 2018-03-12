package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.License;
import com.huacainfo.ace.woc.vo.LicenseQVo;
import com.huacainfo.ace.woc.vo.LicenseVo;

public interface LicenseDao {
    int deleteByPrimaryKey(String LicenseId);

    int insert(License record);


    LicenseVo selectByPrimaryKey(String LicenseId);


    int updateByPrimaryKey(License record);
    
    List<LicenseVo> findList(@Param("condition") LicenseQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") LicenseQVo condition);

	int isExit(License record);

}