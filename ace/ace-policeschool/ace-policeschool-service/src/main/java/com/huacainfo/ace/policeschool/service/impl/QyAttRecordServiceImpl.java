package com.huacainfo.ace.policeschool.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.policeschool.dao.QyAttRecordDao;
import com.huacainfo.ace.policeschool.model.QyAttRecord;
import com.huacainfo.ace.policeschool.service.QyAttRecordService;
import com.huacainfo.ace.policeschool.vo.QyAttRecordQVo;
import com.huacainfo.ace.policeschool.vo.QyAttRecordVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("qyAttRecordService")
/**
 * @author: ArvinZou
 * @version: 2019-03-19
 * @Description: TODO(群英考勤数据)
 */
public class QyAttRecordServiceImpl implements QyAttRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private QyAttRecordDao qyAttRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(群英考勤数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<QyAttRecordVo>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public PageResult<QyAttRecordVo> findQyAttRecordList(QyAttRecordQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<QyAttRecordVo> rst = new PageResult<>();
        List<QyAttRecordVo> list = this.qyAttRecordDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.qyAttRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertQyAttRecord
     * @Description: TODO(添加群英考勤数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse insertQyAttRecord(QyAttRecord o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getAttenId())) {
            return new MessageResponse(1, "考勤编码不能为空！");
        }


        o.setAttenId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        this.qyAttRecordDao.insert(o);
        this.dataBaseLogService.log("添加群英考勤数据", "群英考勤数据", "", o.getAttenId(), o.getAttenId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateQyAttRecord
     * @Description: TODO(更新群英考勤数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse updateQyAttRecord(QyAttRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getAttenId())) {
            return new MessageResponse(1, "考勤编码不能为空！");
        }


        this.qyAttRecordDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更群英考勤数据", "群英考勤数据", "",
                o.getAttenId(), o.getAttenId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectQyAttRecordByPrimaryKey
     * @Description: TODO(获取群英考勤数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<QyAttRecord>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public SingleResult<QyAttRecordVo> selectQyAttRecordByPrimaryKey(String id) throws Exception {
        SingleResult<QyAttRecordVo> rst = new SingleResult<>();
        rst.setValue(this.qyAttRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteQyAttRecordByQyAttRecordId
     * @Description: TODO(删除群英考勤数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse deleteQyAttRecordByQyAttRecordId(String id, UserProp userProp) throws
            Exception {
        this.qyAttRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除群英考勤数据", "群英考勤数据", id, id,
                "群英考勤数据", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核群英考勤数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核群英考勤数据", "群英考勤数据", id, id,
                "群英考勤数据", userProp);
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
     * @author: ArvinZou
     * @version: 2019-03-19
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            QyAttRecord o = new QyAttRecord();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getAttenId())) {
                return new MessageResponse(1, "考勤编码不能为空！");
            }

            int t = this.qyAttRecordDao.isExist(o);
            if (t > 0) {
                this.qyAttRecordDao.updateByPrimaryKey(o);

            } else {
                this.qyAttRecordDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("群英考勤数据导入", "群英考勤数据", "", "rs.xls", "rs.xls", userProp);
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
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.qyAttRecordDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.qyAttRecordDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除群英考勤数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse deleteQyAttRecordByQyAttRecordIds(String[] id, UserProp userProp) throws Exception {

        this.qyAttRecordDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除群英考勤数据", "群英考勤数据", id[0], id[0], "群英考勤数据", userProp);
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
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.qyAttRecordDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "群英考勤数据", id, id, "群英考勤数据", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * 取库中最近一次拉取数据时间
     *
     * @return String
     */
    @Override
    public String findLastSyncDateTime() {
        return this.qyAttRecordDao.findLastSyncDateTime();
    }

}
