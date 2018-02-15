package com.itacademy.jd2.lg.ms.services.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IAccountDao;
import com.itacademy.jd2.lg.ms.dao.IAccountDetailsDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.dao.filter.AccountFilter;
import com.itacademy.jd2.lg.ms.services.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private IAccountDao dao;

	@Autowired
	private IAccountDetailsDao detailsDao;

	@Override
	public void remove(final Integer id) {
		if (detailsDao.get(id) != null) {
			detailsDao.remove(id);
		}
		dao.remove(id);
	}

	@Override
	public Account save(Account account) {
		Date modifiedDate = new Date();
		account.setModified(modifiedDate);
		if (account.getId() == null) {
			account.setCreated(modifiedDate);
			dao.insert(account);
		} else {
			dao.update(account);
		}
		return account;
	}

	@Override
	public Account saveWithDetails(final Account account) {
		final AccountDetails details = account.getDetails();

		final Date modifiedDate = new Date();
		account.setModified(modifiedDate);
		details.setModified(modifiedDate);

		if (account.getId() == null) {

			account.setDetails(null); // temporary reset before 1st save
			account.setCreated(modifiedDate);
			dao.insert(account);

			details.setId(account.getId());
			details.setCreated(modifiedDate);
			details.setAccount(account);
			detailsDao.insert(details);

			account.setDetails(details);
		} else {
			dao.update(account);
			detailsDao.update(details);
		}
		return account;
	}

	@Override
	public Account get(Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(AccountFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<Account> getAll(AccountFilter filter) {
		return dao.find(filter);
	}

	@Override
	public AccountDetails getDetails(final Integer id) {
		return detailsDao.get(id);
	}
}
