package com.huacainfo.ace.taa.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.List;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.taa.dao.RoadManDao;
import com.huacainfo.ace.taa.model.RoadMan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.taa.dao.RoadSectionDao;
import com.huacainfo.ace.taa.model.RoadSection;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.service.RoadSectionService;
import com.huacainfo.ace.taa.vo.RoadSectionVo;
import com.huacainfo.ace.taa.vo.RoadSectionQVo;

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
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
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

        return new MessageResponse(0, "添加路段完成！");
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
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.roadSectionDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更路段", "路段", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更路段完成！");
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
    public SingleResult
            <RoadSectionVo> selectRoadSectionByPrimaryKey(String id) throws Exception {
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
    public MessageResponse deleteRoadSectionByRoadSectionId(String id, UserProp userProp) throws
            Exception {
        this.roadSectionDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除路段", "路段", id, id,
                "路段", userProp);
        return new MessageResponse(0, "路段删除完成！");
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
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp,String roadId) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            RoadSection o = new RoadSection();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setRoadId(roadId);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setId(GUIDUtil.getGUID());
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
            RoadMan man= this.roadManDao.selectByName((String) row.get("roadManName"));
            if(CommonUtils.isNotEmpty(man)){
                o.setAreaCode(man.getAreaCode());
                o.setRoadMan(man.getId());
            }
            int t = this.roadSectionDao.isExit(o);
            if (t > 0) {
                this.roadSectionDao.updateByPrimaryKey(o);

            } else {
                this.roadSectionDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("路段导入", "路段", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入完成！");
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

}
