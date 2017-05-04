package com.huacainfo.ace.operana.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.operana.model.NormCfg;
import com.huacainfo.ace.operana.vo.NormCfgQVo;
import com.huacainfo.ace.operana.vo.NormCfgVo;

public interface NormCfgDao {
    int deleteByPrimaryKey(String NormCfgId);

    int insert(NormCfg record);


    NormCfg selectByPrimaryKey(String NormCfgId);


    int updateByPrimaryKey(NormCfg record);
    
    List<NormCfgVo> findList(@Param("condition") NormCfgQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") NormCfgQVo condition);

	int isExit(NormCfg record);

}