package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.LeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;
import com.huacainfo.ace.glink.api.pojo.le.GetBulidingDetailOut;
import com.huacainfo.ace.glink.api.pojo.le.StrategysDetailOut;
import com.huacainfo.ace.glink.dao.LeBrokenLampDao;
import com.huacainfo.ace.glink.dao.TopBuildingDao;
import com.huacainfo.ace.glink.model.LeScene;
import com.huacainfo.ace.glink.model.TopBuilding;
import com.huacainfo.ace.glink.service.LeSceneService;
import com.huacainfo.ace.glink.service.TopBuildingService;
import com.huacainfo.ace.glink.vo.LeBrokenLampQVo;
import com.huacainfo.ace.glink.vo.LeBrokenLampVo;
import com.huacainfo.ace.glink.vo.TopBuildingQVo;
import com.huacainfo.ace.glink.vo.TopBuildingVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("topBuildingService")
/**
 * @author: luocan
 * @version: 2019-04-09
 * @Description: TODO(建筑物管理)
 */
public class TopBuildingServiceImpl implements TopBuildingService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopBuildingDao topBuildingDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private SqlSessionTemplate sqlSession;
    @Autowired
    private LeSceneService leSceneService;
    @Autowired
    private LeBrokenLampDao leBrokenLampDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(建筑物管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TopBuildingVo>
     * @author: luocan
     * @version: 2019-04-09
     */
    @Override
    public PageResult<TopBuildingVo> findTopBuildingList(TopBuildingQVo condition, int start, int limit, String orderBy) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        TopBuildingDao dao = session.getMapper(TopBuildingDao.class);
        PageResult<TopBuildingVo> rst = new PageResult<>();
        try {
            List<TopBuildingVo> list = dao.findList(condition, start, limit, orderBy);
            rst.setRows(list);
            if (rst.getTotal() <= 1) {
                int allRows = dao.findCount(condition);
                rst.setTotal(allRows);
            }
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTopBuilding
     * @Description: TODO(添加建筑物管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse insertTopBuilding(TopBuilding o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        // o.setCode(String.valueOf(GUIDUtil.getGUID().hashCode() & Integer.MAX_VALUE));
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "建筑编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "建筑名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "建筑物类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getAddress())) {
            return new MessageResponse(1, "所在地不能为空！");
        }
        if (CommonUtils.isBlank(o.getStationCode())) {
            return new MessageResponse(1, "所属站点不能为空！");
        }
        int temp = this.topBuildingDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "建筑物重复！");
        }

        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.topBuildingDao.insert(o);
        this.dataBaseLogService.log("添加建筑物管理", "建筑物管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTopBuilding
     * @Description: TODO(更新建筑物管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse updateTopBuilding(TopBuilding o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "建筑编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "建筑名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "建筑物类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getAddress())) {
            return new MessageResponse(1, "所在地不能为空！");
        }
        if (CommonUtils.isBlank(o.getStationCode())) {
            return new MessageResponse(1, "站点编码不能为空！");
        }
        o.setStatus("1");
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.topBuildingDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更建筑物管理", "建筑物管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTopBuildingByPrimaryKey
     * @Description: TODO(获取建筑物管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopBuilding>
     * @author: luocan
     * @version: 2019-04-09
     */
    @Override
    public SingleResult<TopBuildingVo> selectTopBuildingByPrimaryKey(String id) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        TopBuildingDao dao = session.getMapper(TopBuildingDao.class);
        SingleResult<TopBuildingVo> rst = new SingleResult<>();
        try {
            TopBuildingVo vo = dao.selectVoByPrimaryKey(id);
            rst.setValue(vo);
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTopBuildingByTopBuildingId
     * @Description: TODO(删除建筑物管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse deleteTopBuildingByTopBuildingId(String id, UserProp userProp) throws
            Exception {
        try {
            this.topBuildingDao.deleteByPrimaryKey(id);
            this.dataBaseLogService.log("删除建筑物管理", "建筑物管理", id, id,
                    "建筑物管理", userProp);
            return new MessageResponse(0, "删除成功！");
        } catch (Exception e) {
            return new MessageResponse(0, "删除失败！" + e);
        }

    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核建筑物管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核建筑物管理", "建筑物管理", id, id,
                "建筑物管理", userProp);
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
     * @author: luocan
     * @version: 2019-04-09
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            TopBuilding o = new TopBuilding();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setCreateUserName(userProp.getName());
            o.setId(GUIDUtil.getGUID());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (CommonUtils.isBlank(o.getCode())) {
                return new MessageResponse(1, "建筑编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "建筑名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getType())) {
                return new MessageResponse(1, "建筑物类型不能为空！");
            }
            if (CommonUtils.isBlank(o.getAddress())) {
                return new MessageResponse(1, "所在地不能为空！");
            }
            if (CommonUtils.isBlank(o.getStationCode())) {
                return new MessageResponse(1, "站点编码不能为空！");
            }
            int t = this.topBuildingDao.isExit(o);
            if (t > 0) {
                this.topBuildingDao.updateByPrimaryKey(o);

            } else {
                this.topBuildingDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("建筑物管理导入", "建筑物管理", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.topBuildingDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "建筑物管理", id, id,
                "建筑物管理", userProp);
        return new MessageResponse(0, "成功！");
    }

    @Override
    public MessageResponse syncData(UserProp curUserProp) {
        //接口数据获取
        GetBulidingDetailOut out;
        try {
            out = LeApiToolKit.getBuildingDetail("");
        } catch (Exception e) {
            return new MessageResponse(ResultCode.FAIL, "接口通讯异常");
        }
        if (out.getCode() == LeBaseOut.FAILED || CollectionUtils.isEmpty(out.getData())) {
            return new MessageResponse(ResultCode.FAIL, "接口获取数据失败");
        }
        //清空库存数据
        topBuildingDao.clearAll();
        //存放新获取的数据
        List<GetBulidingDetailOut.BulidingDetail> data = out.getData();
        TopBuilding r;
        for (GetBulidingDetailOut.BulidingDetail item : data) {
            r = new TopBuilding();
            r.setId(GUIDUtil.getGUID());
            r.setCode(item.getBuildingNo());
            r.setName(item.getBuildingName());
            r.setType("1");
            r.setDepict(item.getBuildingName());
            r.setAddress(item.getBuildingAddress());
            r.setLatitude(StringUtil.isEmpty(item.getBuildingY()) ? BigDecimal.ZERO : new BigDecimal(item.getBuildingY()));
            r.setLongitude(StringUtil.isEmpty(item.getBuildingX()) ? BigDecimal.ZERO : new BigDecimal(item.getBuildingX()));
            r.setState(item.getStatus());
            //
            r.setStatus("1");
            r.setCreateDate(DateUtil.getNowDate());
            r.setCreateUserId(curUserProp.getUserId());
            r.setCreateUserName(curUserProp.getName());

            topBuildingDao.insert(r);
        }

        return new MessageResponse(ResultCode.SUCCESS, "同步数据成功");
    }

    /**
     * 获取GIS地图建筑物信息
     *
     * @param buildingCode 建筑物编码
     * @return SingleResult<TopBuildingGISVo>
     */
    @Override
    public ResultResponse getGISInfo(String buildingCode) {
        //建筑物主体信息
        TopBuildingVo b = this.topBuildingDao.findByCode(buildingCode);
        if (b == null) {
            return new ResultResponse(ResultCode.FAIL, "建筑物信息不存在");
        }
        LeBrokenLampQVo leBrokenLampQVo = new LeBrokenLampQVo();
        leBrokenLampQVo.setBuildingNo(buildingCode);
        leBrokenLampQVo.setToday("yes");
        List<LeBrokenLampVo> list = leBrokenLampDao.findList(leBrokenLampQVo, 0, 100, "createDate desc");
        //通过弱电接口，获取建筑物状态；
        GetBulidingDetailOut out = LeApiToolKit.getBuildingDetail(buildingCode);
        GetBulidingDetailOut.BulidingDetail onlineBuilding = out.getData().get(0);
        //
        String isPlaying = "0";
        String sceneNum = "";
        String ctrlCount = "0";
        String lampCount = "0";
        String buildingName = b.getName();
        String address = b.getAddress();
        String buildingNo = buildingCode;
        String status = "0";
        if (onlineBuilding != null) {
            isPlaying = onlineBuilding.getIsPlaying();
            sceneNum = onlineBuilding.getIsPlayingStrategy();
            ctrlCount = onlineBuilding.getControllerCount();
            lampCount = onlineBuilding.getLampCount();
            buildingName = onlineBuilding.getBuildingName();
            address = onlineBuilding.getBuildingAddress();
            buildingNo = onlineBuilding.getBuildingNo();
            status = onlineBuilding.getStatus();
        }
        //策略播放预览文件
        String playURL = "";
        String coverURL = "";
        if (StringUtil.isNotEmpty(sceneNum)) {
            LeScene scene = leSceneService.findBySceneNum(sceneNum);
            playURL = scene == null ? "" : scene.getPlayUrl();
            coverURL = scene == null ? "" : scene.getViewUrl();
        }

        //返回数据体
        Map<String, Object> rst = new HashMap<>();
        //是否正在播放 1：正在播放，0：未播放，
        rst.put("isPlaying", isPlaying);
        //正在播放的文件 策略编号
        rst.put("sceneNum", sceneNum);
        //视频封面地址
        rst.put("coverURL", coverURL);
        //视频播放地址
        rst.put("playURL", playURL);
        //控制器数量
        rst.put("ctrlCount", ctrlCount);
        //设备总数
        rst.put("lampCount", lampCount);
        //建筑物名称
        rst.put("buildingName", buildingName);
        //所在地
        rst.put("address", address);
        //建筑物编号
        rst.put("buildingNo", buildingNo);
        //建筑物状态 1：在线，0：离线
        rst.put("status", status);
        //错误列表
        rst.put("le", list);
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", rst);
    }

    /***
     * 获取当前播放策略
     *
     * @return 策略编号
     */
    private String getCurrentSceneNum() {
        StrategysDetailOut out;
        try {
            out = LeApiToolKit.strategysDetail();
        } catch (Exception e) {
            return "error";
        }
        if (out == null || CollectionUtils.isEmpty(out.getData())) {
            return "error";
        }
        for (StrategysDetailOut.Strategy item : out.getData()) {
            if (1 == item.getIsPlaying()) {
                return item.getStrategyNum();
            }
        }

        return "error";
    }

}
