package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.QyCrm;
import com.huacainfo.ace.policeschool.vo.QyCrmQVo;
import com.huacainfo.ace.policeschool.vo.QyCrmVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QyCrmDao {

    QyCrm selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(QyCrm record);


    int updateByPrimaryKey(QyCrm record);


    QyCrmVo selectVoByPrimaryKey(String id);

    List<QyCrmVo> findList(@Param("condition") QyCrmQVo condition,
                           @Param("start") int start,
                           @Param("limit") int limit,
                           @Param("orderBy") String orderBy);

    int findCount(@Param("condition") QyCrmQVo condition);

    int isExist(QyCrm record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    List<QyCrmVo> findUnSyncList();
}