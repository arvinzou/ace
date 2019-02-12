package com.huacainfo.ace.taa.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.dao.RoadGpsDao;
import com.huacainfo.ace.taa.dao.RoadManDao;
import com.huacainfo.ace.taa.dao.RoadSectionDao;
import com.huacainfo.ace.taa.dao.TraAccDao;
import com.huacainfo.ace.taa.model.RoadMan;
import com.huacainfo.ace.taa.model.RoadSection;
import com.huacainfo.ace.taa.service.RoadSectionService;
import com.huacainfo.ace.taa.vo.RoadSectionQVo;
import com.huacainfo.ace.taa.vo.RoadSectionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roadSectionService")
/**
 * @author: 陈晓克
 * @version: 2019-01-04
 * @Description: TODO(路段)
 */
public class RoadSectionServiceImpl implements RoadSectionService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadSectionDao roadSectionDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private RoadManDao roadManDao;

    @Autowired
    private RoadGpsDao roadGpsDao;
    @Autowired
    private TraAccDao traAccDao;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路段分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RoadSectionVo>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public PageResult<RoadSectionVo> findRoadSectionList(RoadSectionQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<RoadSectionVo> rst = new PageResult<>();
        List<RoadSectionVo> list = this.roadSectionDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.roadSectionDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertRoadSection
     * @Description: TODO(添加路段)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse insertRoadSection(RoadSection o, UserProp userProp) throws Exception {


        if (CommonUtils.isBlank(o.getRoadId())) {
            return new MessageResponse(1, "所属道路不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new MessageResponse(1, "行政区划不能为空！");
        }
        if (CommonUtils.isBlank(o.getRoadMan())) {
            return new MessageResponse(1, "归属路长不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartName())) {
            return new MessageResponse(1, "路段开始不能为空！");
        }
        if (CommonUtils.isBlank(o.getEndName())) {
            return new MessageResponse(1, "路段截止不能为空！");
        }
        int temp = this.roadSectionDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "路段名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.roadSectionDao.insert(o);
        this.dataBaseLogService.log("添加路段", "路段", "",
                o.getId(), o.getId(), userProp);

        //增加路段数量
        int num = this.roadSectionDao.selectSectionCount(o.getRoadId());
        roadSectionDao.updateSectionCount(o.getRoadId(), num);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateRoadSection
     * @Description: TODO(更新路段)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse updateRoadSection(RoadSection o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRoadId())) {
            return new MessageResponse(1, "所属道路不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new MessageResponse(1, "行政区划不能为空！");
        }
        if (CommonUtils.isBlank(o.getRoadMan())) {
            return new MessageResponse(1, "归属路长不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartName())) {
            return new MessageResponse(1, "路段开始不能为空！");
        }
        if (CommonUtils.isBlank(o.getEndName())) {
            return new MessageResponse(1, "路段截止不能为空！");
        }

        o.setStatus(StringUtil.isNotEmpty(o.getStatus()) ? o.getStatus() : "1");
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.roadSectionDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更路段", "路段", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectRoadSectionByPrimaryKey
     * @Description: TODO(获取路段)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RoadSection>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public SingleResult<RoadSectionVo> selectRoadSectionByPrimaryKey(String id) throws Exception {
        SingleResult<RoadSectionVo> rst = new SingleResult<>();
        rst.setValue(this.roadSectionDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionId
     * @Description: TODO(删除路段)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse deleteRoadSectionByRoadSectionId(String id, UserProp userProp) throws Exception {
        RoadSection rs = roadSectionDao.selectByPrimaryKey(id);
        if (rs == null) {
            return new MessageResponse(ResultCode.FAIL, "数据记录丢失");
        }
        MessageResponse ms = delCheck(new String[]{id});
        if (ms.getStatus() == ResultCode.FAIL) {
            return ms;
        }

        this.roadSectionDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除路段", "路段", id, id, "路段", userProp);
        //道路路段统计信息更改
        int num = this.roadSectionDao.selectSectionCount(rs.getRoadId());
        this.roadSectionDao.updateSectionCount(rs.getRoadId(), num);

        return new MessageResponse(0, "路段删除完成！");
    }

    private MessageResponse delCheck(String[] ids) {
        int gpsCount = roadGpsDao.findCount(ids);
        if (gpsCount > 0) {
            return new MessageResponse(ResultCode.FAIL, "存在路段采集信息，不允许删除");
        }
        int accCount = traAccDao.findCountBySectionIds(ids);
        if (accCount > 0) {
            return new MessageResponse(ResultCode.FAIL, "存在事故绑定信息，不允许删除");
        }

        return new MessageResponse(ResultCode.SUCCESS, "允许删除！");
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核路段)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核路段", "路段", id, id,
                "路段", userProp);
        return new MessageResponse(0, "路段审核完成！");
    }

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp, String roadId) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            RoadSection o = new RoadSection();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setRoadId(roadId);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setCreateUserName(userProp.getName());
            o.setId(GUIDUtil.getGUID());
            o.setStatus("1");
            this.logger.info(o.toString());
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "行" + i + ",名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getStartName())) {
                return new MessageResponse(1, "行" + i + ",路段开始名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getEndName())) {
                return new MessageResponse(1, "行" + i + ",路段截止名称不能为空！");
            }
            RoadMan man = this.roadManDao.selectByName((String) row.get("roadManName"));
            if (CommonUtils.isNotEmpty(man)) {
                o.setAreaCode(man.getAreaCode());
                o.setRoadMan(man.getId());
            }
            int t = this.roadSectionDao.isExit(o);
            if (t > 0) {
                this.roadSectionDao.updateByName(o);

            } else {
                this.roadSectionDao.insert(o);
            }
            i++;
        }
        int num = this.roadSectionDao.selectSectionCount(roadId);
        this.roadSectionDao.updateSectionCount(roadId, num);
        this.dataBaseLogService.log("路段导入", "路段", "", "rs.xls", "rs.xls", userProp);
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
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.roadSectionDao.getList(p));
        return rst;
    }


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionId
     * @Description: TODO(删除路段)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse deleteRoadSectionByRoadSectionIds(String roadId, String[] id, UserProp userProp) throws Exception {
        MessageResponse ms = delCheck(id);
        if (ms.getStatus() == ResultCode.FAIL) {
            return ms;
        }
        //批量删除
        this.roadSectionDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除路段", "路段", id[0], id[0], "路段", userProp);
        //道路路段统计信息更改
        int num = this.roadSectionDao.selectSectionCount(roadId);
        this.roadSectionDao.updateSectionCount(roadId, num);

        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(路长查询，用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: chenxiaoke
     * @version: 2019年1月04日 下午1:24:14
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.roadSectionDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }
}
