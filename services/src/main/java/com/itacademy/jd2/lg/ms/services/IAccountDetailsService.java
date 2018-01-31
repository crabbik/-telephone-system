package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.dao.filter.AccountDetailsFilter;

public interface IAccountDetailsService {
	AccountDetails get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	AccountDetails save(AccountDetails accountDetails);

	List<AccountDetails> getAll();

	Long getCount(AccountDetailsFilter filter);

	List<AccountDetails> getAll(AccountDetailsFilter filter);

}
