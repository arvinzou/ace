package com.huacainfo.ace.jxb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.Banner;
import com.huacainfo.ace.jxb.vo.BannerQVo;
import com.huacainfo.ace.jxb.vo.BannerVo;

public interface BannerDao {



    int deleteByPrimaryKey(String id);

    int insert(Banner record);


    int updateByPrimaryKey(Banner record);


    BannerVo selectVoByPrimaryKey(String id);

    List<BannerVo> findList(@Param("condition") BannerQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BannerQVo condition);

    int isExit(Banner record);

    int updateStatus(Banner record);

}