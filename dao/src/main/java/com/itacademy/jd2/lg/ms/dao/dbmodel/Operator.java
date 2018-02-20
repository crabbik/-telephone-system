package com.itacademy.jd2.lg.ms.dao.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Operator extends AbstractDbModel {
	@Column
	private String title;
	@Column
	private boolean deleted;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
