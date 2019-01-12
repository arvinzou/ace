package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.Files;
import com.huacainfo.ace.partyschool.vo.FilesVo;
import com.huacainfo.ace.partyschool.vo.FilesQVo;

/**
 * @author: Arvin
 * @version: 2019-01-04
 * @Description: TODO(班级文件)
 */
public interface ClsFilesService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(班级文件分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FilesVo>
     * @author: Arvin
     * @version: 2019-01-04
     */
    PageResult<FilesVo> findFilesList(FilesQVo condition, int start, int limit, String orderBy) throws Exception;

    ResultResponse findFilesListVo(FilesQVo condition, int start, int limit, String orderBy, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:insertFiles
     * @Description: TODO(添加班级文件)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-04
     */
    MessageResponse insertFiles(Files obj, UserProp userProp) throws Exception;

    ResultResponse insertFilesVo(Files obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFiles
     * @Description: TODO(更新班级文件)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-04
     */
    MessageResponse updateFiles(Files obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFilesByPrimaryKey
     * @Description: TODO(获取班级文件)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Files>
     * @author: Arvin
     * @version: 2019-01-04
     */
    SingleResult
            <FilesVo> selectFilesByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFilesByFilesId
     * @Description: TODO(删除班级文件)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-04
     */
    MessageResponse deleteFilesByFilesId(String id, UserProp userProp) throws Exception;

}
