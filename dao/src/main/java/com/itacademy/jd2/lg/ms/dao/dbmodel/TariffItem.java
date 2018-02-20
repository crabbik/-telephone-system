package com.itacademy.jd2.lg.ms.dao.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class TariffItem extends AbstractDbModel {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Service.class)
	private Service service;
	@Column
	private Integer cost;
	@Column
	private boolean deleted;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Tariff.class)
	private Tariff tariff;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Tariff getTariff() {
		return tariff;
	}

	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
