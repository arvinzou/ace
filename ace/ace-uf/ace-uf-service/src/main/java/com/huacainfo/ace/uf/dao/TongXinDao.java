package com.huacainfo.ace.uf.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.TongXin;
import com.huacainfo.ace.uf.vo.TongXinQVo;
import com.huacainfo.ace.uf.vo.TongXinVo;

public interface TongXinDao {
    int deleteByPrimaryKey(String TongXinId);

    int insert(TongXin record);


    TongXinVo selectByPrimaryKey(String TongXinId);


    int updateByPrimaryKey(TongXin record);

    /**
     *
     * @param condition
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    List<TongXinVo> findList(@Param("condition") TongXinQVo condition,
                             @Param("start") int start,
                             @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

	int findCount(@Param("condition") TongXinQVo condition);

	int isExit(TongXin record);

}