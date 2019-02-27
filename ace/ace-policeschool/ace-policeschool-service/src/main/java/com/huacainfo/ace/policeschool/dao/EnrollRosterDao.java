package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.EnrollRoster;
import com.huacainfo.ace.policeschool.vo.EnrollRosterQVo;
import com.huacainfo.ace.policeschool.vo.EnrollRosterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EnrollRosterDao {

    EnrollRoster selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(EnrollRoster record);


    int updateByPrimaryKey(EnrollRoster record);


    EnrollRosterVo selectVoByPrimaryKey(String id);

    List<EnrollRosterVo> findList(@Param("condition") EnrollRosterQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") EnrollRosterQVo condition);

    int isExist(EnrollRoster record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    /**
     * * 批量开启/关闭报名
     *
     * @param clsId  班级ID
     * @param status 开启/关闭  1-开，0-关
     */
    int updateStatusByClsId(@Param("clsId") String clsId,
                            @Param("status") String status);

    int delByClsId(String clsId);
}