package com.itacademy.jd2.lg.ms.web.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;

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