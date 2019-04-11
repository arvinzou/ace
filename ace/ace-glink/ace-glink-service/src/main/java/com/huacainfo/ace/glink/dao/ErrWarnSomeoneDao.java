package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.ErrWarnSomeone;
import com.huacainfo.ace.glink.vo.ErrWarnSomeoneQVo;
import com.huacainfo.ace.glink.vo.ErrWarnSomeoneVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ErrWarnSomeoneDao {

    ErrWarnSomeone selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ErrWarnSomeone record);

    int updateByPrimaryKey(ErrWarnSomeone record);

    ErrWarnSomeoneVo selectVoByPrimaryKey(String id);

    List<ErrWarnSomeoneVo> findList(@Param("condition") ErrWarnSomeoneQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ErrWarnSomeoneQVo condition);

    int isExist(ErrWarnSomeone record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
