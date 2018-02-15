package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.dao.filter.AccountFilter;

public interface IAccountService {
	Account get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	Account save(Account account);

	@Transactional
	Account saveWithDetails(final Account account);

	Long getCount(AccountFilter filter);

	List<Account> getAll(AccountFilter filter);

	AccountDetails getDetails(Integer id);

}
