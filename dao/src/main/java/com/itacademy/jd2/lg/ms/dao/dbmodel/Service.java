package com.itacademy.jd2.lg.ms.dao.dbmodel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Service extends AbstractDbModel {
	@Column
	private String type;
	@Column
	private String unit;
	@Column
	private boolean deleted;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
	private List<TariffItem> tariff_item;

	public List<TariffItem> getTariff_item() {
		return tariff_item;
	}

	public void setTariff_item(List<TariffItem> tariff_item) {
		this.tariff_item = tariff_item;
	}

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
