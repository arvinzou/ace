package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

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
import com.huacainfo.ace.glink.dao.TopStationDao;
import com.huacainfo.ace.glink.model.TopStation;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.TopStationService;
import com.huacainfo.ace.glink.vo.TopStationVo;
import com.huacainfo.ace.glink.vo.TopStationQVo;

@Service("topStationService")
/**
 * @author: heshuang
 * @version: 2019-04-09
 * @Description: TODO(站点管理)
 */
public class TopStationServiceImpl implements TopStationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopStationDao topStationDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(站点管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TopStationVo>
     * @author: heshuang
     * @version: 2019-04-09
     */
    @Override
    public PageResult
            <TopStationVo> findTopStationList(TopStationQVo condition, int start, int limit, String orderBy) throws
            Exception {
        PageResult
                <TopStationVo> rst = new PageResult<>();
        List
                <TopStationVo> list = this.topStationDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.topStationDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTopStation
     * @Description: TODO(添加站点管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse insertTopStation(TopStation o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubareaCode())) {
            return new MessageResponse(1, "分区编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "站点名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        int temp = this.topStationDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "站点管理名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.topStationDao.insert(o);
        this.dataBaseLogService.log("添加站点管理", "站点管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTopStation
     * @Description: TODO(更新站点管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse updateTopStation(TopStation o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubareaCode())) {
            return new MessageResponse(1, "分区编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "站点名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.topStationDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更站点管理", "站点管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTopStationByPrimaryKey
     * @Description: TODO(获取站点管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopStation>
     * @author: heshuang
     * @version: 2019-04-09
     */
    @Override
    public SingleResult
            <TopStationVo> selectTopStationByPrimaryKey(String id) throws Exception {
        SingleResult
                <TopStationVo> rst = new SingleResult<>();
        rst.setValue(this.topStationDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTopStationByTopStationId
     * @Description: TODO(删除站点管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse deleteTopStationByTopStationId(String id, UserProp userProp) throws
            Exception {
        this.topStationDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除站点管理", "站点管理", id, id,
                "站点管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核站点管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核站点管理", "站点管理", id, id,
                "站点管理", userProp);
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
     * @author: heshuang
     * @version: 2019-04-09
     */

    @Override
    public MessageResponse importXls(List
                                             <Map
                                                     <String
                                                             , Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map
                <String
                        , Object> row : list) {
            TopStation o = new TopStation();
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
            if (CommonUtils.isBlank(o.getSubareaCode())) {
                return new MessageResponse(1, "分区编码不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "站点名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.topStationDao.isExit(o);
            if (t > 0) {
                this.topStationDao.updateByPrimaryKey(o);

            } else {
                this.topStationDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("站点管理导入", "站点管理", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult
     * <Map
     * <String
     * ,Object>>
     * @author: heshuang
     * @version: 2019-04-09
     */
    @Override
    public ListResult
            <Map
                    <String
                            , Object>> getList(Map
                                                       <String
                                                               , Object> p) throws Exception {
        ListResult
                <Map
                        <String
                                , Object>> rst = new ListResult<>();
        rst.setValue(this.topStationDao.getList(p));

        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: heshuang
     * @version: 2019-04-09
     */
    @Override
    public Map
            <String
                    , Object> getListByCondition(Map
                                                         <String
                                                                 , Object> params) {
        Map
                <String
                        , Object> rst = new HashMap
                <String
                        , Object>();
        List
                <Map
                        <String
                                , Object>> list = this.topStationDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除站点管理 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse deleteTopStationByTopStationIds(String[] id, UserProp userProp)
            throws Exception {

        this.topStationDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除站点管理", "站点管理", id[0],
                id[0], "站点管理", userProp);
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
     * @author: heshuang
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.topStationDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "站点管理", id, id,
                "站点管理", userProp);
        return new MessageResponse(0, "成功！");
    }

}
