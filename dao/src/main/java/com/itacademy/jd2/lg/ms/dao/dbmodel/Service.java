package com.itacademy.jd2.lg.ms.dao.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Service extends AbstractDbModel {
	@Column
	private String type;
	@Column
	private String unit;
	@Column
	private boolean deleted;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
