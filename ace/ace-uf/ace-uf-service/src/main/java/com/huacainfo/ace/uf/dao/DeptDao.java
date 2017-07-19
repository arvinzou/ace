package com.huacainfo.ace.uf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DeptDao {


    List<Map<String,Object>> selectDeptCategoryList(String areaCode);

    List<Map<String,Object>> selectDeptList(@Param("q") String q);

    Map<String,Object> selectDeptById(String id);

    List<Map<String,String>> selectDeptImagesById(String id);

    List<Map<String,Object>> selectAreaCodeList(String areaCode);

    List<Map<String,Object>> selectDeptByCategory(@Param("category") String category);


}