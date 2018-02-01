package com.itacademy.jd2.lg.ms.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AbstractDTO {

	private Integer id;

	private Date created;

	private Date modified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

}
