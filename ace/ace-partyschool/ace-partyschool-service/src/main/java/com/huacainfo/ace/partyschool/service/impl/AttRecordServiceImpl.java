package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.access.AccessHelper;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.*;
import com.huacainfo.ace.partyschool.constant.CommConstant;
import com.huacainfo.ace.partyschool.dao.AttRecordDao;
import com.huacainfo.ace.partyschool.dao.ZkAttDataDao;
import com.huacainfo.ace.partyschool.model.AttRecord;
import com.huacainfo.ace.partyschool.model.ZkAttData;
import com.huacainfo.ace.partyschool.service.AttRecordService;
import com.huacainfo.ace.partyschool.vo.*;
import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.ConfigService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service("attRecordService")
/**
 * @author: Arvin
 * @version: 2019-02-20
 * @Description: TODO(学员考勤)
 */
public class AttRecordServiceImpl implements AttRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AttRecordDao attRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private ZkAttDataDao zkAttDataDao;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ConfigService configService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(学员考勤分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<AttRecordVo>
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public PageResult<AttRecordVo> findAttRecordList(AttRecordQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<AttRecordVo> rst = new PageResult<>();
        List<AttRecordVo> list = this.attRecordDao.findRecordList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.attRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertAttRecord
     * @Description: TODO(添加学员考勤)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse insertAttRecord(AttRecord o, UserProp userProp) throws Exception {
        //系统考勤时间
        o.setAttTime(DateUtil.getNowDate());
        if (CommonUtils.isBlank(o.getAttTime())) {
            return new MessageResponse(1, "考勤时间不能为空！");
        }
        if ((null == o.getLatitude() || o.getLatitude().compareTo(BigDecimal.ZERO) == 0)
                || (null == o.getLongitude() || o.getLongitude().compareTo(BigDecimal.ZERO) == 0)) {
            return new MessageResponse(1, "考勤定位不能为空！");
        }
        Users user = usersService.selectUsersByPrimaryKey(userProp.getUserId()).getValue();
        if (user == null) {
            return new MessageResponse(ResultCode.FAIL, "用户数据丢失");
        }
        //判断是否在扫描半径之内
        MessageResponse posCheck = isInside(o.getLatitude(), o.getLongitude());
        if (posCheck.getStatus() == ResultCode.FAIL) {
            return posCheck;
        }
        //获取考勤配置参数
        String userType = user.getUserLevel();
        boolean isStudent = userType.equals(CommConstant.STUDENT);
        String cfgKey = isStudent ? "STU" : "TEA";
        Map<String, String> config = getConfigMap(isStudent, cfgKey);
        String cfgKey2 = isStudent ? "R_STU" : "R_TEA";
        Map<String, String> config2 = getConfigLimitMap(isStudent, cfgKey2);
        if (CollectionUtils.isEmpty(config) || CollectionUtils.isEmpty(config2)) {
            return new MessageResponse(ResultCode.FAIL, "考勤配置参数有误");
        }
        //设置状态值
        String attState = setState(isStudent, o.getAttTime(), config, config2);
        //存储对象
        o.setAttState(attState);
        o.setUserType(userType);
        o.setUserId(userProp.getUserId());

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        attRecordDao.insert(o);
        dataBaseLogService.log("添加学员考勤", "学员考勤", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "签到成功！");
    }

    private String setState(boolean isStudent, Date attTime,
                            Map<String, String> config, Map<String, String> config2) {
        //区间划分
        String time = DateUtil.toStr(attTime, "HH:mm:ss");
        if (isStudent) {
            //1.上午签到    08:00~08:30(8:00~10:00) -- 标准（范围）
            String amIn_1 = config.get("amIn_1");//打卡范围
            String amIn_2 = config.get("amIn_2");//打卡范围
            String r_amIn_1 = config2.get("r_amIn_1");//签到标准
            String r_amIn_2 = config2.get("r_amIn_2");//签到标准
            //2.上午签退    11:00~12:30(10:00~12:30)
            String amOut_1 = config.get("amOut_1");
            String amOut_2 = config.get("amOut_2");
            String r_amOut_1 = config2.get("r_amOut_1");
            String r_amOut_2 = config2.get("r_amOut_2");
            //3.下午签到    14:00~14:30(14:00~15:30)
            String pmIn_1 = config.get("pmIn_1");
            String pmIn_2 = config.get("pmIn_2");
            String r_pmIn_1 = config2.get("r_pmIn_1");
            String r_pmIn_2 = config2.get("r_pmIn_2");
            //4.下午签退    16:30~18:00(15:30~18:00)
            String pmOut_1 = config.get("pmOut_1");
            String pmOut_2 = config.get("pmOut_2");
            String r_pmOut_1 = config2.get("r_pmOut_1");
            String r_pmOut_2 = config2.get("r_pmOut_2");
            //5.晚上签到    22:00~23:00(22:00~23:00)
            String nightIn_1 = config.get("nightIn_1");
            String nightIn_2 = config.get("nightIn_2");
            String r_nightIn_1 = config2.get("r_nightIn_1");
            String r_nightIn_2 = config2.get("r_nightIn_2");
            //1.上午签到--准点  08:00~08:30(8:00~10:00) -- 标准（范围）
            if (DateUtil.compareTime(time, r_amIn_1) >= 0 && DateUtil.compareTime(time, r_amIn_2) < 0) {
                return CommConstant.ATT_STATE_ON_TIME;
            }
            //1.上午签到--迟到
            else if (DateUtil.compareTime(time, r_amIn_2) > 0 && DateUtil.compareTime(time, amIn_2) < 0) {
                return CommConstant.ATT_STATE_BE_LATE;
            }
            //2.上午签退--早退  11:00~12:30(10:00~12:30)
            else if (DateUtil.compareTime(time, amOut_1) >= 0 && DateUtil.compareTime(time, r_amOut_1) < 0) {
                return CommConstant.ATT_STATE_LEAVE_EARLY;
            }
            //2.上午签退--准点
            else if (DateUtil.compareTime(time, r_amOut_1) >= 0 && DateUtil.compareTime(time, r_amOut_2) < 0) {
                return CommConstant.ATT_STATE_ON_TIME;
            }
            //3.下午签到--准点    14:00~14:30(14:00~15:30)
            else if (DateUtil.compareTime(time, r_pmIn_1) >= 0 && DateUtil.compareTime(time, r_pmIn_2) < 0) {
                return CommConstant.ATT_STATE_ON_TIME;
            }
            //3.下午签到--迟到
            else if (DateUtil.compareTime(time, r_pmIn_2) >= 0 && DateUtil.compareTime(time, pmIn_2) < 0) {
                return CommConstant.ATT_STATE_BE_LATE;
            }
            //4.下午签退--早退    16:30~18:00(15:30~18:00)
            else if (DateUtil.compareTime(time, pmOut_1) >= 0 && DateUtil.compareTime(time, r_pmOut_1) < 0) {
                return CommConstant.ATT_STATE_LEAVE_EARLY;
            }
            //4.下午签退--准点
            else if (DateUtil.compareTime(time, r_pmOut_1) >= 0 && DateUtil.compareTime(time, r_pmOut_2) < 0) {
                return CommConstant.ATT_STATE_ON_TIME;
            }
            //5.晚上签到    22:00~23:00
            else if (DateUtil.compareTime(time, nightIn_1) >= 0 && DateUtil.compareTime(time, nightIn_2) < 0) {
                return CommConstant.ATT_STATE_ON_TIME;
            }
        } else {
            //1.上午签到    08:00~08:30(8:00~10:00) -- 标准（范围）
            String amIn_1 = config.get("amIn_1");//打卡范围
            String amIn_2 = config.get("amIn_2");//打卡范围
            String r_amIn_1 = config2.get("r_amIn_1");//签到标准
            String r_amIn_2 = config2.get("r_amIn_2");//签到标准
            //4.下午签退    16:30~18:00(15:30~18:00)
            String pmOut_1 = config.get("pmOut_1");
            String pmOut_2 = config.get("pmOut_2");
            String r_pmOut_1 = config2.get("r_pmOut_1");
            String r_pmOut_2 = config2.get("r_pmOut_2");
            //1.上午签到--准点  08:00~08:30(8:00~10:00) -- 标准（范围）
            if (DateUtil.compareTime(time, r_amIn_1) >= 0 && DateUtil.compareTime(time, r_amIn_2) < 0) {
                return CommConstant.ATT_STATE_ON_TIME;
            }
            //1.上午签到--迟到
            else if (DateUtil.compareTime(time, r_amIn_2) > 0 && DateUtil.compareTime(time, amIn_2) < 0) {
                return CommConstant.ATT_STATE_BE_LATE;
            }
            //2.下午签退--早退    16:30~18:00(15:30~18:00)
            else if (DateUtil.compareTime(time, pmOut_1) >= 0 && DateUtil.compareTime(time, r_pmOut_1) < 0) {
                return CommConstant.ATT_STATE_LEAVE_EARLY;
            }
            //2.下午签退--准点
            else if (DateUtil.compareTime(time, r_pmOut_1) >= 0 && DateUtil.compareTime(time, r_pmOut_2) < 0) {
                return CommConstant.ATT_STATE_ON_TIME;
            }
        }

        return null;
    }

    /**
     * 腾讯地图坐标
     *
     * @param lat 纬度
     * @param lng 经度
     * @return MessageResponse
     */
    private MessageResponse isInside(BigDecimal lat, BigDecimal lng) {
        //参考中心点
        Config center = configService.findByKey(CommConstant.SYS_ID, "att_ponit");
        if (center == null) {
            return new MessageResponse(ResultCode.FAIL, "未配置考勤中心点");
        }
        String[] centerArray = center.getConfigValue().split(",");
        if (centerArray.length != 2) {
            return new MessageResponse(ResultCode.FAIL, "考勤中心点配置有误");
        }
        double cLat = Double.parseDouble(centerArray[0]);
        double cLng = Double.parseDouble(centerArray[1]);
        //参考半径
        Config radius = configService.findByKey(CommConstant.SYS_ID, "att_radius");
        if (radius == null) {
            return new MessageResponse(ResultCode.FAIL, "未配置考勤有效范围");
        }
        //距离计算
        MapKit.Point centerPoint = new MapKit.Point(cLat, cLng);
        MapKit.Point attPoint = new MapKit.Point(lat.doubleValue(), lng.doubleValue());
        double distance = MapKit.getDistance(centerPoint, attPoint);
        //
        if (distance > Double.parseDouble(radius.getConfigValue())) {
            return new MessageResponse(ResultCode.FAIL, "ERROR_POINT", "无效定位点");
        }
        return new MessageResponse(ResultCode.SUCCESS, "SUCCESS");
    }


    /**
     * @throws
     * @Title:updateAttRecord
     * @Description: TODO(更新学员考勤)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse updateAttRecord(AttRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserType())) {
            return new MessageResponse(1, "用户类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getAttTime())) {
            return new MessageResponse(1, "考勤时间不能为空！");
        }

        this.attRecordDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更学员考勤", "学员考勤", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectAttRecordByPrimaryKey
     * @Description: TODO(获取学员考勤)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AttRecord>
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public SingleResult<AttRecordVo> selectAttRecordByPrimaryKey(String id) throws Exception {
        SingleResult<AttRecordVo> rst = new SingleResult<>();
        rst.setValue(this.attRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteAttRecordByAttRecordId
     * @Description: TODO(删除学员考勤)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse deleteAttRecordByAttRecordId(String id, UserProp userProp) throws Exception {
        this.attRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除学员考勤", "学员考勤", id, id, "学员考勤", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核学员考勤)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception {


        dataBaseLogService.log("审核学员考勤", "学员考勤", id, id,
                "学员考勤", userProp);
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
     * @version: 2019-02-20
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            AttRecord o = new AttRecord();
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
            if (CommonUtils.isBlank(o.getUserId())) {
                return new MessageResponse(1, "用户编码不能为空！");
            }
            if (CommonUtils.isBlank(o.getUserType())) {
                return new MessageResponse(1, "用户类型不能为空！");
            }
            if (CommonUtils.isBlank(o.getAttTime())) {
                return new MessageResponse(1, "考勤时间不能为空！");
            }

            int t = attRecordDao.isExist(o);
            if (t > 0) {
                this.attRecordDao.updateByPrimaryKey(o);

            } else {
                this.attRecordDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("学员考勤导入", "学员考勤", "", "rs.xls", "rs.xls", userProp);
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
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.attRecordDao.getList(p));

        return rst;
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.attRecordDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除学员考勤 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse deleteAttRecordByAttRecordIds(String[] id, UserProp userProp) throws Exception {

        this.attRecordDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除学员考勤", "学员考勤", id[0], id[0], "学员考勤", userProp);
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
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.attRecordDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "学员考勤", id, id, "学员考勤", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * 导入中控数据
     *
     * @param fileName db文件
     * @param nowTime  当前操作时间
     * @return MessageResponse
     */
    @Override
    public MessageResponse saveZkData(String fileName, String nowTime) {

        //暂存zk导入数据
        String sql = "select u.USERID, u.SSN, u.Name, c.CHECKTIME \n" +
                "from CHECKINOUT c\n" +
                "left join USERINFO u on c.USERID = u.USERID\n";
        List<Map<String, Object>> accessList = AccessHelper.query(sql, fileName);
        ZkAttData data;
        zkAttDataDao.clearUp();//清理原有数据
        for (Map<String, Object> map : accessList) {
            data = new ZkAttData();
            data.setId(GUIDUtil.getGUID());
            data.setUserId((String) map.get("USERID"));
            data.setAttTime(DateUtil.toDate((String) map.get("CHECKTIME")));
            data.setSsn((String) map.get("SSN"));
            data.setName((String) map.get("NAME"));
            data.setStatus("1");
            data.setRemark(nowTime + "导入");
            data.setCreateDate(DateUtil.getNowDate());
            zkAttDataDao.insert(data);
        }

        return new MessageResponse(ResultCode.SUCCESS, "导入成功");
    }

    /**
     * 查询登录用户考勤信息 -- 查询某一天的考勤数据
     *
     * @param userId
     * @param dateTimeStr 日期字符串 ，包含年月日；示例： 2019-02-21
     * @return ResultResponse
     */
    @Override
    public ResultResponse findList(String userId, String dateTimeStr) {
        Map<String, Object> config = attRecordDao.getAttSrc();
        if (config == null) {
            return new ResultResponse(ResultCode.FAIL, "未配置考勤数据来源");
        }
        //中控智慧来源
        if ("ZK".equals(String.valueOf(config.get("config_value")))) {
            ZkAttDataQVo condition = new ZkAttDataQVo();
            condition.setDateTimeStr(dateTimeStr);
            condition.setUserId(userId);
            List<ZkAttDataVo> list = zkAttDataDao.findVoList(condition, 0, 100, "t.attTime asc");
            //解析分组
            Map<String, List<ZkAttDataVo>> view = new HashMap<>();
            List<ZkAttDataVo> am = new LinkedList<>();
            List<ZkAttDataVo> pm = new LinkedList<>();
            List<ZkAttDataVo> night = new LinkedList<>();
            view.put("am", am);
            view.put("pm", pm);
            view.put("night", night);
            String hour;
            int iHour;
            String dtStr;
            for (ZkAttDataVo item : list) {
                dtStr = DateUtil.toStr(item.getAttTime(), DateUtil.DEFAULT_DATE_TIME_REGEX);
                if (dtStr.length() == 19) {
                    hour = dtStr.substring(11, 13);
                    iHour = Integer.parseInt(hour);
                    if (iHour < 12) {//上午
                        am = view.get("am");
                        am.add(item);
                    } else if (iHour >= 18) {//晚上
                        night = view.get("night");
                        night.add(item);
                    } else {
                        pm = view.get("pm");
                        pm.add(item);
                    }
                }
            }
            return new ResultResponse(ResultCode.SUCCESS, "SUCCESS", view);
        }
        //手机定位来源
        else {
            AttRecordQVo condition = new AttRecordQVo();
            condition.setDateTimeStr(dateTimeStr);
            condition.setUserId(userId);
            List<AttRecordVo> list = attRecordDao.findRecordList(condition, 0, 100, "");
            //解析分组
            Map<String, List<AttRecordVo>> view = new HashMap<>();
            List<AttRecordVo> am = new LinkedList<>();
            List<AttRecordVo> pm = new LinkedList<>();
            List<AttRecordVo> night = new LinkedList<>();
            view.put("am", am);
            view.put("pm", pm);
            view.put("night", night);
            String hour;
            int iHour;
            String dtStr;
            for (AttRecordVo item : list) {
                dtStr = DateUtil.toStr(item.getAttTime(), DateUtil.DEFAULT_DATE_TIME_REGEX);
                if (dtStr.length() == 19) {
                    hour = dtStr.substring(11, 13);
                    iHour = Integer.parseInt(hour);
                    if (iHour < 12) {//上午
                        am = view.get("am");
                        am.add(item);
                    } else if (iHour >= 18) {//晚上
                        night = view.get("night");
                        night.add(item);
                    } else {
                        pm = view.get("pm");
                        pm.add(item);
                    }
                }
            }
            return new ResultResponse(ResultCode.SUCCESS, "SUCCESS", view);
        }
    }

    @Override
    public List<AttRecordExport> exportAttRecord(AttRecordQVo condition) {
        String userType = condition.getUserType();
        Date startDate = DateUtil.getDate(condition.getStartDate(), DateUtil.DEFAULT_DATE_REGEX);
        Date endDate = DateUtil.getDate(condition.getEndDate(), DateUtil.DEFAULT_DATE_REGEX);
        //获取区间配置
        boolean isStudent = userType.equals(CommConstant.STUDENT);
        String cfgKey = isStudent ? "STU" : "TEA";
        Map<String, String> config = getConfigMap(isStudent, cfgKey);
        if (CollectionUtils.isEmpty(config)) {
            throw new CustomException("考勤配置参数有误");
        }
        //返回参数
        List<AttRecordExport> rst = new LinkedList<>();
        //循环日期
        Date nowDate = startDate;
        String nowDateStr;
        Calendar c = Calendar.getInstance();
        List<AttRecordExport> subList;
        while (nowDate.compareTo(endDate) <= 0) {
            nowDateStr = DateUtil.toStr(nowDate);
            condition.setNowDate(nowDateStr);
            condition.setStartDate(nowDateStr + " 00:00:00");
            condition.setEndDate(nowDateStr + " 23:59:59");
            subList = getOneDayList(userType, config, condition);
            rst.addAll(subList);
            //循环条件
            c.setTime(nowDate);
            c.add(Calendar.DAY_OF_MONTH, 1);
            nowDate = c.getTime();
        }

        return rst;


    }

    private List<AttRecordExport> getOneDayList(String userType, Map<String, String> config, AttRecordQVo condition) {
        //1.数据库原始记录
        List<AttRecordVo> list = attRecordDao.findList(condition, 0, 65536, "");
        //2.转换为数据map
        Map<String, AttRecordExport> dataMap = convertDataMap(userType, config, list);
        //3.包装
        return packageExcelResult(userType, dataMap);
    }

    private List<AttRecordExport> packageExcelResult(String userType, Map<String, AttRecordExport> dataMap) {
        List<AttRecordExport> rst = new LinkedList<>();
        Map.Entry<String, AttRecordExport> entry;
        Iterator<Map.Entry<String, AttRecordExport>> entries = dataMap.entrySet().iterator();
        while (entries.hasNext()) {
            entry = entries.next();
            if (CommConstant.STUDENT.equals(userType)) {
                AttStudentExcel temp = (AttStudentExcel) dataMap.get(entry.getKey());
                temp.setAmIn(StringUtil.isEmpty(temp.getAmIn()) ? "(缺勤)" : temp.getAmIn());
                temp.setAmOut(StringUtil.isEmpty(temp.getAmOut()) ? "(缺勤)" : temp.getAmOut());
                temp.setPmIn(StringUtil.isEmpty(temp.getPmIn()) ? "(缺勤)" : temp.getPmIn());
                temp.setPmOut(StringUtil.isEmpty(temp.getPmOut()) ? "(缺勤)" : temp.getPmOut());
                temp.setNightIn(StringUtil.isEmpty(temp.getNightIn()) ? "(缺勤)" : temp.getNightIn());
                rst.add(temp);
            } else {
                AttTeacherExcel temp = (AttTeacherExcel) dataMap.get(entry.getKey());
                temp.setAmIn(StringUtil.isEmpty(temp.getAmIn()) ? "(缺勤)" : temp.getAmIn());
                temp.setPmOut(StringUtil.isEmpty(temp.getPmOut()) ? "(缺勤)" : temp.getPmOut());
                rst.add(temp);
            }
        }
        return rst;
    }

    private Map<String, AttRecordExport> convertDataMap(String userType,
                                                        Map<String, String> config,
                                                        List<AttRecordVo> list) {
        //返回数据
        Map<String, AttRecordExport> rst = new HashMap<>();
        //逻辑分支
        if (CommConstant.STUDENT.equals(userType)) {
            Map<String, AttStudentExcel> sun = new HashMap<>();
            AttStudentExcel student;
            String key;
            Date date;
            for (AttRecordVo item : list) {
                date = item.getAttTime();
                key = item.getAttDate() + "&" + item.getUserId();
                student = sun.get(key);
                student = null == student ? new AttStudentExcel() : student;
                student.setName(item.getUserName());
                student.setUserType(item.getUserTypeName());
                student.setClsName(item.getClsName());
                student.setAttDate(item.getAttDate());
                student = setStudentAttTime(config, date, item.getAttState(), student);
                sun.put(key, student);
            }
            rst.putAll(sun);
        } else {
            Map<String, AttTeacherExcel> sun = new HashMap<>();
            AttTeacherExcel teacher;
            String key;
            Date date;
            for (AttRecordVo item : list) {
                date = item.getAttTime();
                key = item.getAttDate() + "&" + item.getUserId();
                teacher = sun.get(key);
                teacher = null == teacher ? new AttTeacherExcel() : teacher;
                teacher.setName(item.getUserName());
                teacher.setUserType(item.getUserTypeName());
//                stu.setClsName(item.getClsName());
                teacher.setAttDate(item.getAttDate());
                teacher = setTeacherAttTime(config, date, item.getAttState(), teacher);
                sun.put(key, teacher);
            }
            rst.putAll(sun);
        }
        return rst;

    }

    private AttTeacherExcel setTeacherAttTime(Map<String, String> config, Date attTime,
                                              String attState, AttTeacherExcel teacher) {
        String attStateName = parseState(attState);
        if (attTime == null) {
            return teacher;
        }
        //itemValue
        String str = DateUtil.toStr(attTime, DateUtil.DEFAULT_DATE_TIME_REGEX) + "  (" + attStateName + ")";
        //区间划分
        String time = DateUtil.toStr(attTime, "HH:mm:ss");
        //1.上午签到
        String amIn_1 = config.get("amIn_1");//"08:00" + ":00";
        String amIn_2 = config.get("amIn_2");//"10:00" + ":00";
        //2.下午签退
        String pmOut_1 = config.get("pmOut_1");//"15:30" + ":00";
        String pmOut_2 = config.get("pmOut_2");//"16:00" + ":00";

        if (DateUtil.compareTime(time, amIn_1) >= 0 && DateUtil.compareTime(time, amIn_2) < 0) {
            teacher.setAmIn(str);
        } else if (DateUtil.compareTime(time, pmOut_1) >= 0 && DateUtil.compareTime(time, pmOut_2) < 0) {
            teacher.setPmOut(str);
        }

        return teacher;
    }

    private AttStudentExcel setStudentAttTime(Map<String, String> config,
                                              Date attTime, String attState, AttStudentExcel stu) {
        String attStateName = parseState(attState);
        if (attTime == null) {
            return stu;
        }
        //itemValue
        String str = DateUtil.toStr(attTime, DateUtil.DEFAULT_DATE_TIME_REGEX) + "  (" + attStateName + ")";
        //区间划分
        String time = DateUtil.toStr(attTime, "HH:mm:ss");
        //1.上午签到
        String amIn_1 = config.get("amIn_1");//"08:00" + ":00";
        String amIn_2 = config.get("amIn_2");//"10:00" + ":00";
        //2.上午签退
        String amOut_1 = config.get("amOut_1");//"10:00" + ":00";
        String amOut_2 = config.get("amOut_2");//"12:30" + ":00";
        //3.下午签到
        String pmIn_1 = config.get("pmIn_1");//"14:00" + ":00";
        String pmIn_2 = config.get("pmIn_2");//"15:30" + ":00";
        //4.下午签退
        String pmOut_1 = config.get("pmOut_1");//"15:30" + ":00";
        String pmOut_2 = config.get("pmOut_2");//"16:00" + ":00";
        //5.晚上签到
        String nightIn_1 = config.get("nightIn_1");//"22:00" + ":00";
        String nightIn_2 = config.get("nightIn_2");//"23:00" + ":00";
        if (DateUtil.compareTime(time, amIn_1) >= 0 && DateUtil.compareTime(time, amIn_2) < 0) {
            stu.setAmIn(str);
        } else if (DateUtil.compareTime(time, amOut_1) >= 0 && DateUtil.compareTime(time, amOut_2) <= 0) {
            stu.setAmOut(str);
        } else if (DateUtil.compareTime(time, pmIn_1) >= 0 && DateUtil.compareTime(time, pmIn_2) < 0) {
            stu.setPmIn(str);
        } else if (DateUtil.compareTime(time, pmOut_1) >= 0 && DateUtil.compareTime(time, pmOut_2) < 0) {
            stu.setPmOut(str);
        } else if (DateUtil.compareTime(time, nightIn_1) >= 0 && DateUtil.compareTime(time, nightIn_2) < 0) {
            stu.setNightIn(str);
        }

        return stu;
    }

    private Map<String, String> getConfigLimitMap(boolean isStudent, String cfgKey) {
        //返回集合
        Map<String, String> rst = new HashMap<>();
        //配置参数
        List<Map<String, Object>> configs = attRecordDao.findConfigList(cfgKey, CommConstant.SYS_ID);
        String key;
        String value;
        String[] strArray;
        for (Map<String, Object> item : configs) {
            key = String.valueOf(item.get("config_key"));
            value = String.valueOf(item.get("config_value"));
            //学员
            if (isStudent) {
                if (CommConstant.STU_ATT_AM_IN.equals(key)) {
                    strArray = value.split("~");
                    rst.put("r_amIn_1", strArray[0] + ":00");
                    rst.put("r_amIn_2", strArray[1] + ":00");
                    continue;
                }
                if (CommConstant.STU_ATT_AM_OUT.equals(key)) {
                    strArray = value.split("~");
                    rst.put("r_amOut_1", strArray[0] + ":00");
                    rst.put("r_amOut_2", strArray[1] + ":00");
                    continue;
                }
                if (CommConstant.STU_ATT_PM_IN.equals(key)) {
                    strArray = value.split("~");
                    rst.put("r_pmIn_1", strArray[0] + ":00");
                    rst.put("r_pmIn_2", strArray[1] + ":00");
                    continue;
                }
                if (CommConstant.STU_ATT_PM_OUT.equals(key)) {
                    strArray = value.split("~");
                    rst.put("r_pmOut_1", strArray[0] + ":00");
                    rst.put("r_pmOut_2", strArray[1] + ":00");
                    continue;
                }
                if (CommConstant.STU_ATT_NIGHT_IN.equals(key)) {
                    strArray = value.split("~");
                    rst.put("r_nightIn_1", strArray[0] + ":00");
                    rst.put("r_nightIn_2", strArray[1] + ":00");
                    continue;
                }
            }
            //教职工
            else {
                if (CommConstant.TEA_ATT_AM_IN.equals(key)) {
                    strArray = value.split("~");
                    rst.put("r_amIn_1", strArray[0] + ":00");
                    rst.put("r_amIn_2", strArray[1] + ":00");
                    continue;
                }
                if (CommConstant.TEA_ATT_PM_OUT.equals(key)) {
                    strArray = value.split("~");
                    rst.put("r_pmOut_1", strArray[0] + ":00");
                    rst.put("r_pmOut_2", strArray[1] + ":00");
                    continue;
                }
            }
        }

        return rst;
    }

    private Map<String, String> getConfigMap(boolean isStudent, String cfgKey) {
        //返回集合
        Map<String, String> rst = new HashMap<>();
        //配置参数
        List<Map<String, Object>> configs = attRecordDao.findConfigList(cfgKey, CommConstant.SYS_ID);
        String key;
        String value;
        String[] strArray;
        for (Map<String, Object> item : configs) {
            key = String.valueOf(item.get("config_key"));
            value = String.valueOf(item.get("config_value"));
            if (isStudent) {
                if (CommConstant.STU_ATT_SCOPE_AM_IN.equals(key)) {
                    strArray = value.split("~");
                    rst.put("amIn_1", strArray[0] + ":00");
                    rst.put("amIn_2", strArray[1] + ":00");
                    continue;
                }
                if (CommConstant.STU_ATT_SCOPE_AM_OUT.equals(key)) {
                    strArray = value.split("~");
                    rst.put("amOut_1", strArray[0] + ":00");
                    rst.put("amOut_2", strArray[1] + ":00");
                    continue;
                }
                if (CommConstant.STU_ATT_SCOPE_PM_IN.equals(key)) {
                    strArray = value.split("~");
                    rst.put("pmIn_1", strArray[0] + ":00");
                    rst.put("pmIn_2", strArray[1] + ":00");
                    continue;
                }
                if (CommConstant.STU_ATT_SCOPE_PM_OUT.equals(key)) {
                    strArray = value.split("~");
                    rst.put("pmOut_1", strArray[0] + ":00");
                    rst.put("pmOut_2", strArray[1] + ":00");
                    continue;
                }
                if (CommConstant.STU_ATT_SCOPE_NIGHT_IN.equals(key)) {
                    strArray = value.split("~");
                    rst.put("nightIn_1", strArray[0] + ":00");
                    rst.put("nightIn_2", strArray[1] + ":00");
                    continue;
                }
            } else {
                if (CommConstant.TEA_ATT_SCOPE_AM_IN.equals(key)) {
                    strArray = value.split("~");
                    rst.put("amIn_1", strArray[0] + ":00");
                    rst.put("amIn_2", strArray[1] + ":00");
                    continue;
                }
                if (CommConstant.TEA_ATT_SCOPE_PM_OUT.equals(key)) {
                    strArray = value.split("~");
                    rst.put("pmOut_1", strArray[0] + ":00");
                    rst.put("pmOut_2", strArray[1] + ":00");
                    continue;
                }
            }
        }

        return rst;
    }

    private String parseState(String state) {
        switch (state) {
            case CommConstant.ATT_STATE_NO_SIGN:
                return "缺勤";
            case CommConstant.ATT_STATE_ON_TIME:
                return "正常";
            case CommConstant.ATT_STATE_BE_LATE:
                return "迟到";
            case CommConstant.ATT_STATE_LEAVE_EARLY:
                return "早退";
            default:
                return "缺勤";
        }
    }


}
