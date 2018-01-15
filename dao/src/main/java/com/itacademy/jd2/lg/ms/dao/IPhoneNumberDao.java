package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;

public interface IPhoneNumberDao {
	PhoneNumber get(Integer id);

	void remove(Integer id);

	void update(PhoneNumber phoneNumber);

	int insert(PhoneNumber phoneNumber);

	List<PhoneNumber> getAll();

	List<PhoneNumber> getAll(int limit, int offset);

}
