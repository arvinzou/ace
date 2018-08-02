package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.canvas.DrawItem;
import com.huacainfo.ace.portal.model.CanvasTmpl;
import com.huacainfo.ace.portal.vo.CanvasTmplQVo;
import com.huacainfo.ace.portal.vo.CanvasTmplVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2018-07-05
 * @Description: TODO(绘图模板)
 */
public interface CanvasTmplService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(绘图模板分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<CanvasTmplVo>
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract PageResult<CanvasTmplVo> findCanvasTmplList(CanvasTmplQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCanvasTmpl
     * @Description: TODO(添加绘图模板)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract MessageResponse insertCanvasTmpl(CanvasTmpl obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCanvasTmpl
     * @Description: TODO(更新绘图模板)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract MessageResponse updateCanvasTmpl(CanvasTmpl obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCanvasTmplByPrimaryKey
     * @Description: TODO(获取绘图模板)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CanvasTmpl>
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract SingleResult<CanvasTmplVo> selectCanvasTmplByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCanvasTmplByCanvasTmplId
     * @Description: TODO(删除绘图模板)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract MessageResponse deleteCanvasTmplByCanvasTmplId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(查询绘图模板)
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
     * @Description: TODO(获取绘图模板)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2018-07-05
     */
    public abstract Map<String, Object> getById(String id) throws Exception;

    /**
     * 获取系统下所有绘制模板
     *
     * @param sysId 系统ID
     * @return List<CanvasTmpl>
     */
    List<CanvasTmpl> findBySysId(String sysId);

    /**
     * 获取绘画子项配置
     *
     * @param tmplId 绘制模板ID
     * @return Map<String, DrawItem>
     */
    Map<String, DrawItem> getDrawItem(String tmplId);
}
