package com.huacainfo.ace.operana.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.operana.model.Tpa;
import com.huacainfo.ace.operana.vo.TpaQVo;
import com.huacainfo.ace.operana.vo.TpaVo;

public interface TpaDao {
	int deleteByPrimaryKey(String TpaId);

	int insert(Tpa record);

	Tpa selectByPrimaryKey(String TpaId);

	int updateByPrimaryKey(Tpa record);

	List<TpaVo> findList(@Param("condition") TpaQVo condition, @Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	List<TpaVo> findListUserTask(@Param("condition") TpaQVo condition, @Param("start") int start,
			@Param("limit") int limit, @Param("orderBy") String orderBy);

	int findCount(@Param("condition") TpaQVo condition, @Param("orderBy") String orderBy);

	int findCountUserTask(@Param("condition") TpaQVo condition, @Param("orderBy") String orderBy);

	int isExit(Tpa record);

	int updateById(Tpa record);

	List<Map<String,Object>> selectTaskAByUserId( @Param("userId") String userId);
	List<Map<String,Object>> selectTaskBByUserId( @Param("userId") String userId);

}