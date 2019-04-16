package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.dao.MapCtrlBuildingDao;
import com.huacainfo.ace.glink.model.MapCtrlBuilding;
import com.huacainfo.ace.glink.service.MapCtrlBuildingService;
import com.huacainfo.ace.glink.vo.MapCtrlBuildingQVo;
import com.huacainfo.ace.glink.vo.MapCtrlBuildingVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("mapCtrlBuildingService")
/**
 * @author: Arvin
 * @version: 2019-04-16
 * @Description: TODO(控制器映射关系)
 */
public class MapCtrlBuildingServiceImpl implements MapCtrlBuildingService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MapCtrlBuildingDao mapCtrlBuildingDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(控制器映射关系分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<MapCtrlBuildingVo>
     * @author: Arvin
     * @version: 2019-04-16
     */
    @Override
    public PageResult<MapCtrlBuildingVo> findMapCtrlBuildingList(MapCtrlBuildingQVo condition,
                                                                 int start, int limit, String orderBy) throws Exception {
        PageResult<MapCtrlBuildingVo> rst = new PageResult<>();
        List<MapCtrlBuildingVo> list = mapCtrlBuildingDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.mapCtrlBuildingDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertMapCtrlBuilding
     * @Description: TODO(添加控制器映射关系)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    @Override
    public MessageResponse insertMapCtrlBuilding(MapCtrlBuilding o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCtrlCode())) {
            return new MessageResponse(1, "控制器编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getBuildingCode())) {
            return new MessageResponse(1, "建筑物编码不能为空！");
        }


        int temp = this.mapCtrlBuildingDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "控制器编码重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        this.mapCtrlBuildingDao.insert(o);
        this.dataBaseLogService.log("添加控制器映射关系", "控制器映射关系", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateMapCtrlBuilding
     * @Description: TODO(更新控制器映射关系)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    @Override
    public MessageResponse updateMapCtrlBuilding(MapCtrlBuilding o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCtrlCode())) {
            return new MessageResponse(1, "控制器编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getBuildingCode())) {
            return new MessageResponse(1, "建筑物编码不能为空！");
        }
        int temp = this.mapCtrlBuildingDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "控制器编码重复！");
        }

        MapCtrlBuilding obj = mapCtrlBuildingDao.selectByPrimaryKey(o.getId());
        if (obj == null) {
            return new MessageResponse(1, "数据丢失！");
        }

        o.setCreateDate(obj.getCreateDate());
        o.setStatus(obj.getStatus());
        this.mapCtrlBuildingDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更控制器映射关系", "控制器映射关系", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectMapCtrlBuildingByPrimaryKey
     * @Description: TODO(获取控制器映射关系)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MapCtrlBuilding>
     * @author: Arvin
     * @version: 2019-04-16
     */
    @Override
    public SingleResult<MapCtrlBuildingVo> selectMapCtrlBuildingByPrimaryKey(String id) throws Exception {
        SingleResult<MapCtrlBuildingVo> rst = new SingleResult<>();
        rst.setValue(this.mapCtrlBuildingDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteMapCtrlBuildingByMapCtrlBuildingId
     * @Description: TODO(删除控制器映射关系)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    @Override
    public MessageResponse deleteMapCtrlBuildingByMapCtrlBuildingId(String id, UserProp userProp) throws
            Exception {
        this.mapCtrlBuildingDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除控制器映射关系", "控制器映射关系", id, id,
                "控制器映射关系", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核控制器映射关系)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核控制器映射关系", "控制器映射关系", id, id,
                "控制器映射关系", userProp);
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
     * @author: Arvin
     * @version: 2019-04-16
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            MapCtrlBuilding o = new MapCtrlBuilding();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setId(GUIDUtil.getGUID());
            o.setCreateDate(DateUtil.getNowDate());
            o.setStatus("1");

            if (CommonUtils.isBlank(o.getCtrlCode())) {
                return new MessageResponse(1, "序号[" + o.getIndex() + "]," + "控制器编码不能为空！");
            }
            if (CommonUtils.isBlank(o.getBuildingCode())) {
                return new MessageResponse(1, "序号[" + o.getIndex() + "]," + "建筑物编码不能为空！");
            }

            int t = this.mapCtrlBuildingDao.isExist(o);
            if (t > 0) {
                this.mapCtrlBuildingDao.updateByCtrlCode(o);

            } else {
                this.mapCtrlBuildingDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("控制器映射关系导入", "控制器映射关系", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }

}
