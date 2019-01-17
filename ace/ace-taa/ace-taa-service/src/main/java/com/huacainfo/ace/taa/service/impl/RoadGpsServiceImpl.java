package com.huacainfo.ace.taa.service.impl;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.dao.RoadGpsDao;
import com.huacainfo.ace.taa.model.RoadGps;
import com.huacainfo.ace.taa.service.RoadGpsService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * @return: ListResult<Map<String,Object>>
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

}
