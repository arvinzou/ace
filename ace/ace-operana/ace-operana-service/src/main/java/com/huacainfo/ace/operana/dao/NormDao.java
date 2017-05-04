package com.huacainfo.ace.operana.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.operana.model.Norm;
import com.huacainfo.ace.operana.vo.NormQVo;
import com.huacainfo.ace.operana.vo.NormVo;
import java.util.Map;
public interface NormDao {

	int deleteByPrimaryKey(String NormId);

	int insert(Norm record);

	Norm selectByPrimaryKey(String NormId);

	int updateByPrimaryKey(Norm record);

	List<NormVo> findList(@Param("condition") NormQVo condition, @Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") NormQVo condition);

	int isExit(Norm record);

	List<Map<String, Object>> selectAllNormCategory();
	List<Map<String, Object>> selectNormByCategory(@Param("category") String category,
			@Param("topicId") String topicId);
	List<Map<String, Object>> selectNormByTopicId(String topicId);

	List<Map<String, Object>> selectNormByName(@Param("name") String name,
					 @Param("topicId") String topicId);

}