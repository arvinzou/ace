package com.huacainfo.ace.taa.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.*;
import com.huacainfo.ace.portal.service.AuthorityService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.dao.OfficeAdminDao;
import com.huacainfo.ace.taa.dao.TraAccCauseDao;
import com.huacainfo.ace.taa.dao.TraAccDao;
import com.huacainfo.ace.taa.dao.TraAccMtypeDao;
import com.huacainfo.ace.taa.model.TraAcc;
import com.huacainfo.ace.taa.model.TraAccCause;
import com.huacainfo.ace.taa.model.TraAccMtype;
import com.huacainfo.ace.taa.service.TraAccService;
import com.huacainfo.ace.taa.vo.TraAccQVo;
import com.huacainfo.ace.taa.vo.TraAccVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service("traAccService")
/**
 * @author: 陈晓克
 * @version: 2019-01-10
 * @Description: TODO(事故)
 */
public class TraAccServiceImpl implements TraAccService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TraAccDao traAccDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private TraAccCauseDao traAccCauseDao;
    @Autowired
    private TraAccMtypeDao traAccMtypeDao;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private OfficeAdminDao officeAdminDao;

    @Autowired
    private SqlSessionTemplate sqlSession;

    private SqlSession getSqlSession() {
        SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);

        return session;
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(事故分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TraAccVo>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public PageResult<TraAccVo> findTraAccList(TraAccQVo condition, int start, int limit, String orderBy) throws Exception {
        //
        PageResult<TraAccVo> rst = new PageResult<>();
        //sql
        SqlSession session = getSqlSession();
        TraAccDao dao = session.getMapper(TraAccDao.class);
        //
        try {
            List<TraAccVo> list = dao.findList(condition, start, limit, orderBy);
            rst.setRows(list);
            if (start <= 1) {
                int allRows = traAccDao.findCount(condition);
                rst.setTotal(allRows);
            }
            return rst;
        } catch (Exception e) {
            logger.error("{}", e);
            if (session != null) {
                session.close();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTraAcc
     * @Description: TODO(添加事故)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse insertTraAcc(TraAcc o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getLatitude())) {
            return new MessageResponse(1, "纬度不能为空！");
        }
        if (CommonUtils.isBlank(o.getLongitude())) {
            return new MessageResponse(1, "经度不能为空！");
        }
        if (CommonUtils.isBlank(o.getWeather())) {
            return new MessageResponse(1, "天气不能为空！");
        }
        if (CommonUtils.isBlank(o.getRoadSectionId())) {
            return new MessageResponse(1, "所属路段不能为空！");
        }
        if (CommonUtils.isBlank(o.getRoadManId())) {
            return new MessageResponse(1, "所属路长不能为空！");
        }

        String id = (StringUtil.isEmpty(o.getId()) || o.getId().length() < 32) ? GUIDUtil.getGUID() : o.getId();
        o.setId(id);
        o.setStatus("1");
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        if (CommonUtils.isBlank(o.getAreaCode())) {
            o.setAreaCode(userProp.getAreaCode());
        }
        this.traAccDao.insert(o);
        this.dataBaseLogService.log("添加事故", "事故", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTraAcc
     * @Description: TODO(更新事故)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse updateTraAcc(TraAcc o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getLatitude())) {
            return new MessageResponse(1, "纬度不能为空！");
        }
        if (CommonUtils.isBlank(o.getLongitude())) {
            return new MessageResponse(1, "经度不能为空！");
        }
        if (CommonUtils.isBlank(o.getWeather())) {
            return new MessageResponse(1, "天气不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.traAccDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更事故", "事故", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTraAccByPrimaryKey
     * @Description: TODO(获取事故)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TraAcc>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public SingleResult<TraAccVo> selectTraAccByPrimaryKey(String id) throws Exception {
        SingleResult<TraAccVo> rst = new SingleResult<>();
        //sql
        SqlSession session = getSqlSession();
        TraAccDao dao = session.getMapper(TraAccDao.class);
        //
        try {
            rst.setValue(dao.selectVoByPrimaryKey(id));
            return rst;
        } catch (Exception e) {
            logger.error("{}", e);
            if (session != null) {
                session.close();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return rst;
    }

    /**
     * @throws
     * @Title:deleteTraAccByTraAccId
     * @Description: TODO(删除事故)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse deleteTraAccByTraAccId(String id, UserProp userProp) throws
            Exception {
        this.traAccDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除事故", "事故", id, id,
                "事故", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核事故)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核事故", "事故", id, id,
                "事故", userProp);
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
     * @author: 陈晓克
     * @version: 2019-01-10
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            TraAcc o = new TraAcc();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setCreateUserName(userProp.getName());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getLatitude())) {
                return new MessageResponse(1, "纬度不能为空！");
            }
            if (CommonUtils.isBlank(o.getLongitude())) {
                return new MessageResponse(1, "经度不能为空！");
            }
            if (CommonUtils.isBlank(o.getAreaCode())) {
                return new MessageResponse(1, "行政区划不能为空！");
            }
            if (CommonUtils.isBlank(o.getWeather())) {
                return new MessageResponse(1, "天气不能为空！");
            }
            if (CommonUtils.isBlank(o.getVehicleType())) {
                return new MessageResponse(1, "车型不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }
            int t = this.traAccDao.isExit(o);
            if (t > 0) {
                this.traAccDao.updateByPrimaryKey(o);

            } else {
                this.traAccDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("事故导入", "事故", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map < String, Object>>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.traAccDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.traAccDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除事故
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse deleteTraAccByTraAccIds(String[] id, UserProp userProp) throws Exception {
        this.traAccDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除事故", "事故", id[0], id[0], "事故", userProp);
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
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.traAccDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "事故", id, id, "事故", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * 功能描述: 事故快报
     *
     * @param user   上报用户
     * @param params 上报参数
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/12 10:57
     */
    @Override
    public ResultResponse flashReport(WxUser user, TraAccVo params) throws Exception {
        //todo 根据经纬度自动获取所属：路段ID、路长ID
        String id = GUIDUtil.getGUID();
        params.setId(id);
        params.setAccidentTime(DateUtil.getNowDate());//事故发生时间 -- 默认为系统当前时间

        MessageResponse ms = insertTraAcc(params, parseUser(user));
        if (ms.getStatus() == ResultCode.FAIL) {
            return new ResultResponse(ms);
        } else {
            insertCauseMtype(id, params);
        }

        return new ResultResponse(ResultCode.SUCCESS, "上报成功");
    }

    /**
     * 插入 事故缘由 or 事故车型
     *
     * @param id     事故编码
     * @param params 事故缘由 or 事故车型
     */
    private void insertCauseMtype(String id, TraAccVo params) {

        if (!CollectionUtils.isEmpty(params.getCauseList())) {
            traAccCauseDao.reset(id);//先清后插
            for (TraAccCause item : params.getCauseList()) {
                item.setId(GUIDUtil.getGUID());
                item.setAccId(id);
                item.setCreateDate(DateUtil.getNowDate());
                traAccCauseDao.insert(item);
            }
        }
        if (!CollectionUtils.isEmpty(params.getMtypeList())) {
            traAccMtypeDao.reset(id);//先清后插
            for (TraAccMtype item : params.getMtypeList()) {
                item.setId(GUIDUtil.getGUID());
                item.setAccId(id);
                item.setCreateDate(DateUtil.getNowDate());
                traAccMtypeDao.insert(item);
            }
        }
    }

    /**
     * 功能描述: 事故续报
     *
     * @param user   用户信息
     * @param params 续报参数
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/12 11:15
     */
    @Override
    public ResultResponse report(WxUser user, TraAccVo params) throws Exception {
        TraAcc record = traAccDao.selectByPrimaryKey(params.getId());
        if (record == null) {
            return new ResultResponse(ResultCode.FAIL, "事故数据丢失");
        }

        //事故发生时间、归属路段、死亡人数、受伤人数、事故原因。
        //可变更项
        record.setRoadManId(params.getRoadManId());
        record.setRoadSectionId(params.getRoadSectionId());
        record.setDeadthToll(params.getDeadthToll());
        record.setInjuries(params.getInjuries());
        record.setWeather(params.getWeather());
        record.setAccidentTime(params.getAccidentTime());
        //不可变更项
        UserProp sysUser = parseUser(user);
        record.setCreateDate(record.getCreateDate());
        record.setLastModifyUserId(sysUser.getUserId());
        record.setLastModifyUserName(sysUser.getName());
        record.setLastModifyDate(DateUtil.getNowDate());
        int i = traAccDao.updateByPrimaryKey(record);
        if (i == 1) {
            insertCauseMtype(record.getId(), params);//事故原因&事故车型变更
            return new ResultResponse(ResultCode.SUCCESS, "续报提交成功");
        }

        return new ResultResponse(ResultCode.FAIL, "续报提交失败");
    }

    /**
     * 交通事故倒序表
     * <p>路段交通事故次数倒叙表</p>
     * <p>路段交通死亡人数倒叙表</p>
     *
     * @param params  参数
     * @param start   分页1
     * @param limit   分页2
     * @param orderBy 排序规则
     *                ORDER BY v.occurTimes DESC
     *                ORDER BY v.deathNum DESC
     * @return List<Map < String, Object>>
     */
    @Override
    public List<Map<String, Object>> reverseReport(Map<String, Object> params, int start, int limit, String orderBy) {
        return traAccDao.reverseReport(params, start, limit, orderBy);
    }

    /**
     * 事故死亡人数同期对比 报表
     *
     * @param params
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> contrastiveReport(Map<String, String> params) {
        String nowDataTime = DateUtil.getNow();
        String nowYear = nowDataTime.substring(0, 4);
        Map<String, String> nowParams = getMonthParams(Integer.parseInt(nowYear));
        nowParams.putAll(params);
        Map<String, String> pastParams = getMonthParams(Integer.parseInt(nowYear) - 1);
        pastParams.putAll(params);

        Map<String, Object> now = traAccDao.contrastiveReport(nowParams);
        Map<String, Object> past = traAccDao.contrastiveReport(pastParams);

        Map<String, Object> rst = new HashMap<>();
        rst.put("now", now);
        rst.put("past", past);
        return rst;
    }

    private Map<String, String> getMonthParams(int year) {
        Map<String, String> params = new HashMap<>();
        params.put("Jan_S", year + "-01-01 00:00:00");
        params.put("Jan_E", year + "-01-31 23:59:59");
        params.put("Feb_S", year + "-02-01 00:00:00");
        params.put("Feb_E", year + "-02-29 23:59:59");
        params.put("Mar_S", year + "-03-01 00:00:00");
        params.put("Mar_E", year + "-03-31 23:59:59");
        params.put("Apr_S", year + "-04-01 00:00:00");
        params.put("Apr_E", year + "-04-30 23:59:59");
        params.put("May_S", year + "-05-01 00:00:00");
        params.put("May_E", year + "-05-31 23:59:59");
        params.put("Jun_S", year + "-06-01 00:00:00");
        params.put("Jun_E", year + "-06-30 23:59:59");
        params.put("Jul_S", year + "-07-01 00:00:00");
        params.put("Jul_E", year + "-07-31 23:59:59");
        params.put("Aug_S", year + "-08-01 00:00:00");
        params.put("Aug_E", year + "-08-31 23:59:59");
        params.put("Sep_S", year + "-09-01 00:00:00");
        params.put("Sep_E", year + "-09-30 23:59:59");
        params.put("Oct_S", year + "-10-01 00:00:00");
        params.put("Oct_E", year + "-10-31 23:59:59");
        params.put("Nov_S", year + "-11-01 00:00:00");
        params.put("Nov_E", year + "-11-30 23:59:59");
        params.put("Dec_S", year + "-12-01 00:00:00");
        params.put("Dec_E", year + "-12-31 23:59:59");

        return params;
    }

    private UserProp parseUser(WxUser user) throws Exception {
        SingleResult<UserProp> rst = authorityService.getCurUserPropByOpenId(user.getUnionId());

        return rst.getValue();
    }

    /**
     * @throws
     * @Title:getLatLongByAreaCode
     * @Description: TODO(获取行政区划中心坐标)
     * @param: @param areaCode 行政区划编码
     * @param: @throws Exception
     * @return: SingleResult
     * @author: 陈晓克
     * @version: 2019-01-19
     */
    @Override
    public SingleResult<Map<String, Object>> getLatLongByAreaCode(String areaCode) throws Exception {
        SingleResult<Map<String, Object>> rst = new SingleResult();
        String areaCode6 = CommonUtils.rightPad(areaCode, 6, "0");
        rst.setValue(this.traAccDao.getLatLongByAreaCode(areaCode6));
        return rst;
    }

    /**
     * @throws
     * @Title:getTraAccListBd
     * @Description: TODO(交通事故热力图)
     * @param: @param condition
     * @param: @throws Exception
     * @return: List<Map < String, Object>>
     * @author: 陈晓克
     * @version: 2019-01-21
     */
    @Override
    public List<Map<String, Object>> getTraAccListBd(TraAccQVo condition) throws Exception {
        List<Map<String, Object>> rst = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> list = this.traAccDao.getTraAccList(condition);
        for (Map<String, Object> o : list) {
            double[] e = LatLonUtil.map_tx2bd(((java.math.BigDecimal) o.get("latitude")).doubleValue(), ((java.math.BigDecimal) o.get("longitude")).doubleValue());
            Map<String, Object> map = new HashMap<>();
            map.put("latitude", e[0]);
            map.put("longitude", e[1]);
            map.put("deadthToll", o.get("deadthToll"));
            map.put("injuries", o.get("injuries"));
            rst.add(map);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:getTraAccListTx
     * @Description: TODO(交通事故热力图)
     * @param: @param condition
     * @param: @throws Exception
     * @return: List<Map < String, Object>>
     * @author: 陈晓克
     * @version: 2019-01-21
     */
    @Override
    public List<Map<String, Object>> getTraAccListTx(TraAccQVo condition) throws Exception {

        List<Map<String, Object>> list = this.traAccDao.getTraAccList(condition);
        return list;
    }

    /**
     * 掌上驾驶仓
     *
     * @param areaCode    行政区划
     * @param dateTimeStr 查询年月;7位有效数据，默认当前年月
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> multipleReport(String areaCode, String dateTimeStr) {
        dateTimeStr = StringUtil.isEmpty(dateTimeStr) ? DateUtil.getNow().substring(0, 7) : dateTimeStr;

        //当月数据统计
        Map<String, Object> month = traAccDao.monthReport(areaCode, dateTimeStr);
        //事故top10
        List<Map<String, Object>> top10 = traAccDao.top10Report(areaCode, dateTimeStr);

//        return data
        Map<String, Object> rst = new HashMap<>();
        rst.put("month", month);//当月数据统计
        rst.put("top10", top10);//当月数据统计
        return rst;
    }

    /**
     * 查询行政区划列表
     *
     * @param areaCode
     * @return Map<String, Object>
     */
    @Override
    public List<Map<String, Object>> findDistrictList(String areaCode) {
        return traAccDao.findDistrictList(areaCode);
    }

    /**
     * 掌上驾驶仓 - 事故柱形图
     *
     * @param category    查询类型 times-事故次数 ； death-死亡人数
     * @param dateTimeStr 查询年月;7位有效数据，默认当前年月
     * @return Map<String, Object>
     */
    @Override
    public List<Map<String, Object>> histogramReport(String category, String dateTimeStr) {

        Map<String, Object> p = new HashMap<>();
        p.put("category", category);
        p.put("dateTimeStr", dateTimeStr);
        //事故柱形图
        return traAccDao.histogramReport(p);
    }

    /**
     * 事故分析 报表
     *
     * @param category      查询类型 按年-year, 按季度-season, 按月-month
     * @param dateTimeStr   时间字符串
     * @param roadManId     路长ID
     * @param roadSectionId 路段ID
     * @param field         统计字段 deadthToll ,injuries
     * @return Map<String, Object>
     */
    @Override
    public List<Map<String, Object>> analysisReport(String category, String dateTimeStr,
                                                    String roadManId, String roadSectionId, String field) {
        dateTimeStr = StringUtil.isEmpty(dateTimeStr) ? DateUtil.getNow() : dateTimeStr;
        String year = dateTimeStr.substring(0, 4);
        Map<String, Object> condition = new HashMap<>();
        condition.put("roadManId", roadManId);
        condition.put("roadSectionId", roadSectionId);
        condition.put("field", field);
        condition.put("category", category);

        switch (category) {
            case "year":
                List<Map<String, Object>> yearList = traAccDao.yearList();
                if (CollectionUtils.isEmpty(yearList)) {
                    return null;
                }
                List<String> yearArray = new ArrayList<>();
                for (Map<String, Object> item : yearList) {
                    yearArray.add(String.valueOf(item.get("yearStr")));
                }
                condition.put("yearArray", yearArray);
                break;
            case "season":
                condition.put("yearStr", year);
                break;
            case "month":
                Map<String, String> p = getMonthParams(Integer.parseInt(year));
                condition.putAll(p);
                break;
            default:
                return null;
        }

        return traAccDao.analysisReport(condition);
    }

    /**
     * 获取事故报表 --小程序端展示
     *
     * @param user      查询用户
     * @param condition 条件查询
     * @param page      分页条件
     * @return PageResult<TraAccVo>
     * @throws Exception
     */
    @Override
    public ResultResponse findViewList(UserProp user, TraAccQVo condition, PageParamNoChangeSord page) throws Exception {
        //部门筛选条件
        condition.setDeptId(user.getCorpId());
        //本人or内勤人员
        int i = officeAdminDao.isExistByUserId(user.getUserId());
        if (i == 1) {
            //内勤人员，展示同部门所有数据
            condition.setOfficeAdmin("1");
        } else {
            //非内勤人员，仅展示本人上报数据
            condition.setOfficeAdmin(user.getUserId());
        }

        PageResult<TraAccVo> rst = findTraAccList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS", rst);
    }


}
