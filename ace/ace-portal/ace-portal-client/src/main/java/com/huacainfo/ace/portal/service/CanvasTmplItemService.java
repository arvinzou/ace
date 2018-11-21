package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.CanvasTmplItem;
import com.huacainfo.ace.portal.vo.CanvasTmplItemQVo;
import com.huacainfo.ace.portal.vo.CanvasTmplItemVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2018-07-05
 * @Description: TODO(绘图模板-子项)
 */
public interface CanvasTmplItemService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(绘图模板-子项分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<CanvasTmplItemVo>
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract PageResult<CanvasTmplItemVo> findCanvasTmplItemList(CanvasTmplItemQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCanvasTmplItem
     * @Description: TODO(添加绘图模板-子项)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract MessageResponse insertCanvasTmplItem(CanvasTmplItem obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCanvasTmplItem
     * @Description: TODO(更新绘图模板-子项)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract MessageResponse updateCanvasTmplItem(CanvasTmplItem obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCanvasTmplItemByPrimaryKey
     * @Description: TODO(获取绘图模板-子项)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CanvasTmplItem>
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract SingleResult<CanvasTmplItemVo> selectCanvasTmplItemByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCanvasTmplItemByCanvasTmplItemId
     * @Description: TODO(删除绘图模板-子项)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract MessageResponse deleteCanvasTmplItemByCanvasTmplItemId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(查询绘图模板-子项)
     * @param: @param params
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract Map<String, Object> getList(Map<String, Object> params) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取绘图模板-子项)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract Map<String, Object> getById(String id) throws Exception;

    /**
     * 绘画模板内容
     *
     * @param tmplId
     * @return
     */
    List<CanvasTmplItem> findItemList(String tmplId);
}
