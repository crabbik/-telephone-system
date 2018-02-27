package com.itacademy.jd2.lg.ms.web.dto;

import java.util.HashSet;
import java.util.Set;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;

public class PhoneNumberDTO extends AbstractDTO {

	private Integer accountId;
	private String number;
	private Integer tariffId;
	private Set<Invoice> invoices = new HashSet<>();

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

}
