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
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.common.constant.FopConstant;
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.dao.FopCompanyDao;
import com.huacainfo.ace.fop.dao.FopLoanProductDao;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.model.FopLoanProduct;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.FopLoanProductService;
import com.huacainfo.ace.fop.service.FopQuestionService;
import com.huacainfo.ace.fop.vo.FopLoanProductQVo;
import com.huacainfo.ace.fop.vo.FopLoanProductVo;
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

@Service("fopLoanProductService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(通知公告)
 */
public class FopLoanProductServiceImpl implements FopLoanProductService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopLoanProductDao fopLoanProductDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private FopQuestionService fopQuestionService;
    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    @Autowired
    private FopCompanyDao fopCompanyDao;

    @Autowired
    private UsersService usersService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopLoanProductVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopLoanProductVo> findFopLoanProductList(FopLoanProductQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<FopLoanProductVo> rst = new PageResult<FopLoanProductVo>();
        List<FopLoanProductVo> list = this.fopLoanProductDao.findListVo(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopLoanProductDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopLoanProductVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public ResultResponse findLoanProductList(FopLoanProductQVo condition, int page, int limit, String orderBy) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.fopLoanProductDao.findListVo(condition, (page - 1) * limit, limit, orderBy));
        map.put("total", this.fopLoanProductDao.findCount(condition));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "金融产品列表", map);
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopLoanProduct
     * @Description: TODO(添加通知公告)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopLoanProduct(FopLoanProduct o, UserProp userProp)
            throws Exception {

         /*获取登陆用户信息*/
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
            return new MessageResponse(1, "账户没有绑定企业！");
        }
        if (!"3".equals(fc.getCompanyType())) {
            return new MessageResponse(1, "非银行不能发布金融产品");
        }
        o.setCompanyId(fc.getId());
        if (CommonUtils.isBlank(o.getProductName())) {
            return new MessageResponse(1, "产品名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getDescription())) {
            return new MessageResponse(1, "产品内容不能为空！");
        }

        int temp = this.fopLoanProductDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "通知公告名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setReleaseDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopLoanProductDao.insertSelective(o);
        this.dataBaseLogService.log("添加通知公告", "通知公告", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加通知公告完成！");
    }

    /**
     * @throws
     * @Title:updateFopLoanProduct
     * @Description: TODO(更新通知公告)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopLoanProduct(FopLoanProduct o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProductName())) {
            return new MessageResponse(1, "产品名称不能为空！");
        }

        if (CommonUtils.isBlank(o.getDescription())) {
            return new MessageResponse(1, "产品内容不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopLoanProductDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更通知公告", "通知公告", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更通知公告完成！");
    }

    /**
     * @throws
     * @Title:selectFopLoanProductByPrimaryKey
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopLoanProduct>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopLoanProductVo> selectFopLoanProductByPrimaryKey(String id) throws Exception {
        SingleResult<FopLoanProductVo> rst = new SingleResult<FopLoanProductVo>();
        rst.setValue(this.fopLoanProductDao.selectVoByPrimaryKeyVo(id));
        return rst;
    }

    @Override
    public ResultResponse selectLoanProductByPrimaryKey(String id) throws Exception {
        FopLoanProductVo ffp = this.fopLoanProductDao.selectVoByPrimaryKeyVo(id);
        ffp.setComments(fopQuestionService.findCommentList(ffp.getId()));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "金融产品详情", ffp);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopLoanProductByFopLoanProductId
     * @Description: TODO(删除通知公告)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopLoanProductByFopLoanProductId(String id,
                                                                  UserProp userProp) throws Exception {
        FopLoanProduct fopLoanProduct = fopLoanProductDao.selectByPrimaryKey(id);
        if (null == fopLoanProduct) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失！");
        }
        fopLoanProduct.setStatus("0");
        fopLoanProduct.setLastModifyUserId(userProp.getUserId());
        fopLoanProduct.setLastModifyUserName(userProp.getName());
        fopLoanProduct.setLastModifyDate(DateUtil.getNowDate());
        fopLoanProductDao.updateByPrimaryKeySelective(fopLoanProduct);
        dataBaseLogService.log("删除金融产品", "金融产品",
                String.valueOf(id),
                String.valueOf(id), "金融产品", userProp);
        return new MessageResponse(0, "通知公告删除完成！");
    }

    /**
     * 功能描述:  审核
     *
     * @param id
     * @param auditResult  审核结果 -- 0-通过，1-不通过
     * @param auditOpinion 审核备注
     * @param: id Fop_Finance_Project.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    @Override
    public MessageResponse audit(String id, String auditResult, String auditOpinion, UserProp userProp) throws Exception {
        FopLoanProduct obj = fopLoanProductDao.selectByPrimaryKey(id);
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失");
        }
        if (obj.getStatus().equals("2")) {
            return new MessageResponse(ResultCode.FAIL, "请勿重复发布");
        }
        //提交审核流程
        String flowId = GUIDUtil.getGUID();
        MessageResponse rs = fopFlowRecordService.submitFlowRecord(flowId, FlowType.LOAN_PROJECT, id, userProp);
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
}
