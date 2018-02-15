package com.itacademy.jd2.lg.ms.dao.dbmodel;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Account extends AbstractDbModel {

	@Column
	private String email;
	@Column
	private String password;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
	private AccountDetails details;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private List<PhoneNumber> numbers;

	public AccountDetails getDetails() {
		return details;
	}

	public void setDetails(AccountDetails details) {
		this.details = details;
	}

	public List<PhoneNumber> getBooks() {
		return numbers;
	}

	public List<PhoneNumber> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<PhoneNumber> numbers) {
		this.numbers = numbers;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "account_2_role", joinColumns = @JoinColumn(name = "account_id"))
	@Column(name = "role_id")
	@Enumerated(EnumType.ORDINAL)
	private Set<Role> roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
