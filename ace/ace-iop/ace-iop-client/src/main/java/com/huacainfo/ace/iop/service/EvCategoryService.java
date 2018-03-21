package com.huacainfo.ace.iop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.iop.vo.EvCategoryQVo;
import com.huacainfo.ace.iop.vo.EvCategoryVo;
import com.huacainfo.ace.iop.model.EvCategory;

public interface EvCategoryService {

    PageResult<EvCategoryVo> findEvCategoryList(EvCategoryQVo condition, int start, int limit, String orderBy) throws Exception;

    MessageResponse insertEvCategory(EvCategory o, UserProp userProp) throws Exception;

    MessageResponse updateEvCategory(EvCategory o, UserProp userProp) throws Exception;

    SingleResult<EvCategoryVo> selectEvCategoryByPrimaryKey(String id) throws Exception;

    MessageResponse deleteEvCategoryByEvCategoryId(String id, UserProp userProp) throws Exception;

    MessageResponse saveOrUpdateEvCategory(EvCategory o, UserProp userProp) throws Exception;

}
