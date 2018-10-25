package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.dao.PersonInfoDao;
import com.huacainfo.ace.society.dao.PersonOrgRelationDao;
import com.huacainfo.ace.society.model.PersonInfo;
import com.huacainfo.ace.society.model.PersonOrgRelation;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.PersonInfoService;
import com.huacainfo.ace.society.vo.PersonInfoQVo;
import com.huacainfo.ace.society.vo.PersonInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("personInfoService")
/**
 * @author: Arvin
 * @version: 2018-09-11
 * @Description: TODO(个人信息)
 */
public class PersonInfoServiceImpl implements PersonInfoService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PersonInfoDao personInfoDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;
    @Autowired
    private PersonOrgRelationDao personOrgRelationDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(个人信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<PersonInfoVo>
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public PageResult<PersonInfoVo> findPersonInfoList(PersonInfoQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<PersonInfoVo> rst = new PageResult<>();
        List<PersonInfoVo> list = this.personInfoDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.personInfoDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertPersonInfo
     * @Description: TODO(添加个人信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse insertPersonInfo(PersonInfo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobilePhone())) {
            return new MessageResponse(1, "手机号不能为空！");
        }

        String personId = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(personId);

        int temp = this.personInfoDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "手机号码重复！");
        }

        //组织关系绑定
        if (StringUtil.isNotEmpty(o.getOrgId())) {
            bindOrgRelation(o, userProp);
        }

        o.setCreateDate(new Date());
        o.setStatus("2");//默认自动送审
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.personInfoDao.insert(o);
        this.dataBaseLogService.log("添加个人信息", "个人信息", "", o.getId(), o.getId(), userProp);

        //自动送审
        sendAudit(o);

        return new MessageResponse(0, "添加个人信息完成！");
    }

    public ResultResponse sendAudit(PersonInfo obj) {
        if (null == obj) {
            return new ResultResponse(ResultCode.FAIL, "数据记录丢失");
        }
        //提交审核
        auditRecordService.submit(GUIDUtil.getGUID(), BisType.SOCIETY_ORG_INFO, obj.getId(), obj.getId());

        return new ResultResponse(ResultCode.SUCCESS, "送审成功");
    }


    /**
     * 组织关系绑定
     *
     * @param o
     * @param userProp
     * @return
     */
    private MessageResponse bindOrgRelation(PersonInfo o, UserProp userProp) {
        PersonOrgRelation relation = new PersonOrgRelation();
        relation.setId(GUIDUtil.getGUID());
        relation.setPersonId(o.getId());
        relation.setOrgId(o.getOrgId());
        relation.setCreateDate(new Date());
        relation.setStatus("1");
        relation.setCreateUserName(userProp.getName());
        relation.setCreateUserId(userProp.getUserId());
        personOrgRelationDao.insert(relation);

        return new MessageResponse(ResultCode.SUCCESS, "绑定成功");
    }

    /**
     * @throws
     * @Title:updatePersonInfo
     * @Description: TODO(更新个人信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse updatePersonInfo(PersonInfo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-unionId不能为空！");
        }
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobilePhone())) {
            return new MessageResponse(1, "手机号不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.personInfoDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更个人信息", "个人信息", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更个人信息完成！");
    }

    /**
     * @throws
     * @Title:selectPersonInfoByPrimaryKey
     * @Description: TODO(获取个人信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PersonInfo>
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public SingleResult<PersonInfoVo> selectPersonInfoByPrimaryKey(String id) throws Exception {
        SingleResult<PersonInfoVo> rst = new SingleResult<>();
        rst.setValue(this.personInfoDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deletePersonInfoByPersonInfoId
     * @Description: TODO(删除个人信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse deletePersonInfoByPersonInfoId(String id, UserProp userProp) throws
            Exception {
        this.personInfoDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除个人信息", "个人信息",
                String.valueOf(id),
                String.valueOf(id), "个人信息", userProp);
        return new MessageResponse(0, "个人信息删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核个人信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception {
        PersonInfo personInfo = personInfoDao.selectByPrimaryKey(id);
        if (null == personInfo) {
            return new MessageResponse(ResultCode.FAIL, "个人信息丢失！");
        }

        //更改审核记录
        MessageResponse auditRs = auditRecordService.audit(BisType.PERSON_INFO, personInfo.getId(),
                personInfo.getId(), rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }

        personInfo.setStatus(rst);
        personInfo.setLastModifyDate(DateUtil.getNowDate());
        personInfo.setLastModifyUserId(userProp.getUserId());
        personInfo.setLastModifyUserName(userProp.getName());
        personInfoDao.updateByPrimaryKey(personInfo);

        //todo 发送微信公众号模板消息

        dataBaseLogService.log("审核个人信息", "个人信息", id, id, "个人信息", userProp);
        return new MessageResponse(0, "个人信息审核完成！");
    }

    /**
     * 个人信息注册
     *
     * @param personInfo 个人信息
     * @param userinfo   微信信息
     * @return ResultResponse
     */
    @Override
    public ResultResponse register(PersonInfo personInfo, Userinfo userinfo) {
        return null;
    }

}
