package com.huacainfo.ace.iop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.iop.model.EvTemplete;
import com.huacainfo.ace.iop.vo.EvTempleteQVo;
import com.huacainfo.ace.iop.vo.EvTempleteVo;

public interface EvTempleteService {

    PageResult<EvTempleteVo> findEvTempleteList(EvTempleteQVo condition, int start, int limit, String orderBy) throws Exception;

    MessageResponse insertEvTemplete(EvTemplete o, UserProp userProp) throws Exception;

    MessageResponse updateEvTemplete(EvTemplete o, UserProp userProp) throws Exception;

    SingleResult<EvTempleteVo> selectEvTempleteByPrimaryKey(String id) throws Exception;

    MessageResponse deleteEvTempleteByEvTempleteId(String id, UserProp userProp) throws Exception;

    MessageResponse saveOrUpdateEvTemplete(EvTemplete o, UserProp userProp) throws Exception;

}
