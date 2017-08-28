package com.huacainfo.ace.uf.dao;

import java.util.List;
import java.util.Map;
import com.huacainfo.ace.uf.model.ActivityComment;
public interface ActivityCommentDao {
    int deleteByPrimaryKey(String ActivityCommentId);
    int insert(ActivityComment record);
    List<Map<String, Object>> selectListByActivityId(String activityId);
}