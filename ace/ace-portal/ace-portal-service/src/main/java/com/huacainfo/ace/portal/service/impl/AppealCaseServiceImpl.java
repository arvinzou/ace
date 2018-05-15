package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.model.AppealCaseFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.AppealCaseDao;
import com.huacainfo.ace.portal.dao.AppealCaseFileDao;
import com.huacainfo.ace.portal.model.AppealCase;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.AppealCaseService;
import com.huacainfo.ace.portal.vo.AppealCaseVo;
import com.huacainfo.ace.portal.vo.AppealCaseQVo;

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
        PageResult<AppealCaseVo> rst = new PageResult<AppealCaseVo>();
        List<AppealCaseVo> list = this.appealCaseDao.findList(condition,
                start, start + limit, orderBy);
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
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getMediType())) {
            return new MessageResponse(1, "媒体类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubmitTime())) {
            return new MessageResponse(1, "提交时间不能为空！");
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
        this.appealCaseDao.insert(o);
        for(AppealCaseFile e:list){
            e.setId(GUIDUtil.getGUID());
            e.setAppealCaseId(o.getId());
            e.setCreateDate(new Date());
            this.appealCaseFileDao.insert(e);
        }
        return new MessageResponse(0, "添加诉求完成！");
    }

    /**
     * @throws
     * @Title:updateAppealCase
     * @Description: TODO(更新诉求)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public MessageResponse updateAppealCase(AppealCase o, List<AppealCaseFile> list)
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
        o.setStatus("2");
        o.setAnswerTime(new Date());
        this.appealCaseDao.updateByPrimaryKey(o);
        for(AppealCaseFile e:list){
            e.setAppealCaseId(o.getId());
            e.setId(GUIDUtil.getGUID());
            e.setCreateDate(new Date());
            this.appealCaseFileDao.insert(e);
        }
        return new MessageResponse(0, "变更诉求完成！");
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
        SingleResult<AppealCaseVo> rst = new SingleResult<AppealCaseVo>();
        rst.setValue(this.appealCaseDao.selectByPrimaryKey(id));
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
    public MessageResponse deleteAppealCaseByAppealCaseId(String id,
                                                          UserProp userProp) throws Exception {
        this.appealCaseDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除诉求", "诉求", String.valueOf(id),
                String.valueOf(id), "诉求", userProp);
        return new MessageResponse(0, "诉求删除完成！");
    }

    /**
     *
     * @Title:getList
     * @Description:  TODO(获取列表)
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @throws
     * @author: 陈晓克
     * @version: 2018-05-15
     */
    @Override
    public Map<String,Object> getList(Map<String,Object> params) throws Exception{
        Map<String,Object> rst=new HashMap<>();
        rst.put("status",0);
        rst.put("data",this.appealCaseDao.getList(params));
        return rst;
    }
}
