package com.itacademy.jd2.lg.ms.dao.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Tariff extends AbstractDbModel {
	@Column
	private String name;
	@Column
	private boolean deleted;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "tariff")
	private PhoneNumber number;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Operator.class)
	private Operator operator;

	public PhoneNumber getNumber() {
		return number;
	}

	public void setNumber(PhoneNumber number) {
		this.number = number;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getName() {
		return name;
	}

	public void setName(String title) {
		this.name = title;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
