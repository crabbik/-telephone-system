package com.itacademy.jd2.lg.mobile_system.dao.dbmodel;

import java.util.Date;

public class ServiceHistory {
	private Integer id;
	private Date data;
	private Integer tariffItemId;
	private Integer quantity;
	private Integer sum;
	private Integer phoneNumberId;

	public Integer getTariffItemId() {
		return tariffItemId;
	}

	public void setTariffItemId(Integer tariffItemId) {
		this.tariffItemId = tariffItemId;
	}

	public Integer getPhoneNumberId() {
		return phoneNumberId;
	}

	public void setPhoneNumberId(Integer phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
