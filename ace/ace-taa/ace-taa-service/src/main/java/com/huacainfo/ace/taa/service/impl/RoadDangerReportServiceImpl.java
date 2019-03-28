package com.huacainfo.ace.taa.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.AuthorityService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.dao.OfficeAdminDao;
import com.huacainfo.ace.taa.dao.RoadDangerReportDao;
import com.huacainfo.ace.taa.dao.RoadDangerReportPicDao;
import com.huacainfo.ace.taa.model.RoadDangerReport;
import com.huacainfo.ace.taa.model.RoadDangerReportPic;
import com.huacainfo.ace.taa.service.RoadDangerReportService;
import com.huacainfo.ace.taa.vo.RoadDangerReportQVo;
import com.huacainfo.ace.taa.vo.RoadDangerReportVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roadDangerReportService")
/**
 * @author: 何双
 * @version: 2019-03-15
 * @Description: TODO(路况上报)
 */
public class RoadDangerReportServiceImpl implements RoadDangerReportService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadDangerReportDao roadDangerReportDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private RoadDangerReportPicDao roadDangerReportPicDao;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private OfficeAdminDao officeAdminDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路况上报分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RordDangerReportVo>
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public PageResult<RoadDangerReportVo> findRordDangerReportList(RoadDangerReportQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<RoadDangerReportVo> rst = new PageResult<>();
        List<RoadDangerReportVo> list = this.roadDangerReportDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.roadDangerReportDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertRordDangerReport
     * @Description: TODO(添加路况上报)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */

    @Override
    public MessageResponse insertRordDangerReport(RoadDangerReportQVo obj, UserProp userProp) throws Exception {
        String id = GUIDUtil.getGUID();
        if (CommonUtils.isBlank(obj.getLatitude())) {
            return new MessageResponse(ResultCode.FAIL, "纬度不能为空！");
        }
        if (CommonUtils.isBlank(obj.getLongitude())) {
            return new MessageResponse(ResultCode.FAIL, "经度不能为空！");
        }
        if (CommonUtils.isBlank(obj.getType())) {
            return new MessageResponse(ResultCode.FAIL, "路患类型不能为空！");
        }
        if (CommonUtils.isBlank(obj.getAddress())) {
            return new MessageResponse(ResultCode.FAIL, "路患地点不能为空！");
        }
        obj.setId(id);
        obj.setReportDate(new Date());
        obj.setCreateDate(new Date());
        obj.setStatus("1");
        obj.setCreateUserName(userProp.getName());
        obj.setCreateUserId(userProp.getUserId());
        obj.setUserId(userProp.getUserId());
        obj.setDptId(userProp.getCorpId());
        if (CommonUtils.isBlank(obj.getAreaCode())) {
            obj.setAreaCode(userProp.getAreaCode());
        }
        this.roadDangerReportDao.insert(obj);
        List<String> list = obj.getNoChangedImagesUrl();
        if (list != null) {
            RoadDangerReportPic pic = new RoadDangerReportPic();
            pic.setCatgeory("1");
            pic.setCreateDate(new Date());
            pic.setCreateUserName(userProp.getName());
            pic.setCreateUserId(userProp.getUserId());
            for (int i = 0; i < list.size(); i++) {
                pic.setId(GUIDUtil.getGUID());
                pic.setReportId(id);
                pic.setFileUrl(list.get(i));
                this.roadDangerReportPicDao.insert(pic);
            }
        }
        this.dataBaseLogService.log("添加路况上报", "路况上报", "",
                obj.getId(), obj.getId(), userProp);

        return new MessageResponse(0, "隐患上报成功！");
    }


    /**
     * @throws
     * @Title:updateRordDangerReport
     * @Description: TODO(更新路况上报)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public MessageResponse updateRordDangerReport(RoadDangerReportQVo o, UserProp userProp) throws Exception {
        String id = o.getId();
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        if (CommonUtils.isBlank(o.getAreaCode())) {
            o.setAreaCode(userProp.getAreaCode());
        }
        this.roadDangerReportDao.updateByPrimaryKey(o);

        List<String> list = o.getChangedImagesUrl();
        if (list != null) {
            RoadDangerReportPic pic = new RoadDangerReportPic();
            pic.setCatgeory("2");
            pic.setCreateDate(new Date());
            pic.setCreateUserName(userProp.getName());
            pic.setCreateUserId(userProp.getUserId());
            for (int i = 0; i < list.size(); i++) {
                pic.setId(GUIDUtil.getGUID());
                pic.setReportId(id);
                pic.setFileUrl(list.get(i));
                this.roadDangerReportPicDao.insert(pic);
            }
        }
        this.dataBaseLogService.log("变更路况上报", "路况上报", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");

    }

    /**
     * @throws
     * @Title:selectRordDangerReportByPrimaryKey
     * @Description: TODO(获取路况上报详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RordDangerReport>
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public SingleResult<RoadDangerReportVo> selectRordDangerReportByPrimaryKey(String id) throws Exception {

        SingleResult<RoadDangerReportVo> rst = new SingleResult<>();
        RoadDangerReportVo o = roadDangerReportDao.selectVoByPrimaryKey(id);
        //获取上报时的现场图片
        List<RoadDangerReportPic> picList = roadDangerReportPicDao.selectNochangedMethodImg(id);
        //获取整改措施提交的图片
        List<RoadDangerReportPic> changedList = roadDangerReportPicDao.selectChangedMethodImg(id);
        o.setPicList(picList);
        o.setChangedList(changedList);
        rst.setValue(o);
        return rst;
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
     * @author: 何双
     * @version: 2019-03-15
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            RoadDangerReport o = new RoadDangerReport();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setCreateUserName(userProp.getName());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }

            int t = this.roadDangerReportDao.isExit(o);
            if (t > 0) {
                this.roadDangerReportDao.updateByPrimaryKey(o);

            } else {
                this.roadDangerReportDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("路况上报导入", "路况上报", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:deleteByPrimaryKey
     * @Description: TODO(删除路况上报 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws Exception {

        this.roadDangerReportDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除路况隐患上报", "路况隐患上报", id, id, "路况隐患上报", userProp);
        return new MessageResponse(0, "成功！");

    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {

        this.roadDangerReportDao.updateStatus(id, status);

        this.dataBaseLogService.log("跟新状态", "路况上报", id, id, "路况上报", userProp);
        return new MessageResponse(0, "成功！");
    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(撤回原因)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @Override
    public MessageResponse updateReason(String id, String status, String reason, UserProp userProp) throws Exception {

        this.roadDangerReportDao.updateReason(id, status, reason);
        this.dataBaseLogService.log("退回成功", "退回成功", id, id, "退回成功", userProp);
        return new MessageResponse(0, "成功！");
    }


    private UserProp parseUser(WxUser user) throws Exception {
        SingleResult<UserProp> rst = authorityService.getCurUserPropByOpenId(user.getUnionId());

        return rst.getValue();
    }


    /**
     * 获取路况列表 --小程序端展示
     *
     * @param user      查询用户
     * @param condition 条件查询
     * @param page      分页条件
     * @return PageResult<RoadDangerReportQVo>
     * @throws Exception
     */
    @Override
    public ResultResponse findViewList(UserProp user, RoadDangerReportQVo condition, PageParamNoChangeSord page) throws Exception {

        //获取用户id
        condition.setUserId(user.getUserId());

        PageResult<RoadDangerReportVo> rst = findRordDangerReportList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS", rst);
    }

    /**
     * 获取用户所有权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectUserRole(String userId) {
        return roadDangerReportDao.selectUserRole(userId);
    }

    /**
     * 获取用户路况权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectUserRoadRole(String userId) {

        return roadDangerReportDao.selectUserRoadRole(userId);
    }


}
