package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.ValidateUtils;
import com.huacainfo.ace.fop.dao.FopPersonDao;
import com.huacainfo.ace.fop.model.FopPerson;
import com.huacainfo.ace.fop.service.FopPersonService;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import com.huacainfo.ace.fop.vo.FopPersonQVo;
import com.huacainfo.ace.fop.vo.FopPersonVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopPersonService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description:人员档案
 */
public class FopPersonServiceImpl implements FopPersonService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopPersonDao fopPersonDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopPersonVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopPersonVo> findFopPersonList(FopPersonQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult<FopPersonVo> rst = new PageResult<FopPersonVo>();
        List<FopPersonVo> list = this.fopPersonDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopPersonDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopPerson
     * @Description: TODO(添加企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopPerson(FopPerson o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(1, "姓名不能为空");
        }
        if (CommonUtils.isBlank(o.getSex())) {
            return new MessageResponse(1, "性别不能为空");
        }
//        if (CommonUtils.isBlank(o.getIdentityCard())) {
//            return new MessageResponse(1, "身份证号码不能为空");
//        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空");
        }
        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }
        int temp = this.fopPersonDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "个人档案重复");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopPersonDao.insertSelective(o);
        this.dataBaseLogService.log("添加个人档案", "个人档案", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加个人档案完成");
    }

    /**
     * @throws
     * @Title:updateFopPerson
     * @Description: TODO(更新企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopPerson(FopPerson o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(1, "法人姓名不能为空！");
        }
//        if (CommonUtils.isBlank(o.getSex())) {
//            return new MessageResponse(1, "性别不能为空！");
//        }
//        if (CommonUtils.isBlank(o.getIdentityCard())) {
//            return new MessageResponse(1, "身份证号码不能为空！");
//        }
        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopPersonDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更企业管理完成！");

    }

    /**
     * @throws
     * @Title:selectFopPersonByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopPerson>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopPersonVo> selectFopPersonByPrimaryKey(String id) throws Exception {
        SingleResult<FopPersonVo> rst = new SingleResult<FopPersonVo>();
        rst.setValue(this.fopPersonDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopPersonByFopPersonId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopPersonByFopPersonId(String id, UserProp userProp) throws Exception {
        this.fopPersonDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除企业管理", "企业管理", String.valueOf(id),
                String.valueOf(id), "企业管理", userProp);
        return new MessageResponse(0, "企业管理删除完成！");
    }

    @Override
    public ResultResponse insertPerson(FopCompanyVo companyVo, UserProp userProp) throws Exception {
        FopPerson person = new FopPerson();
        String realName = StringUtil.isNotEmpty(companyVo.getRealName())
                ? companyVo.getRealName() : companyVo.getFullName();
        person.setRealName(realName);
        person.setMobileNumer(companyVo.getLpMobile());
        person.setSex(companyVo.getLpSex());
        person.setBirthDate(companyVo.getLpBirthDt());
        person.setNativePlace(companyVo.getLpNativePlace());
        person.setNationality(companyVo.getLpNationality());
        person.setPolitical(companyVo.getLpPolitical());
        person.setRecruitmentDate(companyVo.getLpRecruitmentDate());
        person.setEducation(companyVo.getLpEducation());
        person.setSkillJobTitle(companyVo.getLpSkillJobTitle());
        person.setDeptPost(companyVo.getLpDeptPost());
        person.setIdentityCard(companyVo.getLpIdentityCard());
        person.setSocietyPost(companyVo.getLpSocietyPost());
        MessageResponse mm = validate(person);
        if (ResultCode.FAIL == mm.getStatus()) {
            return new ResultResponse(ResultCode.FAIL, mm.getErrorMessage());
        }
        int temp = this.fopPersonDao.isExit(person);
        if (temp > 0) {
            return new ResultResponse(ResultCode.FAIL, "个人档案重复");
        }
        person.setId(GUIDUtil.getGUID());
        person.setCreateDate(DateUtil.getNowDate());
        person.setStatus("1");
        person.setCreateUserName(userProp.getName());
        person.setCreateUserId(userProp.getUserId());
        fopPersonDao.insertSelective(person);
        dataBaseLogService.log("添加个人档案", "企业注册-自动插入", "", person.getId(),
                person.getId(), userProp);
        return new ResultResponse(ResultCode.SUCCESS, "添加个人档案完成", person);
    }

    @Override
    public FopPerson selectByMobile(String mobileNumber) {
        return fopPersonDao.selectByMobile(mobileNumber);
    }

    private MessageResponse validate(FopPerson o) throws Exception {
        if (!CommonUtils.isBlank(o.getRealName())) {
            if (!ValidateUtils.Chinese(o.getRealName())) {
                return new MessageResponse(ResultCode.FAIL, "姓名格式不对，必须中文");
            }
        }

        if (!CommonUtils.isBlank(o.getIdentityCard())) {
            if (!ValidateUtils.IDcard(String.valueOf(o.getIdentityCard()))) {
                return new MessageResponse(ResultCode.FAIL, "身份证号码格式不正确");
            }
        }
        if (!CommonUtils.isBlank(o.getMobileNumer())) {
            if (!ValidateUtils.Mobile(String.valueOf(o.getMobileNumer()))) {
                return new MessageResponse(ResultCode.FAIL, "手机号码格式不正确");
            }
        }
        return new MessageResponse(ResultCode.SUCCESS, "数据格式正确");
    }
}
