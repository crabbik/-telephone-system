package com.itacademy.jd2.lg.ms.web.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AccountDTO {
	private Integer id;
	
    @NotNull
    @Size(min = 1, max = 40)
	private String email;
    
    @NotNull
    @Size(min = 1, max = 40)
	private String password;
	private Timestamp created;
	private Timestamp modified;

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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