package com.huacainfo.ace.uf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.FuPin;
import com.huacainfo.ace.uf.vo.FuPinQVo;
import com.huacainfo.ace.uf.vo.FuPinVo;

public interface FuPinDao {
    /**
     * 删除
     * @param FuPinId
     * @return
     */
    int deleteByPrimaryKey(String FuPinId);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(FuPin record);


    /**
     * 按主键查找
     * @param FuPinId
     * @return
     */
    FuPinVo selectByPrimaryKey(String FuPinId);


    /**
     * 按主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(FuPin record);

    /**
     * 查找所有
     * @param condition
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    List<FuPinVo> findList(@Param("condition") FuPinQVo condition,
                           @Param("start") int start,
                           @Param("limit") int limit,
                           @Param("orderBy") String orderBy);

    /**
     * 获取多少行数据
     * @param condition
     * @return
     */
    int findCount(@Param("condition") FuPinQVo condition);

    /**
     * 查看是否存在
     * @param record
     * @return
     */
    int isExit(FuPin record);

}