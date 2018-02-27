package com.itacademy.jd2.lg.ms.web.dto;

public class TariffItemDTO extends AbstractDTO {

	private Integer phoneServiceId;
	private Integer cost;
	private boolean deleted;
	private Integer tariffId;

	public Integer getPhoneServiceId() {
		return phoneServiceId;
	}

	public void setPhoneServiceId(Integer phoneServiceId) {
		this.phoneServiceId = phoneServiceId;
	}

	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
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
