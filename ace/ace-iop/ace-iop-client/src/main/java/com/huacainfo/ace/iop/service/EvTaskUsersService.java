package com.huacainfo.ace.iop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.iop.model.EvTaskUsers;
import com.huacainfo.ace.iop.vo.EvTaskUsersQVo;
import com.huacainfo.ace.iop.vo.EvTaskUsersVo;

import java.util.List;
import java.util.Map;

public interface EvTaskUsersService {

    PageResult<EvTaskUsersVo> findEvTaskUsersList(EvTaskUsersQVo condition, int start, int limit, String orderBy) throws Exception;

    MessageResponse insertEvTaskUsers(String jsons, UserProp userProp) throws Exception;

    MessageResponse updateEvTaskUsers(EvTaskUsers o, UserProp userProp) throws Exception;

    SingleResult<EvTaskUsersVo> selectEvTaskUsersByPrimaryKey(String id) throws Exception;

    MessageResponse deleteEvTaskUsersByEvTaskUsersId(String id, UserProp userProp) throws Exception;

    MessageResponse saveOrUpdateEvTaskUsers(EvTaskUsers o, UserProp userProp) throws Exception;

    List<Map<String, Object>> selectUserListByDeptId(String evTaskId, int limit);

    MessageResponse updateForReset(String id, UserProp userProp);

    List<Map<String, Object>> selectPrintUserListByDeptId(String evTaskId, int limit);

}
