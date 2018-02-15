package com.itacademy.jd2.lg.ms.dao.dbmodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class ServiceHistory extends AbstractDbModel {
	@Column
	private Date data;

	@Column
	private Integer quantity;
	@Column
	private Integer sum;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ServiceHistory.class)
	private PhoneNumber phone_number;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ServiceHistory.class)
	private TariffItem tariff_item;

	public TariffItem getTariff_item() {
		return tariff_item;
	}

	public void setTariff_item(TariffItem tariff_item) {
		this.tariff_item = tariff_item;
	}

	public PhoneNumber getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(PhoneNumber phone_number) {
		this.phone_number = phone_number;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date date) {
		this.data = date;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

}
