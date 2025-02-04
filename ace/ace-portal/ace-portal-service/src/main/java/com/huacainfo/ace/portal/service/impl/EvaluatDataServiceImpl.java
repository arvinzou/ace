package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.portal.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.EvaluatDataDao;
import com.huacainfo.ace.portal.model.EvaluatData;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.EvaluatDataService;
import com.huacainfo.ace.portal.vo.EvaluatDataVo;
import com.huacainfo.ace.portal.vo.EvaluatDataQVo;

@Service("evaluatDataService")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(测评结果)
 */
public class EvaluatDataServiceImpl implements EvaluatDataService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluatDataDao evaluatDataDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private UserinfoService userinfoService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测评结果分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatDataVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public PageResult<EvaluatDataVo> findEvaluatDataList(EvaluatDataQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult<EvaluatDataVo> rst = new PageResult<EvaluatDataVo>();

        List<EvaluatDataVo> list = this.evaluatDataDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluatDataDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse findEvaluatDataLists(EvaluatDataQVo condition, int start, int limit, String orderBy) throws Exception {
        if (CommonUtils.isBlank(start)) {
            start = 0;
        }
        if (CommonUtils.isBlank(limit)) {
            start = 100;
        }
        List<EvaluatDataVo> list = this.evaluatDataDao.findDataList(condition, start, limit, orderBy);
        for (EvaluatDataVo item : list) {
            Userinfo userinfo = userinfoService.selectUserinfoByKey(item.getCreateUserId());
            item.setHeadimgurl(userinfo.getHeadimgurl());
            item.setNickname(userinfo.getNickname());
        }
        return new ResultResponse(ResultCode.SUCCESS, "成绩列表", list);
    }

    /**
     * @throws
     * @Title:insertEvaluatData
     * @Description: TODO(添加测评结果)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse insertEvaluatData(EvaluatData o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "成绩不能为空！");
        }
        if (CommonUtils.isBlank(o.getHeadImgUrl())) {
            return new MessageResponse(1, "头像不能为空！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserId(userProp.getOpenId());
        this.evaluatDataDao.insertSelective(o);
        return new MessageResponse(0, "添加测评结果完成！");
    }

    @Override
    public MessageResponse insertData(EvaluatData o)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "成绩不能为空！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.evaluatDataDao.insertSelective(o);
        return new MessageResponse(0, "添加测评结果完成！");
    }


    @Override
    public int getRanking(EvaluatData o)
            throws Exception {
        return this.evaluatDataDao.getRanking(o);
    }

    /**
     * @throws
     * @Title:updateEvaluatData
     * @Description: TODO(更新测评结果)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse updateEvaluatData(EvaluatData o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "成绩不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        this.evaluatDataDao.updateByPrimaryKeySelective(o);
        return new MessageResponse(0, "变更测评结果完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluatDataByPrimaryKey
     * @Description: TODO(获取测评结果)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatData>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public SingleResult<EvaluatDataVo> selectEvaluatDataByPrimaryKey(String id) throws Exception {
        SingleResult<EvaluatDataVo> rst = new SingleResult<EvaluatDataVo>();
        rst.setValue(this.evaluatDataDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEvaluatDataByEvaluatDataId
     * @Description: TODO(删除测评结果)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse deleteEvaluatDataByEvaluatDataId(String id,
                                                            UserProp userProp) throws Exception {
        this.evaluatDataDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除测评结果", "测评结果", String.valueOf(id),
                String.valueOf(id), "测评结果", userProp);
        return new MessageResponse(0, "测评结果删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取测评结果列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatDataDao.getList(params));
        return rst;
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取测评结果)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getById(String id) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatDataDao.getById(id));
        return rst;
    }
}
