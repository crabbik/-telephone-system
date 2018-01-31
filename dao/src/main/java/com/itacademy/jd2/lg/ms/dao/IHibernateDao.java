package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

public interface IHibernateDao<T, ID> {
	@Deprecated
	List<T> getAll();

	T get(final ID id);

	T insert(final T entity);

	T update(T entity);

	void remove(ID id);
}