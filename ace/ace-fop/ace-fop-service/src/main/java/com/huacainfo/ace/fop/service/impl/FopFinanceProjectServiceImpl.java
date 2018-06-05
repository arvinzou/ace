package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.ValidateUtils;
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.dao.FopCompanyDao;
import com.huacainfo.ace.fop.dao.FopFinanceProjectDao;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.model.FopFinanceProject;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.service.FopFinanceProjectService;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.FopQuestionService;
import com.huacainfo.ace.fop.vo.FopFinanceProjectQVo;
import com.huacainfo.ace.fop.vo.FopFinanceProjectVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fopFinanceProjectService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(流程记录)
 */
public class FopFinanceProjectServiceImpl implements FopFinanceProjectService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopFinanceProjectDao fopFinanceProjectDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private FopQuestionService fopQuestionService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FopCompanyDao fopCompanyDao;

    @Autowired
    private FopAssociationDao fopAssociationDao;
    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(流程记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopFinanceProjectVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopFinanceProjectVo> findFopFinanceProjectList(FopFinanceProjectQVo condition, int start,
                                                                     int limit, String orderBy) throws Exception {
        PageResult<FopFinanceProjectVo> rst = new PageResult<FopFinanceProjectVo>();
        List<FopFinanceProjectVo> list = this.fopFinanceProjectDao.findListVo(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopFinanceProjectDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(流程记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopFinanceProjectVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public ResultResponse findFinanceProjectList(FopFinanceProjectQVo condition, int page, int limit, String orderBy) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.fopFinanceProjectDao.findListVo(condition, (page - 1) * limit, limit, orderBy));
        map.put("total", this.fopFinanceProjectDao.findCount(condition));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "融资列表", map);
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopFinanceProject
     * @Description: TODO(添加流程记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopFinanceProject(FopFinanceProject o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceTitle())) {
            return new MessageResponse(1, "融资名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "所属公司不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceAmount())) {
            return new MessageResponse(1, "融资金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceYear())) {
            return new MessageResponse(1, "融资年限不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceContent())) {
            return new MessageResponse(1, "融资内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getYearYield())) {
            return new MessageResponse(1, "预期年收益不能为空！");
        }
        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }
        int temp = this.fopFinanceProjectDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "流程记录名称重复！");
        }
        o.setReleaseDate(new Date());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopFinanceProjectDao.insertSelective(o);
        this.dataBaseLogService.log("添加流程记录", "流程记录", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加流程记录完成！");
    }

    @Override
    public MessageResponse insertFinanceProject(FopFinanceProject o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceTitle())) {
            return new MessageResponse(1, "融资名称不能为空！");
        }

        SingleResult<UsersVo> singleResult = usersService.selectUsersByPrimaryKey(userProp.getUserId());
        UsersVo user = singleResult.getValue();
        if (null == user) {
            return new MessageResponse(1, "没有注册");
        }
        if (CommonUtils.isBlank(user.getDepartmentId())) {
            return new MessageResponse(1, "账户没有绑定企业！");
        }
        FopCompany fc = fopCompanyDao.selectByDepartmentId(user.getDepartmentId());
        if (null == fc) {
            if ("3" == fc.getCompanyType()) {
                return new MessageResponse(ResultCode.FAIL, "注册银行不能发布");
            }
            return new MessageResponse(1, "注册账号不是企业账号！");
        }
        o.setCompanyId(fc.getId());
        if (CommonUtils.isBlank(o.getFinanceAmount())) {
            return new MessageResponse(1, "融资金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceYear())) {
            return new MessageResponse(1, "融资年限不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceContent())) {
            return new MessageResponse(1, "融资内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getYearYield())) {
            return new MessageResponse(1, "预期年收益不能为空！");
        }
        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }
        int temp = this.fopFinanceProjectDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "流程记录名称重复！");
        }
        o.setReleaseDate(new Date());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopFinanceProjectDao.insertSelective(o);
        this.dataBaseLogService.log("添加流程记录", "流程记录", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加流程记录完成！");
    }

    /**
     * @throws
     * @Title:updateFopFinanceProject
     * @Description: TODO(更新流程记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopFinanceProject(FopFinanceProject o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceTitle())) {
            return new MessageResponse(1, "融资名称不能为空！");
        }

        if (CommonUtils.isBlank(o.getFinanceAmount())) {
            return new MessageResponse(1, "融资金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceYear())) {
            return new MessageResponse(1, "融资年限不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceContent())) {
            return new MessageResponse(1, "融资内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getYearYield())) {
            return new MessageResponse(1, "预期年收益不能为空！");
        }

        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopFinanceProjectDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更流程记录", "流程记录", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "变更流程记录完成！");
    }

    /**
     * @throws
     * @Title:selectFopFinanceProjectByPrimaryKey
     * @Description: TODO(获取流程记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopFinanceProject>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopFinanceProjectVo> selectFopFinanceProjectByPrimaryKey(String id) throws Exception {
        SingleResult<FopFinanceProjectVo> rst = new SingleResult<FopFinanceProjectVo>();
        rst.setValue(this.fopFinanceProjectDao.selectVoByPrimaryKey(id));
        return rst;
    }


    @Override
    public ResultResponse selectFinanceProjectByPrimaryKey(String id) throws Exception {
        FopFinanceProjectVo ffp = this.fopFinanceProjectDao.selectVoByPrimaryKey(id);
        ffp.setComments(fopQuestionService.findCommentList(ffp.getId()));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "融资详情", ffp);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopFinanceProjectByFopFinanceProjectId
     * @Description: TODO(删除流程记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopFinanceProjectByFopFinanceProjectId(String id, UserProp userProp) throws Exception {

        this.fopFinanceProjectDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除流程记录", "流程记录", String.valueOf(id),
                String.valueOf(id), "流程记录", userProp);
        return new MessageResponse(0, "流程记录删除完成！");
    }

    /**
     * 功能描述:  审核
     *
     * @param id
     * @param auditResult  审核结果 -- 0-通过，1-不通过
     * @param auditOpinion 审核备注
     * @param userProp
     * @param: id Fop_Finance_Project.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    @Override
    public MessageResponse audit(String id, String auditResult, String auditOpinion, UserProp userProp) throws Exception {
        FopFinanceProject obj = fopFinanceProjectDao.selectByPrimaryKey(id);
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失");
        }
        if (obj.getStatus().equals("2")) {
            return new MessageResponse(ResultCode.FAIL, "请勿重复发布");
        }
        //提交审核流程
        String flowId = GUIDUtil.getGUID();
        MessageResponse rs = fopFlowRecordService.submitFlowRecord(flowId, FlowType.FINANCE_PROJECT, id, userProp);
        if (ResultCode.FAIL == rs.getStatus()) {
            return rs;
        }
        //审核
        FopFlowRecord record = fopFlowRecordService.selectByPrimaryKey(flowId);
        record.setAuditResult(auditResult);
        record.setAuditOpinion(auditOpinion);
        MessageResponse rs1 = fopFlowRecordService.audit(record, userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            throw new CustomException(rs1.getErrorMessage());
        }

        return new MessageResponse(ResultCode.SUCCESS, "审核成功");
    }


    private MessageResponse validate(FopFinanceProject o) throws Exception {
        if (!CommonUtils.isBlank(o.getFinanceAmount())) {
            if (!ValidateUtils.Two_point(String.valueOf(o.getFinanceAmount()))) {
                return new MessageResponse(ResultCode.FAIL, "融资金额(万元) 精确到小数点后两位");
            }
        }
        if (!CommonUtils.isBlank(o.getYearYield())) {
            if (!ValidateUtils.Two_point(String.valueOf(o.getFinanceAmount()))) {
                return new MessageResponse(ResultCode.FAIL, "预期年收益(%) 精确到小数点后两位");
            }
        }
        return new MessageResponse(ResultCode.SUCCESS, "数据格式正确");
    }
}
