package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IAccount2RoleDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Account2Role;
import com.itacademy.jd2.lg.ms.dao.filter.Account2RoleFilter;
import com.itacademy.jd2.lg.ms.services.IAccount2RoleService;

@Service
public class Account2RoleServiceImpl implements IAccount2RoleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(Account2RoleServiceImpl.class);
	@Autowired
	private IAccount2RoleDao dao;

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public Account2Role save(Account2Role account2Role) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		account2Role.setModified(modifiedDate);
		if (account2Role.getId() == null) {
			account2Role.setCreated(modifiedDate);
			dao.insert(account2Role);
		} else {
			dao.update(account2Role);
		}
		return account2Role;
	}

	@Override
	public Account2Role get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<Account2Role> getAll() {
		return dao.getAll();
	}

	@Override
	public Long getCount(Account2RoleFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<Account2Role> getAll(Account2RoleFilter filter) {
		return dao.find(filter);
	}
}