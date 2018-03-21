package com.huacainfo.ace.iop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.iop.model.EvTaskData;
import com.huacainfo.ace.iop.vo.EvTaskDataQVo;
import com.huacainfo.ace.iop.vo.EvTaskDataVo;

public interface EvTaskDataService {

    PageResult<EvTaskDataVo> findEvTaskDataList(EvTaskDataQVo condition, int start, int limit, String orderBy) throws Exception;

    MessageResponse insertEvTaskData(String jsons, UserProp userProp) throws Exception;

    MessageResponse updateEvTaskData(EvTaskData o, UserProp userProp) throws Exception;

    SingleResult<EvTaskDataVo> selectEvTaskDataByPrimaryKey(String id) throws Exception;

    MessageResponse deleteEvTaskDataByEvTaskDataId(String id, UserProp userProp) throws Exception;

    MessageResponse saveOrUpdateEvTaskData(EvTaskData o, UserProp userProp) throws Exception;

    MessageResponse insertWWWEvTaskData(String jsons, String ip) throws Exception;

    MessageResponse insertZMEvTaskData(String jsons, String mobile)
            throws Exception;

}
