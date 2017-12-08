package com.huacainfo.ace.uf.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.Personage;
import com.huacainfo.ace.uf.vo.*;

/**
 * @author hcwy
 */
public interface FileService {


    /**
     * 获取文件列表
     * @param start
     * @param limit
     * @param orderBy
     * @return
     * @throws Exception
     */
    public abstract PageResult<FileVo> findFileList(FileQVo condition,int start, int limit, String orderBy) throws Exception;

    /**
     * 根据ID更新统战文件
     * @param obj
     * @param userProp
     * @return
     * @throws Exception
     */
    public abstract MessageResponse updateFileById(FileQVo obj, UserProp userProp) throws Exception;

    /**
     * 删除统战文件
     * @param id
     * @param userProp
     * @return
     * @throws Exception
     */
    public abstract MessageResponse deleteFileByFileId(String id, UserProp userProp) throws Exception;

    /**
     * 添加统战文件
     * @param obj
     * @param userProp
     * @return
     * @throws Exception
     */
    public abstract MessageResponse insertFile(FileQVo obj, UserProp userProp) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public abstract SingleResult<FileVo> selectFileByPrimaryKey(String id) throws Exception;
}
