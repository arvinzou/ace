package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SmsSchedulerMapped;
import com.huacainfo.ace.glink.vo.SmsSchedulerMappedQVo;
import com.huacainfo.ace.glink.vo.SmsSchedulerMappedVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SmsSchedulerMappedDao {

    SmsSchedulerMapped selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SmsSchedulerMapped record);

    int updateByPrimaryKey(SmsSchedulerMapped record);

    SmsSchedulerMappedVo selectVoByPrimaryKey(String id);

    List<SmsSchedulerMappedVo> findList(@Param("condition") SmsSchedulerMappedQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SmsSchedulerMappedQVo condition);

    int isExit(SmsSchedulerMapped record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
