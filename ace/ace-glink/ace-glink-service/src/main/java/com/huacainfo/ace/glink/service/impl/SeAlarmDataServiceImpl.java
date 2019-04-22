package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

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
import com.huacainfo.ace.glink.dao.SeAlarmDataDao;
import com.huacainfo.ace.glink.model.SeAlarmData;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.SeAlarmDataService;
import com.huacainfo.ace.glink.vo.SeAlarmDataVo;
import com.huacainfo.ace.glink.vo.SeAlarmDataQVo;

@Service("seAlarmDataService")
/**
 * @author: luocan
 * @version: 2019-04-18
 * @Description: TODO(强电 - 报警数据)
 */
public class SeAlarmDataServiceImpl implements SeAlarmDataService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeAlarmDataDao seAlarmDataDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(强电 - 报警数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeAlarmDataVo>
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public PageResult<SeAlarmDataVo> findSeAlarmDataList(SeAlarmDataQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<SeAlarmDataVo> rst = new PageResult<>();
        List<SeAlarmDataVo> list = seAlarmDataDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.seAlarmDataDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSeAlarmData
     * @Description: TODO(添加强电 - 报警数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse insertSeAlarmData(SeAlarmData o, UserProp userProp) throws Exception {
        seAlarmDataDao.allClear();
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);
        o.setStatus("1");
        if (CommonUtils.isBlank(o.getAreaNodeID())) {
            return new MessageResponse(1, "区域节点编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        int temp = this.seAlarmDataDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "强电-报警数据名称重复！");
        }
        o.setCreateDate(new Date());
        this.seAlarmDataDao.insert(o);
        this.dataBaseLogService.log("添加强电-报警数据", "强电-报警数据", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSeAlarmData
     * @Description: TODO(更新强电 - 报警数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateSeAlarmData(SeAlarmData o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaNodeID())) {
            return new MessageResponse(1, "区域节点编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        this.seAlarmDataDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更强电-报警数据", "强电-报警数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSeAlarmDataByPrimaryKey
     * @Description: TODO(获取强电 - 报警数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeAlarmData>
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public SingleResult<SeAlarmDataVo> selectSeAlarmDataByPrimaryKey(String id) throws Exception {
        SingleResult<SeAlarmDataVo> rst = new SingleResult<>();
        rst.setValue(this.seAlarmDataDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSeAlarmDataBySeAlarmDataId
     * @Description: TODO(删除强电 - 报警数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeAlarmDataBySeAlarmDataId(String id, UserProp userProp) throws
            Exception {
        this.seAlarmDataDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除强电-报警数据", "强电-报警数据", id, id,
                "强电-报警数据", userProp);
        return new MessageResponse(0, "删除成功！");
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
     * @author: luocan
     * @version: 2019-04-18
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map
                <String, Object> row : list) {
            SeAlarmData o = new SeAlarmData();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getAreaNodeID())) {
                return new MessageResponse(1, "区域节点编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态不能为空！");
            }

            int t = this.seAlarmDataDao.isExit(o);
            if (t > 0) {
                this.seAlarmDataDao.updateByPrimaryKey(o);

            } else {
                this.seAlarmDataDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("强电-报警数据导入", "强电-报警数据", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除强电 - 报警数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeAlarmDataBySeAlarmDataIds(List<String> list, UserProp userProp)
            throws Exception {

        this.seAlarmDataDao.deleteByPrimaryKeys(list);
        this.dataBaseLogService.log("批量删除强电-报警数据", "强电-报警数据", list.get(0),
                list.get(0), "强电-报警数据", userProp);
        return new MessageResponse(0, "删除成功！");

    }

}
