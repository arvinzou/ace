package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.dao.AppealCaseDao;
import com.huacainfo.ace.portal.dao.AppealCaseFileDao;
import com.huacainfo.ace.portal.dao.AppealDao;
import com.huacainfo.ace.portal.model.Appeal;
import com.huacainfo.ace.portal.model.AppealCase;
import com.huacainfo.ace.portal.model.AppealCaseFile;
import com.huacainfo.ace.portal.service.AppealCaseService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.vo.AppealCaseQVo;
import com.huacainfo.ace.portal.vo.AppealCaseVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("appealCaseService")
/**
 * @author: 陈晓克
 * @version: 2018-05-14
 * @Description: TODO(诉求)
 */
public class AppealCaseServiceImpl implements AppealCaseService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AppealCaseDao appealCaseDao;
    @Autowired
    private AppealCaseFileDao appealCaseFileDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private SqlSessionTemplate sqlSession;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private AppealDao appealDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(诉求分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<AppealCaseVo>
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public PageResult<AppealCaseVo> findAppealCaseList(AppealCaseQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {

        PageResult<AppealCaseVo> rst = new PageResult<>();
        List<AppealCaseVo> list = appealCaseDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.appealCaseDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertAppealCase
     * @Description: TODO(添加诉求)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public MessageResponse insertAppealCase(AppealCase o, List<AppealCaseFile> list)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAppealId())) {
            return new MessageResponse(1, "所属诉求不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyName())) {
            return new MessageResponse(1, "企业/单位不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubmitName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getTel())) {
            return new MessageResponse(1, "联系电话不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getMediType())) {
            return new MessageResponse(1, "媒体类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubmitOpenId())) {
            return new MessageResponse(1, "提交人openId不能为空！");
        }
        int temp = this.appealCaseDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "诉求名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setSubmitTime(new Date());
        this.appealCaseDao.insert(o);
        for (AppealCaseFile e : list) {
            e.setId(GUIDUtil.getGUID());
            e.setAppealCaseId(o.getId());
            e.setCreateDate(new Date());
            e.setStatus("1");
            e.setType("1");
            this.appealCaseFileDao.insert(e);
        }

        //企业发布诉求，通知管理员
        sendNoticeToAdministrator(o);

        return new MessageResponse(0, "提交诉求完成！");
    }

    /**
     * 企业发布诉求，通知管理员
     *
     * @param obj
     */
    private void sendNoticeToAdministrator(AppealCase obj) {
        try {
            Appeal appeal = appealDao.selectByPrimaryKey(obj.getAppealId());
            if (null == appeal) {
                logger.error("企业发布诉求[{}]，通知管理员异常：{}", obj.getId(), "诉求主题不存在");
                return;
            }
            Map<String, Object> params = new HashMap<>();
            //kafka所需内容
            params.put("service", "messageTemplateService");
            params.put("sysId", "fop");
            params.put("tmplCode", appeal.getTplCode());//处理通知模板
            //data
            params.put("openid", appeal.getOpenId());
            params.put("url", "www.baidu.com");
//        params.put("first", "哈哈哈哈哈");
            //data
            params.put("appealContent", obj.getTitle());
            params.put("dealResult", "待处理");
            params.put("dealDate", DateUtil.getNow(DateUtil.DEFAULT_DATE_REGEX));
            params.put("consultingTel", "400-12345678");
            kafkaProducerService.sendMsg("topic.sys.msg", params);
        } catch (Exception e) {
            logger.error("企业发布诉求[{}]，通知管理员异常：{}", obj.getId(), e);
        }
    }

    /**
     * @throws
     * @Title:updateAppealCase
     * @Description: TODO(诉求答复)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public MessageResponse updateAppealCase(AppealCase o, List<AppealCaseFile> list, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswerOpenId())) {
            return new MessageResponse(1, "答复人不能为空！");
        }

        if (CommonUtils.isBlank(o.getAnswerContent())) {
            return new MessageResponse(1, "答复内容不能为空！");
        }
        o.setStatus("3");
        o.setAnswerTime(new Date());
        this.appealCaseDao.updateByPrimaryKey(o);
        for (AppealCaseFile e : list) {
            e.setAppealCaseId(o.getId());
            e.setId(GUIDUtil.getGUID());
            e.setCreateDate(new Date());
            e.setStatus("1");
            e.setType("2");
            this.appealCaseFileDao.insert(e);
        }
        this.dataBaseLogService.log("诉求答复", "诉求", o.getId(),
                o.getId(), "诉求", userProp);

        //管理员答复诉求，通知诉求人
        sendNoticeToAppealPerson(o);

        return new MessageResponse(0, "完成！");
    }

    /**
     * 管理员答复诉求，通知诉求人
     *
     * @param obj
     */
    private void sendNoticeToAppealPerson(AppealCase obj) {
        try {
            Appeal appeal = appealDao.selectByPrimaryKey(obj.getAppealId());
            if (null == appeal) {
                logger.error("企业发布诉求[{}]，通知管理员异常：{}", obj.getId(), "诉求主题不存在");
                return;
            }
            Map<String, Object> params = new HashMap<>();
            //kafka所需内容
            params.put("service", "messageTemplateService");
            params.put("sysId", "fop");
            params.put("tmplCode", appeal.getAnswerTplCode());//答复通知模板
            //data
            params.put("openid", appeal.getOpenId());
            params.put("url", "www.baidu.com");
//        params.put("first", "哈哈哈哈哈");
            //data
            params.put("appealContent", obj.getTitle());
            params.put("dealResult", "已完成");
            params.put("dealDate", DateUtil.getNow(DateUtil.DEFAULT_DATE_REGEX));
            params.put("consultingTel", "400-12345678");
            kafkaProducerService.sendMsg("topic.sys.msg", params);
        } catch (Exception e) {
            logger.error("管理员答复诉求[{}]，通知诉求人异常：{}", obj.getId(), e);
        }
    }

    /**
     * @throws
     * @Title:selectAppealCaseByPrimaryKey
     * @Description: TODO(获取诉求)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AppealCase>
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public SingleResult<AppealCaseVo> selectAppealCaseByPrimaryKey(String id) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        AppealCaseDao dao = session.getMapper(AppealCaseDao.class);
        SingleResult<AppealCaseVo> rst = new SingleResult<AppealCaseVo>();
        try {
            rst.setValue(dao.selectByPrimaryKey(id));
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return rst;
    }

    /**
     * @throws
     * @Title:deleteAppealCaseByAppealCaseId
     * @Description: TODO(删除诉求)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public MessageResponse deleteAppealCaseByAppealCaseId(String id, UserProp userProp) throws Exception {
        this.appealCaseDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除诉求", "诉求", String.valueOf(id),
                String.valueOf(id), "诉求", userProp);
        return new MessageResponse(0, "诉求删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-05-15
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.appealCaseDao.getList(params));
        return rst;
    }

    /**
     * @throws
     * @Title:updateAccept
     * @Description: TODO(接受处理诉求)
     * @param: @param id
     * @param: @param answerDept
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-16
     */
    @Override
    public MessageResponse updateAccept(String id, String answerDept, String operator, UserProp userProp) throws Exception {
        this.appealCaseDao.updateAccept(id, answerDept, operator);
        this.dataBaseLogService.log("接受处理诉求", "诉求", String.valueOf(id),
                String.valueOf(id), "诉求", userProp);
        return new MessageResponse(0, "完成！");
    }


    /**
     * @throws
     * @Title:updateDetailsOfProgress
     * @Description: TODO(诉求进展情况更新)
     * @param: @param id
     * @param: @param detailsOfProgress
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-16
     */
    @Override
    public MessageResponse updateDetailsOfProgress(String id, String detailsOfProgress, UserProp userProp) throws Exception {
        this.appealCaseDao.updateDetailsOfProgress(id, detailsOfProgress);
        this.dataBaseLogService.log("诉求进展情况更新", "诉求", String.valueOf(id),
                String.valueOf(id), "诉求", userProp);
        return new MessageResponse(0, "完成！");
    }

    /**
     * 获取附件列表
     *
     * @param appealCaseId
     * @param type         类型（1诉求2答复）
     * @param curUserProp  @return
     */
    @Override
    public ResultResponse findFileList(String appealCaseId, String type, UserProp curUserProp) {
        type = CommonUtils.isEmpty(type) ? "2" : type;

        List<AppealCaseFile> list = appealCaseFileDao.findByAppealCaseId(appealCaseId, new String[]{type});
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }

    /**
     * 删除附件
     *
     * @param id
     * @param userProp
     * @return
     */
    @Override
    public ResultResponse deleteAppealCaseFile(String id, UserProp userProp) {
        appealCaseFileDao.deleteByPrimaryKey(id);
        return new ResultResponse(ResultCode.SUCCESS, "删除成功");
    }

    @Override
    public AppealCaseFile insertAppealCaseFile(String appealCaseId, String name, String mediType, String url) {
        AppealCaseFile obj = new AppealCaseFile();
        obj.setId(GUIDUtil.getGUID());
        obj.setAppealCaseId(appealCaseId);
        obj.setName(name);
        obj.setType("2");//类型（1诉求2答复）
        obj.setMediType(mediType);
        obj.setMediUrl(url);
        obj.setStatus("1");
        obj.setCreateDate(DateUtil.getNowDate());
        appealCaseFileDao.insert(obj);

        return obj;
    }

    /**
     * pc端，诉求答复
     *
     * @param obj
     * @param userProp
     * @return
     * @throws Exception
     */
    @Override
    public MessageResponse updateAppealCase(AppealCase obj, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(obj.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(obj.getAnswerContent())) {
            return new MessageResponse(1, "答复内容不能为空！");
        }

        obj.setAnswerTime(new Date());
        this.appealCaseDao.updateByPrimaryKey(obj);
        this.dataBaseLogService.log("诉求答复", "诉求", obj.getId(), obj.getId(), "诉求", userProp);


        //管理员答复诉求，通知诉求人
        sendNoticeToAppealPerson(obj);
        return new MessageResponse(0, "完成！");
    }

}
