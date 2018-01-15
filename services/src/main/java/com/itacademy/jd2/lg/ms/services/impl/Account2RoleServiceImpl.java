package com.itacademy.jd2.lg.ms.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IAccount2RoleDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Account2Role;
import com.itacademy.jd2.lg.ms.dao.impl.Account2RoleDaoImpl;
import com.itacademy.jd2.lg.ms.services.IAccount2RoleService;
@Service
public class Account2RoleServiceImpl implements IAccount2RoleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(Account2RoleServiceImpl.class);
	@Autowired
	private IAccount2RoleDao dao ;

	@Override
	public Account2Role get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public void update(Account2Role account2Role) {
		dao.update(account2Role);

	}

	@Override
	public void insert(Account2Role account2Role) {
		dao.insert(account2Role);
	}

	@Override
	public List<Account2Role> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Account2Role> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}