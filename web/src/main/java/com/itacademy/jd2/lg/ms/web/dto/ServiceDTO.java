package com.itacademy.jd2.lg.ms.web.dto;

public class ServiceDTO extends AbstractDTO {

	private String type;
	private String unit;
	private boolean deleted;

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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
