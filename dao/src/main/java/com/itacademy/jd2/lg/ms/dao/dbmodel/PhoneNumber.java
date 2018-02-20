package com.itacademy.jd2.lg.ms.dao.dbmodel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity
public class PhoneNumber extends AbstractDbModel {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
	private Account account;

	@Column
	private String number;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private Tariff tariff;

	@JoinTable(name = "phone_number_2_invoice", joinColumns = {
			@JoinColumn(name = "phone_number_id") }, inverseJoinColumns = { @JoinColumn(name = "invoice_id") })
	@ManyToMany(targetEntity = Invoice.class, fetch = FetchType.LAZY)
	@OrderBy("name ASC")
	private Set<Invoice> invoices = new HashSet<>();

	public Set<Invoice> getCatalogs() {
		return invoices;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Tariff getTariff() {
		return tariff;
	}

	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
