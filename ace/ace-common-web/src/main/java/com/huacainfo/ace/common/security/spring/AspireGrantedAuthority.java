package com.huacainfo.ace.common.security.spring;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

public class AspireGrantedAuthority implements GrantedAuthority, Serializable {
	private static final long serialVersionUID = 1L;
	private String role;

	public AspireGrantedAuthority() {

	}

	public AspireGrantedAuthority(String role) {
		Assert.hasText(role,
				"A granted authority textual representation is required");
		this.role = role;
	}

	public boolean equals(Object obj) {
		if (obj instanceof String) {
			return obj.equals(role);
		}

		if (obj instanceof GrantedAuthority) {
			GrantedAuthority attr = (GrantedAuthority) obj;

			return role.equals(attr.getAuthority());
		}

		return false;
	}

	public String getAuthority() {
		return role;
	}

	public int hashCode() {
		return role.hashCode();
	}

	public String toString() {
		return role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
