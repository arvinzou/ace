package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.dao.CanvasTmplItemDao;
import com.huacainfo.ace.portal.model.CanvasTmplItem;
import com.huacainfo.ace.portal.service.CanvasTmplItemService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.vo.CanvasTmplItemQVo;
import com.huacainfo.ace.portal.vo.CanvasTmplItemVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("canvasTmplItemService")
/**
 * @author: Arvin
 * @version: 2018-07-05
 * @Description: TODO(绘图模板-子项)
 */
public class CanvasTmplItemServiceImpl implements CanvasTmplItemService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CanvasTmplItemDao canvasTmplItemDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

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
    @Override
    public PageResult<CanvasTmplItemVo> findCanvasTmplItemList(CanvasTmplItemQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<CanvasTmplItemVo> rst = new PageResult<CanvasTmplItemVo>();
        List<CanvasTmplItemVo> list = this.canvasTmplItemDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.canvasTmplItemDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCanvasTmplItem
     * @Description: TODO(添加绘图模板-子项)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public MessageResponse insertCanvasTmplItem(CanvasTmplItem o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTmplId())) {
            return new MessageResponse(1, "模板ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemType())) {
            return new MessageResponse(1, "绘制类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.canvasTmplItemDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "绘图模板-子项名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.canvasTmplItemDao.insert(o);
        this.dataBaseLogService.log("添加绘图模板-子项", "绘图模板-子项", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加绘图模板-子项完成！");
    }

    /**
     * @throws
     * @Title:updateCanvasTmplItem
     * @Description: TODO(更新绘图模板-子项)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public MessageResponse updateCanvasTmplItem(CanvasTmplItem o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTmplId())) {
            return new MessageResponse(1, "模板ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemType())) {
            return new MessageResponse(1, "绘制类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.canvasTmplItemDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更绘图模板-子项", "绘图模板-子项", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更绘图模板-子项完成！");
    }

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
    @Override
    public SingleResult<CanvasTmplItemVo> selectCanvasTmplItemByPrimaryKey(String id) throws Exception {
        SingleResult<CanvasTmplItemVo> rst = new SingleResult<CanvasTmplItemVo>();
        rst.setValue(this.canvasTmplItemDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCanvasTmplItemByCanvasTmplItemId
     * @Description: TODO(删除绘图模板-子项)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public MessageResponse deleteCanvasTmplItemByCanvasTmplItemId(String id,
                                                                  UserProp userProp) throws Exception {
        this.canvasTmplItemDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除绘图模板-子项", "绘图模板-子项", String.valueOf(id),
                String.valueOf(id), "绘图模板-子项", userProp);
        return new MessageResponse(0, "绘图模板-子项删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取绘图模板-子项列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.canvasTmplItemDao.getList(params));
        return rst;
    }

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
    @Override
    public Map<String, Object> getById(String id) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.canvasTmplItemDao.getById(id));
        return rst;
    }
}
