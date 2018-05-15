package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.common.constant.FopConstant;
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.dao.FopCompanyDao;
import com.huacainfo.ace.fop.dao.FopQuestionnaireResultDao;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.model.FopQuestionnaireResult;
import com.huacainfo.ace.fop.service.FopQuestionnaireResultService;
import com.huacainfo.ace.fop.vo.FopQuestionnaireResultQVo;
import com.huacainfo.ace.fop.vo.FopQuestionnaireResultVo;
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

@Service("fopQuestionnaireResultService")
/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: (满意度调查)
 */
public class FopQuestionnaireResultServiceImpl implements FopQuestionnaireResultService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopQuestionnaireResultDao fopQuestionnaireResultDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private FopCompanyDao fopCompanyDao;

    @Autowired
    private FopAssociationDao fopAssociationDao;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: (满意度调查分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopQuestionnaireResultVo>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public PageResult<FopQuestionnaireResultVo> findFopQuestionnaireResultList(FopQuestionnaireResultQVo condition, int start,
                                                                               int limit, String orderBy) throws Exception {
        PageResult<FopQuestionnaireResultVo> rst = new PageResult<>();
        List<FopQuestionnaireResultVo> list = this.fopQuestionnaireResultDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopQuestionnaireResultDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse findQuestionnaireResultList(FopQuestionnaireResultQVo condition, int page,
                                                      int limit, String orderBy) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.fopQuestionnaireResultDao.findList(condition, (page - 1) * limit, limit, orderBy));
        map.put("total", this.fopQuestionnaireResultDao.findCount(condition));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "法律帮助列表", map);
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopQuestionnaireResult
     * @Description: (添加满意度调查)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse insertFopQuestionnaireResult(FopQuestionnaireResult o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getAnswerId())) {
            return new MessageResponse(1, "答题人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswerType())) {
            return new MessageResponse(1, "答题人类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getQuestionnaireId())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getResult())) {
            return new MessageResponse(1, "调查结果不能为空！");
        }


        int temp = this.fopQuestionnaireResultDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "满意度调查名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setReleaseDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopQuestionnaireResultDao.insertSelective(o);
        this.dataBaseLogService.log("添加满意度调查", "满意度调查", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加满意度调查完成！");
    }

    @Override
    public MessageResponse insertQuestionnaireResult(FopQuestionnaireResult o, UserProp userProp) throws Exception {
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
            o.setAnswerId(fa.getId());
            o.setAnswerType(FopConstant.ASSOCIATION);
        } else {
            o.setAnswerId(fc.getId());
            o.setAnswerType(FopConstant.COMPANY);
        }
        if (CommonUtils.isBlank(o.getQuestionnaireId())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getResult())) {
            return new MessageResponse(1, "调查结果不能为空！");
        }
        int temp = this.fopQuestionnaireResultDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "满意度调查名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setReleaseDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopQuestionnaireResultDao.insertSelective(o);
        this.dataBaseLogService.log("添加满意度调查", "满意度调查", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加满意度调查完成！");
    }

    /**
     * @throws
     * @Title:updateFopQuestionnaireResult
     * @Description: (更新满意度调查)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse updateFopQuestionnaireResult(FopQuestionnaireResult o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswerId())) {
            return new MessageResponse(1, "答题人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswerType())) {
            return new MessageResponse(1, "答题人类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getQuestionnaireId())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getResult())) {
            return new MessageResponse(1, "调查结果不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopQuestionnaireResultDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更满意度调查", "满意度调查", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更满意度调查完成！");
    }

    /**
     * @throws
     * @Title:selectFopQuestionnaireResultByPrimaryKey
     * @Description: (获取满意度调查)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopQuestionnaireResult>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public SingleResult<FopQuestionnaireResultVo> selectVoByPrimaryKey(String id) throws Exception {
        SingleResult<FopQuestionnaireResultVo> rst = new SingleResult<>();
        rst.setValue(this.fopQuestionnaireResultDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopQuestionnaireResultByFopQuestionnaireResultId
     * @Description: (删除满意度调查)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws Exception {
        this.fopQuestionnaireResultDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除满意度调查", "满意度调查", id, id, "满意度调查", userProp);
        return new MessageResponse(0, "满意度调查删除完成！");
    }

}
