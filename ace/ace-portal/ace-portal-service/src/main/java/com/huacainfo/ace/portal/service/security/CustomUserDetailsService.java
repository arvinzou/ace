package com.huacainfo.ace.portal.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.security.spring.AspireGrantedAuthority;
import com.huacainfo.ace.common.security.spring.BasicUsers;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.SystemService;

/**
 * 该类的主要作用是为Spring Security提供一个经过用户认证后的UserDetails。
 * 该UserDetails包括用户名、密码、是否可用、是否过期等信息。 sparta 11/3/29
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SystemService systemService;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Users syUser = systemService.selectUsersByAccount(username);
		Collection<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		if (syUser != null) {
			List<Map<String, String>> roles = this.systemService
					.selectRoleListByUserId(syUser.getUserId());
			List<String> role = new ArrayList<String>();
			for (Map<String, String> o : roles) {
				GrantedAuthority e = new AspireGrantedAuthority(o.get("ROLE"));
				auths.add(e);
				role.add(o.get("id"));
			}
			List<String> roleType = systemService
					.selectRoleTypeListByUserId(syUser.getUserId());
			String[] syid = systemService
					.selectSyidByUserId(syUser.getUserId());
			if(CommonUtils.isBlank(syid)){
				syid=new String[]{"sys"};
			}
			if(CommonUtils.isBlank(syUser.getCurSyid())){
				syUser.setCurSyid(syid[0]);
			}
			Map<String,Object> cfg=this.systemService.selectUserCfgByUserId(syUser.getUserId());
			this.logger.info("授予用户：{}的角色列表：{} 归属社保类型 {} 系统 {}",
					syUser.getAccount(), auths, roleType, syid);
			this.logger
					.info("============load UserProp from db===============");
			BasicUsers o = new BasicUsers(syUser.getUserId(),
					syUser.getPassword(), syUser.getAccount(),
					syUser.getName(), syUser.getName(),
					syUser.getDepartmentId(), syUser.getCorpName(),
					syUser.getAreaCode(), syUser.getStauts().equals("1"), true,
					true, true, auths, roleType, syUser.getParentCorpId(),
					syUser.getEmail(), syUser.getAccount(), role, syid, syUser.getCurSyid(),cfg,syUser.getOpenId());
			logger.info("加载用户信息:{}", o);
			return o;
		} else {
			return new BasicUsers("0", "default", "default", "default",
					"default", "default", "default", "default", false, true,
					true, false, auths, null, "default", null, null, null,
					null, null,null,null);
		}
	}
}
