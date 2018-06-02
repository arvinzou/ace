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
import org.springframework.data.redis.core.RedisOperations;
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

	@Autowired
	private RedisOperations<String,Object> redisTemplate;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		this.logger.info("============================>{}",username);
		Users syUser =null;
		String account=null;
		String passwd=null;
		if(username!=null&&username.length()==36){
			String code =(String) redisTemplate.opsForValue().get(username);
			this.logger.info("微信扫描登录 ============================>{}",code);
			try {
				syUser = systemService.selectUsersByCode(code, null);
				if(syUser != null){
					account=username;
					passwd="4297f44b13955235245b2497399d7a93";
				}
			}catch (Exception e){
				this.logger.error("{}",e);
			}
		}else{
			syUser = systemService.selectUsersByAccount(username);
			if(syUser != null){
				account=syUser.getAccount();
				passwd=syUser.getPassword();
			}
		}
		Collection<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		this.logger.info("========================================================");
		if (syUser != null) {
			this.logger.info("============================>开始登录挂载权限信息：{}",username);
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
			logger.info("===========================加载用户信息开始=====================================");
			BasicUsers o = new BasicUsers(syUser.getUserId(),
					passwd, account,
					syUser.getName(), syUser.getName(),
					syUser.getDepartmentId(), syUser.getCorpName(),
					syUser.getAreaCode(), syUser.getStauts().equals("1"), true,
					true, true, auths, roleType, syUser.getParentCorpId(),
					syUser.getEmail(), syUser.getAccount(), role, syid, syUser.getCurSyid(),cfg,syUser.getOpenId(),syUser.getAppOpenId());
			logger.info("============加载用户信息完成============:{}", o);
			return o;
		} else {
			return new BasicUsers("0", "default", "default", "default",
					"default", "default", "default", "default", false, true,
					true, false, auths, null, "default", null, null, null,
					null, null,null,null,null);
		}
	}
}
