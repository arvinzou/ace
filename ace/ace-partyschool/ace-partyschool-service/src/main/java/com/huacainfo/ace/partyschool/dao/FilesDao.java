package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.Files;
import com.huacainfo.ace.partyschool.vo.FilesQVo;
import com.huacainfo.ace.partyschool.vo.FilesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilesDao {

    Files selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Files record);


    int updateByPrimaryKey(Files record);


    FilesVo selectVoByPrimaryKey(String id);

    List<FilesVo> findStudentFileList(@Param("condition") FilesQVo condition,
                           @Param("start") int start,
                           @Param("limit") int limit,
                           @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FilesQVo condition);

    int isExit(Files record);

    int updateStatus(Files record);

    List<FilesVo> findFileListAboutMe(@Param("condition") FilesQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    List<FilesVo> findList(@Param("condition") FilesQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);
}