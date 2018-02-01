package com.itacademy.jd2.lg.ms.dao.dbmodel;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tariff extends AbstractDbModel implements Serializable {
	@Column
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	private Operator operator;
	@Column
	private boolean deleted;

	public String getName() {
		return name;
	}

	public void setName(String title) {
		this.name = title;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
