package com.huacainfo.ace.partyschool.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.TopicOptDao;
import com.huacainfo.ace.partyschool.model.TopicOpt;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.TopicOptService;
import com.huacainfo.ace.partyschool.vo.TopicOptVo;
import com.huacainfo.ace.partyschool.vo.TopicOptQVo;

@Service("topicOptService")
/**
 * @author: 王恩
 * @version: 2019-02-28
 * @Description: TODO(试题选项管理)
 */
public class TopicOptServiceImpl implements TopicOptService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicOptDao topicOptDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(试题选项管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TopicOptVo>
     * @author: 王恩
     * @version: 2019-02-28
     */
    @Override
    public PageResult<TopicOptVo> findTopicOptList(TopicOptQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<TopicOptVo> rst = new PageResult<>();
        List<TopicOptVo> list = this.topicOptDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.topicOptDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }


    @Override
    public MessageResponse insertTopicOptList(List<TopicOpt> options, String topicId, UserProp userProp) throws Exception {
        for (TopicOpt item : options) {
            item.setTopicId(topicId);
            MessageResponse mr = insertTopicOpt(item, userProp);
            if (mr.getStatus() == ResultCode.FAIL) {
                return mr;
            }
        }
        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:insertTopicOpt
     * @Description: TODO(添加试题选项管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse insertTopicOpt(TopicOpt o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTopicId())) {
            return new MessageResponse(1, "题目主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "选项内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswer())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        o.setCreateDate(new Date());
        this.topicOptDao.insert(o);
        this.dataBaseLogService.log("添加试题选项管理", "试题选项管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTopicOpt
     * @Description: TODO(更新试题选项管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse updateTopicOpt(TopicOpt o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTopicId())) {
            return new MessageResponse(1, "题目主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getViewTag())) {
            return new MessageResponse(1, "显示标号不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "选项内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswer())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getIndex())) {
            return new MessageResponse(1, "排列顺序不能为空！");
        }

        this.topicOptDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更试题选项管理", "试题选项管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTopicOptByPrimaryKey
     * @Description: TODO(获取试题选项管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopicOpt>
     * @author: 王恩
     * @version: 2019-02-28
     */
    @Override
    public SingleResult<TopicOptVo> selectTopicOptByPrimaryKey(String id) throws Exception {
        SingleResult<TopicOptVo> rst = new SingleResult<>();
        rst.setValue(this.topicOptDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTopicOptByTopicOptId
     * @Description: TODO(删除试题选项管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse deleteTopicOptByTopicOptId(String id, UserProp userProp) throws
            Exception {
        this.topicOptDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除试题选项管理", "试题选项管理", id, id,
                "试题选项管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核试题选项管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核试题选项管理", "试题选项管理", id, id,
                "试题选项管理", userProp);
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
     * @version: 2019-02-28
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            TopicOpt o = new TopicOpt();
            o.setCreateDate(new Date());


            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getTopicId())) {
                return new MessageResponse(1, "题目主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getViewTag())) {
                return new MessageResponse(1, "显示标号不能为空！");
            }
            if (CommonUtils.isBlank(o.getContent())) {
                return new MessageResponse(1, "选项内容不能为空！");
            }
            if (CommonUtils.isBlank(o.getAnswer())) {
                return new MessageResponse(1, "分值不能为空！");
            }
            if (CommonUtils.isBlank(o.getIndex())) {
                return new MessageResponse(1, "排列顺序不能为空！");
            }

            int t = this.topicOptDao.isExit(o);
            if (t > 0) {
                this.topicOptDao.updateByPrimaryKey(o);

            } else {
                this.topicOptDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("试题选项管理导入", "试题选项管理", "", "rs.xls", "rs.xls", userProp);
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
     * @version: 2019-02-28
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.topicOptDao.getList(p));

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
     * @version: 2019-02-28
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.topicOptDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除试题选项管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse deleteTopicOptByTopicOptIds(String[] id, UserProp userProp) throws Exception {

        this.topicOptDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除试题选项管理", "试题选项管理", id[0], id[0], "试题选项管理", userProp);
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
     * @version: 2019-02-28
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.topicOptDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "试题选项管理", id, id, "试题选项管理", userProp);
        return new MessageResponse(0, "成功！");
    }

}
