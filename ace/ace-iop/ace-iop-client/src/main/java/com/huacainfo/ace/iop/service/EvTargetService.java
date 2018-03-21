package com.huacainfo.ace.iop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.iop.model.EvTarget;
import com.huacainfo.ace.iop.vo.EvTargetQVo;
import com.huacainfo.ace.iop.vo.EvTargetVo;

public interface EvTargetService {
    PageResult<EvTargetVo> findEvTargetList(EvTargetQVo condition, int start, int limit, String orderBy) throws Exception;

    MessageResponse insertEvTarget(EvTarget o, UserProp userProp) throws Exception;

    MessageResponse updateEvTarget(EvTarget o, UserProp userProp) throws Exception;

    SingleResult<EvTargetVo> selectEvTargetByPrimaryKey(String id) throws Exception;

    MessageResponse deleteEvTargetByEvTargetId(String id, UserProp userProp) throws Exception;

    MessageResponse saveOrUpdateEvTarget(EvTarget o, UserProp userProp) throws Exception;

}
