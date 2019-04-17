package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.AnimaLnk;
import com.huacainfo.ace.glink.vo.AnimaLnkVo;
import com.huacainfo.ace.glink.vo.AnimaLnkQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: luocan
 * @version: 2019-04-10
 * @Description: TODO(节目上传)
 */
public interface AnimaLnkService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节目上传分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AnimaLnkVo>
     * @author: luocan
     * @version: 2019-04-10
     */
    PageResult<AnimaLnkVo> findAnimaLnkList(AnimaLnkQVo condition,
                                          int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertAnimaLnk
     * @Description: TODO(添加节目上传)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    MessageResponse insertAnimaLnk(List<AnimaLnk> list, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateAnimaLnk
     * @Description: TODO(更新节目上传)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    MessageResponse updateAnimaLnk(AnimaLnk obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectAnimaLnkByPrimaryKey
     * @Description: TODO(获取节目上传)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AnimaLnk>
     * @author: luocan
     * @version: 2019-04-10
     */
    SingleResult
            <AnimaLnkVo> selectAnimaLnkByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteAnimaLnkByAnimaLnkId
     * @Description: TODO(删除节目上传)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    MessageResponse deleteAnimaLnkByAnimaLnkId(String id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;

    MessageResponse updatePrePlayUrl(String id, String prePlayUrl) throws Exception;

}
