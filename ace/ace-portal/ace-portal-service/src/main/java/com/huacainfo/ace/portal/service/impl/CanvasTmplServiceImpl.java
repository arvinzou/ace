package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.canvas.DrawItem;
import com.huacainfo.ace.portal.dao.CanvasTmplDao;
import com.huacainfo.ace.portal.dao.CanvasTmplItemDao;
import com.huacainfo.ace.portal.model.CanvasTmpl;
import com.huacainfo.ace.portal.model.CanvasTmplItem;
import com.huacainfo.ace.portal.service.CanvasTmplService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.vo.CanvasTmplQVo;
import com.huacainfo.ace.portal.vo.CanvasTmplVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("canvasTmplService")
/**
 * @author: Arvin
 * @version: 2018-07-05
 * @Description: TODO(绘图模板)
 */
public class CanvasTmplServiceImpl implements CanvasTmplService {
    private static final String DRAW_IMAGE = "0";
    private static final String DRAW_TEXT = "1";
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CanvasTmplDao canvasTmplDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CanvasTmplItemDao canvasTmplItemDao;

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
    @Override
    public PageResult<CanvasTmplVo> findCanvasTmplList(CanvasTmplQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<CanvasTmplVo> rst = new PageResult<CanvasTmplVo>();
        List<CanvasTmplVo> list = this.canvasTmplDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.canvasTmplDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCanvasTmpl
     * @Description: TODO(添加绘图模板)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public MessageResponse insertCanvasTmpl(CanvasTmpl o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getBaseImageUrl())) {
            return new MessageResponse(1, "底图地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getSysId())) {
            return new MessageResponse(1, "系统标识不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.canvasTmplDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "绘图模板名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.canvasTmplDao.insert(o);
        this.dataBaseLogService.log("添加绘图模板", "绘图模板", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加绘图模板完成！");
    }

    /**
     * @throws
     * @Title:updateCanvasTmpl
     * @Description: TODO(更新绘图模板)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public MessageResponse updateCanvasTmpl(CanvasTmpl o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getBaseImageUrl())) {
            return new MessageResponse(1, "底图地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getSysId())) {
            return new MessageResponse(1, "系统标识不能为空！");
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
        this.canvasTmplDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更绘图模板", "绘图模板", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更绘图模板完成！");
    }

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
    @Override
    public SingleResult<CanvasTmplVo> selectCanvasTmplByPrimaryKey(String id) throws Exception {
        SingleResult<CanvasTmplVo> rst = new SingleResult<CanvasTmplVo>();
        rst.setValue(this.canvasTmplDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCanvasTmplByCanvasTmplId
     * @Description: TODO(删除绘图模板)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public MessageResponse deleteCanvasTmplByCanvasTmplId(String id,
                                                          UserProp userProp) throws Exception {
        this.canvasTmplDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除绘图模板", "绘图模板", String.valueOf(id),
                String.valueOf(id), "绘图模板", userProp);
        return new MessageResponse(0, "绘图模板删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取绘图模板列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.canvasTmplDao.getList(params));
        return rst;
    }

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
    @Override
    public Map<String, Object> getById(String id) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.canvasTmplDao.getById(id));
        return rst;
    }

    /**
     * 获取系统下所有绘制模板
     *
     * @param sysId 系统ID
     * @return List<CanvasTmpl>
     */
    @Override
    public List<CanvasTmpl> findBySysId(String sysId) {
        return canvasTmplDao.findBySysId(sysId);
    }

    /**
     * 获取绘画子项配置
     *
     * @param tmplId 绘制模板ID
     * @return Map<String, DrawItem>
     */
    @Override
    /**
     * 获取绘画子项配置
     *
     * @param tmplId 绘制模板ID
     * @return Map<String, DrawItem>
     */
    public Map<String, DrawItem> getDrawItem(String tmplId) {
        //绘制子项
        List<CanvasTmplItem> tmplItemList = canvasTmplItemDao.findItemList(tmplId);
        if (CollectionUtils.isEmpty(tmplItemList)) {
            return null;
        }
        Map<String, DrawItem> itemMap = new HashMap<>();
        DrawItem drawItem;
        String[] fontConfig;
        for (CanvasTmplItem item : tmplItemList) {
            if (DRAW_IMAGE.equals(item.getItemType())) {
                drawItem = new DrawItem(item.getPositionX(), item.getPositionY(), item.getWidth(), item.getHeight());
                itemMap.put(item.getItemCode(), drawItem);
                continue;
            }
            if (DRAW_TEXT.equals(item.getItemType())) {
                fontConfig = item.getFont().split(",");//demo: PingFang-SC-Medium,1,30
                drawItem = new DrawItem(item.getPositionX(), item.getPositionY(),
                        item.getColor(), fontConfig[0], Integer.valueOf(fontConfig[1]), Integer.valueOf(fontConfig[2]));
                itemMap.put(item.getItemCode(), drawItem);
                continue;
            }
        }

        return itemMap;
    }
}
