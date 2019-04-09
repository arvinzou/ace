package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.TopSubarea;
import com.huacainfo.ace.glink.vo.TopSubareaQVo;
import com.huacainfo.ace.glink.vo.TopSubareaVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TopSubareaDao {

    TopSubarea selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TopSubarea record);

    int updateByPrimaryKey(TopSubarea record);

    TopSubareaVo selectVoByPrimaryKey(String id);

    List<TopSubareaVo> findList(@Param("condition") TopSubareaQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TopSubareaQVo condition);

    int isExist(TopSubarea record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
