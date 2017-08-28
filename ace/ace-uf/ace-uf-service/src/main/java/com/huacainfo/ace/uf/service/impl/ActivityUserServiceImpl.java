package com.huacainfo.ace.uf.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.uf.dao.ActivityUserDao;
import com.huacainfo.ace.uf.model.ActivityUser;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.service.ActivityUserService;
@Service("activityUserService")
public class ActivityUserServiceImpl implements ActivityUserService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActivityUserDao activityUserDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Map<String, Object>> selectListByActivityId(String id) throws Exception {
		return this.activityUserDao.selectListByActivityId(id);
	}

	public MessageResponse insertActivityUser(List<ActivityUser> list, UserProp userProp) throws Exception {
		SqlSession session = this.sqlSession.getSqlSessionFactory()
				.openSession(ExecutorType.BATCH, false);
		if(list.isEmpty()){
			return new MessageResponse(1, "提交的数据为空！");
		}
		try {
			ActivityUserDao activityUserDao = session.getMapper(ActivityUserDao.class);
			activityUserDao.deletePersonageByActivityId(list.get(0).getActivityId());
			int i=0;
			for (ActivityUser o:list) {
				o.setId(UUID.randomUUID().toString());
				o.setCreateDate(new Date());
				activityUserDao.insert(o);
				i++;
				if(i%200==0){
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
		return new MessageResponse(0, "添加参与人员完成！");
	}

	public MessageResponse deleteActivityUserByActivityUserId(String id, UserProp userProp) throws Exception {
		this.activityUserDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除参与人员", "参与人员", String.valueOf(id), String.valueOf(id), "参与人员", userProp);
		return new MessageResponse(0, "参与人员删除完成！");
	}
}
