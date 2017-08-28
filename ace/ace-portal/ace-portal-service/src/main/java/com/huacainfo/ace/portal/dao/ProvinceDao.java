package com.huacainfo.ace.portal.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.Province;
import com.huacainfo.ace.portal.vo.ProvinceQVo;
import com.huacainfo.ace.portal.vo.ProvinceVo;

public interface ProvinceDao {
    int deleteByPrimaryKey(String code);

    int insert(Province record);


    Province selectByPrimaryKey(String code);


    int updateByPrimaryKey(Province record);
    
    List<ProvinceVo> findList(@Param("condition") ProvinceQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ProvinceQVo condition);

	int isExit(Province record);

}