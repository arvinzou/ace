package com.huacainfo.ace.taa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.dao.RoadGpsDao;
import com.huacainfo.ace.taa.model.RoadGps;
import com.huacainfo.ace.taa.service.RoadGpsService;
import com.huacainfo.ace.taa.vo.RoadGpsVo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service("roadGpsService")
/**
 * @author: 陈晓克
 * @version: 2019-01-08
 * @Description: TODO(GPS)
 */
public class RoadGpsServiceImpl implements RoadGpsService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadGpsDao roadGpsDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * @throws
     * @Title:insertRoadGps
     * @Description: TODO(添加GPS)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse insertRoadGps(List<RoadGps> list, UserProp userProp) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            RoadGpsDao dao = session.getMapper(RoadGpsDao.class);
            int i = 0;
            for (RoadGps o : list) {
                i++;
                o.setId(GUIDUtil.getGUID());
                o.setCreateDate(new Date());
                o.setCreateUserName(userProp.getName());
                o.setCreateUserId(userProp.getUserId());
                dao.insert(o);
                if (i % 200 == 0) {
                    session.commit();
                }
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.clearCache();
            session.close();
        }
        return new MessageResponse(0, "保存成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map < String, Object>>
     * @author: 陈晓克
     * @version: 2019-01-08
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.roadGpsDao.getList(p));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除GPS
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse deleteRoadGpsByRoadGpsIds(String[] id, UserProp userProp) throws Exception {
        this.roadGpsDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除GPS", "GPS", id[0], id[0], "GPS", userProp);
        return new MessageResponse(0, "删除成功！");

    }

    /**
     * 获取最近路段信息
     *
     * @param lat    纬度坐标
     * @param lon    经度坐标
     * @param radius 扫描半径距离，单位：米
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse getCloseRoadSection(double lat, double lon, int radius) {
////        坐标范围
        Map<String, Object> p = new HashMap<>();
        p.put("lat", lat);
        p.put("lng", lon);
//      筛选数据
        List<RoadGpsVo> list = roadGpsDao.getAroundList(p);
        if (CollectionUtils.isEmpty(list)) {
            RoadGpsVo undefined = roadGpsDao.getUndefined();
            list = new ArrayList<>();
            list.add(undefined);
//            return new ResultResponse(ResultCode.FAIL, "没找到匹配数据");
        }
        //区分去取来的数据是否为同一路段
        List<RoadGpsVo> rows = new LinkedList<>();
        rows.add(list.get(0));
        if (list.size() > 1 &&
                !list.get(0).getRoadSectionId().equals(list.get(1).getRoadSectionId())) {
            rows.add(list.get(1));
        }

        PageResult<RoadGpsVo> rst = new PageResult<>();
        rst.setTotal(rows.size());
        rst.setRows(rows);
        return new ResultResponse(ResultCode.SUCCESS, "success", rst);
    }

    /**
     * 重置路段采集数据
     *
     * @param sectionId 路段ID
     * @param userProp  操作员
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public MessageResponse resetSectionGPS(String sectionId, UserProp userProp) {
        int i = roadGpsDao.deleteBySectionId(sectionId);

        dataBaseLogService.log("路段", "路段重置", sectionId, sectionId, userProp.getName(), userProp);

        return new MessageResponse(ResultCode.SUCCESS, "重置成功");
    }

}
