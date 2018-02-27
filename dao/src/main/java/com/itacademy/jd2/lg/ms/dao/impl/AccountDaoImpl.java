package com.itacademy.jd2.lg.ms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.lg.ms.dao.IAccountDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Account_;
import com.itacademy.jd2.lg.ms.dao.filter.AccountFilter;

@Repository
public class AccountDaoImpl extends AbstractHibernateDaoImpl<Account, Integer> implements IAccountDao {

	protected AccountDaoImpl() {
		super(Account.class);
	}

	@Override
	public Long count(AccountFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Account> from = cq.from(Account.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Account> find(AccountFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Account> cq = cb.createQuery(Account.class);
		Root<Account> from = cq.from(Account.class);
		cq.select(from);
		from.fetch(Account_.details, JoinType.LEFT);
		from.fetch(Account_.roles, JoinType.LEFT);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Account> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
