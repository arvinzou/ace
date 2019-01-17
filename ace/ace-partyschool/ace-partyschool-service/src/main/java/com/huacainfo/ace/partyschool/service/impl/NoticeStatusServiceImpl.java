package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.NoticeStatusDao;
import com.huacainfo.ace.partyschool.model.NoticeStatus;
import com.huacainfo.ace.partyschool.service.NoticeStatusService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service("noticeStatusService")
/**
 * @author: 陈晓克
 * @version: 2019-01-12
 * @Description: TODO(公告通知)
 */
public class NoticeStatusServiceImpl implements NoticeStatusService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NoticeStatusDao noticeStatusDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    @Override
    public MessageResponse updateStatus(String id, UserProp userProp) throws Exception {
        this.noticeStatusDao.updateStatus(id);
        this.dataBaseLogService.log("跟新状态", "公告通知", id, id, "公告通知", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * @throws
     * @Title:insertNoticeStatus
     * @Description: TODO(批量插入)
     * @param: @param id
     * @param: @param userIds
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-16
     */
    @Override
    public MessageResponse insertNoticeStatus(String id, String[] userIds, UserProp userProp) throws Exception {

        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            NoticeStatusDao dao = session.getMapper(NoticeStatusDao.class);
            int i = 0;
            for (String e : userIds) {
                i++;
                NoticeStatus o=new NoticeStatus();
                o.setId(GUIDUtil.getGUID());
                o.setCreateDate(new Date());
                o.setNoticeId(id);
                o.setUserId(e);
                o.setStatus("1");
                dao.insert(o);
                if (i % 200 == 0) {
                    session.commit();
                }
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            return new MessageResponse(1, "发布失败！");
        } finally {
            session.clearCache();
            session.close();
        }
        return new MessageResponse(0, "发布成功！");
    }

    /**
     * @throws
     * @Title:getPushUsersList
     * @Description: TODO(获取已经发送人员列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-01-16
     */
    @Override
    public ListResult<Map<String, Object>> getPushUsersList(String id) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult();
        rst.setValue(this.noticeStatusDao.getPushUsersList(id));
        return rst;
    }

}
