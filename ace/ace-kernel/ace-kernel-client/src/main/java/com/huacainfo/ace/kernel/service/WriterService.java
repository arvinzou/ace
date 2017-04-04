package com.huacainfo.ace.kernel.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.kernel.model.Writer;
import com.huacainfo.ace.kernel.vo.WriterQVo;
import com.huacainfo.ace.kernel.vo.WriterVo;

public interface WriterService {

    public abstract PageResult<WriterVo> findWriterList(WriterQVo condition, int start, int limit, String orderBy) throws Exception;

    public abstract MessageResponse insertWriter(Writer obj, UserProp userProp) throws Exception;

    public abstract MessageResponse updateWriter(Writer obj, UserProp userProp) throws Exception;

    public abstract SingleResult<Writer> selectWriterByPrimaryKey(String id) throws Exception;

    public abstract MessageResponse deleteWriterByWriterId(String id, UserProp userProp) throws Exception;


}
