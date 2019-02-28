package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.taa.model.OfficeAdmin;
import com.huacainfo.ace.taa.vo.OfficeAdminQVo;
import com.huacainfo.ace.taa.vo.OfficeAdminVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OfficeAdminDao {

    OfficeAdmin selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(OfficeAdmin record);

    int updateByPrimaryKey(OfficeAdmin record);

    OfficeAdminVo selectVoByPrimaryKey(String id);

    List<OfficeAdminVo> findList(@Param("condition") OfficeAdminQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") OfficeAdminQVo condition);

    int isExist(OfficeAdmin record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    List<Map<String,Object>> selectList(@Param("nickname") String nickname);

    int isExistByUserId(@Param("userId") String userId);
}