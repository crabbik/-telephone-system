package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.IAccount2RoleDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Account2Role;
import com.itacademy.jd2.lg.mobile_system.dao.impl.Account2RoleDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IAccount2RoleService;

public class Account2RoleServiceImpl implements IAccount2RoleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(Account2RoleServiceImpl.class);
	public static final IAccount2RoleService ACCOUNT2ROLE_SERVICE = new Account2RoleServiceImpl();

	private Account2RoleServiceImpl() {

	}

	private IAccount2RoleDao dao = Account2RoleDaoImpl.ACCOUNT2ROLE_DAO;

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