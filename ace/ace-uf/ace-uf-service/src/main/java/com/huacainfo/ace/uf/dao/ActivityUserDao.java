package com.huacainfo.ace.uf.dao;

import java.util.List;
import java.util.Map;
import com.huacainfo.ace.uf.model.ActivityUser;
public interface ActivityUserDao {
    int deletePersonageByActivityId(String id);

    int deleteByPrimaryKey(String id);

    int insert(ActivityUser record);



    List<Map<String, Object>> selectListByActivityId(String activityId);

}