package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account2Role;
import com.itacademy.jd2.lg.ms.dao.filter.Account2RoleFilter;

public interface IAccount2RoleDao extends IHibernateDao<Account2Role, Integer> {
	Long count(Account2RoleFilter filter);

	List<Account2Role> find(Account2RoleFilter filter);

}
