package com.huacainfo.ace.iop.service;

import java.util.List;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.iop.model.EvScoreTemlete;
import com.huacainfo.ace.iop.vo.EvScoreTemleteQVo;
import com.huacainfo.ace.iop.vo.EvScoreTemleteVo;

public interface EvScoreTemleteService {
    PageResult<EvScoreTemleteVo> findEvScoreTemleteList(
            EvScoreTemleteQVo condition, int start, int limit, String orderBy)
            throws Exception;

    MessageResponse insertEvScoreTemlete(EvScoreTemlete o,
                                         UserProp userProp) throws Exception;

    MessageResponse updateEvScoreTemlete(EvScoreTemlete o,
                                         UserProp userProp) throws Exception;

    SingleResult<EvScoreTemleteVo> selectEvScoreTemleteByPrimaryKey(String id)
            throws Exception;

    MessageResponse deleteEvScoreTemleteByEvScoreTemleteId(
            String id, UserProp userProp) throws Exception;

    MessageResponse saveOrUpdateEvScoreTemlete(EvScoreTemlete o,
                                               UserProp userProp) throws Exception;


    List<EvScoreTemlete> selectListAll() throws Exception;

}
