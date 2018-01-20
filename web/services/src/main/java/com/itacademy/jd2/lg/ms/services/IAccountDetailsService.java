package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;

public interface IAccountDetailsService {
	AccountDetails get(Integer id);

	void remove(Integer id);

	AccountDetails save(AccountDetails accountDetails);

	List<AccountDetails> getAll();

	List<AccountDetails> getAll(int limit, int offset);

}
