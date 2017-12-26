package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Account2Role;
import com.itacademy.jd2.lg.mobile_system.services.IAccount2RoleService;

public class Account2RoleServiceImpl implements IAccount2RoleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	public static final IAccount2RoleService ACCOUNT_SERVICE = new Account2RoleServiceImpl();

	private Account2RoleServiceImpl() {

	}

	//Fprivate IAccount2RoleService dao = Account2RoleServiceImpl.ACCOUNT_DAO;

	@Override
	public Account2Role get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Account2Role save(Account2Role account2Role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account2Role> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account2Role> getAll(int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

}
