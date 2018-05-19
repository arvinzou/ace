package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.vo.FopCompanyQVo;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FopCompanyDao {

    int deleteByPrimaryKey(String id);

    int insert(FopCompany record);

    int insertSelective(FopCompany record);

    FopCompany selectByPrimaryKey(String id);

    FopCompany selectByDepartmentId(String departmentId);

    int updateByPrimaryKeySelective(FopCompany record);

    int updateByPrimaryKey(FopCompany record);

    FopCompanyVo selectVoByPrimaryKey(String id);

    List<FopCompanyVo> findList(@Param("condition") FopCompanyQVo condition,
                                @Param("start") int start, @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    List<FopCompanyVo> findGisList();

    int findCount(@Param("condition") FopCompanyQVo condition);

    int isExit(FopCompany record);

    /**
     * 查询系统下，指定类型的角色信息
     *
     * @param sysId 系统id
     * @param types 类型数组
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> selectRoleList(@Param("") String sysId,
                                             @Param("types") String[] types);


    List<Map<String, String>> selectPerson(
            @Param("params") Map<String, Object> params);
}