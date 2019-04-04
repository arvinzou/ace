package com.huacainfo.ace.taa.service.impl;


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
import com.huacainfo.ace.taa.dao.BranchRoadManDao;
import com.huacainfo.ace.taa.model.BranchRoadMan;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.service.BranchRoadManService;
import com.huacainfo.ace.taa.vo.BranchRoadManVo;
import com.huacainfo.ace.taa.vo.BranchRoadManQVo;

@Service("branchRoadManService")
/**
 * @author: heshaung
 * @version: 2019-03-29
 * @Description: TODO(BranchRoadMan)
 */
public class BranchRoadManServiceImpl implements BranchRoadManService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BranchRoadManDao branchRoadManDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(BranchRoadMan分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BranchRoadManVo>
     * @author: heshaung
     * @version: 2019-03-29
     */
    @Override
    public PageResult<BranchRoadManVo> findBranchRoadManList(BranchRoadManQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<BranchRoadManVo> rst = new PageResult<>();
        List<BranchRoadManVo> list = this.branchRoadManDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.branchRoadManDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertBranchRoadMan
     * @Description: TODO(添加BranchRoadMan)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    @Override
    public MessageResponse insertBranchRoadMan(BranchRoadMan o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.branchRoadManDao.insert(o);
        this.dataBaseLogService.log("添加BranchRoadMan", "BranchRoadMan", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateBranchRoadMan
     * @Description: TODO(更新BranchRoadMan)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    @Override
    public MessageResponse updateBranchRoadMan(BranchRoadMan o, UserProp userProp) throws Exception {
        o.setLastModifyDate(new Date());
        this.branchRoadManDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更BranchRoadMan", "BranchRoadMan", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectBranchRoadManByPrimaryKey
     * @Description: TODO(获取BranchRoadMan)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<BranchRoadMan>
     * @author: heshaung
     * @version: 2019-03-29
     */
    @Override
    public SingleResult<BranchRoadMan> selectBranchRoadManByPrimaryKey(String id) throws Exception {
        SingleResult<BranchRoadMan> rst = new SingleResult<>();
        rst.setValue(this.branchRoadManDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteBranchRoadManByBranchRoadManId
     * @Description: TODO(删除BranchRoadMan)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    @Override
    public MessageResponse deleteBranchRoadManByBranchRoadManId(String id, UserProp userProp) throws
            Exception {
        this.branchRoadManDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除BranchRoadMan", "BranchRoadMan", id, id,
                "BranchRoadMan", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核BranchRoadMan)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核BranchRoadMan", "BranchRoadMan", id, id,
                "BranchRoadMan", userProp);
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
     * @author: heshaung
     * @version: 2019-03-29
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            BranchRoadMan o = new BranchRoadMan();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            //   o.setCreateUserId(userProp.getUserId());
            //  o.setCreateUserName(userProp.getName());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }

            int t = this.branchRoadManDao.isExit(o);
            if (t > 0) {
                this.branchRoadManDao.updateByPrimaryKey(o);

            } else {
                this.branchRoadManDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("BranchRoadMan导入", "BranchRoadMan", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map < String, Object>>
     * @author: heshaung
     * @version: 2019-03-29
     */
    @Override
    public ListResult<Map<String, Object>> getList(String roadSectionId) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.branchRoadManDao.getList(roadSectionId));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: heshaung
     * @version: 2019-03-29
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.branchRoadManDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除BranchRoadMan ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    @Override
    public MessageResponse deleteBranchRoadManByBranchRoadManIds(String[] id, UserProp userProp) throws Exception {

        this.branchRoadManDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除BranchRoadMan", "BranchRoadMan", id[0], id[0], "BranchRoadMan", userProp);
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
     * @author: heshaung
     * @version: 2019-03-29
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.branchRoadManDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "BranchRoadMan", id, id, "BranchRoadMan", userProp);
        return new MessageResponse(0, "成功！");
    }

    @Override
    public SingleResult<BranchRoadManVo> selectVoBranchRoadManByPrimaryKey(String id) throws Exception {
        SingleResult<BranchRoadManVo> rst = new SingleResult<>();
        rst.setValue(this.branchRoadManDao.selectVoByPrimaryKey(id));
        return rst;
    }

}
