package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.IPhoneNumberDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.mobile_system.dao.impl.PhoneNumberDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IPhoneNumberService;

public class PhoneNumberServiceImpl implements IPhoneNumberService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumberServiceImpl.class);
	public static final IPhoneNumberService PHONENUMBER_SERVICE = new PhoneNumberServiceImpl();

	private PhoneNumberServiceImpl() {

	}

	private IPhoneNumberDao dao = PhoneNumberDaoImpl.PHONENUMBER_DAO;

	@Override
	public PhoneNumber get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public PhoneNumber save(PhoneNumber phoneNumber) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		phoneNumber.setModified(modifiedDate);
		if (phoneNumber.getId() == null) {
			phoneNumber.setCreated(modifiedDate);
			int id = dao.insert(phoneNumber);
			phoneNumber.setId(id);
		} else {
			dao.update(phoneNumber);
		}
		return phoneNumber;
	}

	@Override
	public List<PhoneNumber> getAll() {
		return dao.getAll();
	}

	@Override
	public List<PhoneNumber> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}