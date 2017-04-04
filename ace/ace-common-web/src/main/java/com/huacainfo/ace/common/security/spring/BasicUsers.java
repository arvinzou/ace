package com.huacainfo.ace.common.security.spring;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.huacainfo.ace.common.model.UserProp;

public class BasicUsers extends UserProp implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String password;
	private String username;
	private boolean accountNonExpired;
	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	private boolean enabled;
	private Collection<GrantedAuthority> authorities;

	public BasicUsers() {

	}


	public BasicUsers(String userId, String password, String username,
			String trueName, String nickName, String corpId, String corpName,
			String areaCode, boolean accountNonExpired,
			boolean accountNonLocked, boolean credentialsNonExpired,
			boolean enabled, Collection<GrantedAuthority> authorities,
			List<String> roleType, String parentCorpId,String email,String account,List<String> role, String[] syid, String activeSyId) {
		super();
		this.password = password;
		this.username = username;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.authorities = authorities;
		setUserId(userId);
		setCorpId(corpId);
		setName(trueName);
		setNickName(nickName);
		setCorpId(corpId);
		setCorpName(corpName);
		setAreaCode(areaCode);
		setRoleType(roleType);
		setParentCorpId(parentCorpId);
		setEmail(email);
		setAccount(account);
		setRole(role);
		setSyid(syid);
		setActiveSyId(activeSyId);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities == null ? new HashSet<GrantedAuthority>(0)
				: authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public boolean belongToRole(String role) {
		if (authorities == null) {
			return false;
		}
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.equals(role)) {
				return true;
			}
		}
		return false;
	}
}
