package com.huacainfo.ace.glink.service.impl;


import java.util.*;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.glink.api.SeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.fe.TimerDataOut;
import com.huacainfo.ace.glink.dao.SeTimerDayDao;
import com.huacainfo.ace.glink.dao.SeTimerMonthDao;
import com.huacainfo.ace.glink.dao.SeTimerWeekDao;
import com.huacainfo.ace.glink.model.SeTimerDay;
import com.huacainfo.ace.glink.model.SeTimerMonth;
import com.huacainfo.ace.glink.model.SeTimerWeek;
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
    @Autowired
    private SeTimerMonthDao seTimerMonthDao;
    @Autowired
    private SeTimerDayDao seTimerDayDao;
    @Autowired
    private SeTimerWeekDao seTimerWeekDao;


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

    /**
     * 同步定时任务数据
     *
     * @param userProp
     * @return
     */
    @Override
    public MessageResponse syncData(UserProp userProp) {
        TimerDataOut o = testDate();  //SeApiToolKit.getTimerData();
        // seTimerDataDao.allClear();
        List<TimerDataOut.TimerData> outData = o.getTimerData();
        SeTimerData timerData;
        for (TimerDataOut.TimerData item : outData) {
            timerData = new SeTimerData();
            timerData.setId(GUIDUtil.getGUID());
            timerData.setTimerID(item.getTimerID());
            timerData.setTimerName(item.getTimerName());
            timerData.setStartTime(item.getStartTime());
            timerData.setTimerEnable(item.getTimerEnable());
            timerData.setTaskNo(item.getTaskNo());
            timerData.setStatus("1");
            timerData.setCreateDate(new Date());
            timerData.setRemark("同步任务数据");
            insertTimerMoth(item.getTimerID(), item.getMonthEnable());
            insertTimerDay(item.getTimerID(), item.getDayEnable());
            insertTimerWeek(item.getTimerID(), item.getWeekEnable());

            seTimerDataDao.insert(timerData);

        }
        return new MessageResponse(ResultCode.SUCCESS, "同步成功");
    }


    public int insertTimerMoth(int timeId, TimerDataOut.MonthEnable data) {
        SeTimerMonth month = new SeTimerMonth();
        month.setId(GUIDUtil.getGUID());
        month.setTimerID(timeId);
        month.setM1(data.getM1());
        month.setM2(data.getM2());
        month.setM3(data.getM3());
        month.setM4(data.getM4());
        month.setM5(data.getM5());
        month.setM6(data.getM6());
        month.setM7(data.getM7());
        month.setM8(data.getM8());
        month.setM9(data.getM9());
        month.setM10(data.getM10());
        month.setM11(data.getM11());
        month.setM12(data.getM12());
        month.setCreateDate(new Date());
        month.setStatus("1");
        month.setRemark("同步定时任务月数据");
        return seTimerMonthDao.insert(month);
    }

    public int insertTimerDay(int timeId, TimerDataOut.DayEnable data) {
        SeTimerDay day = new SeTimerDay();
        day.setId(GUIDUtil.getGUID());
        day.setTimerID(timeId);
        day.setD1(data.getD1());
        day.setD2(data.getD2());
        day.setD3(data.getD3());
        day.setD4(data.getD4());
        day.setD5(data.getD5());
        day.setD6(data.getD6());
        day.setD7(data.getD7());
        day.setD8(data.getD8());
        day.setD9(data.getD9());
        day.setD10(data.getD10());
        day.setD11(data.getD11());
        day.setD12(data.getD12());
        day.setD13(data.getD13());
        day.setD14(data.getD14());
        day.setD15(data.getD15());
        day.setD16(data.getD16());
        day.setD17(data.getD17());
        day.setD18(data.getD18());
        day.setD19(data.getD19());
        day.setD20(data.getD20());
        day.setD21(data.getD21());
        day.setD22(data.getD22());
        day.setD23(data.getD23());
        day.setD24(data.getD24());
        day.setD25(data.getD25());
        day.setD26(data.getD26());
        day.setD27(data.getD27());
        day.setD28(data.getD28());
        day.setD29(data.getD29());
        day.setD30(data.getD30());
        day.setD31(data.getD31());
        day.setCreateDate(new Date());
        day.setStatus("1");
        day.setRemark("同步定时任务日数据");
        return seTimerDayDao.insert(day);
    }

    public int insertTimerWeek(int timeId, TimerDataOut.WeekEnable data) {
        SeTimerWeek week = new SeTimerWeek();
        week.setId(GUIDUtil.getGUID());
        week.setTimerID(timeId);
        week.setW1(data.getW1());
        week.setW2(data.getW2());
        week.setW3(data.getW3());
        week.setW4(data.getW4());
        week.setW5(data.getW5());
        week.setW6(data.getW6());
        week.setW7(data.getW7());
        week.setRemark("同步定时任务周数据");
        week.setStatus("1");
        week.setCreateDate(new Date());
        return seTimerWeekDao.insert(week);

    }


    public TimerDataOut testDate() {
        String jsons = "{\n" +
                "    \"TimerCount\": 2,\n" +
                "    \"TimerData\": [\n" +
                "        {\n" +
                "            \"TimerID\": 1,\n" +
                "            \"TimerName\": \"全开模式\",\n" +
                "            \"TimerEnable\": 1,\n" +
                "            \"StartTime\": \"19:00:00\",\n" +
                "            \"TaskNo\": 1,\n" +
                "            \"MonthEnable\": {\n" +
                "                \"M1\": 1,\n" +
                "                \"M2\": 1,\n" +
                "                \"M2\": 1,\n" +
                "                \"M3\": 1,\n" +
                "                \"M4\": 1,\n" +
                "                \"M5\": 1,\n" +
                "                \"M6\": 1,\n" +
                "                \"M7\": 1,\n" +
                "                \"M8\": 1,\n" +
                "                \"M9\": 1,\n" +
                "                \"M10\": 1,\n" +
                "                \"M11\": 1,\n" +
                "                \"M12\": 1\n" +
                "            },\n" +
                "            \"DayEnable\": {\n" +
                "                \"D1\": 1,\n" +
                "                \"D2\": 1,\n" +
                "                \"D3\": 1,\n" +
                "                \"D4\": 1,\n" +
                "                \"D5\": 1,\n" +
                "                \"D6\": 1,\n" +
                "                \"D7\": 1,\n" +
                "                \"D8\": 1,\n" +
                "                \"D9\": 1,\n" +
                "                \"D10\": 1,\n" +
                "                \"D11\": 1,\n" +
                "                \"D12\": 1,\n" +
                "                \"D13\": 1,\n" +
                "                \"D14\": 1,\n" +
                "                \"D15\": 1,\n" +
                "                \"D16\": 1,\n" +
                "                \"D17\": 1,\n" +
                "                \"D18\": 1,\n" +
                "                \"D19\": 1,\n" +
                "                \"D20\": 1,\n" +
                "                \"D21\": 1,\n" +
                "                \"D22\": 1,\n" +
                "                \"D23\": 1,\n" +
                "                \"D24\": 1,\n" +
                "                \"D25\": 1,\n" +
                "                \"D26\": 1,\n" +
                "                \"D27\": 1,\n" +
                "                \"D28\": 1,\n" +
                "                \"D29\": 1,\n" +
                "                \"D30\": 1,\n" +
                "                \"D31\": 1\n" +
                "            },\n" +
                "            \"WeekEnable\": {\n" +
                "                \"W1\": 1,\n" +
                "                \"W2\": 1,\n" +
                "                \"W2\": 1,\n" +
                "                \"W3\": 1,\n" +
                "                \"W4\": 1,\n" +
                "                \"W5\": 1,\n" +
                "                \"W6\": 1,\n" +
                "                \"W7\": 1\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"TimerID\": 2,\n" +
                "            \"TimerName\": \"全关模式\",\n" +
                "            \"TimerEnable\": 1,\n" +
                "            \"StartTime\": \"23:00:00\",\n" +
                "            \"TaskNo\": 4,\n" +
                "            \"MonthEnable\": {\n" +
                "                \"M1\": 1,\n" +
                "                \"M2\": 1,\n" +
                "                \"M2\": 1,\n" +
                "                \"M3\": 1,\n" +
                "                \"M4\": 1,\n" +
                "                \"M5\": 1,\n" +
                "                \"M6\": 1,\n" +
                "                \"M7\": 1,\n" +
                "                \"M8\": 1,\n" +
                "                \"M9\": 1,\n" +
                "                \"M10\": 1,\n" +
                "                \"M11\": 1,\n" +
                "                \"M12\": 1\n" +
                "            },\n" +
                "            \"DayEnable\": {\n" +
                "                \"D1\": 1,\n" +
                "                \"D2\": 1,\n" +
                "                \"D3\": 1,\n" +
                "                \"D4\": 1,\n" +
                "                \"D5\": 1,\n" +
                "                \"D6\": 1,\n" +
                "                \"D7\": 1,\n" +
                "                \"D8\": 1,\n" +
                "                \"D9\": 1,\n" +
                "                \"D10\": 1,\n" +
                "                \"D11\": 1,\n" +
                "                \"D12\": 1,\n" +
                "                \"D13\": 1,\n" +
                "                \"D14\": 1,\n" +
                "                \"D15\": 1,\n" +
                "                \"D16\": 1,\n" +
                "                \"D17\": 1,\n" +
                "                \"D18\": 1,\n" +
                "                \"D19\": 1,\n" +
                "                \"D20\": 1,\n" +
                "                \"D21\": 1,\n" +
                "                \"D22\": 1,\n" +
                "                \"D23\": 1,\n" +
                "                \"D24\": 1,\n" +
                "                \"D25\": 1,\n" +
                "                \"D26\": 1,\n" +
                "                \"D27\": 1,\n" +
                "                \"D28\": 1,\n" +
                "                \"D29\": 1,\n" +
                "                \"D30\": 1,\n" +
                "                \"D31\": 1\n" +
                "            },\n" +
                "            \"WeekEnable\": {\n" +
                "                \"W1\": 1,\n" +
                "                \"W2\": 1,\n" +
                "                \"W2\": 1,\n" +
                "                \"W3\": 1,\n" +
                "                \"W4\": 1,\n" +
                "                \"W5\": 1,\n" +
                "                \"W6\": 1,\n" +
                "                \"W7\": 1\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        TimerDataOut o = JsonUtil.toObject(jsons, TimerDataOut.class);

        return o;
    }

}
