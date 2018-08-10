package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.pushmsg.CommonUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.dao.EvaluatCaseSubDao;
import com.huacainfo.ace.portal.model.EvaluatCaseSub;
import com.huacainfo.ace.portal.service.EvaluatCaseSubService;
import com.huacainfo.ace.portal.vo.EvaluatCaseSubVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.EvaluatCaseDao;
import com.huacainfo.ace.portal.model.EvaluatCase;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.EvaluatCaseService;
import com.huacainfo.ace.portal.vo.EvaluatCaseVo;
import com.huacainfo.ace.portal.vo.EvaluatCaseQVo;

@Service("evaluatCaseService")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(题目)
 */
public class EvaluatCaseServiceImpl implements EvaluatCaseService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluatCaseDao evaluatCaseDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private EvaluatCaseSubDao evaluatCaseSubDao;

    @Autowired
    private EvaluatCaseSubService evaluatCaseSubService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(题目分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatCaseVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public PageResult<EvaluatCaseVo> findEvaluatCaseList(EvaluatCaseQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<EvaluatCaseVo> rst = new PageResult<EvaluatCaseVo>();
        if (CommonUtils.isBlank(condition.getEvaluatTplId())) {
            return rst;
        }
        List<EvaluatCaseVo> list = this.evaluatCaseDao.findList(condition, start, limit, orderBy);
        for (EvaluatCaseVo item : list) {
            List<EvaluatCaseSubVo> listsub = this.evaluatCaseSubDao.findLists(item.getId());
            EvaluatCaseSubVo evaluatCaseSubVo = null;
            switch (listsub.size()) {
                case 4:
                    evaluatCaseSubVo = listsub.get(3);
                    item.setaCntImg4(evaluatCaseSubVo.getCntImg());
                    item.setIsAnswer4(evaluatCaseSubVo.getIsAnswer());
                    item.setaName4(evaluatCaseSubVo.getName());

                case 3:
                    evaluatCaseSubVo = listsub.get(2);
                    item.setaCntImg3(evaluatCaseSubVo.getCntImg());
                    item.setIsAnswer3(evaluatCaseSubVo.getIsAnswer());
                    item.setaName3(evaluatCaseSubVo.getName());

                case 2:
                    evaluatCaseSubVo = listsub.get(1);
                    item.setaCntImg2(evaluatCaseSubVo.getCntImg());
                    item.setIsAnswer2(evaluatCaseSubVo.getIsAnswer());
                    item.setaName2(evaluatCaseSubVo.getName());
                    evaluatCaseSubVo = listsub.get(0);
                    item.setaCntImg1(evaluatCaseSubVo.getCntImg());
                    item.setIsAnswer1(evaluatCaseSubVo.getIsAnswer());
                    item.setaName1(evaluatCaseSubVo.getName());
            }
        }
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluatCaseDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(题目分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatCaseVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public ResultResponse findEvaluatCaseListVo(EvaluatCaseQVo condition, int start, int limit, String orderBy) throws Exception {
        List<EvaluatCaseVo> list = this.evaluatCaseDao.findList(condition, start, limit, orderBy);
        for (EvaluatCaseVo item : list) {
            List<EvaluatCaseSubVo> listsub = this.evaluatCaseSubDao.findLists(item.getId());
            item.setCaseSubData(listsub);
        }
        return new ResultResponse(ResultCode.SUCCESS, "题目内容列表", list);
    }


    @Override
    public PageResult<EvaluatCaseVo> findEvaluatCaseListSecond(EvaluatCaseQVo condition, int page, int limit, String orderBy) throws Exception {
        PageResult<EvaluatCaseVo> rst = new PageResult<EvaluatCaseVo>();
        List<EvaluatCaseVo> list = this.evaluatCaseDao.findList(condition, (page - 1) * limit, limit, orderBy);
        rst.setRows(list);
        if (page <= 1) {
            int allRows = this.evaluatCaseDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }


    /**
     * @throws
     * @Title:insertEvaluatCase
     * @Description: TODO(添加题目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse insertEvaluatCase(String json, UserProp userProp) throws Exception {
        EvaluatCase o = JSON.parseObject(json, EvaluatCase.class);
        JSONObject jsonObj = JSON.parseObject(json);
        EvaluatCaseSub evaluatCaseSub = new EvaluatCaseSub();
        String evaluatCaseId = GUIDUtil.getGUID();
        o.setId(evaluatCaseId);
        if (CommonUtils.isBlank(jsonObj.getString("aName2"))) {
            return new MessageResponse(1, "至少要填写两个选项！");
        }
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "所属评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "选题类型(1单选2多选)不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        MessageResponse rr = insertCaseSub(jsonObj, userProp, evaluatCaseId);
        if (rr.getStatus() == 1) {
            throw new CustomException(rr.getErrorMessage());
        }
        o.setSolution(rr.getErrorMessage());
//        int temp = this.evaluatCaseDao.isExit(o);
//        if (temp > 0) {
//            return new MessageResponse(1, "题目名称重复！");
//        }
        o.setCreateDate(new Date());
        this.evaluatCaseDao.insert(o);
        this.dataBaseLogService.log("添加题目", "题目", "", o.getTitle(),
                o.getTitle(), userProp);
        return new MessageResponse(0, "添加题目完成！");
    }

    /**
     * @throws
     * @Title:insertEvaluatCase
     * @Description: TODO(添加题目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse insertEvaluatCaseVo(EvaluatCase o, List<EvaluatCaseSub> list, UserProp userProp) throws Exception {
        String evaluatCaseId = GUIDUtil.getGUID();
        o.setId(evaluatCaseId);
        if (CommonUtils.isBlank(evaluatCaseId)) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "所属评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "选题类型(1单选2多选)不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        int temp = this.evaluatCaseDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "题目名称重复！");
        }
        for (EvaluatCaseSub item : list) {
            item.setEvaluatCaseId(evaluatCaseId);
            item.setSort(1);
            item.setIsAnswer("0");
            this.evaluatCaseSubService.insertEvaluatCaseSub(item, userProp);
        }
        o.setCreateDate(new Date());
        this.evaluatCaseDao.insert(o);
        this.dataBaseLogService.log("添加题目", "题目", "", o.getTitle(),
                o.getTitle(), userProp);
        return new MessageResponse(0, "添加题目完成！");
    }

    /**
     * @throws
     * @Title:updateEvaluatCase
     * @Description: TODO(更新题目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse updateEvaluatCase(String jsons, UserProp userProp)
            throws Exception {

        EvaluatCase o = JSON.parseObject(jsons, EvaluatCase.class);
        JSONObject jsonObj = JSON.parseObject(jsons);
        EvaluatCaseSub evaluatCaseSub = new EvaluatCaseSub();
        String evaluatCaseId = o.getId();
        if (CommonUtils.isBlank(jsonObj.getString("aName2"))) {
            return new MessageResponse(1, "至少要填写两个选项！");
        }
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "所属评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "选题类型(1单选2多选)不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        this.evaluatCaseSubDao.deleteByEvaluatCaseId(evaluatCaseId);

        MessageResponse rr = insertCaseSub(jsonObj, userProp, evaluatCaseId);
        if (rr.getStatus() == 1) {
            throw new CustomException(rr.getErrorMessage());
        }
        o.setSolution(rr.getErrorMessage());
        this.evaluatCaseDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更题目", "题目", "", o.getTitle(),
                o.getTitle(), userProp);
        return new MessageResponse(0, "变更题目完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluatCaseByPrimaryKey
     * @Description: TODO(获取题目)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatCase>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public SingleResult<EvaluatCaseVo> selectEvaluatCaseByPrimaryKey(String id) throws Exception {
        SingleResult<EvaluatCaseVo> rst = new SingleResult<EvaluatCaseVo>();
        rst.setValue(this.evaluatCaseDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEvaluatCaseByEvaluatCaseId
     * @Description: TODO(删除题目)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse deleteEvaluatCaseByEvaluatCaseId(String id,
                                                            UserProp userProp) throws Exception {
        this.evaluatCaseDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除题目", "题目", String.valueOf(id),
                String.valueOf(id), "题目", userProp);
        return new MessageResponse(0, "题目删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取题目列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatCaseDao.getList(params));
        return rst;
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取题目)
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
        rst.put("data", this.evaluatCaseDao.getById(id));
        return rst;
    }


    private MessageResponse insertCaseSub(JSONObject jsonObj, UserProp userProp, String evaluatCaseId) throws Exception {
        EvaluatCaseSub evaluatCaseSub = new EvaluatCaseSub();
        evaluatCaseSub.setEvaluatCaseId(evaluatCaseId);
        String answer = "";
        for (int i = 1; i < 5; i++) {
            if (jsonObj.getString("aName" + i).equals("")) {
                return new MessageResponse(0, answer);
            }
            evaluatCaseSub.setName(jsonObj.getString("aName" + i));
            evaluatCaseSub.setCntImg(jsonObj.getString("aCntImg" + i));
            evaluatCaseSub.setIsAnswer(jsonObj.getString("isAnswer" + i));
            evaluatCaseSub.setSort(i);
            if ("1".equals(jsonObj.getString("isAnswer" + i))) {
                if (!answer.equals("")) {
                    answer += ",";
                }
                answer = answer + i;
            }
            MessageResponse mr = this.evaluatCaseSubService.insertEvaluatCaseSub(evaluatCaseSub, userProp);
            if (mr.getStatus() == 1) {
                return new MessageResponse(1, mr.getErrorMessage());
            }
        }
        if (Integer.parseInt(jsonObj.getString("type")) == 2 && answer.length() < 2) {
            return new MessageResponse(1, "多选题只有一个选项");
        }
        if (Integer.parseInt(jsonObj.getString("type")) == 1 && answer.length() > 1) {
            return new MessageResponse(1, "单选题有多个选项");
        }
        if (answer.equals("")) {
            return new MessageResponse(1, "没有填写答案");
        }
        return new MessageResponse(0, answer);
    }
}
