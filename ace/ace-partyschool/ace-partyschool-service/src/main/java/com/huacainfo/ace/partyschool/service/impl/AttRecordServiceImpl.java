package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.access.AccessHelper;
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
        List<AttRecordVo> list = this.attRecordDao.findList(condition, start, limit, orderBy);
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
        if (o.getLatitude().compareTo(BigDecimal.ZERO) == 0
                || o.getLongitude().compareTo(BigDecimal.ZERO) == 0) {
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

        //存储对象
        o.setAttTimeStr(DateUtil.toStr(o.getAttTime()));
        o.setUserId(userProp.getUserId());
        o.setUserType(user.getUserLevel());
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        attRecordDao.insert(o);
        dataBaseLogService.log("添加学员考勤", "学员考勤", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "签到成功！");
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
            List<AttRecordVo> list = attRecordDao.findList(condition, 0, 100, "t.attTime asc");
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
    public List<AttRecordExcel> exportAttRecord(AttRecordQVo condition) {
        List<AttRecordExcel> rst = new LinkedList<>();

        AttRecordExcel data;
        List<AttRecordVo> list = attRecordDao.findList(condition, 0, 65536, "t.attTime desc");
        for (AttRecordVo item : list) {
            data = new AttRecordExcel();
            data.setName(item.getUserName());
            data.setUserType(item.getUserTypeName());
            data.setSrcType("2".equalsIgnoreCase(item.getStatus()) ? "中控考勤" : "手机考勤");
            data.setAttTime(item.getAttTime());
            rst.add(data);
        }
        return rst;
    }


}