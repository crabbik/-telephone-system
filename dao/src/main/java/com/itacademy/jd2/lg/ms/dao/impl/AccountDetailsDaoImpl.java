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

import com.itacademy.jd2.lg.ms.dao.IAccountDetailsDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.dao.filter.AccountDetailsFilter;

@Repository
public class AccountDetailsDaoImpl extends AbstractHibernateDaoImpl<AccountDetails, Integer>
		implements IAccountDetailsDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDetailsDaoImpl.class);

	protected AccountDetailsDaoImpl() {
		super(AccountDetails.class);
	}

	@Override
	public Long count(AccountDetailsFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<AccountDetails> from = cq.from(AccountDetails.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<AccountDetails> find(AccountDetailsFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AccountDetails> cq = cb.createQuery(AccountDetails.class);
		Root<AccountDetails> from = cq.from(AccountDetails.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<AccountDetails> q = em.createQuery(cq);
		// setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public List<AccountDetails> getChildren(Integer parentId) {
		// TODO Auto-generated method stub
		return null;
	}

}