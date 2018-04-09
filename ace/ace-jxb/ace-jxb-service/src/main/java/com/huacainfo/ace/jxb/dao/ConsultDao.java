package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.Consult;
import com.huacainfo.ace.jxb.vo.ConsultQVo;
import com.huacainfo.ace.jxb.vo.ConsultVo;

public interface ConsultDao {

    ConsultVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Consult record);

    int updateByPrimaryKey(Consult record);

    
    List<ConsultVo> findList(@Param("condition") ConsultQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ConsultQVo condition);

	int isExit(Consult record);

}