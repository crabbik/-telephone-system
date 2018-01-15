package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;

public interface IAccountService {
	Account get(Integer id);

	void remove(Integer id);

	Account save(Account account);

	List<Account> getAll();

	List<Account> getAll(int limit, int offset);

}
