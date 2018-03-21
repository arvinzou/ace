package com.huacainfo.ace.iop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.iop.model.EvScoreTemleteSub;
import com.huacainfo.ace.iop.vo.EvScoreTemleteSubQVo;
import com.huacainfo.ace.iop.vo.EvScoreTemleteSubVo;

public interface EvScoreTemleteSubService {

    PageResult<EvScoreTemleteSubVo> findEvScoreTemleteSubList(EvScoreTemleteSubQVo condition, int start, int limit, String orderBy) throws Exception;

    MessageResponse insertEvScoreTemleteSub(EvScoreTemleteSub o, UserProp userProp) throws Exception;

    MessageResponse updateEvScoreTemleteSub(EvScoreTemleteSub o, UserProp userProp) throws Exception;

    SingleResult<EvScoreTemleteSubVo> selectEvScoreTemleteSubByPrimaryKey(String id) throws Exception;

    MessageResponse deleteEvScoreTemleteSubByEvScoreTemleteSubId(String id, UserProp userProp) throws Exception;

    MessageResponse saveOrUpdateEvScoreTemleteSub(EvScoreTemleteSub o, UserProp userProp) throws Exception;

}
