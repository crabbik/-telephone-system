package com.itacademy.jd2.lg.ms.web.dto;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Role;

public class AccountDTO extends AbstractDTO {

	@NotNull
	@Size(min = 1, max = 40)
	private String email;

	@NotNull
	@Size(min = 1, max = 40)
	private String password;

	@NotNull
	@Valid
	private AccountDetailsDTO details = new AccountDetailsDTO();
	
	private Set<Role> roles;

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public AccountDetailsDTO getDetails() {
		return details;
	}

	public void setDetails(AccountDetailsDTO details) {
		this.details = details;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}