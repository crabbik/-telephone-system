package com.itacademy.jd2.lg.mobile_system.dao;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Account;

public interface IAccountDao {
	Account get(Integer id);

	void remove(Integer id);

	void update(Account account);

	int insert(Account account);

	List<Account> getAll();

	List<Account> getAll(int limit, int offset);

}
