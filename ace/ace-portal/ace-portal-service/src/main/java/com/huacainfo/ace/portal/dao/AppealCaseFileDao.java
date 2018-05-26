package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.AppealCaseFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppealCaseFileDao {
    int deleteByPrimaryKey(String id);

    int insert(AppealCaseFile record);

    /**
     * @param appealCaseId
     * @param types        类型（1诉求2答复）
     * @return
     */
    List<AppealCaseFile> findByAppealCaseId(@Param("appealCaseId") String appealCaseId,
                                            @Param("types") String[] types);

}