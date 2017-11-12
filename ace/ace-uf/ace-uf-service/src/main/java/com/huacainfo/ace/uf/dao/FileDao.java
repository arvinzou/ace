package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.vo.FileQVo;
import com.huacainfo.ace.uf.vo.FileVo;
import com.huacainfo.ace.uf.vo.PersonageQVo;
import com.huacainfo.ace.uf.vo.PersonageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileDao {

    /**
     * 获取文件列表
     *
     * @param condition
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    List<FileVo> findFileList(@Param("condition") FileQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    /**
     * 获取文件总数。
     *
     * @param condition
     * @return
     */
    int findFileCount(@Param("condition") FileQVo condition);

    /**
     * 更新统战文件。
     */

    int updateFileById(FileQVo file);

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    int deleteFileByFileId(String id);

    /**
     * 添加文件
     */
    int insertFile(FileQVo file);
}
