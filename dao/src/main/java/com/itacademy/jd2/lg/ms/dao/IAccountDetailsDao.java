package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.dao.filter.AccountDetailsFilter;

public interface IAccountDetailsDao extends IHibernateDao<AccountDetails, Integer> {
	Long count(AccountDetailsFilter filter);

	List<AccountDetails> find(AccountDetailsFilter filter);

	List<AccountDetails> getChildren(Integer parentId);

}
