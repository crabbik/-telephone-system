package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.dao.filter.AccountFilter;

public interface IAccountDao extends IHibernateDao<Account, Integer> {
	Long count(AccountFilter filter);

	List<Account> find(AccountFilter filter);

}
