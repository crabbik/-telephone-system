package com.itacademy.jd2.lg.mobile_system.services;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Account2Role;

public interface IAccount2RoleService {
	Account2Role get(Integer id);

	void remove(Integer id);

	Account2Role save(Account2Role account2Role);

	List<Account2Role> getAll();

	List<Account2Role> getAll(int limit, int offset);

}
