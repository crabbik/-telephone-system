package com.itacademy.jd2.lg.ms.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountDetailsDTO {

	@NotNull
	@Size(min = 1, max = 40)
	private String lastName;
	@NotNull
	private String firstName;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
