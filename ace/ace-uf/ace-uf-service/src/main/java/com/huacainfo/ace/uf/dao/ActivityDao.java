package com.huacainfo.ace.uf.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.Activity;
import com.huacainfo.ace.uf.vo.ActivityQVo;
import com.huacainfo.ace.uf.vo.ActivityVo;

public interface ActivityDao {
    int deleteByPrimaryKey(String ActivityId);

    int insert(Activity record);


    ActivityVo selectByPrimaryKey(String ActivityId);


    int updateByPrimaryKey(Activity record);
    
    List<ActivityVo> findList(@Param("condition") ActivityQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ActivityQVo condition);

	int isExit(Activity record);

	int updateReading(String id);
    int updateUp(String id);
    int updateComplain(String id);

    List<Map<String,Object>> selectActivityPageList(@Param("p") Map<String,Object> p,@Param("start") int start, @Param("limit") int limit);
    List<Map<String,Object>> selectPhotoListById(String id);
    Map<String,Object> selectActivityById(String id);

}