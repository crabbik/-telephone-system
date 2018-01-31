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

import com.itacademy.jd2.lg.ms.dao.IOperatorDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.ms.dao.filter.OperatorFilter;

@Repository
public class OperatorDaoImpl extends AbstractHibernateDaoImpl<Operator, Integer> implements IOperatorDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(OperatorDaoImpl.class);

	protected OperatorDaoImpl() {
		super(Operator.class);
	}

	@Override
	public Long count(OperatorFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Operator> from = cq.from(Operator.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Operator> find(OperatorFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Operator> cq = cb.createQuery(Operator.class);
		Root<Operator> from = cq.from(Operator.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Operator> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}