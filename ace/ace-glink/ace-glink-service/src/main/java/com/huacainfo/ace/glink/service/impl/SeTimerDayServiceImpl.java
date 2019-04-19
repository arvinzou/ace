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
import com.huacainfo.ace.glink.dao.SeTimerDayDao;
import com.huacainfo.ace.glink.model.SeTimerDay;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.SeTimerDayService;
import com.huacainfo.ace.glink.vo.SeTimerDayVo;
import com.huacainfo.ace.glink.vo.SeTimerDayQVo;

@Service("seTimerDayService")
/**
 * @author: heshuang
 * @version: 2019-04-19
 * @Description: TODO(定时任务 - 日期数据)
 */
public class SeTimerDayServiceImpl implements SeTimerDayService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeTimerDayDao seTimerDayDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(定时任务 - 日期数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeTimerDayVo>
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public PageResult
            <SeTimerDayVo> findSeTimerDayList(SeTimerDayQVo condition,
                                              int start, int limit, String orderBy) throws Exception {
        PageResult
                <SeTimerDayVo> rst = new PageResult<>();
        List
                <SeTimerDayVo> list = seTimerDayDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.seTimerDayDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSeTimerDay
     * @Description: TODO(添加定时任务 - 日期数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public MessageResponse insertSeTimerDay(SeTimerDay o, UserProp userProp) throws Exception {
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


        int temp = this.seTimerDayDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "定时任务-日期数据名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");

        this.seTimerDayDao.insert(o);
        this.dataBaseLogService.log("添加定时任务-日期数据", "定时任务-日期数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSeTimerDay
     * @Description: TODO(更新定时任务 - 日期数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public MessageResponse updateSeTimerDay(SeTimerDay o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTimerID())) {
            return new MessageResponse(1, "定时任务序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        this.seTimerDayDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更定时任务-日期数据", "定时任务-日期数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSeTimerDayByPrimaryKey
     * @Description: TODO(获取定时任务 - 日期数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeTimerDay>
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public SingleResult
            <SeTimerDayVo> selectSeTimerDayByPrimaryKey(String id) throws Exception {
        SingleResult
                <SeTimerDayVo> rst = new SingleResult<>();
        rst.setValue(this.seTimerDayDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSeTimerDayBySeTimerDayId
     * @Description: TODO(删除定时任务 - 日期数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public MessageResponse deleteSeTimerDayBySeTimerDayId(String id, UserProp userProp) throws
            Exception {
        this.seTimerDayDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除定时任务-日期数据", "定时任务-日期数据", id, id,
                "定时任务-日期数据", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核定时任务 - 日期数据)
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


        dataBaseLogService.log("审核定时任务-日期数据", "定时任务-日期数据", id, id,
                "定时任务-日期数据", userProp);
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
            SeTimerDay o = new SeTimerDay();
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

            int t = this.seTimerDayDao.isExit(o);
            if (t > 0) {
                this.seTimerDayDao.updateByPrimaryKey(o);

            } else {
                this.seTimerDayDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("定时任务-日期数据导入", "定时任务-日期数据", "",
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
        rst.setValue(this.seTimerDayDao.getList(p));

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
                                , Object>> list = this.seTimerDayDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除定时任务 - 日期数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @Override
    public MessageResponse deleteSeTimerDayBySeTimerDayIds(String[] id, UserProp userProp)
            throws Exception {

        this.seTimerDayDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除定时任务-日期数据", "定时任务-日期数据", id[0],
                id[0], "定时任务-日期数据", userProp);
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
        this.seTimerDayDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "定时任务-日期数据", id, id,
                "定时任务-日期数据", userProp);
        return new MessageResponse(0, "成功！");
    }

}
