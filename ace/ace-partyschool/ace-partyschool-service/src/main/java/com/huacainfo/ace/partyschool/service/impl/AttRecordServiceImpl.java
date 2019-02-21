package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.AttRecordDao;
import com.huacainfo.ace.partyschool.model.AttRecord;
import com.huacainfo.ace.partyschool.service.AttRecordService;
import com.huacainfo.ace.partyschool.vo.AttRecordQVo;
import com.huacainfo.ace.partyschool.vo.AttRecordVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


        int temp = this.attRecordDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "学员考勤名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.attRecordDao.insert(o);
        this.dataBaseLogService.log("添加学员考勤", "学员考勤", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
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

            int t = attRecordDao.isExit(o);
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

}
