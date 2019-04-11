package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.ErrWarnSms;
import com.huacainfo.ace.glink.vo.ErrWarnSmsQVo;
import com.huacainfo.ace.glink.vo.ErrWarnSmsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ErrWarnSmsDao {

    ErrWarnSms selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ErrWarnSms record);

    int updateByPrimaryKey(ErrWarnSms record);

    ErrWarnSmsVo selectVoByPrimaryKey(String id);

    List<ErrWarnSmsVo> findList(@Param("condition") ErrWarnSmsQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ErrWarnSmsQVo condition);

    int isExist(ErrWarnSms record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
