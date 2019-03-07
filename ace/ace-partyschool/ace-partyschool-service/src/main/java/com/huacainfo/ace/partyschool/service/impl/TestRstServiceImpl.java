package com.huacainfo.ace.partyschool.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.TestRstDao;
import com.huacainfo.ace.partyschool.model.TestRst;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.TestRstService;
import com.huacainfo.ace.partyschool.vo.TestRstVo;
import com.huacainfo.ace.partyschool.vo.TestRstQVo;

@Service("testRstService")
/**
 * @author: 王恩
 * @version: 2019-03-06
 * @Description: TODO(测试答案管理)
 */
public class TestRstServiceImpl implements TestRstService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TestRstDao testRstDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测试答案管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TestRstVo>
     * @author: 王恩
     * @version: 2019-03-06
     */
    @Override
    public PageResult<TestRstVo> findTestRstList(TestRstQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<TestRstVo> rst = new PageResult<>();
        List<TestRstVo> list = this.testRstDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.testRstDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTestRst
     * @Description: TODO(添加测试答案管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-06
     */
    @Override
    public MessageResponse insertTestRst(TestRst o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "测试名称不能为空！");
        }


        int temp = this.testRstDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "测试答案管理名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.testRstDao.insert(o);
        this.dataBaseLogService.log("添加测试答案管理", "测试答案管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTestRst
     * @Description: TODO(更新测试答案管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-06
     */
    @Override
    public MessageResponse updateTestRst(TestRst o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "测试名称不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.testRstDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更测试答案管理", "测试答案管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTestRstByPrimaryKey
     * @Description: TODO(获取测试答案管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TestRst>
     * @author: 王恩
     * @version: 2019-03-06
     */
    @Override
    public SingleResult<TestRstVo> selectTestRstByPrimaryKey(String id) throws Exception {
        SingleResult<TestRstVo> rst = new SingleResult<>();
        rst.setValue(this.testRstDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTestRstByTestRstId
     * @Description: TODO(删除测试答案管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-06
     */
    @Override
    public MessageResponse deleteTestRstByTestRstId(String id, UserProp userProp) throws
            Exception {
        this.testRstDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除测试答案管理", "测试答案管理", id, id,
                "测试答案管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核测试答案管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-06
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核测试答案管理", "测试答案管理", id, id,
                "测试答案管理", userProp);
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
     * @version: 2019-03-06
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            TestRst o = new TestRst();
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
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "测试名称不能为空！");
            }

            int t = this.testRstDao.isExit(o);
            if (t > 0) {
                this.testRstDao.updateByPrimaryKey(o);

            } else {
                this.testRstDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("测试答案管理导入", "测试答案管理", "", "rs.xls", "rs.xls", userProp);
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
     * @version: 2019-03-06
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.testRstDao.getList(p));

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
     * @version: 2019-03-06
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.testRstDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除测试答案管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-06
     */
    @Override
    public MessageResponse deleteTestRstByTestRstIds(String[] id, UserProp userProp) throws Exception {

        this.testRstDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除测试答案管理", "测试答案管理", id[0], id[0], "测试答案管理", userProp);
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
     * @version: 2019-03-06
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.testRstDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "测试答案管理", id, id, "测试答案管理", userProp);
        return new MessageResponse(0, "成功！");
    }

}
