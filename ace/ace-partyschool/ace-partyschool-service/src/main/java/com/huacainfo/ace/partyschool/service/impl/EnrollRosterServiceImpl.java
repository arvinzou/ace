package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.EnrollRosterDao;
import com.huacainfo.ace.partyschool.model.EnrollRoster;
import com.huacainfo.ace.partyschool.service.EnrollRosterService;
import com.huacainfo.ace.partyschool.vo.EnrollRosterQVo;
import com.huacainfo.ace.partyschool.vo.EnrollRosterVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("enrollRosterService")
/**
 * @author: Arvin
 * @version: 2019-01-16
 * @Description: TODO(报名花名册管理)
 */
public class EnrollRosterServiceImpl implements EnrollRosterService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EnrollRosterDao enrollRosterDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(报名花名册管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EnrollRosterVo>
     * @author: Arvin
     * @version: 2019-01-16
     */
    @Override
    public PageResult<EnrollRosterVo> findEnrollRosterList(EnrollRosterQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<EnrollRosterVo> rst = new PageResult<>();
        List<EnrollRosterVo> list = this.enrollRosterDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.enrollRosterDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertEnrollRoster
     * @Description: TODO(添加报名花名册管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    @Override
    public MessageResponse insertEnrollRoster(EnrollRoster o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getClsId())) {
            return new MessageResponse(1, "报名班次不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "学员姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getWorkUnitName())) {
            return new MessageResponse(1, "单位全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPostName())) {
            return new MessageResponse(1, "职务全称不能为空！");
        }

        int temp = this.enrollRosterDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "人员重复！");
        }

        String id = CommonUtils.isBlank(o.getId()) || "_empty".equals(o.getId()) ?
                GUIDUtil.getGUID() : o.getId();
        o.setId(id);
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.enrollRosterDao.insert(o);
        this.dataBaseLogService.log("添加报名花名册管理", "报名花名册管理", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateEnrollRoster
     * @Description: TODO(更新报名花名册管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    @Override
    public MessageResponse updateEnrollRoster(EnrollRoster o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getClsId())) {
            return new MessageResponse(1, "报名班次不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "学员姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getWorkUnitName())) {
            return new MessageResponse(1, "单位全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPostName())) {
            return new MessageResponse(1, "职务全称不能为空！");
        }

        EnrollRoster old = enrollRosterDao.selectByPrimaryKey(o.getId());
        if (old == null) {
            return new MessageResponse(1, "数据丢失！");
        }

        o.setStatus("1");
        o.setUpdateDate(DateUtil.getNowDate());
        o.setCreateDate(old.getCreateDate());
        this.enrollRosterDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更报名花名册管理", "报名花名册管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectEnrollRosterByPrimaryKey
     * @Description: TODO(获取报名花名册管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EnrollRoster>
     * @author: Arvin
     * @version: 2019-01-16
     */
    @Override
    public SingleResult<EnrollRosterVo> selectEnrollRosterByPrimaryKey(String id) throws Exception {
        SingleResult<EnrollRosterVo> rst = new SingleResult<>();
        rst.setValue(this.enrollRosterDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEnrollRosterByEnrollRosterId
     * @Description: TODO(删除报名花名册管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    @Override
    public MessageResponse deleteEnrollRosterByEnrollRosterId(String id, UserProp userProp) throws
            Exception {
        this.enrollRosterDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除报名花名册管理", "报名花名册管理", id, id,
                "报名花名册管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核报名花名册管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核报名花名册管理", "报名花名册管理", id, id,
                "报名花名册管理", userProp);
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
     * @version: 2019-01-16
     */

    @Override
    public MessageResponse importXls(String clsId, List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        int t;
        for (Map<String, Object> row : list) {
            EnrollRoster o = new EnrollRoster();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setClsId(clsId);
            o.setStatus("1");
            o.setId(GUIDUtil.getGUID());
            o.setCreateDate(new Date());

            this.logger.info(o.toString());
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "第" + i + "行，" + "学员姓名不能为空！");
            }
            if (CommonUtils.isBlank(o.getWorkUnitName())) {
                return new MessageResponse(1, "第" + i + "行，" + "单位全称不能为空！");
            }
            if (CommonUtils.isBlank(o.getPostName())) {
                return new MessageResponse(1, "第" + i + "行，" + "职务全称不能为空！");
            }

            t = enrollRosterDao.isExist(o);
            if (t > 0) {
                this.enrollRosterDao.updateByPrimaryKey(o);
            } else {
                this.enrollRosterDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("报名花名册管理导入", "报名花名册管理", "", "rs.xls", "rs.xls", userProp);
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
     * @version: 2019-01-16
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.enrollRosterDao.getList(p));

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
     * @version: 2019-01-16
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.enrollRosterDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除报名花名册管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    @Override
    public MessageResponse deleteEnrollRosterByEnrollRosterIds(String[] id, UserProp userProp) throws Exception {

        this.enrollRosterDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除报名花名册管理", "报名花名册管理", id[0], id[0], "报名花名册管理", userProp);
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
     * @version: 2019-01-16
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.enrollRosterDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "报名花名册管理", id, id, "报名花名册管理", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * 是否准许报名
     *
     * @param name 学员姓名
     * @return t/f
     */
    @Override
    public boolean isAllowed(String name) {
        EnrollRosterQVo condition = new EnrollRosterQVo();
        condition.setName(name);
        condition.setStatus("1");
        int i = enrollRosterDao.findCount(condition);

        if (i <= 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 批量开启/关闭报名
     *
     * @param clsId       班级ID
     * @param status      开启/关闭  1-开，0-关
     * @param curUserProp
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse updateStatusByClsId(String clsId, String status, UserProp curUserProp) {
        int i = enrollRosterDao.updateStatusByClsId(clsId, status);
        logger.info("已更新[" + i + "]条数据");
        return new MessageResponse(ResultCode.SUCCESS, "操作成功");
    }

    /**
     * 批量删除报名数据
     *
     * @param clsId 班级ID
     * @return MessageResponse
     */
    @Override
    public MessageResponse batchDel(String clsId) {
        int i = enrollRosterDao.delByClsId(clsId);

        return new MessageResponse(ResultCode.SUCCESS, "删除成功");
    }

}
