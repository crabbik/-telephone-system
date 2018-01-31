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

import com.itacademy.jd2.lg.ms.dao.IPhoneNumberDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.dao.filter.PhoneNumberFilter;

@Repository
public class PhoneNumberDaoImpl extends AbstractHibernateDaoImpl<PhoneNumber, Integer> implements IPhoneNumberDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumberDaoImpl.class);

	protected PhoneNumberDaoImpl() {
		super(PhoneNumber.class);
	}

	@Override
	public Long count(PhoneNumberFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PhoneNumber> from = cq.from(PhoneNumber.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<PhoneNumber> find(PhoneNumberFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PhoneNumber> cq = cb.createQuery(PhoneNumber.class);
		Root<PhoneNumber> from = cq.from(PhoneNumber.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<PhoneNumber> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
