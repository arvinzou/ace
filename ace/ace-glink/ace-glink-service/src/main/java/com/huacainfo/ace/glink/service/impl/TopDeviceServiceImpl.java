package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.dao.TopDeviceDao;
import com.huacainfo.ace.glink.model.TopDevice;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.TopDeviceService;
import com.huacainfo.ace.glink.vo.TopDeviceVo;
import com.huacainfo.ace.glink.vo.TopDeviceQVo;

@Service("topDeviceService")
/**
 * @author: heshuang
 * @version: 2019-04-10
 * @Description: TODO(设备管理)
 */
public class TopDeviceServiceImpl implements TopDeviceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopDeviceDao topDeviceDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(设备管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TopDeviceVo>
     * @author: heshuang
     * @version: 2019-04-10
     */
    @Override
    public PageResult
            <TopDeviceVo> findTopDeviceList(TopDeviceQVo condition,
                                            int start, int limit, String orderBy) throws Exception {
        PageResult
                <TopDeviceVo> rst = new PageResult<>();
        List
                <TopDeviceVo> list = this.topDeviceDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.topDeviceDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTopDevice
     * @Description: TODO(添加设备管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse insertTopDevice(TopDevice o, UserProp userProp) throws Exception {


        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "设备编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "设备名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "设备类型不能为空！");
        }


        int temp = this.topDeviceDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "设备编号重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("2");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.topDeviceDao.insert(o);
        this.dataBaseLogService.log("添加设备管理", "设备管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTopDevice
     * @Description: TODO(更新设备管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse updateTopDevice(TopDevice o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "设备编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "设备名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "设备类型不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.topDeviceDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更设备管理", "设备管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTopDeviceByPrimaryKey
     * @Description: TODO(获取设备管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopDevice>
     * @author: heshuang
     * @version: 2019-04-10
     */
    @Override
    public SingleResult
            <TopDeviceVo> selectTopDeviceByPrimaryKey(String id) throws Exception {
        SingleResult
                <TopDeviceVo> rst = new SingleResult<>();
        rst.setValue(this.topDeviceDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTopDeviceByTopDeviceId
     * @Description: TODO(删除设备管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse deleteTopDeviceByTopDeviceId(String id, UserProp userProp) throws
            Exception {
        this.topDeviceDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除设备管理", "设备管理", id, id,
                "设备管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核设备管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核设备管理", "设备管理", id, id,
                "设备管理", userProp);
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
     * @version: 2019-04-10
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        String importDateTime = DateUtil.getNow();
        int i = 1;
        for (Map<String, Object> row : list) {
            TopDevice o = new TopDevice();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setId(GUIDUtil.getGUID());
            o.setCreateDate(new Date());
            o.setStatus("2");
            o.setCreateUserName(userProp.getName());
            o.setCreateUserId(userProp.getUserId());
            o.setRemark("批量导入设备：" + importDateTime);
            if (CommonUtils.isBlank(o.getCode())) {
                return new MessageResponse(1, "设备编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "设备名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getType())) {
                return new MessageResponse(1, "设备类型不能为空！");
            }


            int t = this.topDeviceDao.isExit(o);
            if (t > 0) {
                //continue;
                this.topDeviceDao.updateByPrimaryKey(o);

            } else {

                this.topDeviceDao.insert(o);

            }
            i++;

        }
        this.dataBaseLogService.log("设备管理导入", "设备管理", "",
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
     * @version: 2019-04-10
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.topDeviceDao.getList(p));

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
     * @version: 2019-04-10
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = this.topDeviceDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除设备管理 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse deleteTopDeviceByTopDeviceIds(String[] id, UserProp userProp)
            throws Exception {

        this.topDeviceDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除设备管理", "设备管理", id[0],
                id[0], "设备管理", userProp);
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
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.topDeviceDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "设备管理", id, id,
                "设备管理", userProp);
        return new MessageResponse(0, "成功！");
    }

}
