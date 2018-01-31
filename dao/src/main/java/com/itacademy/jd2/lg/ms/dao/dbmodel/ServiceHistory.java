package com.itacademy.jd2.lg.ms.dao.dbmodel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceHistory extends AbstractDbModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Date data;
	@Column
	private Integer tariffItemId;
	@Column
	private Integer quantity;
	@Column
	private Integer sum;
	@Column
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
