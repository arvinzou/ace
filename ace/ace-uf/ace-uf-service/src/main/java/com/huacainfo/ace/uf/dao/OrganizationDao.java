package com.huacainfo.ace.uf.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.Organization;
import com.huacainfo.ace.uf.vo.OrganizationQVo;
import com.huacainfo.ace.uf.vo.OrganizationVo;

public interface OrganizationDao {
    int deleteByPrimaryKey(String OrganizationId);

    int insert(Organization record);


    OrganizationVo selectByPrimaryKey(String OrganizationId);


    int updateByPrimaryKey(Organization record);
    
    List<OrganizationVo> findList(@Param("condition") OrganizationQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") OrganizationQVo condition);

	int isExit(Organization record);

    List<Map<String,Object>> selectOrganizationCategoryList();
    List<Map<String,Object>> selectOrganizationList(@Param("q")String q,@Param("longitude") String longitude,@Param("latitude") String latitude);
    Map<String,Object> selectOrganizationById(String id);
    List<Map<String,String>> selectOrganizationImagesById(String id);

    List<Map<String,Object>> selectAreaCodeList(String areaCode);

    List<Map<String,Object>> selectOrganizationByCategory(@Param("category")String category);

    List<Map<String,Object>> selectOrganizationListByText(@Param("q")String q);


}