package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.jxb.dao.CourseDao;
import com.huacainfo.ace.jxb.dao.LiveDao;
import com.huacainfo.ace.jxb.model.Live;
import com.huacainfo.ace.jxb.service.WWWService;
import com.huacainfo.ace.jxb.vo.LiveQVo;
import com.huacainfo.ace.jxb.vo.LiveVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("wwwService")
/**
 * @author: 陈晓克
 * @version: 2017-12-28
 * @Description: TODO(直播)
 */
public class WWWServiceImpl implements WWWService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveDao jxbDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private SqlSessionTemplate sqlSession;

    private int defaultPageSize = 10;

    /**
     * @throws
     * @Title:getLiveList
     * @Description: TODO(微网页获取直播列表)
     * @param: @param p
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-01
     */
    @Override
    public Map<String, Object> getLiveList(String companyId, int page, Map<String, Object> p) {
        Map<String, Object> rst = this.jxbDao.getLiveTotalNum(companyId);
        Long totalNum = (Long) rst.get("totalNum");
        Long totalpage = this.calPage(totalNum, this.defaultPageSize);
        rst.put("data", this.jxbDao.getLiveList(p));
        rst.put("currentpage", page);
        rst.put("pagecount", totalNum);
        rst.put("status", 0);
        rst.put("totalcount", totalNum);
        rst.put("totalpage", totalpage);
        return rst;
    }

    /**
     * @throws
     * @Title:getLive
     * @Description: TODO(微网页根据直播获取ID，直播信息信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-01
     */
    @Override
    public Map<String, Object> getLive(Map<String, Object> p) {
        return this.jxbDao.getLive(p);
    }


    /**
     * @throws
     * @Title:getLiveSubList
     * @Description: TODO(微网页根据直播间RID获取图文直播内容)
     * @param: @param p
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    @Override
    public Map<String, Object> getLiveRptList(String rid, int page, Map<String, Object> p) {
        SqlSession session = this.sqlSession.getSqlSessionFactory()
                .openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration(); //反射得到configuration ,然后
        configuration.setSafeResultHandlerEnabled(false);
        LiveDao dao = session.getMapper(LiveDao.class);
        Map<String, Object> rst = dao.getLiveRptTotalNum(rid);
        Long totalNum = (Long) rst.get("totalNum");
        Long totalpage = this.calPage(totalNum, 9999);
        rst.put("data", dao.getLiveRptList(p));
        rst.put("currentpage", page);
        rst.put("pagecount", totalNum);
        rst.put("status", 0);
        rst.put("totalcount", totalNum);
        rst.put("totalpage", totalpage);
        return rst;
    }

    /**
     * @throws
     * @Title:getLiveMsgList
     * @Description: TODO(微网页根据直播间RID获取互动内容)
     * @param: @param p
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    @Override
    public List<Map<String, Object>> getLiveMsgList(Map<String, Object> p) {
        return this.jxbDao.getLiveMsgList(p);
    }

    /**
     * @throws
     * @Title:getTotalNumAndOrgInfo
     * @Description: TODO(微网页根据单位单位代码直播获取合计直播信息)
     * @param: @param deptId
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-09
     */

    @Override
    public Map<String, Object> getTotalNumAndOrgInfo(String deptId, String id) {
        return this.jxbDao.getTotalNumAndOrgInfo(deptId, id);
    }


    /**
     * @throws
     * @Title:getTotalPageAndOrgInfo
     * @Description: TODO(微网页根据单位单位代码获取合计直播信息)
     * @param: @param deptId
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-09
     */

    @Override
    public Map<String, Object> getTotalPageAndOrgInfo(String deptId) {
        return this.jxbDao.getLiveTotalNum(deptId);
    }

    /**
     * @throws
     * @Title:getShareContent
     * @Description: TODO(微网页根据单位代码获取分享信息)
     * @param: @param deptId
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-09
     */
    @Override
    public Map<String, Object> getShareContent(String deptId,String fastdfs_server) {
        return this.jxbDao.getShareContent(deptId,fastdfs_server);
    }


    private Long calPage(Long totalNum, int defaultPageSize) {
        Long totalpage = new Long(1);
        if (totalNum % defaultPageSize == 0) {
            totalpage = totalNum / defaultPageSize;
        } else {
            totalpage = (totalNum / defaultPageSize) + 1;
        }
        return totalpage;
    }

    /**
     * @throws
     * @Title:getWxJsSign
     * @Description: TODO(微网页报道点赞)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-14
     */
    @Override
    public Map<String, Object> updateRptLikeNum(String id, String type) {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        if (type.equals("1")) {
            this.jxbDao.updateLiveLikeNum(id);
        } else {
            this.jxbDao.updateRptLikeNum(id);
        }

        return rst;
    }


    /**
     * @throws
     * @Title:updateRptVisitNum
     * @Description: TODO(微网页浏览人次)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-14
     */
    @Override
    public Map<String, Object> updateRptVisitNum(String id) {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        this.jxbDao.updateLiveVisitNum(id);
        return rst;
    }


    /**
     * @throws
     * @Title:getLiveListByUserId
     * @Description: TODO(小程序获取用户所属直播列表)
     * @param: @param p
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-04-18
     */
    @Override
    public Map<String, Object> getLiveListByUserId(String userId, int page, Map<String, Object> p){
        Map<String, Object> rst = this.jxbDao.getLiveTotalNumByUserId(userId);
        Long totalNum = (Long) rst.get("totalNum");
        Long totalpage = this.calPage(totalNum, this.defaultPageSize);
        rst.put("data", this.jxbDao.getLiveListByUserId(p));
        rst.put("currentpage", page);
        rst.put("status", 0);
        rst.put("totalcount", totalNum);
        rst.put("totalpage", totalpage);
        return rst;
    }


    /**
     * @throws
     * @Title:geCourseListByUserId
     * @Description: TODO(小程序获取用户所属课程列表)
     * @param: @param p
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-04-18
     */
    @Override
    public Map<String, Object> getCourseListByUserId(String userId, int page, Map<String, Object> p){
        Map<String, Object> rst = this.courseDao.getCourseTotalNum(userId);
        Long totalNum = (Long) rst.get("totalNum");
        Long totalpage = this.calPage(totalNum, this.defaultPageSize);
        rst.put("data", this.courseDao.getCourseList(p));
        rst.put("currentpage", page);
        rst.put("status", 0);
        rst.put("totalcount", totalNum);
        rst.put("totalpage", totalpage);
        return rst;
    }
}