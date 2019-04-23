package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.glink.api.SeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.fe.CustomAreaOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.dao.SeCustomAreaDao;
import com.huacainfo.ace.glink.model.SeCustomArea;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.SeCustomAreaService;
import com.huacainfo.ace.glink.vo.SeCustomAreaVo;
import com.huacainfo.ace.glink.vo.SeCustomAreaQVo;

@Service("seCustomAreaService")
/**
 * @author: heshuang
 * @version: 2019-04-18
 * @Description: TODO(逻辑区数据)
 */
public class SeCustomAreaServiceImpl implements SeCustomAreaService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeCustomAreaDao seCustomAreaDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目区域数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeCustomAreaVo>
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public PageResult
            <SeCustomAreaVo> findSeCustomAreaList(SeCustomAreaQVo condition,
                                                  int start, int limit, String orderBy) throws Exception {
        PageResult<SeCustomAreaVo> rst = new PageResult<>();
        List<SeCustomAreaVo> list = seCustomAreaDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.seCustomAreaDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSeCustomArea
     * @Description: TODO(添加逻辑区数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse insertSeCustomArea(SeCustomArea o, UserProp userProp) throws Exception {

            String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
            o.setId(guid);
            if (CommonUtils.isBlank(o.getAreaName())) {
                return new MessageResponse(1, "区名称不能为空！");
            }
            int temp = this.seCustomAreaDao.isExit(o);
            if (temp > 0) {
                return new MessageResponse(1, "逻辑区数据名称重复！");
            }
            o.setCreateDate(new Date());
            o.setStatus("1");

            this.seCustomAreaDao.insert(o);


        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSeCustomArea
     * @Description: TODO(更新逻辑区数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateSeCustomArea(SeCustomArea o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaName())) {
            return new MessageResponse(1, "区名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        this.seCustomAreaDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更逻辑区数据", "逻辑区数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSeCustomAreaByPrimaryKey
     * @Description: TODO(获取逻辑区数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeCustomArea>
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public SingleResult
            <SeCustomAreaVo> selectSeCustomAreaByPrimaryKey(String id) throws Exception {
        SingleResult
                <SeCustomAreaVo> rst = new SingleResult<>();
        rst.setValue(this.seCustomAreaDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSeCustomAreaBySeCustomAreaId
     * @Description: TODO(删除逻辑区数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeCustomAreaBySeCustomAreaId(String id, UserProp userProp) throws
            Exception {
        this.seCustomAreaDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除逻辑区数据", "逻辑区数据", id, id,
                "逻辑区数据", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核逻辑区数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核逻辑区数据", "逻辑区数据", id, id,
                "逻辑区数据", userProp);
        return new MessageResponse(0, "审核成功！");
    }


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */

    @Override
    public MessageResponse importXls(List
                                             <Map
                                                     <String
                                                             , Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map
                <String
                        , Object> row : list) {
            SeCustomArea o = new SeCustomArea();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            // o.setCreateUserId(userProp.getUserId());
            //o.setCreateUserName(userProp.getName());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getAreaName())) {
                return new MessageResponse(1, "区名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态不能为空！");
            }

            int t = this.seCustomAreaDao.isExit(o);
            if (t > 0) {
                this.seCustomAreaDao.updateByPrimaryKey(o);

            } else {
                this.seCustomAreaDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("逻辑区数据导入", "逻辑区数据", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult
     * <Map
     * <String
     * ,Object>>
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public ListResult
            <Map
                    <String
                            , Object>> getList(Map
                                                       <String
                                                               , Object> p) throws Exception {
        ListResult
                <Map
                        <String
                                , Object>> rst = new ListResult<>();
        rst.setValue(this.seCustomAreaDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public Map
            <String
                    , Object> getListByCondition(Map
                                                         <String
                                                                 , Object> params) {
        Map
                <String
                        , Object> rst = new HashMap<>();
        List
                <Map
                        <String
                                , Object>> list = this.seCustomAreaDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除逻辑区数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeCustomAreaBySeCustomAreaIds(String[] id, UserProp userProp)
            throws Exception {

        this.seCustomAreaDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除逻辑区数据", "逻辑区数据", id[0],
                id[0], "逻辑区数据", userProp);
        return new MessageResponse(0, "删除成功！");

    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.seCustomAreaDao.updateStatus(id, status);
        this.dataBaseLogService.log("更新状态", "逻辑区数据", id, id,
                "逻辑区数据", userProp);
        return new MessageResponse(0, "成功！");
    }

    @Override
    public MessageResponse syncCustomData(List<SeCustomArea> list, UserProp userProp) {
        CustomAreaOut o = SeApiToolKit.getCustomAreaInfo();
        SeCustomArea area;
        List<CustomAreaOut.AreaData> itemData = o.getAreaData();
        int count = o.getAreaCount();
        for (CustomAreaOut.AreaData item : itemData) {
            area = new SeCustomArea();
            area.setId(GUIDUtil.getGUID());
            area.setAreaName(item.getAreaName());
            area.setAreaNo(item.getAreaNo());
            area.setAreaNodeID(item.getAreaNodeID());
            area.setCreateDate(new Date());
            area.setStatus("1");
            area.setRemark("同步逻辑区数据");
            this.seCustomAreaDao.insert(area);
        }
        return new MessageResponse(0, "同步成功！");
    }

}
