package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopResource;
import com.huacainfo.ace.fop.vo.FopResourceQVo;
import com.huacainfo.ace.fop.vo.FopResourceVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 资源文件
 */
public interface FopResourceService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: 资源文件分页查询
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopResourceVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    PageResult<FopResourceVo> findFopResourceList(FopResourceQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopResource
     * @Description: 添加资源文件
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    MessageResponse insertFopResource(FopResource obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopResource
     * @Description: 更新资源文件
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    MessageResponse updateFopResource(FopResource obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopResourceByPrimaryKey
     * @Description: 获取资源文件
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopResource>
     * @author: Arvin
     * @version: 2018-05-02
     */
    SingleResult<FopResourceVo> selectFopResourceByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopResourceByFopResourceId
     * @Description: 删除资源文件
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    MessageResponse deleteFopResourceByFopResourceId(String id, UserProp userProp) throws Exception;

}
