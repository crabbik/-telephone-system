package com.itacademy.jd2.lg.ms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.lg.ms.dao.ITariffDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory_;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff_;
import com.itacademy.jd2.lg.ms.dao.filter.TariffFilter;

@Repository
public class TariffDaoImpl extends AbstractHibernateDaoImpl<Tariff, Integer> implements ITariffDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(TariffDaoImpl.class);

	protected TariffDaoImpl() {
		super(Tariff.class);
	}

	@Override
	public Long count(TariffFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Tariff> from = cq.from(Tariff.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Tariff> find(TariffFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tariff> cq = cb.createQuery(Tariff.class);
		Root<Tariff> from = cq.from(Tariff.class);
		cq.select(from);
		from.fetch(Tariff_.number, JoinType.LEFT);
		from.fetch(Tariff_.operator, JoinType.LEFT);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Tariff> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
