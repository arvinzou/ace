package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.*;
import com.huacainfo.ace.glink.api.SeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.fe.ProjectAreaOut;
import com.huacainfo.ace.glink.dao.SeProjectAreaDao;
import com.huacainfo.ace.glink.model.SeProjectArea;
import com.huacainfo.ace.glink.service.SeProjectAreaService;
import com.huacainfo.ace.glink.vo.SeProjectAreaQVo;
import com.huacainfo.ace.glink.vo.SeProjectAreaVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("seProjectAreaService")
/**
 * @author: heshuang
 * @version: 2019-04-18
 * @Description: TODO(项目区域数据)
 */
public class SeProjectAreaServiceImpl implements SeProjectAreaService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeProjectAreaDao seProjectAreaDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    private static List<SeProjectArea> list = new ArrayList<>();

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(区级整体控制分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeProjectAreaVo>
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public PageResult<SeProjectAreaVo> findSeProjectAreaList(SeProjectAreaQVo condition,
                                                             int start, int limit, String orderBy) throws Exception {
        PageResult<SeProjectAreaVo> rst = new PageResult<>();

        List<SeProjectAreaVo> list = seProjectAreaDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.seProjectAreaDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:updateSeProjectArea
     * @Description: TODO(更新项目区域数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateSeProjectArea(SeProjectArea o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectName())) {
            return new MessageResponse(1, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaName())) {
            return new MessageResponse(1, "区域名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        //  o.setLastModifyDate(new Date());
        //  o.setLastModifyUserName(userProp.getName());
        //  o.setLastModifyUserId(userProp.getUserId());
        this.seProjectAreaDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更项目区域数据", "项目区域数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:insertSeProjectArea
     * @Description: TODO(添加区级整体控制)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse insertSeProjectArea(SeProjectArea o, UserProp userProp) throws Exception {


        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);
        if (CommonUtils.isBlank(o.getProjectName())) {
            return new MessageResponse(1, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaName())) {
            return new MessageResponse(1, "区域名称不能为空！");
        }
        int temp = this.seProjectAreaDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "项目区域数据名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.seProjectAreaDao.insert(o);
        this.dataBaseLogService.log("添加项目区域数据", "项目区域数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:deleteSeProjectAreaBySeProjectAreaId
     * @Description: TODO(删除区级整体控制)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeProjectAreaBySeProjectAreaId(String id, UserProp userProp) throws
            Exception {
        this.seProjectAreaDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除项目区域数据", "项目区域数据", id, id,
                "项目区域数据", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核区级整体控制)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核项目区域数据", "项目区域数据", id, id,
                "项目区域数据", userProp);
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
     * @version: 2019-04-18
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
            SeProjectArea o = new SeProjectArea();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            //  o.setCreateUserId(userProp.getUserId());
            //   o.setCreateUserName(userProp.getName());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getProjectName())) {
                return new MessageResponse(1, "项目名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getAreaName())) {
                return new MessageResponse(1, "区域名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态不能为空！");
            }

            int t = this.seProjectAreaDao.isExit(o);
            if (t > 0) {
                this.seProjectAreaDao.updateByPrimaryKey(o);

            } else {
                this.seProjectAreaDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("项目区域数据导入", "项目区域数据", "",
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
     * @version: 2019-04-18
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
        rst.setValue(this.seProjectAreaDao.getList(p));

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
     * @version: 2019-04-18
     */
    @Override
    public Map
            <String
                    , Object> getListByCondition(Map
                                                         <String
                                                                 , Object> params) {
        Map
                <String
                        , Object> rst = new HashMap<>();
        List
                <Map
                        <String
                                , Object>> list = this.seProjectAreaDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除区级整体控制 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeProjectAreaBySeProjectAreaIds(String[] id, UserProp userProp)
            throws Exception {

        this.seProjectAreaDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除项目区域数据", "项目区域数据", id[0],
                id[0], "项目区域数据", userProp);
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
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.seProjectAreaDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "区级整体控制", id, id,
                "区级整体控制", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * @throws
     * @Title:selectSeProjectAreaByPrimaryKey
     * @Description: TODO(获取区级整体控制)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeProjectArea>
     * @author: heshuang
     * @version: 2019-04-18
     */
    @Override
    public SingleResult<SeProjectAreaVo> selectSeProjectAreaByPrimaryKey(String id) throws Exception {
        SingleResult<SeProjectAreaVo> rst = new SingleResult<>();
        rst.setValue(this.seProjectAreaDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * 区域树
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Tree> selectTreeList() throws Exception {
        CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
                this.seProjectAreaDao.selectTreeList());
        return commonTreeUtils.getTreeList("top");
    }

    @Override
    public MessageResponse syncProjectData(UserProp userProp) {
        ProjectAreaOut o = SeApiToolKit.getAreaProjectInfo();
        int i = seProjectAreaDao.allClear();//数据清空
        list.clear(); //列表清空
        String pid = o.getAreaNodeID();
        SeProjectArea r = new SeProjectArea();
        //第一条
        r.setId(pid);
        r.setStatus("1");
        r.setCreateDate(DateUtil.getNowDate());
        r.setPid("top");
        r.setProjectName(o.getProjectName());
        r.setAreaName(o.getProjectName());
        r.setAreaNodeCount(o.getAreaNodeCount());
        r.setAreaNodeID(o.getAreaNodeID());
        r.setAreaType(o.getAreaType());
        list.add(r);

        areaList(pid, o.getProjectName(), o);

        for (SeProjectArea record : list) {

            this.seProjectAreaDao.insert(record);
        }


        return new MessageResponse(ResultCode.SUCCESS, "同步成功");
    }

    private void areaList(String pid, String projectName, ProjectAreaOut obj) {
        SeProjectArea record;

        if (obj.getAreaType() != 1 && obj.getAreaNode() != null) {

            List<ProjectAreaOut> outList = JsonUtil.toList(obj.getAreaNode(), ProjectAreaOut.class);
            for (ProjectAreaOut item : outList) {
                String newPid = obj.getAreaNodeID();
                record = newObject(newPid, projectName, item);
                if (item.getAreaType() == 1) {
                    list.add(record);
                    continue;
                } else {
                    list.add(record);
                    areaList(newPid, projectName, item);
                }
            }
        }

    }

    private SeProjectArea newObject(String pid, String projectName, ProjectAreaOut in) {
        SeProjectArea r = new SeProjectArea();
        r.setId(in.getAreaNodeID());
        r.setStatus("1");
        r.setCreateDate(DateUtil.getNowDate());
        //
        r.setPid(pid);
        r.setProjectName(projectName);
        //
        r.setAreaName(in.getAreaName());
        r.setAreaNodeCount(in.getAreaNodeCount());
        r.setAreaNodeID(in.getAreaNodeID());
        r.setAreaType(in.getAreaType());

        return r;
    }
}
