package com.itacademy.jd2.lg.ms.web.dto;

import java.util.Date;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;

public class ServiceHistoryDTO extends AbstractDTO {

	private Date data;
	private Integer quantity;
	private Integer sum;
	private PhoneNumber phone_number;
	private TariffItem tariff_item;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	public PhoneNumber getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(PhoneNumber phone_number) {
		this.phone_number = phone_number;
	}

	public TariffItem getTariff_item() {
		return tariff_item;
	}

	public void setTariff_item(TariffItem tariff_item) {
		this.tariff_item = tariff_item;
	}

}
