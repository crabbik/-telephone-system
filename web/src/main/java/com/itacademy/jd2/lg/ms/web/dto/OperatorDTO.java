package com.itacademy.jd2.lg.ms.web.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;

public class OperatorDTO extends AbstractDTO {

	@NotNull
	@Size(min = 1, max = 40)
	private String title;

	private boolean deleted;

	private List<Tariff> tariff;

	public void setTariff(List<Tariff> tariff) {
		this.tariff = tariff;
	}

	public List<Tariff> getTariff() {
		return tariff;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
