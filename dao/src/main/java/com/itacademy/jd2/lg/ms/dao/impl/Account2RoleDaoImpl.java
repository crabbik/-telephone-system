package com.itacademy.jd2.lg.ms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.lg.ms.dao.IAccount2RoleDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Account2Role;
import com.itacademy.jd2.lg.ms.dao.filter.Account2RoleFilter;

@Repository
public class Account2RoleDaoImpl extends AbstractHibernateDaoImpl<Account2Role, Integer> implements IAccount2RoleDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Account2RoleDaoImpl.class);

	protected Account2RoleDaoImpl() {
		super(Account2Role.class);
	}

	@Override
	public Long count(Account2RoleFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Account2Role> from = cq.from(Account2Role.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Account2Role> find(Account2RoleFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Account2Role> cq = cb.createQuery(Account2Role.class);
		Root<Account2Role> from = cq.from(Account2Role.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Account2Role> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
