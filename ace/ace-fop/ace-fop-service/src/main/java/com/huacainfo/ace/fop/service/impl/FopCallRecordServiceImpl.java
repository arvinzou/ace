package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.common.constant.FopConstant;
import com.huacainfo.ace.fop.common.constant.KafkaConstant;
import com.huacainfo.ace.fop.dao.FopCallRecordDao;
import com.huacainfo.ace.fop.dao.FopCallRecordDetailDao;
import com.huacainfo.ace.fop.model.FopCallRecord;
import com.huacainfo.ace.fop.model.FopCallRecordDetail;
import com.huacainfo.ace.fop.service.FopAssociationService;
import com.huacainfo.ace.fop.service.FopCallRecordService;
import com.huacainfo.ace.fop.service.FopCompanyService;
import com.huacainfo.ace.fop.vo.FopAssociationVo;
import com.huacainfo.ace.fop.vo.FopCallRecordQVo;
import com.huacainfo.ace.fop.vo.FopCallRecordVo;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import com.huacainfo.ace.portal.service.BackendService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.MessageTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fopCallRecordService")
/**
 * @author: Arvin
 * @version: 2018-05-17
 * @Description: TODO(催缴记录)
 */
public class FopCallRecordServiceImpl implements FopCallRecordService, BackendService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FopCallRecordDao fopCallRecordDao;
    @Autowired
    private FopCallRecordDetailDao fopCallRecordDetailDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private FopCompanyService companyService;
    @Autowired
    private FopAssociationService associationService;
    @Autowired
    private MessageTemplateService messageTemplateService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(催缴记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopCallRecordVo>
     * @author: Arvin
     * @version: 2018-05-17
     */
    @Override
    public PageResult<FopCallRecordVo> findFopCallRecordList(FopCallRecordQVo condition, int start,
                                                             int limit, String orderBy) throws Exception {
        PageResult<FopCallRecordVo> rst = new PageResult<>();
        List<FopCallRecordVo> list = fopCallRecordDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = fopCallRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopCallRecord
     * @Description: TODO(添加催缴记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse insertFopCallRecord(FopCallRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getPayTitle())) {
            return new MessageResponse(1, "催缴标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayCategory())) {
            return new MessageResponse(1, "催缴项目不能为空！");
        }
        if (CommonUtils.isBlank(o.getClosingDate())) {
            return new MessageResponse(1, "截止日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getMsgTmplCode())) {
            return new MessageResponse(1, "消息模板编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayAmount())) {
            return new MessageResponse(1, "缴费金额不能为空！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        fopCallRecordDao.insertSelective(o);
        dataBaseLogService.log("添加催缴记录", "催缴记录", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加催缴记录完成！");
    }

    /**
     * @throws
     * @Title:updateFopCallRecord
     * @Description: TODO(更新催缴记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse updateFopCallRecord(FopCallRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayTitle())) {
            return new MessageResponse(1, "催缴标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayCategory())) {
            return new MessageResponse(1, "催缴项目不能为空！");
        }
        if (CommonUtils.isBlank(o.getClosingDate())) {
            return new MessageResponse(1, "截止日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getMsgTmplCode())) {
            return new MessageResponse(1, "消息模板编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayAmount())) {
            return new MessageResponse(1, "缴费金额不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        fopCallRecordDao.updateByPrimaryKeySelective(o);
        dataBaseLogService.log("变更催缴记录", "催缴记录", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更催缴记录完成！");
    }

    /**
     * @throws
     * @Title:selectFopCallRecordByPrimaryKey
     * @Description: TODO(获取催缴记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCallRecord>
     * @author: Arvin
     * @version: 2018-05-17
     */
    @Override
    public SingleResult<FopCallRecordVo> selectFopCallRecordByPrimaryKey(String id) throws Exception {
        SingleResult<FopCallRecordVo> rst = new SingleResult<>();
        rst.setValue(fopCallRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopCallRecordByFopCallRecordId
     * @Description: TODO(删除催缴记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse deleteFopCallRecordByFopCallRecordId(String id, UserProp
            userProp) throws Exception {
        fopCallRecordDao.deleteByPrimaryKey(id);
        dataBaseLogService.log("删除催缴记录", "催缴记录",
                String.valueOf(id),
                String.valueOf(id), "催缴记录", userProp);
        return new MessageResponse(0, "催缴记录删除完成！");
    }

    /**
     * 功能描述: 推送催缴通知
     *
     * @param recordId
     * @param userProp
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/18 9:39
     */
    @Override
    public MessageResponse sendCallNotice(String recordId, UserProp userProp) throws Exception {
        FopCallRecord callRecord = fopCallRecordDao.selectByPrimaryKey(recordId);
        if (null == callRecord) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失");
        }

        //构造发送对象
        List<Map<String, Object>> dataList = fopCallRecordDao.selectAllSendObject();
        FopCallRecordDetail detail;
        for (Map<String, Object> map : dataList) {
            detail = new FopCallRecordDetail();
            detail.setId(GUIDUtil.getGUID());
            detail.setRecordId(recordId);
            detail.setToUserId((String) map.get("toUserId"));
            detail.setToUserType((String) map.get("toUserType"));
            detail.setStatus("1");
            detail.setCreateUserId(userProp.getUserId());
            detail.setCreateUserName(userProp.getName());
            detail.setCreateDate(DateUtil.getNowDate());
            detail.setLastModifyDate(DateUtil.getNowDate());

            fopCallRecordDetailDao.insertSelective(detail);
        }

        //更新状态
        callRecord.setStatus("2");
        updateFopCallRecord(callRecord, userProp);

        //kafka完成实际催缴业务
        Map<String, Object> data = new HashMap<>();
        data.put("service", "fopCallRecordService");
        data.put("recordId", recordId);
        data.put("sysId", userProp.getActiveSyId());

        kafkaProducerService.sendMsg(KafkaConstant.TOPIC_NAME, data);

        return new MessageResponse(ResultCode.SUCCESS, "发送成功");
    }

    /**
     * 功能描述: 添加催缴对象
     *
     * @param recordId
     * @param detailList
     * @param userProp
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/18 9:39
     */
    @Override
    public MessageResponse insertCallRecordDetail(String recordId, List<Map<String, String>> detailList, UserProp userProp) {
        FopCallRecord callRecord = fopCallRecordDao.selectByPrimaryKey(recordId);
        if (null == callRecord) {
            return new MessageResponse(ResultCode.FAIL, "催缴记录不存在");
        }

        int delCount = fopCallRecordDetailDao.deleteByRecordId(recordId);

        FopCallRecordDetail detail;
        for (Map<String, String> map : detailList) {
            detail = new FopCallRecordDetail();
            detail.setId(GUIDUtil.getGUID());
            detail.setRecordId(recordId);
            detail.setToUserId(map.get("toUserId"));
            detail.setToUserType(map.get("toUserType"));
            detail.setStatus("1");
            detail.setCreateUserId(userProp.getUserId());
            detail.setCreateUserName(userProp.getName());
            detail.setCreateDate(DateUtil.getNowDate());
            detail.setLastModifyDate(DateUtil.getNowDate());

            fopCallRecordDetailDao.insertSelective(detail);
        }


        return new MessageResponse(ResultCode.SUCCESS, "添加成功");
    }

    /**
     * @param recordId fop_call_record.id
     * @return
     */
    @Override
    public List<FopCallRecordDetail> findCallRecordDetail(String recordId) {
        return fopCallRecordDetailDao.findByRecordId(recordId);
    }

    @Override
    public MessageResponse test2(Map<String, Object> data) throws Exception {
        return service(data);
    }

    /**
     * 实际催缴业务通知
     *
     * @param data
     * @return
     * @throws Exception
     */
    @Override
    public MessageResponse service(Map<String, Object> data) throws Exception {

        String recordId = (String) data.get("recordId");
        FopCallRecord callRecord = fopCallRecordDao.selectByPrimaryKey(recordId);
        if (callRecord == null) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失");
        }

        String sysId = (String) data.get("sysId");
        String tmplCode = callRecord.getMsgTmplCode();
        List<FopCallRecordDetail> sendList = findCallRecordDetail(recordId);
        FopCompanyVo company;
        FopAssociationVo association;
        Map<String, Object> params;
        for (FopCallRecordDetail item : sendList) {
            params = new HashMap<>();
            //企业
            if (FopConstant.COMPANY.equals(item.getToUserType())) {
                company = companyService.selectFopCompanyByPrimaryKey(item.getToUserId()).getValue();
                params.put("nickName", company.getFullName());
                sendNotice("", company.getLpMobile(), tmplCode, sysId, params);
                continue;
            }
            //协会
            if (FopConstant.ASSOCIATION.equals(item.getToUserType())) {
                association = associationService.selectFopAssociationByPrimaryKey(item.getToUserId()).getValue();
                params.put("nickName", association.getFullName());
                sendNotice("", association.getPhoneNumber(), tmplCode, sysId, params);
                continue;
            }
        }

        return new MessageResponse(ResultCode.SUCCESS, "发送成功");
    }

    /**
     * 调用模板，发送消息通知
     *
     * @param openid   微信openid
     * @param mobile   手机号码
     * @param tmplCode 模板消息编码
     * @param sysId    系统识别id
     * @param params   参数列表
     */
    private void sendNotice(String openid, String mobile, String tmplCode, String sysId, Map<String, Object> params) throws Exception {
        if (CommonUtils.isValidMobile(mobile)) {
            params.put("mobile", mobile);
        }
        if (!CommonUtils.isEmpty(openid)) {
            params.put("openid", openid);
        }

        params.put("service", "messageTemplateService");
        params.put("tmplCode", tmplCode);
        params.put("sysId", sysId);
        //service send
//        ResultResponse rs = messageTemplateService.send(sysId, tmplCode, params);
//        logger.debug("***********sendNotice.ResultResponse: \n{}", JsonUtil.toJson(rs));
        //kafka send
        kafkaProducerService.sendMsg(KafkaConstant.TOPIC_NAME, params);
    }
}
