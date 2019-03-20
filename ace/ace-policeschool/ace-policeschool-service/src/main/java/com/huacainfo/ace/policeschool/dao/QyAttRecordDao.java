package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.QyAttRecord;
import com.huacainfo.ace.policeschool.vo.QyAttRecordQVo;
import com.huacainfo.ace.policeschool.vo.QyAttRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QyAttRecordDao {

    QyAttRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(QyAttRecord record);


    int updateByPrimaryKey(QyAttRecord record);


    QyAttRecordVo selectVoByPrimaryKey(String id);

    List<QyAttRecordVo> findList(@Param("condition") QyAttRecordQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") QyAttRecordQVo condition);

    int isExist(QyAttRecord record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}