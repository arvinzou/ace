package com.huacainfo.ace.portal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.CheckTree;
import com.huacainfo.ace.common.redis.AspireRedisTemplate;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.CKTreeUtils;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;
import com.huacainfo.ace.portal.dao.RoleDao;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.ResourcesService;
import com.huacainfo.ace.portal.service.RoleService;
import com.huacainfo.ace.portal.vo.RoleVo;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private ResourcesService resourceService;

	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Autowired
	private SqlSessionTemplate sqlSession;
	@Override
	public PageResult<RoleVo> findRoleList(Role condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<RoleVo> rst = new PageResult<RoleVo>();
		List<RoleVo> list = this.roleDao.findRoleList(condition, start, start
				+ limit, orderBy);
		rst.setRows(list);
		int allRows = this.roleDao.findRoleCount(condition);
		rst.setTotal(allRows);
		rst.setStatus(0);
		return rst;
	}
	@Override
	public MessageResponse insertRole(Role role, UserProp userProp)
			throws Exception {

		if (CommonUtils.isBlank(role.getRoleName())) {

			return new MessageResponse(1, "角色名称不能为空！");
		}
		role.setRoleId(UUID.randomUUID().toString());
		role.setCreateUserId(userProp.getUserId());
		role.setSyid(userProp.getActiveSyId());
		role.setCreateTime(new Date());
		int temp = this.roleDao.isExitRoleByRoleName(role.getRoleName());
		if (temp > 0) {

			return new MessageResponse(1, "角色名称已存在！");
		}
		this.roleDao.insertRole(role);
		this.dataBaseLogService.log("添加新角色", "角色", "", role.getRoleName(),
				role.getRoleName(), userProp);
		return new MessageResponse(0, "添加新角色完成！");
	}
	@Override
	public MessageResponse updateRole(Role role, UserProp userProp)
			throws Exception {

		if (CommonUtils.isBlank(role.getRoleId())) {

			return new MessageResponse(1, "角色编号不能为空！");
		}

		if (CommonUtils.isBlank(role.getRoleName())) {

			return new MessageResponse(1, "角色名称不能为空！");
		}
		if(CommonUtils.isBlank(role.getSyid())){
			role.setSyid(userProp.getActiveSyId());
		}
		this.roleDao.updateRoleByPrimaryKey(role);

		this.dataBaseLogService.log("角色信息变更", "角色", "", role.getRoleName(),
				role.getRoleId(), userProp);

		return new MessageResponse(0, "角色信息变更完成！");
	}
	@Override
	public MessageResponse insertRoleResources(String roleId,
			String[] resourcesId, UserProp userProp) throws Exception {
		SqlSession session = this.sqlSession.getSqlSessionFactory()
				.openSession(ExecutorType.BATCH, false);
		try {
			RoleDao roleDao = session.getMapper(RoleDao.class);
			roleDao.deleteRoleResources(roleId);
			int i=0;
			for (String resId : resourcesId) {
				roleDao.insertRoleResources(roleId, resId);
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
		List<Map<String, String>> list =resourceService.loadResourceDefine();
		AspireRedisTemplate redisTemplateString = (AspireRedisTemplate) SpringUtils
				.getBean("redisTemplateString");
		WebUtils.flushRoleResourceCache(redisTemplateString, list);
		this.logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>flushRoleResourceCache>>>>>>>>>>>>>>>>>>>>>>>>>");
		this.dataBaseLogService.log("角色权限变更", "角色权限", "", "", roleId, userProp);
		return new MessageResponse(0, "权限分配完成！");
	}
	@Override
	public SingleResult<RoleVo> selectRoleByPrimaryKey(String roleId)
			throws Exception {
		SingleResult<RoleVo> rst = new SingleResult<RoleVo>();
		RoleVo roleVo = this.roleDao.selectRoleVoByPrimaryKey(roleId);
		rst.setValue(roleVo);
		rst.setStatus(0);
		return rst;
	}
	@Override
	public List<CheckTree> selectRoleResourceTreeList(String roleId) {
		CKTreeUtils checkTreeUtils = new CKTreeUtils(
				this.roleDao.selectRoleResourceTreeList(roleId));
		return checkTreeUtils.getCheckTreeList("0");
	}
	@Override
	public MessageResponse deleteRoleByRoleId(String roleId, UserProp userProp)
			throws Exception {
		if (roleId.indexOf(",") != -1) {
			String[] role = roleId.split(",");
			for (String id : role) {
				if (this.roleDao.isExitRoleUsedByRoleId(id) > 0) {
					RoleVo rv = this.roleDao.selectRoleVoByPrimaryKey(id);
					return new MessageResponse(1, "已有用户使用角色:"
							+ rv.getRoleName() + "，请先解除绑定！");
				} else {
					this.roleDao.deleteRoleByRoleId(id);
				}
			}
		} else {
			if (this.roleDao.isExitRoleUsedByRoleId(roleId) > 0) {
				RoleVo rv = this.roleDao.selectRoleVoByPrimaryKey(roleId);

				return new MessageResponse(1, "已有用户使用角色:" + rv.getRoleName()
						+ "，请先解除绑定！");
			} else {
				this.roleDao.deleteRoleByRoleId(roleId);
			}

		}
		this.dataBaseLogService.log("删除角色", "角色", "", "", roleId, userProp);
		return new MessageResponse(0, "删除角色完成！");
	}
}
