package com.itacademy.jd2.lg.ms.web.dto;

public class ServiceHistoryDTO extends AbstractDTO {

	private Integer quantity;
	private Integer sum;
	private Integer phoneNumberId;
	private Integer tariffItemId;

	public Integer getPhoneNumberId() {
		return phoneNumberId;
	}

	public void setPhoneNumberId(Integer phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}

	public Integer getTariffItemId() {
		return tariffItemId;
	}

	public void setTariffItemId(Integer tariffItemId) {
		this.tariffItemId = tariffItemId;
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
