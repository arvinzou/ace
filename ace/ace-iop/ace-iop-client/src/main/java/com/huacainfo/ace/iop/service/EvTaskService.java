package com.huacainfo.ace.iop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.CheckTree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.iop.model.EvTask;
import com.huacainfo.ace.iop.vo.EvTaskQVo;
import com.huacainfo.ace.iop.vo.EvTaskVo;


import java.util.List;
import java.util.Map;

public interface EvTaskService {
    PageResult<EvTaskVo> findEvTaskList(EvTaskQVo condition, int start, int limit, String orderBy) throws Exception;

    MessageResponse insertEvTask(EvTask o, UserProp userProp) throws Exception;

    MessageResponse updateEvTask(EvTask o, UserProp userProp) throws Exception;

    SingleResult<EvTaskVo> selectEvTaskByPrimaryKey(String id) throws Exception;

    MessageResponse deleteEvTaskByEvTaskId(String id, UserProp userProp) throws Exception;

    MessageResponse saveOrUpdateEvTask(EvTask o, UserProp userProp) throws Exception;

    MessageResponse updateByPrimaryKeySelective(EvTask o, UserProp userProp) throws Exception;

    List<EvTaskVo> findMyEvTaskList(EvTaskQVo condition) throws Exception;

    List<CheckTree> selectDepAndUsersTreeList(String id, String taskId);

    List<Map<String, Object>> selectVoteTeacherListByQr(Map<String, Object> p) throws Exception;

    List<Map<String, Object>> selectVoteClassesListByDeptId(Map<String, Object> p) throws Exception;

    List<Map<String, Object>> selectZMTeacherBYTaskId(String takId, String orderBy) throws Exception;

}
