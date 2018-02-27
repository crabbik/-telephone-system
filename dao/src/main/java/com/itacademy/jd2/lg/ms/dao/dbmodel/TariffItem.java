package com.itacademy.jd2.lg.ms.dao.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class TariffItem extends AbstractDbModel {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PhoneService.class)
	private PhoneService phoneService;
	@Column
	private Integer cost;
	@Column
	private boolean deleted;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Tariff.class)
	private Tariff tariff;

	public PhoneService getPhoneservice() {
		return phoneService;
	}

	public void setPhoneservice(PhoneService phoneservice) {
		this.phoneService = phoneservice;
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
