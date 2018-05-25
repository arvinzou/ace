package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.AppealCase;
import com.huacainfo.ace.portal.vo.AppealCaseQVo;
import com.huacainfo.ace.portal.vo.AppealCaseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AppealCaseDao {
    int deleteByPrimaryKey(String id);

    int insert(AppealCase record);

    AppealCaseVo selectByPrimaryKey(String id);

    int updateByPrimaryKey(AppealCase record);

    List<AppealCaseVo> findList(@Param("condition") AppealCaseQVo condition,
                                @Param("start") int start, @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") AppealCaseQVo condition);

    int isExit(AppealCase record);

    List<Map<String, Object>> getList(Map<String, Object> params);

    int updateAccept(@Param("id") String id,
                     @Param("answerDept") String answerDept,
                     @Param("operator") String operator);

    int updateDetailsOfProgress(@Param("id") String id, @Param("detailsOfProgress") String detailsOfProgress);

}