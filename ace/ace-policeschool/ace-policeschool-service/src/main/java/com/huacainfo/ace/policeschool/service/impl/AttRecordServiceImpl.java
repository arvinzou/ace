package com.huacainfo.ace.policeschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.access.AccessHelper;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.policeschool.dao.AttRecordDao;
import com.huacainfo.ace.policeschool.dao.ZkAttDataDao;
import com.huacainfo.ace.policeschool.model.AttRecord;
import com.huacainfo.ace.policeschool.model.AttRecordExcel;
import com.huacainfo.ace.policeschool.model.ZkAttData;
import com.huacainfo.ace.policeschool.service.AttRecordService;
import com.huacainfo.ace.policeschool.vo.AttRecordQVo;
import com.huacainfo.ace.policeschool.vo.AttRecordVo;
import com.huacainfo.ace.policeschool.vo.ZkAttDataQVo;
import com.huacainfo.ace.policeschool.vo.ZkAttDataVo;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

    @Override
    public PageResult<AttRecordVo> findAttRecordListExpor(AttRecordQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<AttRecordVo> rst = new PageResult<>();
        List<AttRecordVo> list = this.attRecordDao.findListExpor(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.attRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
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
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
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
     * @return: Map<String,Object>
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
     * @Description: TODO(批量删除学员考勤）
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
     * 学员考勤导出
     * @param condition
     * @return
     */
    @Override
    public List<AttRecordExcel> exportAttRecord(AttRecordQVo condition) {
        List<AttRecordExcel> rst = new LinkedList<>();
        Date startDate = DateUtil.getDate(condition.getStartDate(), DateUtil.DEFAULT_DATE_REGEX);
        Date endDate = DateUtil.getDate(condition.getEndDate(), DateUtil.DEFAULT_DATE_REGEX);

        //循环日期
        Date nowDate = startDate;
        String nowDateStr;
        Calendar c = Calendar.getInstance();
        List<AttRecordExcel> subList;
        while (nowDate.compareTo(endDate) <= 0) {
            nowDateStr = DateUtil.toStr(nowDate);
            condition.setNowDate(nowDateStr);
            condition.setStartDate(nowDateStr + " 00:00:00");
            condition.setEndDate(nowDateStr + " 23:59:59");
            subList = getOneDayList(condition);
            rst.addAll(subList);
            //循环条件
            c.setTime(nowDate);
            c.add(Calendar.DAY_OF_MONTH, 1);
            nowDate = c.getTime();
        }
        return rst;
    }

    private List<AttRecordExcel> getOneDayList(AttRecordQVo condition) {
        //1.数据库原始记录
        List<AttRecordVo> list = attRecordDao.findList(condition, 0, 65536, "");
        //2.转换为数据map
        Map<String, AttRecordExcel> dataMap = convertDataMap(list);
        //3.包装
        return packageExcelResult(dataMap);
    }

    private Map<String, AttRecordExcel> convertDataMap(List<AttRecordVo> list) {
        Map<String, AttRecordExcel> map = new HashMap<>();
        AttRecordExcel stu;
        String key;
        String date;
        for (AttRecordVo item : list) {
            date = item.getAttenDateTime();
            key = item.getAttDate() + "&" + item.getUserId();
            stu = map.get(key);
            stu = null == stu ? new AttRecordExcel() : stu;
            stu.setUserName(item.getUserName());

            stu.setAttenTime(StringUtil.isEmpty(item.getAttenDateTime()) ? "(缺勤)" : item.getAttenDateTime());
            stu.setAttenDate(item.getAttDate());
            map.put(key, stu);
        }

        return map;
    }

    private List<AttRecordExcel> packageExcelResult(Map<String, AttRecordExcel> dataMap) {
        List<AttRecordExcel> rst = new LinkedList<>();
        AttRecordExcel temp;
        Map.Entry<String, AttRecordExcel> entry;
        Iterator<Map.Entry<String, AttRecordExcel>> entries = dataMap.entrySet().iterator();
        while (entries.hasNext()) {
            entry = entries.next();
            temp = dataMap.get(entry.getKey());

            rst.add(temp);
        }
        return rst;
    }


    private List<AttRecordVo> getOneDayList1(AttRecordQVo condition, int start, int limit, String orderBy) throws Exception {
        //1.数据库原始记录
        List<AttRecordVo> list = attRecordDao.findListExpor(condition, start, limit, orderBy);
        List<AttRecordVo> list1 = new ArrayList();
        //2.转换为数据map
        // Map<String, AttRecordVo> dataMap = convertDataMap1(list);
        Map<String, AttRecordVo> map = new HashMap<>();
        AttRecordVo stu = new AttRecordVo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (AttRecordVo item : list) {

            stu = null == stu ? new AttRecordVo() : stu;
            stu.setUserName(item.getUserName());
            String dat = item.getAttenDateTime();

            if (!StringUtil.isEmpty(item.getAttenDateTime())) {

                Date dt2 = sdf.parse((item.getAttenDateTime()));
                ;
                stu.setAttenTime(dt2.getTime() / 1000);
            }
            stu.setAttenDate(item.getAttDate());
            list1.add(stu);
        }
        return list1;
    }

}
