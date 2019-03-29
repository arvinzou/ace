package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.AttRecord;
import com.huacainfo.ace.policeschool.vo.AttRecordQVo;
import com.huacainfo.ace.policeschool.vo.AttRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AttRecordDao {

    AttRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(AttRecord record);


    int updateByPrimaryKey(AttRecord record);


    AttRecordVo selectVoByPrimaryKey(String id);

    List<AttRecordVo> findList(@Param("condition") AttRecordQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") AttRecordQVo condition);

    int isExist(AttRecord record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    Map<String, Object> getAttSrc();

    List<AttRecordVo> findListExpor(@Param("condition") AttRecordQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);
}