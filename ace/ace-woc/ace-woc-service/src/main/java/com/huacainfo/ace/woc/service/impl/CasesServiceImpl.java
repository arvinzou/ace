package com.huacainfo.ace.woc.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.dao.CasesDao;
import com.huacainfo.ace.woc.model.Cases;
import com.huacainfo.ace.woc.service.CasesService;
import com.huacainfo.ace.woc.vo.CasesQVo;
import com.huacainfo.ace.woc.vo.CasesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("casesService")
/**
 * @author: Arvin
 * @version: 2018-03-28
 * @Description: TODO(案件管理)
 */
public class CasesServiceImpl implements CasesService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CasesDao casesDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(案件管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<CasesVo>
     * @author: Arvin
     * @version: 2018-03-28
     */
    @Override
    public PageResult<CasesVo> findCasesList(CasesQVo condition, int start,
                                             int limit, String orderBy) throws Exception {
        PageResult<CasesVo> rst = new PageResult<CasesVo>();
        List<CasesVo> list = this.casesDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.casesDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCases
     * @Description: TODO(添加案件管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    @Override
    public MessageResponse insertCases(Cases o, UserProp userProp)
            throws Exception {

        if (CommonUtils.isBlank(o.getCaseNo())) {
            return new MessageResponse(1, "案件号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCaseDate())) {
            return new MessageResponse(1, "立案日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getTrafficId())) {
            return new MessageResponse(1, "通行记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getVehicleId())) {
            return new MessageResponse(1, "车辆记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDriver())) {
            return new MessageResponse(1, "驾驶人记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getParty())) {
            return new MessageResponse(1, "当事人记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPartyType())) {
            return new MessageResponse(1, "当事人类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getChp1())) {
            return new MessageResponse(1, "案件处理人1不能为空！");
        }
        if (CommonUtils.isBlank(o.getLecn())) {
            return new MessageResponse(1, "执法证号不能为空！");
        }
        if (CommonUtils.isBlank(o.getChp2())) {
            return new MessageResponse(1, "案件处理人2不能为空！");
        }
        if (CommonUtils.isBlank(o.getRecorder())) {
            return new MessageResponse(1, "记录人不能为空！");
        }
        if (CommonUtils.isBlank(o.getRecordTime())) {
            return new MessageResponse(1, "笔录时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditDept())) {
            return new MessageResponse(1, "审核部门不能为空！");
        }


        int temp = this.casesDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "案件管理名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setLastModifyDate(DateUtil.getNowDate());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.casesDao.insertSelective(o);
        this.dataBaseLogService.log("添加案件管理", "案件管理", "", o.getCaseNo(),
                o.getCaseNo(), userProp);
        return new MessageResponse(0, "添加案件管理完成！");
    }

    /**
     * @throws
     * @Title:updateCases
     * @Description: TODO(更新案件管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    @Override
    public MessageResponse updateCases(Cases o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCaseNo())) {
            return new MessageResponse(1, "案件号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCaseDate())) {
            return new MessageResponse(1, "立案日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getTrafficId())) {
            return new MessageResponse(1, "通行记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getVehicleId())) {
            return new MessageResponse(1, "车辆记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDriver())) {
            return new MessageResponse(1, "驾驶人记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getParty())) {
            return new MessageResponse(1, "当事人记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPartyType())) {
            return new MessageResponse(1, "当事人类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getChp1())) {
            return new MessageResponse(1, "案件处理人1不能为空！");
        }
        if (CommonUtils.isBlank(o.getLecn())) {
            return new MessageResponse(1, "执法证号不能为空！");
        }
        if (CommonUtils.isBlank(o.getChp2())) {
            return new MessageResponse(1, "案件处理人2不能为空！");
        }
        if (CommonUtils.isBlank(o.getRecorder())) {
            return new MessageResponse(1, "记录人不能为空！");
        }
        if (CommonUtils.isBlank(o.getRecordTime())) {
            return new MessageResponse(1, "笔录时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditDept())) {
            return new MessageResponse(1, "审核部门不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.casesDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更案件管理", "案件管理", "", o.getCaseNo(),
                o.getCaseNo(), userProp);
        return new MessageResponse(0, "变更案件管理完成！");
    }

    /**
     * @throws
     * @Title:selectCasesByPrimaryKey
     * @Description: TODO(获取案件管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Cases>
     * @author: Arvin
     * @version: 2018-03-28
     */
    @Override
    public SingleResult<CasesVo> selectCasesByPrimaryKey(String id) throws Exception {
        SingleResult<CasesVo> rst = new SingleResult<CasesVo>();
        rst.setValue(this.casesDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCasesByCasesId
     * @Description: TODO(删除案件管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    @Override
    public MessageResponse deleteCasesByCasesId(String id,
                                                UserProp userProp) throws Exception {
        this.casesDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除案件管理", "案件管理", String.valueOf(id),
                String.valueOf(id), "案件管理", userProp);
        return new MessageResponse(0, "案件管理删除完成！");
    }
}
