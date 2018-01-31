package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.dao.filter.PhoneNumberFilter;

public interface IPhoneNumberDao extends IHibernateDao<PhoneNumber, Integer> {
	Long count(PhoneNumberFilter filter);

	List<PhoneNumber> find(PhoneNumberFilter filter);

}
