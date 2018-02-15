package com.itacademy.jd2.lg.ms.dao.dbmodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TariffItem extends AbstractDbModel {
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Service service;
	@Column
	private Integer cost;
	@Column
	private boolean deleted;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ServiceHistory.class)
	private Tariff tariff;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tariff_item")
	private List<ServiceHistory> serviceHistory;

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

	public List<ServiceHistory> getServiceHistory() {
		return serviceHistory;
	}

	public void setServiceHistory(List<ServiceHistory> serviceHistory) {
		this.serviceHistory = serviceHistory;
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
