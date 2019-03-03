package com.huacainfo.ace.partyschool.service.impl;


import java.math.BigDecimal;
import java.util.*;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.EvaluationRstDao;
import com.huacainfo.ace.partyschool.dao.TestTopicDao;
import com.huacainfo.ace.partyschool.model.EvaluationExport;
import com.huacainfo.ace.partyschool.model.TestTopic;
import com.huacainfo.ace.partyschool.vo.TestTopicQVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.TestDao;
import com.huacainfo.ace.partyschool.model.Test;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.TestService;
import com.huacainfo.ace.partyschool.vo.TestVo;
import com.huacainfo.ace.partyschool.vo.TestQVo;

@Service("testService")
/**
 * @author: 王恩
 * @version: 2019-02-27
 * @Description: TODO(测评结果管理)
 */
public class TestServiceImpl implements TestService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TestDao testDao;
    @Autowired
    private TestTopicDao testTopicDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private SqlSessionTemplate sqlSession;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测评结果管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TestVo>
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public PageResult<TestVo> findTestList(TestQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<TestVo> rst = new PageResult<>();
        List<TestVo> list = this.testDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.testDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTest
     * @Description: TODO(添加测评结果管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public MessageResponse insertTest(Test o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "测试名称不能为空！");
        }
        int temp = this.testDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "测评结果管理名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.testDao.insert(o);
        this.dataBaseLogService.log("添加测评结果管理", "测评结果管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTest
     * @Description: TODO(更新测评结果管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public MessageResponse updateTest(Test o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "测试名称不能为空！");
        }
        o.setStatus("1");
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.testDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更测评结果管理", "测评结果管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTestByPrimaryKey
     * @Description: TODO(获取测评结果管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Test>
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public SingleResult<TestVo> selectTestByPrimaryKey(String id) throws Exception {
        SingleResult<TestVo> rst = new SingleResult<>();
        rst.setValue(this.testDao.selectVoByPrimaryKey(id));
        return rst;
    }

    @Override
    public ResultResponse findTopicsByTestId(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new ResultResponse(ResultCode.FAIL, "testID为空");
        }
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        TestDao dao = session.getMapper(TestDao.class);
        TestVo testVo=null;
        try {
            testVo = dao.findTopicsByTestId(id);
//            WriteExcel(list);
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return new ResultResponse(ResultCode.SUCCESS, "成功", testVo);

    }

    /**
     * @throws
     * @Title:deleteTestByTestId
     * @Description: TODO(删除测评结果管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public MessageResponse deleteTestByTestId(String id, UserProp userProp) throws
            Exception {
        this.testDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除测评结果管理", "测评结果管理", id, id,
                "测评结果管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核测评结果管理)
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


        dataBaseLogService.log("审核测评结果管理", "测评结果管理", id, id,
                "测评结果管理", userProp);
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
            Test o = new Test();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setCreateUserName(userProp.getName());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "测试名称不能为空！");
            }

            int t = this.testDao.isExit(o);
            if (t > 0) {
                this.testDao.updateByPrimaryKey(o);

            } else {
                this.testDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("测评结果管理导入", "测评结果管理", "", "rs.xls", "rs.xls", userProp);
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
        rst.setValue(this.testDao.getList(p));

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
        List<Map<String, Object>> list = this.testDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除测评结果管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @Override
    public MessageResponse deleteTestByTestIds(String[] id, UserProp userProp) throws Exception {

        this.testDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除测评结果管理", "测评结果管理", id[0], id[0], "测评结果管理", userProp);
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
        this.testDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "测评结果管理", id, id, "测评结果管理", userProp);
        return new MessageResponse(0, "成功！");
    }

    @Override
    public MessageResponse inserTopics(TestTopicQVo obj, UserProp userProp) throws Exception {
        if(CommonUtils.isBlank(obj.getTestId())){
            return new MessageResponse(ResultCode.FAIL,"缺失testId");
        }
        if(CommonUtils.isBlank(obj.getTopics())){
            return new MessageResponse(ResultCode.FAIL,"没有选择试题");
        }

        obj.setScore((new BigDecimal(0)));
        List<String> list=obj.getTopics();
        int count=this.testTopicDao.findCount(obj);

        for(int i=0;i<list.size();i++){
            obj.setId(GUIDUtil.getGUID());
            obj.setTopicId(list.get(i));
            obj.setIndex(count+1+i);
            testTopicDao.insert(obj);
        }
        this.dataBaseLogService.log("添加试题", "测评结果管理",obj.getTopics().toString() , obj.getTopics().toString(), "测评结果管理", userProp);
        return new MessageResponse(ResultCode.SUCCESS, "成功！");
    }
    @Override
    public MessageResponse  changeTestTopicIndex(String tid1, String tid2) throws Exception {
        if (CommonUtils.isBlank(tid1)){
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数！");
        }
        if (CommonUtils.isBlank(tid1)){
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数！");
        }
        testTopicDao.changeIndex(tid1,tid2);
        return new MessageResponse(0, "成功！");
    }

}
