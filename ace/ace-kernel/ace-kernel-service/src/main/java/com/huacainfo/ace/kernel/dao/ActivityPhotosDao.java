package com.huacainfo.ace.kernel.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.kernel.model.ActivityPhotos;
import com.huacainfo.ace.kernel.vo.ActivityPhotosQVo;
import com.huacainfo.ace.kernel.vo.ActivityPhotosVo;

public interface ActivityPhotosDao {
    int deleteByPrimaryKey(String ActivityPhotosId);

    int insert(ActivityPhotos record);


    ActivityPhotos selectByPrimaryKey(String ActivityPhotosId);


    int updateByPrimaryKey(ActivityPhotos record);
    
    List<ActivityPhotosVo> findList(@Param("condition") ActivityPhotosQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ActivityPhotosQVo condition);

	int isExit(ActivityPhotos record);

}