package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.common.constant.AuditResult;
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.common.constant.FopConstant;
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.dao.FopCompanyDao;
import com.huacainfo.ace.fop.dao.FopProjectDao;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.model.FopProject;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.FopProjectService;
import com.huacainfo.ace.fop.service.FopQuestionService;
import com.huacainfo.ace.fop.vo.FopProjectQVo;
import com.huacainfo.ace.fop.vo.FopProjectVo;
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

@Service("fopProjectService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 合作项目)
 */
public class FopProjectServiceImpl implements FopProjectService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopProjectDao fopProjectDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private FopFlowRecordService fopFlowRecordService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private FopCompanyDao fopCompanyDao;

    @Autowired
    private FopAssociationDao fopAssociationDao;
    @Autowired
    private FopQuestionService fopQuestionService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: 合作项目分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopProjectVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopProjectVo> findFopProjectList(FopProjectQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<FopProjectVo> rst = new PageResult<FopProjectVo>();
        List<FopProjectVo> list = this.fopProjectDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopProjectDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse findProjectList(FopProjectQVo condition, int page, int limit, String orderBy) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.fopProjectDao.findList(condition, (page - 1) * limit, limit, orderBy));
        map.put("total", this.fopProjectDao.findCount(condition));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "项目列表", map);
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopProject
     * @Description: 添加合作项目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopProject(FopProject o, UserProp userProp)
            throws Exception {

        if (CommonUtils.isBlank(o.getProjectName())) {
            return new MessageResponse(1, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCoopType())) {
            return new MessageResponse(1, "合作方式不能为空！");
        }


        int temp = this.fopProjectDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "合作项目名称重复！");
        }


        o.setReleaseDate(DateUtil.getNowDate());

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopProjectDao.insertSelective(o);
        this.dataBaseLogService.log("添加合作项目", "合作项目", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加合作项目完成！");
    }

    @Override
    public MessageResponse insertProject(FopProject o, UserProp userProp)
            throws Exception {

        if (CommonUtils.isBlank(o.getProjectName())) {
            return new MessageResponse(1, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCoopType())) {
            return new MessageResponse(1, "合作方式不能为空！");
        }
        int temp = this.fopProjectDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "合作项目名称重复！");
        }

        SingleResult<UsersVo> singleResult = usersService.selectUsersByPrimaryKey(userProp.getUserId());
        UsersVo user = singleResult.getValue();
        if (null == user) {
            return new MessageResponse(1, "没有注册");
        }
        if (CommonUtils.isBlank(user.getDepartmentId())) {
            return new MessageResponse(1, "账户没有绑定企业！");
        }
        FopAssociation fa = fopAssociationDao.selectByDepartmentId(user.getDepartmentId());
        FopCompany fc = fopCompanyDao.selectByDepartmentId(user.getDepartmentId());
        if (null == fc) {
            if (null == fa) {
                return new MessageResponse(1, "账户没有绑定企业！");
            }
            o.setRelationId(fa.getId());
            o.setRelationType(FopConstant.ASSOCIATION);
        } else {
            o.setRelationId(fc.getId());
            o.setRelationType(FopConstant.COMPANY);
        }

        o.setReleaseDate(DateUtil.getNowDate());
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopProjectDao.insertSelective(o);
        this.dataBaseLogService.log("添加合作项目", "合作项目", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加合作项目完成！");
    }

    /**
     * @throws
     * @Title:updateFopProject
     * @Description: 更新合作项目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopProject(FopProject o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectName())) {
            return new MessageResponse(1, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCoopType())) {
            return new MessageResponse(1, "合作方式不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopProjectDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更合作项目", "合作项目", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更合作项目完成！");
    }

    /**
     * @throws
     * @Title:selectFopProjectByPrimaryKey
     * @Description: 获取合作项目)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopProject>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopProjectVo> selectFopProjectByPrimaryKey(String id) throws Exception {
        SingleResult<FopProjectVo> rst = new SingleResult<FopProjectVo>();
        rst.setValue(this.fopProjectDao.selectVoByPrimaryKey(id));
        return rst;
    }

    @Override
    public ResultResponse selectProjectByPrimaryKey(String id) throws Exception {
        FopProjectVo fp = this.fopProjectDao.selectVoByPrimaryKey(id);
        fp.setComments(this.fopQuestionService.findCommentList(id));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "项目详情", fp);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopProjectByFopProjectId
     * @Description: 删除合作项目)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopProjectByFopProjectId(String id, UserProp userProp) throws Exception {

        FopProject fopProject = fopProjectDao.selectByPrimaryKey(id);
        if (CommonUtils.isBlank(fopProject)) {
            new MessageResponse(0, "合作项目记录丢失");
        }
        fopProject.setStatus("-1");
        fopProject.setLastModifyDate(DateUtil.getNowDate());
        fopProject.setLastModifyUserName(userProp.getName());
        fopProject.setLastModifyUserId(userProp.getUserId());
        fopProjectDao.updateByPrimaryKeySelective(fopProject);
        dataBaseLogService.log("删除合作项目", "合作项目", id, id, "合作项目", userProp);
        return new MessageResponse(0, "合作项目删除完成！");
    }

    /**
     * 功能描述: 审核发布项目
     *@param id
     * @param: userProp
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 15:47
     */
    @Override
    public MessageResponse audit(String id, UserProp userProp) throws Exception {

        FopProject fopProject = fopProjectDao.selectByPrimaryKey(id);
        if (CommonUtils.isBlank(fopProject)) {
            new MessageResponse(0, "合作项目记录丢失");
        }

        if (fopProject.getStatus().equals("2")) {
            return new MessageResponse(ResultCode.FAIL, "请勿重复发布");
        }
        //提交审核流程
        String flowId = GUIDUtil.getGUID();
        MessageResponse rs = fopFlowRecordService.submitFlowRecord(flowId, FlowType.COOP_PROJECT, id, userProp);
        if (ResultCode.FAIL == rs.getStatus()) {
            return rs;
        }
        //自动审核通过
        FopFlowRecord record = fopFlowRecordService.selectByPrimaryKey(flowId);
        record.setAuditResult(AuditResult.PASS);
        record.setAuditOpinion("系统自动审核");
        record.setRemark("系统自动审核");
        MessageResponse rs1 = fopFlowRecordService.audit(record, userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            throw new CustomException(rs1.getErrorMessage());
        }

        return new MessageResponse(ResultCode.SUCCESS, "发布成功");
    }

    /**
     * 功能描述:  根据主键查询记录-单表
     *
     * @param id
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 17:11
     */
    @Override
    public FopProject selectByPrimaryKey(String id) {

        return fopProjectDao.selectByPrimaryKey(id);
    }
}
