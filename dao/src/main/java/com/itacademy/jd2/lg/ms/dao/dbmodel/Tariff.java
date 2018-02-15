package com.itacademy.jd2.lg.ms.dao.dbmodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Tariff extends AbstractDbModel {
	@Column
	private String name;
	@Column
	private boolean deleted;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "tariff")
	private PhoneNumber number;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tariff")
	private List<TariffItem> tariff_item;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Tariff.class)
	private Operator operator;

	public List<TariffItem> getTariff_item() {
		return tariff_item;
	}

	public PhoneNumber getNumber() {
		return number;
	}

	public void setNumber(PhoneNumber number) {
		this.number = number;
	}

	public void setTariff_item(List<TariffItem> tariff_item) {
		this.tariff_item = tariff_item;
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
