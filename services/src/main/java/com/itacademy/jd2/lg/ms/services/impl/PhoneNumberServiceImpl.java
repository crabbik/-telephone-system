package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IPhoneNumberDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.dao.impl.PhoneNumberDaoImpl;
import com.itacademy.jd2.lg.ms.services.IPhoneNumberService;
@Service
public class PhoneNumberServiceImpl implements IPhoneNumberService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumberServiceImpl.class);
	@Autowired
	private IPhoneNumberDao dao ;

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