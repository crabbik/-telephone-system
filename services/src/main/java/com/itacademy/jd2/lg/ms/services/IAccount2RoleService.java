package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account2Role;
import com.itacademy.jd2.lg.ms.dao.filter.Account2RoleFilter;

public interface IAccount2RoleService {
	Account2Role get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	Account2Role save(Account2Role account2Role);

	List<Account2Role> getAll();

	Long getCount(Account2RoleFilter filter);

	List<Account2Role> getAll(Account2RoleFilter filter);

}
