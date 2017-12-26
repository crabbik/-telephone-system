package com.itacademy.jd2.lg.mobile_system.dao;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.AccountDetails;

public interface IAccountDetailsDao {
	AccountDetails get(Integer id);

	void remove(Integer id);

	void update(AccountDetails accountDetails);

	void insert(AccountDetails accountDetails);

	List<AccountDetails> getAll();

	List<AccountDetails> getAll(int limit, int offset);

}
