package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.mobile_system.dao.IAccountDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Account;
import com.itacademy.jd2.lg.mobile_system.dao.impl.AccountDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IAccountService;
@Service
public class AccountServiceImpl implements IAccountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	@Autowired
	private IAccountDao dao ;

	@Override
	public Account get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public Account save(Account account) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		account.setModified(modifiedDate);
		if (account.getId() == null) {
			account.setCreated(modifiedDate);
			int id = dao.insert(account);
			account.setId(id);
		} else {
			dao.update(account);
		}
		return account;
	}

	@Override
	public List<Account> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Account> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}