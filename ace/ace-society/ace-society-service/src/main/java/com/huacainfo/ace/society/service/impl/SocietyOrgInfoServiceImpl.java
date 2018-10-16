package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.dao.SocietyOrgInfoDao;
import com.huacainfo.ace.society.model.SocietyOrgInfo;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.SocietyOrgInfoService;
import com.huacainfo.ace.society.vo.SocietyOrgInfoQVo;
import com.huacainfo.ace.society.vo.SocietyOrgInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("societyOrgInfoService")
/**
 * @author: Arvin
 * @version: 2018-09-12
 * @Description: TODO(社会组织信息)
 */
public class SocietyOrgInfoServiceImpl implements SocietyOrgInfoService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SocietyOrgInfoDao societyOrgInfoDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(社会组织信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SocietyOrgInfoVo>
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public PageResult<SocietyOrgInfoVo> findSocietyOrgInfoList(SocietyOrgInfoQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<SocietyOrgInfoVo> rst = new PageResult<>();
        List<SocietyOrgInfoVo> list = societyOrgInfoDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = societyOrgInfoDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSocietyOrgInfo
     * @Description: TODO(添加社会组织信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public MessageResponse insertSocietyOrgInfo(SocietyOrgInfo o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getOrgName())) {
            return new MessageResponse(1, "组织名称不能为空！");
        }

        o.setId(StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId());

        int temp = this.societyOrgInfoDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "社会组织信息名称重复！");
        }

        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        societyOrgInfoDao.insert(o);
        dataBaseLogService.log("添加社会组织信息", "社会组织信息", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加社会组织信息完成！");
    }

    /**
     * @throws
     * @Title:updateSocietyOrgInfo
     * @Description: TODO(更新社会组织信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public MessageResponse updateSocietyOrgInfo(SocietyOrgInfo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrgName())) {
            return new MessageResponse(1, "组织名称不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.societyOrgInfoDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更社会组织信息", "社会组织信息", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更社会组织信息完成！");
    }

    /**
     * @throws
     * @Title:selectSocietyOrgInfoByPrimaryKey
     * @Description: TODO(获取社会组织信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SocietyOrgInfo>
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public SingleResult<SocietyOrgInfoVo> selectSocietyOrgInfoByPrimaryKey(String id) throws Exception {
        SingleResult<SocietyOrgInfoVo> rst = new SingleResult<>();
        rst.setValue(this.societyOrgInfoDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSocietyOrgInfoBySocietyOrgInfoId
     * @Description: TODO(删除社会组织信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public MessageResponse deleteSocietyOrgInfoBySocietyOrgInfoId(String id, UserProp userProp) throws
            Exception {
        this.societyOrgInfoDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除社会组织信息", "社会组织信息",
                String.valueOf(id),
                String.valueOf(id), "社会组织信息", userProp);
        return new MessageResponse(0, "社会组织信息删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核社会组织信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {
        SocietyOrgInfo obj = societyOrgInfoDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "组织信息丢失");

        }
        //更改审核记录
        MessageResponse auditRs = auditRecordService.audit(BisType.SOCIETY_ORG_INFO, obj.getId(),
                obj.getId(), rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }

        obj.setStatus(rst);
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        societyOrgInfoDao.updateByPrimaryKeySelective(obj);

        //todo 发送微信公众号模板消息

        dataBaseLogService.log("审核社会组织信息", "社会组织信息", id, id, "社会组织信息", userProp);
        return new MessageResponse(0, "社会组织信息审核完成！");
    }

    @Override
    public List<SocietyOrgInfoVo> findList(SocietyOrgInfoQVo condition, int start, int limit, String orderBy) {
        List<SocietyOrgInfoVo> list = societyOrgInfoDao.findList(condition, start, limit, orderBy);

        return list;
    }

}