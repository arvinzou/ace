package com.huacainfo.ace.woc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.License;
import com.huacainfo.ace.woc.vo.LicenseQVo;
import com.huacainfo.ace.woc.vo.LicenseVo;

public interface LicenseDao {
    License selectByPrimaryKey(String licenseId);

    LicenseVo selectVoByPrimaryKey(String licenseId);

    int deleteByPrimaryKey(String licenseId);

    int insert(License record);

    int insertSelective(License record);

    int updateByPrimaryKey(License record);

    int updateByPrimaryKeySelective(License record);


    List<LicenseVo> findList(@Param("condition") LicenseQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") LicenseQVo condition);

	int isExit(License record);

}