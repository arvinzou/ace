package com.huacainfo.ace.partyschool.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.service.TopicOptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.TopicDao;
import com.huacainfo.ace.partyschool.model.Topic;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.TopicService;
import com.huacainfo.ace.partyschool.vo.TopicVo;
import com.huacainfo.ace.partyschool.vo.TopicQVo;

@Service("topicService")
/**
 * @author: 王恩
 * @version: 2019-02-27
 * @Description: TODO(试题管理)
 */
public class TopicServiceImpl implements TopicService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private TopicOptService topicOptService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(试题管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TopicVo>
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public PageResult<TopicVo> findTopicList(TopicQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<TopicVo> rst = new PageResult<>();
        List<TopicVo> list = this.topicDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.topicDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTopic
     * @Description: TODO(添加试题管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public MessageResponse insertTopic(TopicQVo o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "题目内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "题目类型不能为空！");
        }
        int temp = this.topicDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "试题管理名称重复！");
        }
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.topicDao.insert(o);
        if(o.getOptions().size()>0) {
            this.topicOptService.insertTopicOptList(o.getOptions(), o.getId(), userProp);
        }
        this.dataBaseLogService.log("添加试题管理", "试题管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTopic
     * @Description: TODO(更新试题管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public MessageResponse updateTopic(Topic o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "题目内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "题目类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnalysis())) {
            return new MessageResponse(1, "测试分析不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.topicDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更试题管理", "试题管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTopicByPrimaryKey
     * @Description: TODO(获取试题管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Topic>
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public SingleResult<TopicVo> selectTopicByPrimaryKey(String id) throws Exception {
        SingleResult<TopicVo> rst = new SingleResult<>();
        rst.setValue(this.topicDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTopicByTopicId
     * @Description: TODO(删除试题管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public MessageResponse deleteTopicByTopicId(String id, UserProp userProp) throws
            Exception {
        this.topicDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除试题管理", "试题管理", id, id,
                "试题管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核试题管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核试题管理", "试题管理", id, id,
                "试题管理", userProp);
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
     * @author: 王恩
     * @version: 2019-02-27
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            TopicQVo o = new TopicQVo();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setCreateUserName(userProp.getName());
            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getContent())) {
                return new MessageResponse(1, "题目内容不能为空！");
            }
            if (CommonUtils.isBlank(o.getType())) {
                return new MessageResponse(1, "题目类型不能为空！");
            }
            if (CommonUtils.isBlank(o.getAnalysis())) {
                return new MessageResponse(1, "测试分析不能为空！");
            }

            int t = this.topicDao.isExit(o);
            if (t > 0) {
                this.topicDao.updateByPrimaryKey(o);

            } else {
                this.topicDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("试题管理导入", "试题管理", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.topicDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.topicDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除试题管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public MessageResponse deleteTopicByTopicIds(String[] id, UserProp userProp) throws Exception {

        this.topicDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除试题管理", "试题管理", id[0], id[0], "试题管理", userProp);
        return new MessageResponse(0, "删除成功！");

    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.topicDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "试题管理", id, id, "试题管理", userProp);
        return new MessageResponse(0, "成功！");
    }

}
