package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

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
import com.huacainfo.ace.glink.dao.SeTimerDataDao;
import com.huacainfo.ace.glink.model.SeTimerData;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.SeTimerDataService;
import com.huacainfo.ace.glink.vo.SeTimerDataVo;
import com.huacainfo.ace.glink.vo.SeTimerDataQVo;

@Service("seTimerDataService")
/**
 * @author: heshuang
 * @version: 2019-04-19
 * @Description: TODO(定时任务数据)
 */
public class SeTimerDataServiceImpl implements SeTimerDataService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeTimerDataDao seTimerDataDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(定时任务数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeTimerDataVo>
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public PageResult
            <SeTimerDataVo> findSeTimerDataList(SeTimerDataQVo condition,
                                                int start, int limit, String orderBy) throws Exception {
        PageResult
                <SeTimerDataVo> rst = new PageResult<>();
        List
                <SeTimerDataVo> list = seTimerDataDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.seTimerDataDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSeTimerData
     * @Description: TODO(添加定时任务数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public MessageResponse insertSeTimerData(SeTimerData o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTimerID())) {
            return new MessageResponse(1, "定时任务序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.seTimerDataDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "定时任务数据名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");

        this.seTimerDataDao.insert(o);
        this.dataBaseLogService.log("添加定时任务数据", "定时任务数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSeTimerData
     * @Description: TODO(更新定时任务数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public MessageResponse updateSeTimerData(SeTimerData o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTimerID())) {
            return new MessageResponse(1, "定时任务序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        this.seTimerDataDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更定时任务数据", "定时任务数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSeTimerDataByPrimaryKey
     * @Description: TODO(获取定时任务数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeTimerData>
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public SingleResult
            <SeTimerDataVo> selectSeTimerDataByPrimaryKey(String id) throws Exception {
        SingleResult
                <SeTimerDataVo> rst = new SingleResult<>();
        rst.setValue(this.seTimerDataDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSeTimerDataBySeTimerDataId
     * @Description: TODO(删除定时任务数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public MessageResponse deleteSeTimerDataBySeTimerDataId(String id, UserProp userProp) throws
            Exception {
        this.seTimerDataDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除定时任务数据", "定时任务数据", id, id,
                "定时任务数据", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核定时任务数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核定时任务数据", "定时任务数据", id, id,
                "定时任务数据", userProp);
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
     * @version: 2019-04-19
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
            SeTimerData o = new SeTimerData();
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
            if (CommonUtils.isBlank(o.getTimerID())) {
                return new MessageResponse(1, "定时任务序号不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态不能为空！");
            }

            int t = this.seTimerDataDao.isExit(o);
            if (t > 0) {
                this.seTimerDataDao.updateByPrimaryKey(o);

            } else {
                this.seTimerDataDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("定时任务数据导入", "定时任务数据", "",
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
     * @version: 2019-04-19
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
        rst.setValue(this.seTimerDataDao.getList(p));

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
     * @version: 2019-04-19
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
                                , Object>> list = this.seTimerDataDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除定时任务数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public MessageResponse deleteSeTimerDataBySeTimerDataIds(String[] id, UserProp userProp)
            throws Exception {

        this.seTimerDataDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除定时任务数据", "定时任务数据", id[0],
                id[0], "定时任务数据", userProp);
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
     * @version: 2019-04-19
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.seTimerDataDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "定时任务数据", id, id,
                "定时任务数据", userProp);
        return new MessageResponse(0, "成功！");
    }

}
