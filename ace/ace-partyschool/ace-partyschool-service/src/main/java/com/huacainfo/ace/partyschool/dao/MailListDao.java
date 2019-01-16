package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.MailList;
import com.huacainfo.ace.partyschool.vo.MailListContent;
import com.huacainfo.ace.partyschool.vo.MailListQVo;
import com.huacainfo.ace.partyschool.vo.MailListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MailListDao {

    MailList selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(MailList record);


    int updateByPrimaryKey(MailList record);


    MailListVo selectVoByPrimaryKey(String id);

    List<MailListVo> findList(@Param("condition") MailListQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") MailListQVo condition);

    int isExit(MailList record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);





    List<Map<String, Object>> getClassTreeList(@Param("classId") String classId, @Param("name") String name);

    List<Map<String, Object>> getTeacherTreeList(@Param("name") String name);

    List<Map<String, Object>> getTreeList();

    int updateClassesByIds(@Param("classId") String classId, @Param("ids") String ids);

    List<MailListContent> getMailListContent(@Param("classId") String classId);


    List<Map<String, Object>> getClassList();

    List<Map<String, Object>> getMeClassList(String id);







}