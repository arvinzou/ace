package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.*;
import com.huacainfo.ace.glink.api.LeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;
import com.huacainfo.ace.glink.api.pojo.le.GetBulidingDetailOut;
import com.huacainfo.ace.glink.dao.TopBuildingDao;
import com.huacainfo.ace.glink.model.TopBuilding;
import com.huacainfo.ace.glink.service.TopBuildingService;
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

}
