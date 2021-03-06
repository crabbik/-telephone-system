package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IAccountDetailsDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.dao.impl.AccountDetailsDaoImpl;
import com.itacademy.jd2.lg.ms.services.IAccountDetailsService;
@Service
public class AccountDetailsServiceImpl implements IAccountDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDetailsServiceImpl.class);
	@Autowired
	private IAccountDetailsDao dao;

	@Override
	public AccountDetails get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public AccountDetails save(AccountDetails accountDetails) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		accountDetails.setModified(modifiedDate);
		if (accountDetails.getId() == null) {
			accountDetails.setCreated(modifiedDate);
			int id = dao.insert(accountDetails);
			accountDetails.setId(id);
		} else {
			dao.update(accountDetails);
		}
		return accountDetails;
	}

	@Override
	public List<AccountDetails> getAll() {
		return dao.getAll();
	}

	@Override
	public List<AccountDetails> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}