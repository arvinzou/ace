package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.ErrFeedback;
import com.huacainfo.ace.glink.vo.ErrFeedbackQVo;
import com.huacainfo.ace.glink.vo.ErrFeedbackVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ErrFeedbackDao {

    ErrFeedback selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ErrFeedback record);

    int updateByPrimaryKey(ErrFeedback record);

    ErrFeedbackVo selectVoByPrimaryKey(String id);

    List<ErrFeedbackVo> findList(@Param("condition") ErrFeedbackQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ErrFeedbackQVo condition);

    int isExist(ErrFeedback record);

    int updateStatus(@Param("id") String id, @Param("status") String status);

    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
