package com.huacainfo.ace.luohua.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.luohua.model.Writer;
import com.huacainfo.ace.luohua.vo.WriterQVo;
import com.huacainfo.ace.luohua.vo.WriterVo;

import java.util.Map;

public interface WriterService {

    public abstract PageResult<WriterVo> findWriterList(WriterQVo condition, int start, int limit, String orderBy) throws Exception;

    public abstract MessageResponse insertWriter(Writer obj, UserProp userProp) throws Exception;

    public abstract MessageResponse updateWriter(Writer obj, UserProp userProp) throws Exception;

    public abstract SingleResult<Writer> selectWriterByPrimaryKey(String id) throws Exception;

    public abstract MessageResponse deleteWriterByWriterId(String id, UserProp userProp) throws Exception;

    public Map<String, Object> selectAuthor(Map<String, Object> params) throws Exception;


}
