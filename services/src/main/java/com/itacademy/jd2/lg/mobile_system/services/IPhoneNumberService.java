package com.itacademy.jd2.lg.mobile_system.services;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.PhoneNumber;

public interface IPhoneNumberService {
	PhoneNumber get(Integer id);

	void remove(Integer id);

	PhoneNumber save(PhoneNumber phoneNumber);

	List<PhoneNumber> getAll();

	List<PhoneNumber> getAll(int limit, int offset);

}
