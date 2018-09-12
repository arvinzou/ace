package com.huacainfo.ace.society.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.society.model.BehaviorAnnex;
import com.huacainfo.ace.society.vo.BehaviorAnnexQVo;
import com.huacainfo.ace.society.vo.BehaviorAnnexVo;

public interface BehaviorAnnexDao {

BehaviorAnnex selectByPrimaryKey(String id);

int deleteByPrimaryKey(String id);

int insert(BehaviorAnnex record);

int insertSelective(BehaviorAnnex record);

int updateByPrimaryKey(BehaviorAnnex record);

int updateByPrimaryKeySelective(String id);

BehaviorAnnexVo selectVoByPrimaryKey(String id);

List
<BehaviorAnnexVo> findList(@Param("condition") BehaviorAnnexQVo condition,
    @Param("start") int start,
    @Param("limit") int limit,
    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BehaviorAnnexQVo condition);

    int isExit(BehaviorAnnex record);

    }