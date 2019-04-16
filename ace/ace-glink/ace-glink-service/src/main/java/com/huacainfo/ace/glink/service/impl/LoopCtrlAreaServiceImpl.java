package com.huacainfo.ace.glink.service.impl;


import java.util.*;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
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
import com.huacainfo.ace.glink.dao.LoopCtrlAreaDao;
import com.huacainfo.ace.glink.model.LoopCtrlArea;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.LoopCtrlAreaService;
import com.huacainfo.ace.glink.vo.LoopCtrlAreaVo;
import com.huacainfo.ace.glink.vo.LoopCtrlAreaQVo;

@Service("loopCtrlAreaService")
/**
 * @author: heshuang
 * @version: 2019-04-15
 * @Description: TODO(区级整体控制)
 */
public class LoopCtrlAreaServiceImpl implements LoopCtrlAreaService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoopCtrlAreaDao loopCtrlAreaDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(区级整体控制分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <LoopCtrlAreaVo>
     * @author: heshuang
     * @version: 2019-04-15
     */
    @Override
    public Map<String, List<LoopCtrlAreaVo>> findLoopCtrlAreaList(Map<String, List<LoopCtrlAreaVo>> p, LoopCtrlAreaQVo condition,
                                                                  int start, int limit, String orderBy) throws Exception {
        PageResult<LoopCtrlAreaVo> rst = new PageResult<>();
        List<LoopCtrlAreaVo> list = loopCtrlAreaDao.findList(condition, 0, 500, orderBy);

        List<LoopCtrlAreaVo> ListVo = new ArrayList<LoopCtrlAreaVo>();
        LoopCtrlAreaVo dataItem;
        Map<String, List<LoopCtrlAreaVo>> resultMap = new HashMap<String, List<LoopCtrlAreaVo>>();
        for (int i = 0; i < list.size(); i++) {
            dataItem = list.get(i);
            if (resultMap.containsKey(dataItem.getAreaCode())) {
                resultMap.get(dataItem.getAreaCode()).add(dataItem);
            } else {
                List<LoopCtrlAreaVo> loopCtrlAreaList = new ArrayList<LoopCtrlAreaVo>();
                loopCtrlAreaList.add(dataItem);
                ListVo.addAll(loopCtrlAreaList);
                resultMap.put(dataItem.getAreaCode(), loopCtrlAreaList);
            }
        }
        // rst.setRows(loopCtrlAreaList);
       /* if (start <= 1) {
            int allRows = this.loopCtrlAreaDao.findCount(condition);
            rst.setTotal(allRows);
        }*/

        return resultMap;
    }

    /**
     * @throws
     * @Title:insertLoopCtrlArea
     * @Description: TODO(添加区级整体控制)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse insertLoopCtrlArea(LoopCtrlArea o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoopName())) {
            return new MessageResponse(1, "回路名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoopKey())) {
            return new MessageResponse(1, "回路编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getState())) {
            return new MessageResponse(1, "状态码不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        int temp = this.loopCtrlAreaDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "区级整体控制名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");

        this.loopCtrlAreaDao.insert(o);
        this.dataBaseLogService.log("添加区级整体控制", "区级整体控制", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateLoopCtrlArea
     * @Description: TODO(更新区级整体控制)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse updateLoopCtrlArea(LoopCtrlArea o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoopName())) {
            return new MessageResponse(1, "回路名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoopKey())) {
            return new MessageResponse(1, "回路编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getState())) {
            return new MessageResponse(1, "状态码不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        this.loopCtrlAreaDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更区级整体控制", "区级整体控制", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectLoopCtrlAreaByPrimaryKey
     * @Description: TODO(获取区级整体控制)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LoopCtrlArea>
     * @author: heshuang
     * @version: 2019-04-15
     */
    @Override
    public SingleResult
            <LoopCtrlAreaVo> selectLoopCtrlAreaByPrimaryKey(String id) throws Exception {
        SingleResult
                <LoopCtrlAreaVo> rst = new SingleResult<>();
        rst.setValue(this.loopCtrlAreaDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLoopCtrlAreaByLoopCtrlAreaId
     * @Description: TODO(删除区级整体控制)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse deleteLoopCtrlAreaByLoopCtrlAreaId(String id, UserProp userProp) throws
            Exception {
        this.loopCtrlAreaDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除区级整体控制", "区级整体控制", id, id,
                "区级整体控制", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核区级整体控制)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核区级整体控制", "区级整体控制", id, id,
                "区级整体控制", userProp);
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
     * @version: 2019-04-15
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            LoopCtrlArea o = new LoopCtrlArea();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            //  o.setCreateUserId(userProp.getUserId());
            //   o.setCreateUserName(userProp.getName());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getLoopName())) {
                return new MessageResponse(1, "回路名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getLoopKey())) {
                return new MessageResponse(1, "回路编码不能为空！");
            }
            if (CommonUtils.isBlank(o.getState())) {
                return new MessageResponse(1, "状态码不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.loopCtrlAreaDao.isExit(o);
            if (t > 0) {
                this.loopCtrlAreaDao.updateByPrimaryKey(o);

            } else {
                this.loopCtrlAreaDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("区级整体控制导入", "区级整体控制", "",
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
     * @version: 2019-04-15
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.loopCtrlAreaDao.getList(p));

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
     * @version: 2019-04-15
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = this.loopCtrlAreaDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除区级整体控制 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse deleteLoopCtrlAreaByLoopCtrlAreaIds(String[] id, UserProp userProp)
            throws Exception {

        this.loopCtrlAreaDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除区级整体控制", "区级整体控制", id[0],
                id[0], "区级整体控制", userProp);
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
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.loopCtrlAreaDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "区级整体控制", id, id,
                "区级整体控制", userProp);
        return new MessageResponse(0, "成功！");
    }

    @Override
    public MessageResponse updateState(String id, String state, String areaCode) throws Exception {
        this.loopCtrlAreaDao.updateState(id, state, areaCode);
        return new MessageResponse(0, "成功！");
    }

}
