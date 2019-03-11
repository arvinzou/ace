package com.huacainfo.ace.taa.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.dao.RegisterRuleDao;
import com.huacainfo.ace.taa.model.RegisterRule;
import com.huacainfo.ace.taa.service.RegisterRuleService;
import com.huacainfo.ace.taa.vo.RegisterRuleQVo;
import com.huacainfo.ace.taa.vo.RegisterRuleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("registerRuleService")
/**
 * @author: Arvin
 * @version: 2019-03-08
 * @Description: TODO(注册规则)
 */
public class RegisterRuleServiceImpl implements RegisterRuleService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RegisterRuleDao registerRuleDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(注册规则分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RegisterRuleVo>
     * @author: Arvin
     * @version: 2019-03-08
     */
    @Override
    public PageResult<RegisterRuleVo> findRegisterRuleList(RegisterRuleQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<RegisterRuleVo> rst = new PageResult<>();
        List<RegisterRuleVo> list = registerRuleDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = registerRuleDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertRegisterRule
     * @Description: TODO(添加注册规则)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse insertRegisterRule(RegisterRule o, UserProp userProp) throws Exception {


        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getCopNo())) {
            return new MessageResponse(1, "警号不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号码不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeptId())) {
            return new MessageResponse(1, "部门不能为空！");
        }


        int temp = registerRuleDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "警员信息重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        o.setCreateDate(new Date());
        registerRuleDao.insert(o);
        dataBaseLogService.log("添加注册规则", "注册规则", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateRegisterRule
     * @Description: TODO(更新注册规则)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse updateRegisterRule(RegisterRule o, UserProp userProp) {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        RegisterRule obj = registerRuleDao.selectByPrimaryKey(o.getId());
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "数据记录丢失！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getCopNo())) {
            return new MessageResponse(1, "警号不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号码不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeptId())) {
            return new MessageResponse(1, "部门不能为空！");
        }

        obj.setName(o.getName());
        obj.setCopNo(o.getCopNo());
        obj.setMobile(o.getMobile());
        obj.setDeptId(o.getDeptId());
        registerRuleDao.updateByPrimaryKey(obj);
        dataBaseLogService.log("变更注册规则", "注册规则", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectRegisterRuleByPrimaryKey
     * @Description: TODO(获取注册规则)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RegisterRule>
     * @author: Arvin
     * @version: 2019-03-08
     */
    @Override
    public SingleResult<RegisterRuleVo> selectRegisterRuleByPrimaryKey(String id) throws Exception {
        SingleResult<RegisterRuleVo> rst = new SingleResult<>();
        rst.setValue(registerRuleDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRegisterRuleByRegisterRuleId
     * @Description: TODO(删除注册规则)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse deleteRegisterRuleByRegisterRuleId(String id, UserProp userProp) throws
            Exception {
        registerRuleDao.deleteByPrimaryKey(id);
        dataBaseLogService.log("删除注册规则", "注册规则", id, id,
                "注册规则", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核注册规则)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核注册规则", "注册规则", id, id,
                "注册规则", userProp);
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
     * @version: 2019-03-08
     */

    @Override
    public MessageResponse importXls(Map<String, Object> params, List<Map<String, Object>> list, UserProp userProp) {
        String deptId = String.valueOf(params.get("deptId"));

        for (Map<String, Object> row : list) {
            RegisterRuleVo o = new RegisterRuleVo();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setId(GUIDUtil.getGUID());
            o.setCreateDate(new Date());
            o.setStatus("1");
            o.setDeptId(deptId);

            logger.info(o.toString());
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "序号[" + o.getIndex() + "]主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "序号[" + o.getIndex() + "]" + "姓名不能为空！");
            }
            if (CommonUtils.isBlank(o.getCopNo())) {
                return new MessageResponse(1, "序号[" + o.getIndex() + "]" + "警号不能为空！");
            }
            if (CommonUtils.isBlank(o.getMobile())) {
                return new MessageResponse(1, "序号[" + o.getIndex() + "]" + "手机号码不能为空！");
            }
            if (CommonUtils.isBlank(o.getDeptId())) {
                return new MessageResponse(1, "序号[" + o.getIndex() + "]" + "所属支队不能为空！");
            }

            int t = registerRuleDao.isExist(o);
            if (t > 0) {
                registerRuleDao.updateByPrimaryKey(o);

            } else {
                registerRuleDao.insert(o);
            }
        }
        dataBaseLogService.log("注册规则导入", "注册规则", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
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
     * @version: 2019-03-08
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        registerRuleDao.updateStatus(id, status);
        dataBaseLogService.log("跟新状态", "注册规则", id, id, "注册规则", userProp);
        return new MessageResponse(0, "成功！");
    }

}
