package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.dao.filter.PhoneNumberFilter;

public interface IPhoneNumberService {
	PhoneNumber get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	PhoneNumber save(PhoneNumber phoneNumber);

	List<PhoneNumber> getAll();

	Long getCount(PhoneNumberFilter filter);

	List<PhoneNumber> getAll(PhoneNumberFilter filter);

}
