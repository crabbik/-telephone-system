package com.itacademy.jd2.lg.ms.web.dto;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Role;

public class User2RoleDTO {

	private Integer accountId;
	private Role role; // enum

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
