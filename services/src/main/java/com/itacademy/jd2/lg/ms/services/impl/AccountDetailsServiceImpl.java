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
import com.itacademy.jd2.lg.ms.dao.filter.AccountDetailsFilter;
import com.itacademy.jd2.lg.ms.services.IAccountDetailsService;

@Service
public class AccountDetailsServiceImpl implements IAccountDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDetailsServiceImpl.class);
	@Autowired
	private IAccountDetailsDao dao;

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
			dao.insert(accountDetails);
		} else {
			dao.update(accountDetails);
		}
		return accountDetails;
	}

	@Override
	public AccountDetails get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<AccountDetails> getAll() {
		return dao.getAll();
	}

	@Override
	public Long getCount(AccountDetailsFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<AccountDetails> getAll(AccountDetailsFilter filter) {
		return dao.find(filter);
	}
}
