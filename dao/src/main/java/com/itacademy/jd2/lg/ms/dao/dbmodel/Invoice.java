package com.itacademy.jd2.lg.ms.dao.dbmodel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Invoice extends AbstractDbModel implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Invoice.class)
	private Invoice parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", targetEntity = Invoice.class)
	@OrderBy("name ASC")
	private Set<Invoice> children = new HashSet<>();
	@Column
	private String type;
	@Column
	private Integer quantity;
	@Column
	private Integer sum;
	@Column
	private Integer month;
	@Column
	private Integer year;

	public Invoice getParent() {
		return parent;
	}

	public void setParent(Invoice parent) {
		this.parent = parent;
	}

	public Set<Invoice> getChildren() {
		return children;
	}

	public void setChildren(Set<Invoice> children) {
		this.children = children;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
