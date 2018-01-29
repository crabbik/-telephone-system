package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IAccountDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.services.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	@Autowired
	private IAccountDao dao;

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
	public Integer getCount() {
		return getAll().size(); // FIXME: it is invalid implementation. use the
								// 'select count from...' SQL query from DAO
								// layer
	}

	@Override
	public List<Account> getAll(final String sortColumn, final boolean sortAscending, final int limit,
			final int offset) {
		final List<Account> all = getAll();

		// FIXME: Do not use code below. use an appropriate DAO method instead:
		// return dao.getAll(sortColumn,sortAscending,limit,offset)

		Collections.sort(all, new Comparator<Account>() {
			@Override
			public int compare(Account o1, Account o2) {
				if (sortAscending) {
					final Account temp = o1;
					o1 = o2;
					o2 = temp;
				}

				if ("id".equals(sortColumn)) {
					return o1.getId().compareTo(o2.getId());
				} else if ("email".equals(sortColumn)) {
					return o1.getEmail().compareTo(o2.getEmail());
				} else if ("password".equals(sortColumn)) {
					return o1.getPassword().compareTo(o2.getPassword());
				} else if ("created".equals(sortColumn)) {
					return o1.getCreated().compareTo(o2.getCreated());
				} else if ("modified".equals(sortColumn)) {
					return o1.getModified().compareTo(o2.getModified());
				}
				throw new IllegalArgumentException("unsupported sort column:" + sortColumn);
			}
		});

		return all.subList(Math.min(offset, all.size()), Math.min(offset + limit, all.size()));
	}
}