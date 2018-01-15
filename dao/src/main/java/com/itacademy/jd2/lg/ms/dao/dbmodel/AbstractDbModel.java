package com.itacademy.jd2.lg.ms.dao.dbmodel;

import java.sql.Timestamp;

public class AbstractDbModel {
	private Integer id;
	private Timestamp created;
	private Timestamp modified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

}
