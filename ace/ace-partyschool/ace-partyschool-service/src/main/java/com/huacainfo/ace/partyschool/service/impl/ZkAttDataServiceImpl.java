package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.ZkAttDataDao;
import com.huacainfo.ace.partyschool.model.ZkAttData;
import com.huacainfo.ace.partyschool.service.ZkAttDataService;
import com.huacainfo.ace.partyschool.vo.ZkAttDataQVo;
import com.huacainfo.ace.partyschool.vo.ZkAttDataVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("zkAttDataService")
/**
 * @author: Arvin
 * @version: 2019-02-20
 * @Description: TODO(中控考勤数据源)
 */
public class ZkAttDataServiceImpl implements ZkAttDataService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ZkAttDataDao zkAttDataDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(中控考勤数据源分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ZkAttDataVo>
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public PageResult<ZkAttDataVo> findZkAttDataList(ZkAttDataQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<ZkAttDataVo> rst = new PageResult<>();
        List<ZkAttDataVo> list = this.zkAttDataDao.findVoList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.zkAttDataDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertZkAttData
     * @Description: TODO(添加中控考勤数据源)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse insertZkAttData(ZkAttData o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getAttTime())) {
            return new MessageResponse(1, "考勤时间不能为空！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.zkAttDataDao.insert(o);
        this.dataBaseLogService.log("添加中控考勤数据源", "中控考勤数据源", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateZkAttData
     * @Description: TODO(更新中控考勤数据源)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse updateZkAttData(ZkAttData o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getAttTime())) {
            return new MessageResponse(1, "考勤时间不能为空！");
        }

        this.zkAttDataDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更中控考勤数据源", "中控考勤数据源", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectZkAttDataByPrimaryKey
     * @Description: TODO(获取中控考勤数据源)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ZkAttData>
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public SingleResult<ZkAttDataVo> selectZkAttDataByPrimaryKey(String id) throws Exception {
        SingleResult<ZkAttDataVo> rst = new SingleResult<>();
        rst.setValue(this.zkAttDataDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteZkAttDataByZkAttDataId
     * @Description: TODO(删除中控考勤数据源)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse deleteZkAttDataByZkAttDataId(String id, UserProp userProp) throws
            Exception {
        this.zkAttDataDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除中控考勤数据源", "中控考勤数据源", id, id,
                "中控考勤数据源", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核中控考勤数据源)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核中控考勤数据源", "中控考勤数据源", id, id,
                "中控考勤数据源", userProp);
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
     * @author: Arvin
     * @version: 2019-02-20
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            ZkAttData o = new ZkAttData();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getUserId())) {
                return new MessageResponse(1, "用户编码不能为空！");
            }
            if (CommonUtils.isBlank(o.getAttTime())) {
                return new MessageResponse(1, "考勤时间不能为空！");
            }

            int t = 0;// this.zkAttDataDao.isExit(o);
            if (t > 0) {
                this.zkAttDataDao.updateByPrimaryKey(o);

            } else {
                this.zkAttDataDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("中控考勤数据源导入", "中控考勤数据源", "", "rs.xls", "rs.xls", userProp);
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
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        // rst.setValue(this.zkAttDataDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = null;//this.zkAttDataDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除中控考勤数据源）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse deleteZkAttDataByZkAttDataIds(String[] id, UserProp userProp) throws Exception {

        // this.zkAttDataDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除中控考勤数据源", "中控考勤数据源", id[0], id[0], "中控考勤数据源", userProp);
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
     * @author: Arvin
     * @version: 2019-02-20
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        // this.zkAttDataDao.updateStatus(id, status);
        this.dataBaseLogService.log("更新状态", "中控考勤数据源", id, id, "中控考勤数据源", userProp);
        return new MessageResponse(0, "成功！");
    }

}
