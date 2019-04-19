package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.SeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.fe.PresetDataOut;
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
import com.huacainfo.ace.glink.dao.SePresetDataDao;
import com.huacainfo.ace.glink.model.SePresetData;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.SePresetDataService;
import com.huacainfo.ace.glink.vo.SePresetDataVo;
import com.huacainfo.ace.glink.vo.SePresetDataQVo;

@Service("sePresetDataService")
/**
 * @author: huacai003
 * @version: 2019-04-18
 * @Description: TODO(场景定义数据)
 */
public class SePresetDataServiceImpl implements SePresetDataService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SePresetDataDao sePresetDataDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(场景定义数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SePresetDataVo>
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public PageResult
            <SePresetDataVo> findSePresetDataList(SePresetDataQVo condition,
                                                  int start, int limit, String orderBy) throws Exception {
        PageResult
                <SePresetDataVo> rst = new PageResult<>();
        List
                <SePresetDataVo> list = sePresetDataDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.sePresetDataDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSePresetData
     * @Description: TODO(添加场景定义数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse insertSePresetData(SePresetData o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPresetNo())) {
            return new MessageResponse(1, "场景编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.sePresetDataDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "场景定义数据名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        this.sePresetDataDao.insert(o);
        this.dataBaseLogService.log("添加场景定义数据", "场景定义数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSePresetData
     * @Description: TODO(更新场景定义数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateSePresetData(SePresetData o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPresetNo())) {
            return new MessageResponse(1, "场景编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        this.sePresetDataDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更场景定义数据", "场景定义数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSePresetDataByPrimaryKey
     * @Description: TODO(获取场景定义数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SePresetData>
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public SingleResult
            <SePresetDataVo> selectSePresetDataByPrimaryKey(String id) throws Exception {
        SingleResult
                <SePresetDataVo> rst = new SingleResult<>();
        rst.setValue(this.sePresetDataDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSePresetDataBySePresetDataId
     * @Description: TODO(删除场景定义数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSePresetDataBySePresetDataId(String id, UserProp userProp) throws
            Exception {
        this.sePresetDataDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除场景定义数据", "场景定义数据", id, id,
                "场景定义数据", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核场景定义数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核场景定义数据", "场景定义数据", id, id,
                "场景定义数据", userProp);
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
     * @author: huacai003
     * @version: 2019-04-18
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map
                <String
                        , Object> row : list) {
            SePresetData o = new SePresetData();
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
            if (CommonUtils.isBlank(o.getPresetNo())) {
                return new MessageResponse(1, "场景编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态不能为空！");
            }

            int t = this.sePresetDataDao.isExit(o);
            if (t > 0) {
                this.sePresetDataDao.updateByPrimaryKey(o);

            } else {
                this.sePresetDataDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("场景定义数据导入", "场景定义数据", "",
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
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.sePresetDataDao.getList(p));
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
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = this.sePresetDataDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除场景定义数据）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSePresetDataBySePresetDataIds(String[] id, UserProp userProp)
            throws Exception {
        this.sePresetDataDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除场景定义数据", "场景定义数据", id[0],
                id[0], "场景定义数据", userProp);
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
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.sePresetDataDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "场景定义数据", id, id,
                "场景定义数据", userProp);
        return new MessageResponse(0, "成功！");
    }


    /**
     * 同步场景定义数据
     *
     * @param userProp 操作用户
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse syncData(UserProp userProp) {
        //http请求，获取远程服务器数据
        PresetDataOut out = SeApiToolKit.getPresetData();
        //1、清理库中原有数据
        this.sePresetDataDao.clearAll();
        //2、填充获取的新数据
        List<PresetDataOut.PresetData> PresetDataGroup = out.getPresetData();
        SePresetData sePresetData;
        for (PresetDataOut.PresetData item : PresetDataGroup) {
            sePresetData = new SePresetData();
            sePresetData.setId(GUIDUtil.getGUID());
            sePresetData.setPresetNo(item.getPresetNo());
            sePresetData.setPresetName(item.getPresetName());
            sePresetData.setRemark("同步数据填充");
            sePresetData.setStatus("1");
            sePresetData.setCreateDate(new Date());
            //插入配电箱
            this.sePresetDataDao.insert(sePresetData);
        }
        return new MessageResponse(ResultCode.SUCCESS, "同步成功");
    }

}
